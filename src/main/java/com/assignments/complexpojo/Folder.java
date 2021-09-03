package com.assignments.complexpojo;

import java.util.List;

public class Folder {
	
	private String name;
	List<RequestRoot> item;
	
	public Folder() {
		
	}
	
	public Folder(String name, List<RequestRoot> item) {
		this.name = name;
		this.item = item;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<RequestRoot> getRequestRoot() {
		return item;
	}
	public void setRequestRoot(List<RequestRoot> item) {
		this.item = item;
	}
	
}