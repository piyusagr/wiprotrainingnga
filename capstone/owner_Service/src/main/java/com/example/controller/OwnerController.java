package com.example.controller;

import lombok.RequiredArgsConstructor;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.entity.App;
import com.example.entity.Owner;
import com.example.service.AppService;
import com.example.service.OwnerService;
import com.example.security.JwtUtils;
import com.example.client.UserServiceClient;
import com.example.dto.ReviewDTO;

@Controller
@RequestMapping("/owner")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;
    private final AppService appService;
    private final JwtUtils jwtUtils;
    private final UserServiceClient userServiceClient;
    
    // ===== HOME (JWT Protected) =====
    @GetMapping("/home")
    public String home(HttpServletRequest request, Model model) {
        String email = getEmailFromJwt(request);
        if (email == null) return "redirect:/owner/login";
        
        Owner owner = ownerService.findByEmails(email);
        List<App> apps = appService.getAppsByOwnerId(owner.getId());
        
        model.addAttribute("apps", apps);
        model.addAttribute("totalApps", apps.size());
        model.addAttribute("owner", owner);
        model.addAttribute("username", owner.getDisplayName());
        return "owner-home";
    }

    // ===== REGISTER =====
    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("owner", new Owner());
        return "owner-register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Owner owner, Model model) {
        try {
            ownerService.register(owner);
            model.addAttribute("message", "✅ Registered! Please login.");
            return "owner-login";
        } catch (Exception e) {
            model.addAttribute("error", "❌ " + e.getMessage());
            return "owner-register";
        }
    }

    // ===== LOGIN (Thymeleaf + JWT Cookie) =====
    @GetMapping("/login")
    public String showLogin() {
        return "owner-login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password,
                       HttpServletResponse response, Model model) {
        try {
            Owner owner = ownerService.login(email, password);
            String jwt = jwtUtils.generateToken(owner.getContactEmail());
            
            // ✅ JWT Cookie (httpOnly)
            Cookie jwtCookie = new Cookie("owner_jwt", jwt);
            jwtCookie.setHttpOnly(true);
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(24 * 60 * 60); // 24h
            response.addCookie(jwtCookie);
            
            return "redirect:/owner/home";
        } catch (Exception e) {
            model.addAttribute("error", "❌ Invalid credentials!");
            return "owner-login";
        }
    }

    // ===== LOGOUT =====
    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie jwtCookie = new Cookie("owner_jwt", "");
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(0);
        response.addCookie(jwtCookie);
        return "redirect:/owner/login";
    }

    // ===== PROFILE =====
    @GetMapping("/profile/{id}")
    public String profile(HttpServletRequest request, @PathVariable Long id, Model model) {
        String email = getEmailFromJwt(request);
        if (email == null) {
            return "redirect:/owner/login";
        }
        Owner owner = ownerService.getOwnerById(id);
        if (!owner.getContactEmail().equals(email)) {
            return "redirect:/owner/home";
        }
        model.addAttribute("owner", owner);
        return "owner-profile";
    }

    // ===== UPDATE =====
    @GetMapping("/update/{id}")
    public String showUpdate(HttpServletRequest request, @PathVariable Long id, Model model) {
        String email = getEmailFromJwt(request);
        if (email == null) {
            return "redirect:/owner/login";
        }
        Owner owner = ownerService.getOwnerById(id);
        if (!owner.getContactEmail().equals(email)) {
            return "redirect:/owner/home";
        }
        model.addAttribute("owner", owner);
        return "owner-update";
    }
    
    
    @PostMapping("/update/{id}")
    public String update(HttpServletRequest request, @PathVariable Long id, 
                        @ModelAttribute Owner updatedOwner) {
        String email = getEmailFromJwt(request);
        if (email == null) {
            return "redirect:/owner/login";
        }
        Owner owner = ownerService.getOwnerById(id);
        if (!owner.getContactEmail().equals(email)) {
            return "redirect:/owner/home";
        }
        ownerService.updateOwner(id, updatedOwner);
        return "redirect:/owner/home";
    }

    // ===== PASSWORD =====
    @GetMapping("/update-password/{id}")
    public String showPasswordUpdate(HttpServletRequest request, @PathVariable Long id, Model model) {
        String email = getEmailFromJwt(request);
        if (email == null) {
            return "redirect:/owner/login";
        }
        Owner owner = ownerService.getOwnerById(id);
        if (!owner.getContactEmail().equals(email)) {
            return "redirect:/owner/home";
        }
        model.addAttribute("ownerId", id);
        return "owner-update-password";
    }

    @PostMapping("/update-password/{id}")
    public String updatePassword(HttpServletRequest request, @PathVariable Long id,
                                @RequestParam String newPassword) {
        String email = getEmailFromJwt(request);
        if (email == null) {
            return "redirect:/owner/login";
        }
        Owner owner = ownerService.getOwnerById(id);
        if (!owner.getContactEmail().equals(email)) {
            return "redirect:/owner/home";
        }
        ownerService.updatePassword(id, newPassword);
        return "redirect:/owner/login";
    }

    // ===== DELETE =====
    @PostMapping("/delete/{id}")
    public String delete(HttpServletRequest request, @PathVariable Long id, 
                        HttpServletResponse response) {
        String email = getEmailFromJwt(request);
        if (email == null) {
            return "redirect:/owner/login";
        }
        Owner owner = ownerService.getOwnerById(id);
        if (!owner.getContactEmail().equals(email)) {
            return "redirect:/owner/home";
        }
        ownerService.deleteOwner(id);
        clearJwtCookie(response);
        return "redirect:/owner/login";
    }
    // app Reviews
    @GetMapping("/apps/{id}/reviews")
    public String getReviews(HttpServletRequest request, @PathVariable Long id, Model model)  {
        String email = getEmailFromJwt(request);
        if (email == null) {
            return "redirect:/owner/login";
        }
        try {

	    	 App apps = appService.getAppById(id);
	    	 List<ReviewDTO> reviews = userServiceClient.getReviewsByApp(id);
	         model.addAttribute("app", apps);
            model.addAttribute("review", reviews);
            model.addAttribute("reviewCount", reviews.size());
            model.addAttribute("owner", ownerService.findByEmails(email));

            return "app/app-review";
        } catch (Exception e) {
            return "redirect:/owner/home";
        }
    }
    

    // ===== JWT UTILITY =====
    private String getEmailFromJwt(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("owner_jwt".equals(cookie.getName())) {
                    try {
                        String jwt = cookie.getValue();
                        String email = jwtUtils.getUsernameFromJwt(jwt);
                        if (jwtUtils.validateJwtToken(jwt)) {
                            return email;
                        }
                    } catch (Exception ignored) {}
                }
            }
        }
        return null;
    }

    private void clearJwtCookie(HttpServletResponse response) {
        Cookie jwtCookie = new Cookie("owner_jwt", "");
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(0);
        response.addCookie(jwtCookie);
    }

    
}
