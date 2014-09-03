package com.supermy.guide.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "wizards")
@TypeAlias("wizards")
public class Wizard implements Serializable{

	@Id
	private String id;
	
	private String title;
	private String content;
	
}