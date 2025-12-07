package com.playstore.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.playstore.dto.AppDTO;
import com.playstore.dto.CategoryDTO;

@FeignClient(name = "owner-service", url = "http://localhost:8082")
public interface OwnerServiceClient {
    
    //  Get ALL visible apps from ALL owners
    @GetMapping("/owner/apps/public")
    List<AppDTO> getAppByTrueVisible();
    
    //  Search apps by name or category or both or none with visibility true
    @GetMapping("/owner/apps/search")
    List<AppDTO> searchApps(@RequestParam(value = "keyword", required = false) String keyword,
                           @RequestParam(value = "categoryid", required = false) Long categoryId);
    
    //    list all category
    @GetMapping("/owner/categories/list")
    List<CategoryDTO> getAllCategories();
    
    //    get particular app by id and visibility true
    @GetMapping("/owner/apps/{id}")
    AppDTO getByIdAndVisibilityTrue(@PathVariable("id") Long id);
    
    //    update the download count of particular app
	@PostMapping("/owner/apps/{id}/increment-download")
	Long incrementDownloadCount(@PathVariable("id") Long id);

}
