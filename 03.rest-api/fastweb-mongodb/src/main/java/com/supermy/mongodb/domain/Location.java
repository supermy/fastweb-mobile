package com.supermy.mongodb.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
 
@Document(collection = "location")
public class Location implements Serializable{
	
 
	public Location() {
	}
	
	public Location(String id, double d, double e) {
		this.id=id;
		this.position= new double[2];
		this.position[0]=d;
		this.position[1]=e;
	}

	@Id
	private String id;
 
	private double[] position;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double[] getPosition() {
		return position;
	}

	public void setPosition(double[] position) {
		this.position = position;
	}
 
	/** getter setter hashcode equals toString ...  */
	
 
}