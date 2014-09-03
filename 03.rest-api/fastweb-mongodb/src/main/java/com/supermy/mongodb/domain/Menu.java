package com.supermy.mongodb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @author jamesmo
 *
 */
@Document(collection="menu")
//@JsonAutoDetect
public class Menu implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private String subtitle;
	
	@DBRef
	private List<MenuItem> items=new ArrayList<MenuItem>();
	
	
	public void add(MenuItem e){
		items.add(e);
	}
	
//	public static Menu fromJsonToOwner(String json) {
//		   return new JSONDeserializer<Menu>().use(null, Menu.class).deserialize(json);
//		}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public List<MenuItem> getItems() {
		return items;
	}

	public void setItems(List<MenuItem> items) {
		this.items = items;
	}
	
	
	
	
	}
