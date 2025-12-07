package com.playstore.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.playstore.dto.LoginRequest;
import com.playstore.dto.UserDTO;
import com.playstore.entity.User;
import com.playstore.entity.UserAppDownload;
import com.playstore.repository.UserAppDownloadRepository;
import com.playstore.security.JwtUtil;
import com.playstore.client.OwnerServiceClient;
import com.playstore.client.NotificationServiceClient;
import com.playstore.service.ReviewService;
import com.playstore.service.UserService;
import java.util.List;
import com.playstore.dto.*;


@Controller
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final OwnerServiceClient ownerServiceClient;
    private final UserAppDownloadRepository userAppDownloadRepository;
    private final ReviewService reviewService;
    private final NotificationServiceClient notificationServiceClient;
    
    public UserController(UserService userService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder , 
                         OwnerServiceClient ownerServiceClient, UserAppDownloadRepository userAppDownloadRepository ,
                         ReviewService reviewService, NotificationServiceClient notificationServiceClient) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.ownerServiceClient = ownerServiceClient;
        this.userAppDownloadRepository = userAppDownloadRepository;
        this.reviewService = reviewService;
        this.notificationServiceClient = notificationServiceClient;
    }

    // REGISTER
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserDTO dto,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        User user = userService.register(dto);
        if (user == null) {
            model.addAttribute("message", "Username already exists");
            return "register";
        }
        model.addAttribute("message", "Registration successful! Please login.");
        return "redirect:/login";
    }

    // LOGIN 
    @GetMapping("/login")
    public String showLoginPage(Model model) {
    	model.addAttribute("loginRequest", new LoginRequest()); 
    	return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginRequest") LoginRequest request,
                       Model model, HttpServletResponse response) {
        
        User user = userService.login(request.getUsername(), request.getPassword())
                .orElse(null);
                
        if (user != null) {
            // Generate JWT
            String jwt = jwtUtil.generateToken(user.getUsername(), user.getRole());
            
            // Set JWT as cookie (Thymeleaf will send it automatically)
            Cookie jwtCookie = new Cookie("jwt_token", jwt);
            jwtCookie.setHttpOnly(true);
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(24 * 60 * 60); // 24 hours
            response.addCookie(jwtCookie);
            
            return "redirect:/home";
        }
        model.addAttribute("loginRequest", request);  
        model.addAttribute("message", "Invalid Username/Password");
        return "login";
    }

    // PROTECTED PAGES
    @GetMapping("/home")
    public String homePage(HttpServletRequest request, 
                          @RequestParam(required = false) String keyword, 
                          @RequestParam(required = false) Long categoryid,
                          Model model) {
        String username = getUsernameFromJwt(request);
        if (username == null) {
            return "redirect:/login";
        }
        
        List<AppDTO> apps;  
        List<CategoryDTO> categories = ownerServiceClient.getAllCategories();
        
        if (keyword != null && !keyword.trim().isEmpty() && categoryid != null) {
			apps = ownerServiceClient.searchApps(keyword.trim(), categoryid);
			model.addAttribute("searchQuery", keyword.trim());
			model.addAttribute("selectedCategory", categoryid);
		} else if (categoryid != null) {
			apps = ownerServiceClient.searchApps(null, categoryid);
			model.addAttribute("selectedCategory", categoryid);
		}
        else if (keyword != null && !keyword.trim().isEmpty()) {
            apps = ownerServiceClient.searchApps(keyword.trim(), null);
            model.addAttribute("searchQuery", keyword.trim());
        } else {
            apps = ownerServiceClient.getAppByTrueVisible();
        }
        
        model.addAttribute("username", username);
        model.addAttribute("apps", apps != null ? apps : java.util.Collections.emptyList());
        model.addAttribute("categories", categories);
        return "home";
    }


