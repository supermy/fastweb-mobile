package com.supermy.rest.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;


/**
 * extjs 的sort json 转换为 mongodb 的 filter 查询参数
 * @author jamesmo
 *
 */
public class MyFilter {
	private final Logger logger = LoggerFactory
			.getLogger(MyFilter.class);
	
	DBObject result;

	
	public MyFilter(String filterJson) throws JsonParseException, JsonMappingException, IOException {
		logger.debug("sort value:{}",filterJson);

		ObjectMapper  mapper = new ObjectMapper();
		List<LinkedHashMap<String,Object>> filterMap = mapper.readValue(filterJson, List.class);
		
		BasicDBObjectBuilder documentBuilder = BasicDBObjectBuilder.start();
		BasicDBObject document = new BasicDBObject();;
		//filter:[{"type":"date","comparison":"lt","value":"11/19/2013","field":"newdate"},
		//{"type":"date","comparison":"gt","value":"11/22/2013","field":"newdate"},
		//{"type":"string","value":"abc","field":"content"}]
		
		//按字段 逻辑操作符号 值 整理为树状结构
		Map<String, Map<String, Object>> results = new HashMap<String, Map<String, Object>>();
		for (Map<String, Object> map : filterMap) {
			String field = map.get("field").toString();
			if (results.get(field) == null) {
				results.put(field, map);
				//results.put("count", 1);
			} else {
				Map<String, Object> map2 =results.get(field);

				Map<String, Object> resultdetail = new HashMap<String, Object>();
				resultdetail.put(field + "1", map);
				resultdetail.put(field + "2", map2);

				results.put(field, resultdetail);
				//results.put("count", 2);
			}
		}
		
		for(Map.Entry<String, Map<String, Object>> entry : results.entrySet()) {
			String key = entry.getKey();
			Map<String, Object> value = entry.getValue();
			System.out.println(key + " --> " + value);
			
			String field =key;
			
			Object field1 = value.get(key+"1");
			Object field2 = value.get(key+"2");
			
			if(field1==null || field2 ==null){//单个查询条件
				
				Object comp = value.get("comparison");
				
				System.out.println("============"+field);
				System.out.println("============"+comp);
				System.out.println("============"+value.get("value"));
				
				if (comp==null) {//没有逻辑参数的处理
					documentBuilder.add(field, value.get("value"));	
				} else {//有逻辑参数的处理
					BasicDBObjectBuilder documentBuilderDetail = BasicDBObjectBuilder.start();
					documentBuilderDetail.add("$"+comp.toString(), value.get("value"));
					documentBuilder.add(field, documentBuilderDetail.get());
				}

				
				
			}else{//两个查询条件,都存在比较符号
				
				Map<String, Object> field1map=(Map<String, Object>) field1;
				Map<String, Object> field2map=(Map<String, Object>) field2;
				
				BasicDBObjectBuilder documentBuilderDetail = BasicDBObjectBuilder.start();
				Object comp1 = field1map.get("comparison");
				System.out.println("============"+comp1);
				System.out.println("============"+field1map.get("value"));				
				documentBuilderDetail.add("$"+comp1, field1map.get("value"));
				Object comp2 = field2map.get("comparison");
				System.out.println("============"+comp2);
				System.out.println("============"+field2map.get("value"));				
				documentBuilderDetail.add("$"+comp2, field2map.get("value"));
					
				documentBuilder.add(field, documentBuilderDetail.get());
			}
			System.out.println("+++++++++++"+documentBuilder.get());
		}
		
		this.result=documentBuilder.get();
		
	}
	
	


	public DBObject getResult() {
		return result;
	}

	public void setResult(DBObject result) {
		this.result = result;
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
		String filter="[{\"type\":\"date\",\"comparison\":\"gt\",\"value\":\"11/19/2013\",\"field\":\"newdate\"}," +
				"{\"type\":\"date\",\"comparison\":\"lt\",\"value\":\"11/22/2013\",\"field\":\"newdate\"}," +
				"{\"type\":\"string\",\"value\":\"abc\",\"field\":\"content\"}]";

		MyFilter f = new MyFilter(filter);
		
		System.out.println(f.getResult());
		

	}


}
