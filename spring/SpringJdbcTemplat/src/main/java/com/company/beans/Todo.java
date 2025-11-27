package com.company.beans;

//model class
public class Todo 
{
	
	//Properties 
	private int id;
	private String task;
	private String description;
	
	
	
	public Todo(int id, String task, String description) {
		super();
		this.id = id;
		this.task = task;
		this.description = description;
	}
	public Todo() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return task;
	}
	public void setTitle(String title) {
		this.task = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Todo [id=" + id + ", title=" + task + ", description=" + description + "]";
	}
	
	

}