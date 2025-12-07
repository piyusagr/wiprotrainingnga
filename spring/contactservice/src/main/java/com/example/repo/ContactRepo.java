package com.example.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.bean.Contact;

@Repository
public interface ContactRepo extends CrudRepository<Contact , Integer> {
	
	List<Contact> findByUserId(Integer userId);

}