package com.example.repository;

import com.example.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AppRepository extends JpaRepository<App, Long> {

	//  find app by id
	App findAppById(Long id);
	
    //  find all apps by owner
    List<App> findByOwnerId(Long ownerId);

    //  find all apps by category
    List<App> findByCategoryId(Long categoryId);

    //    find applications with visibility true
    @Query("SELECT a FROM App a WHERE a.visibility = true")
    List<App> findByVisibilityTrue();
    
    //    search apps by name containing keyword and visibility true
	@Query("SELECT a FROM App a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :keyword, '%')) AND a.visibility = true")
	List<App> findByNameContainingIgnoreCase(String keyword);
	

	//	search apps by name and category and visibility
	@Query("SELECT a FROM App a WHERE a.name LIKE %:keyword% AND a.category.id = :categoryid AND a.visibility = true")
	List<App> findByNameAndCategory(String keyword, Long categoryid);
	
	//	search apps by category and visibility
	@Query("SELECT a FROM App a WHERE a.category.id = :categoryid AND a.visibility = true")
	List<App> findByCategoryAndVisibility(Long categoryid);
	
	//	find particular app by id and visibility
	@Query("SELECT a FROM App a WHERE a.id = :id AND a.visibility = true")
	App findByIdAndVisibilityTrue(Long id);
	
	//	increase the download count of particular app
	@Modifying
	@Transactional
	@Query("UPDATE App a SET a.downloadCount = a.downloadCount + 1 WHERE a.id = :id")
	Long incrementDownloadCount(Long id);
	
	//  find apps by owner
	List<App> findAllByOwnerId(Long ownerId);
}
