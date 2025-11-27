package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bean.Task;


//Jpa Repository provides all the basic crud operation like create, update, delete and get and also provide the pagination 
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{

}
