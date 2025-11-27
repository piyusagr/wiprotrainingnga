package com.example.bean;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Version;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="task_details")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    private String assignedTo;

    private String priority;

    private String status;

    private LocalDate dueDate;

    @Version
    private Integer version;

}

	
