package com.supermy.rest.web;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;


/**
 * extjs 的sort json 转换为 mongodb 的 sort查询参数
 * @author jamesmo
 *
 */
public class MySort {
	private final Logger logger = LoggerFactory
			.getLogger(MySort.class);
	
	String property;
	String direction;
	
	public MySort(String sortJson) throws JsonParseException, JsonMappingException, IOException {
		logger.debug("sort value:{}",sortJson);

		ObjectMapper  mapper = new ObjectMapper();
		List<Map<String,Object>> userData = mapper.readValue(sortJson, List.class);
		property = userData.get(0).get("property").toString();
		direction = userData.get(0).get("direction").toString();
	}
	
	public MySort(String property, String direction) {
		//super(orders);
		this.property = property;
		this.direction = direction;
	}
	
	public Sort getSort() {
		Sort.Direction val =Sort.Direction.valueOf(direction.toUpperCase(Locale.ENGLISH));		
		Sort sort=new Sort(new Sort.Order(val,property));
		return sort;
	}
	

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		MySort mySort = new MySort("content", "ASC");
		System.out.println(mySort.getSort());
		
		MySort mySort1 = new MySort("[{\"property\":\"content\",\"direction\":\"ASC\"}]");
		System.out.println(mySort1.getSort());
		

	}


}
