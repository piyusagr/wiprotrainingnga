package com.example.controller;

import com.example.entity.App;
import com.example.entity.Category;
import com.example.entity.Owner;
import com.example.service.AppService;
import com.example.service.CategoryService;
import com.example.service.OwnerService;
import com.example.security.JwtUtils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.client.UserServiceClient;


@Controller
@RequestMapping("/owner")
@RequiredArgsConstructor
public class AppController {

    private final AppService appService;
    private final CategoryService categoryService;
    private final OwnerService ownerService;
    private final JwtUtils jwtUtils;
    private final UserServiceClient userServiceClient;
    // Show form to add new app
    @GetMapping("/apps/new")
    public String showCreateAppForm(Model model) {
        model.addAttribute("app", new App());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "app/create-app";
    }

//    Create new app
    @PostMapping("/apps/save")
    public String saveApp(@ModelAttribute App app, HttpServletRequest request) {

        // 1️ Category
        Long categoryId = app.getCategory().getId();
        Category category = categoryService.getCategoryById(categoryId)
                                           .orElseThrow(() -> new RuntimeException("Category not found"));
        app.setCategory(category);

        // 2️ Get Owner from JWT
        Owner owner = getOwnerFromJwt(request);
        if (owner == null) {
            return "redirect:/owner/login"; // safe fallback
        }
        app.setOwner(owner);

        // 3️ Save App
        appService.createApp(app);

        return "redirect:/owner/home";
    }


    // Show edit form
    @GetMapping("/apps/edit/{id}")
    public String showEditForm(@PathVariable Long id, HttpServletRequest request, Model model) {
        Owner owner = getOwnerFromJwt(request);
        if (owner == null) {
            return "redirect:/owner/login";
        }
        App app = appService.getAppById(id);
        if (!app.getOwner().getId().equals(owner.getId())) {
            return "redirect:/owner/home"; // not authorized
        }
        model.addAttribute("app", app);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "app/edit-app";
    }

    // update app
    @PostMapping("/apps/update/{id}")
    public String updateApp(@PathVariable Long id,
                            @ModelAttribute App app,
                            HttpServletRequest request) {

        Owner owner = getOwnerFromJwt(request);
        if (owner == null) {
            return "redirect:/owner/login";
        }
        app.setOwner(owner);

        appService.updateApp(id, app);
        return "redirect:/owner/home";
    }

    // Delete app
    @GetMapping("/apps/delete/{id}")
    public String deleteApp(@PathVariable Long id, HttpServletRequest request) {
        Owner owner = getOwnerFromJwt(request);
        if (owner == null) {
            return "redirect:/owner/login";
        }
        App app = appService.getAppById(id);
        if (!app.getOwner().getId().equals(owner.getId())) {
            return "redirect:/owner/home"; // not authorized
        }
        appService.deleteApp(id);
        return "redirect:/owner/home";
    }
    
    // Get all apps with visibility true (public endpoint)
    @GetMapping(value = "/apps/public", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<App>> getVisibleApps() {
        List<App> visibleApps = appService.getAppByTrueVisible();
        return ResponseEntity.ok(visibleApps);
    }
    
    // Search apps by name and/or category with visibility true
	@GetMapping(value = "/apps/search", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<App>> searchApps(
			@RequestParam(required = false) String keyword,
			@RequestParam(required = false) Long categoryid) {
		
		List<App> results;
		
		// Use 'categoryid' (lowercase) to match the query parameter in the URL
		if (keyword != null && categoryid != null) {
			results = appService.searchAppsandCategory(keyword, categoryid);
		} else if (keyword != null) {
			results = appService.searchApps(keyword);
		} else if (categoryid != null) {
			results = appService.searchByCategory(categoryid);
		} else {
			results = appService.getAppByTrueVisible(); // return all visible apps if no filters
		}
		
		return ResponseEntity.ok(results);
	}
	
    //	 Get app by ID with visibility true 
    @GetMapping(value = "/apps/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<App> getAppByIdAndVisibilityTrue(@PathVariable Long id) {
        App app = appService.getByIdAndVisibilityTrue(id);
        return ResponseEntity.ok(app);
    }
	
    // Increase download count of an app
	@PostMapping("/apps/{id}/increment-download")
	@ResponseBody
	public ResponseEntity<Long> incrementDownloadCount(@PathVariable Long id) {
		return ResponseEntity.ok(appService.incrementDownloadCounts(id));
	}
	
	//get all email downloaded particular app
	@GetMapping(value="/apps/{id}/downloaders")
	public ResponseEntity<List<String>> getDownloadersEmails(@PathVariable Long id) {
	    List<String> emails = userServiceClient.getDownloadersEmails(id);
	    return ResponseEntity.ok(emails);
	}

	// JWT utility
	private Owner getOwnerFromJwt(HttpServletRequest request) {
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if ("owner_jwt".equals(cookie.getName())) {
	                try {
	                    String jwt = cookie.getValue();
	                    String email = jwtUtils.getUsernameFromJwt(jwt);
	                    if (jwtUtils.validateJwtToken(jwt)) {
	                        return ownerService.findByEmails(email);
	                    }
	                } catch (Exception ignored) {}
	            }
	        }
	    }
	    return null;
	}


}
