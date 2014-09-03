package com.supermy.mongodb.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "oauth2")
@TypeAlias("oauth2")
public class OAuth2 {
	@Field("m")
	private Map<String,Object> map = new HashMap<String,Object>();

	public OAuth2(String key,Object value) {
		map.put(key, value);
	}

	public Map<String,Object> getMap() {
		return map;
	}

	public void setMap(Map<String,Object> map) {
		this.map = map;
	}
}