//  profile page
    @GetMapping("/profile")
    public String profilePage(HttpServletRequest request, Model model) {
        String username = getUsernameFromJwt(request);
        if (username == null) {
            return "redirect:/login";
        }

        User user = userService.findByUsernames(username).orElseThrow();
        model.addAttribute("user", user);
        return "profile";
    }
    
    //profile update
    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute("user") User updatedUser,
                               HttpServletRequest request, Model model) {
        String username = getUsernameFromJwt(request);
        if (username == null) {
            return "redirect:/login";
        }

        User existing = userService.findByUsernames(username).orElseThrow();
        existing.setEmail(updatedUser.getEmail());
        
        if (!updatedUser.getPassword().isEmpty()) {
            existing.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        
        userService.updateUser(existing);
        model.addAttribute("user", existing);
        model.addAttribute("message", "Profile updated successfully");
        return "profile";
    }
    
    //delete profile
    @PostMapping("/profile/delete")
    public String deleteAccount(HttpServletRequest request, HttpServletResponse response) {
        String username = getUsernameFromJwt(request);
        if (username == null) {
            return "redirect:/login";
        }

        userService.deleteByUsername(username);
        
        // Clear JWT cookie
        Cookie jwtCookie = new Cookie("jwt_token", "");
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(0);
        response.addCookie(jwtCookie);
        
        return "redirect:/register";
    }
    
    //logout
    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        // Clear JWT cookie
        Cookie jwtCookie = new Cookie("jwt_token", "");
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(0);
        response.addCookie(jwtCookie);
        return "redirect:/login";
    }

//    App details page
    @GetMapping("/app/{id}")
    public String getAppDetails(HttpServletRequest request, 
                               @PathVariable Long id, 
                               Model model) {
        String username = getUsernameFromJwt(request);
        if (username == null) {
            return "redirect:/login";
        }
        
        try {
            // Get app details
            AppDTO app = ownerServiceClient.getByIdAndVisibilityTrue(id);
            if (app == null) {
                return "redirect:/home";
            }
            
            // Get current user
            User user = userService.findByUsernames(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
            Long userId = user.getId();
            
            boolean isInstalled = userAppDownloadRepository.findByUserIdAndAppId(userId, id).isPresent();
            List<ReviewDTO> reviews = reviewService.getReviewsByAppId(id);  
            boolean hasReviewed = reviewService.hasUserReviewedApp(userId, id);
            model.addAttribute("username", username);
            model.addAttribute("app", app);
            model.addAttribute("isInstalled", isInstalled);
            model.addAttribute("reviews", reviews);           
            model.addAttribute("hasReviewed", hasReviewed);  
            model.addAttribute("reviewCount", reviews.size());
            
            return "appDetail";
        } catch (Exception e) {
            System.err.println("Error loading app details id=" + id + ": " + e.getMessage());
            return "redirect:/home";
        }
    }


    //    Install app and increment download count
    @PostMapping("/app/{id}/install")
    public ResponseEntity<Long> installApp(HttpServletRequest request, @PathVariable Long id) {
        String username = getUsernameFromJwt(request);
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        User user = userService.findByUsernames(username).orElseThrow();
        
        if (userAppDownloadRepository.findByUserIdAndAppId(user.getId(), id).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
        UserAppDownload download = new UserAppDownload();
        download.setUserId(user.getId());
        download.setAppId(id);
        userAppDownloadRepository.save(download);
        
        Long newCount = ownerServiceClient.incrementDownloadCount(id);
        
        // Send download notification to app owner
        try {
            AppDTO app = ownerServiceClient.getByIdAndVisibilityTrue(id);
            if (app != null) {
                AppDownloadNotificationRequest notificationReq = new AppDownloadNotificationRequest(
                    app.getDeveloperEmail(),
                    app.getDeveloper(),
                    app.getName(),
                    user.getUsername()
                );
                notificationServiceClient.sendDownloadNotification(notificationReq);
            }
        } catch (Exception e) {
            // Log notification failure but don't fail the install
            System.err.println("Failed to send download notification: " + e.getMessage());
        }
        
        return ResponseEntity.ok(newCount);  
    }


    //get only user email from appid who download
    @GetMapping(value="/app/{id}/downloaders",  produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<String>> getDownloadersEmails(@PathVariable Long id) {
		try {
			List<String> emails = userAppDownloadRepository.findDistinctByAppId(id);
			return ResponseEntity.ok(emails);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
    }

    // Extract username from JWT cookie
    private String getUsernameFromJwt(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt_token".equals(cookie.getName())) {
                    try {
                        String jwt = cookie.getValue();
                        String username = jwtUtil.extractUsername(jwt);
                        // Validate JWT token
                        if (jwtUtil.isTokenValid(jwt, username)) {
                            return username;
                        }
                    } catch (Exception e) {
                        // Invalid JWT
                    }
                    break;
                }
            }
        }
        return null;
    }

}
