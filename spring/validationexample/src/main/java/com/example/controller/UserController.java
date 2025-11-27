package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	public UserService userService;
	
	@PostMapping("/add")
	public ResponseEntity<String> saveUser(@Valid @RequestBody User u)
	{
		User uobj = userService.saveUSer(u);
		if(uobj != null)
			return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
		else
			return new ResponseEntity<>("Record not added", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/viewall")
	public ResponseEntity<List<User>> getAllUsers()
	{
		List<User> userList = userService.getAllUsers();
		return new ResponseEntity<>(userList , HttpStatus.OK);
	}
	
	// UPDATED: No more "Optional" or "if-else". 
	// If the user isn't found, the Global Exception Handler takes over automatically.
	@GetMapping("/view/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id)
	{
		User user = userService.getUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	// UPDATED: Removed "throws Exception"
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) 
	{
		userService.deleteUserById(id);
		return new ResponseEntity<>("The id is deleted successfully", HttpStatus.OK);
	}
	
	// Full update
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateUser(@PathVariable Long id ,@RequestBody User user)
	{
		userService.updateUserById(id, user);
		return new ResponseEntity<>("The data is updated successfully", HttpStatus.OK);
	}
	
	// Partial update
	@PatchMapping("/update/{id}")
	public ResponseEntity<String> updateUser1(@PathVariable Long id ,@RequestBody User user)
	{
		userService.partialUpdateUserById(id,user);
		return new ResponseEntity<>("The partial data is updated successfully", HttpStatus.OK);
	}

}