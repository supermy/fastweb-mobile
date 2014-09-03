package com.supermy.mongodb;


import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.BasicDBObject;
import com.mongodb.Bytes;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.QueryOperators;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import com.mongodb.util.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/mongo-config.xml")
public class MongoDBTest {
	private final Logger logger = LoggerFactory.getLogger(MongoDBTest.class);
 
	@Autowired
	MongoOperations mongoOperation;
	
	@Autowired
	MongoTemplate template;

	@Test
	public void initData4GridFS()   {   
		long start = new Date().getTime();   
		
		
		DB mydb = template.getDb();   
//		File f = new File("D:\\env_book\\重构-改善即有代码的设计.pdf");
		
		GridFS myFS = new GridFS(mydb);                
		GridFSInputFile inputFile;
//		try {
			
			InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("log4j.xml");
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<:"+resourceAsStream);
			inputFile = myFS.createFile(resourceAsStream);
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<:"+inputFile);
			inputFile.save();   
			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}   

		DBCursor cursor = myFS.getFileList();   
		while(cursor.hasNext()){   
			System.out.println(cursor.next());   
		}      
		
//		db.close();   
		
		long endTime = new Date().getTime();   
		System.out.println(endTime - start);   
		System.out.println((endTime - start) / 10000000);   


	}   

	

	@Test
	public void json2dbsave() {
		DBCollection collection = template.getDb().getCollection("json2db");
		DBObject dbObject = (DBObject) JSON.parse("{'name':'mkyong','age':30}");
		collection.save(dbObject);
	}
	
	@Test
	public void json2dbfind() {
		DBCollection collection = template.getDb().getCollection("json2db");
		DBObject dbObject = (DBObject) JSON.parse("{name:'mkyong',age:{$gt:3}}");
		DBObject findOne = collection.findOne(dbObject);
		System.out.println("==============="+findOne);
	}
	
	
	@Test
	public void mongodbfind() {

		DBCollection collection = template.getDb().getCollection("json2db");
		// 查询age = 24
		collection.find(new BasicDBObject("age", 24)).toArray();

		// 查询age >= 24
		System.out.println("find age >= 24: "
				+ collection
						.find(new BasicDBObject("age", new BasicDBObject(
								"$gte", 24))).toArray());
		System.out.println("find age <= 24: "
				+ collection
						.find(new BasicDBObject("age", new BasicDBObject(
								"$lte", 24))).toArray());

		System.out.println("查询age!=25："
				+ collection.find(
						new BasicDBObject("age", new BasicDBObject("$ne", 25)))
						.toArray());
		System.out.println("查询age in 25/26/27："
				+ collection.find(
						new BasicDBObject("age", new BasicDBObject(
								QueryOperators.IN, new int[] { 25, 26, 27 })))
						.toArray());
		System.out.println("查询age not in 25/26/27："
				+ collection.find(
						new BasicDBObject("age", new BasicDBObject(
								QueryOperators.NIN, new int[] { 25, 26, 27 })))
						.toArray());
		System.out.println("查询age exists 排序："
				+ collection.find(
						new BasicDBObject("age", new BasicDBObject(
								QueryOperators.EXISTS, true))).toArray());

		System.out.println("只查询age属性："
				+ collection.find(null, new BasicDBObject("age", true))
						.toArray());
		System.out.println("只查属性："
				+ collection.find(null, new BasicDBObject("age", true), 0, 2)
						.toArray());
		System.out.println("只查属性："
				+ collection.find(null, new BasicDBObject("age", true), 0, 2,
						Bytes.QUERYOPTION_NOTIMEOUT).toArray());

		// 只查询一条数据，多条去第一条
		System.out.println("findOne: " + collection.findOne());
		System.out.println("findOne: "
				+ collection.findOne(new BasicDBObject("age", 26)));
		System.out.println("findOne: "
				+ collection.findOne(new BasicDBObject("age", 26),
						new BasicDBObject("name", true)));

		// 查询修改、删除
		System.out.println("findAndRemove 查询age=25的数据，并且删除: "
				+ collection.findAndRemove(new BasicDBObject("age", 25)));

		// 查询age=26的数据，并且修改name的值为Abc
		System.out.println("findAndModify: "
				+ collection.findAndModify(new BasicDBObject("age", 26),
						new BasicDBObject("name", "Abc")));
		System.out.println("findAndModify: "
				+ collection.findAndModify(new BasicDBObject("age", 28), // 查询age=28的数据
						new BasicDBObject("name", true), // 查询name属性
						new BasicDBObject("age", true), // 按照age排序
						false, // 是否删除，true表示删除
						new BasicDBObject("name", "Abc"), // 修改的值，将name修改成Abc
						true, true));
	}
	
	@Test
	public void initspring() {
		DBCollection collection = template.getDb().getCollection("my1");
		long start = new Date().getTime();
		
		for (long i = 1; i <= 100000; i++) {
			BasicDBObject doc = new BasicDBObject();
			doc.put("order_id", i);
			doc.put("company_id", 505 + i);
			doc.put("user_id", 180225429 + i);
			doc.put("fetcher_id", 59803 + i);
			doc.put("fetch_schedule_begin", new Date());
			doc.put("fetch_schedule_end", new Date());
			doc.put("sender_id", 59803 + i);
			doc.put("mail_no", "000000");
			doc.put("mail_type", "301");
			doc.put("order_code", "LP10012700003959" + i);
			doc.put("order_status", 30);
			doc.put("prev_order_id", 0);
			doc.put("trade_id", 2010012706189794L + i);
			doc.put("goods_remark", "");
			doc.put("receiver_name", " 凯撒");
			doc.put("receiver_wangwang_id", "sanglin01");
			doc.put("receiver_mobile_phone", "13021525841");
			doc.put("receiver_zip_code", "650045");
			doc.put("receiver_telephone", "13868117135");
			doc.put("receiver_county_id", 350102);
			doc.put("receiver_address", "福建省^^^福州市^^^鼓楼区^^^的萨芬萨芬萨芬的12号");
			doc.put("gmt_create", new Date());
			doc.put("gmt_modified", new Date());
			doc.put("status_reason", "");
			doc.put("logis_type", 0);
			doc.put("seller_wangwang_id", "tbtest943" + i);
			doc.put("seller_send_confirm", 0);
			doc.put("shipping", 2);
			doc.put("company_code", "");
			doc.put("taobao_trade_id", "2232358300" + i);
			doc.put("options", 2);
			doc.put("shipping2", 0);
			doc.put("order_source", 0);
			doc.put("status_date", new Date());
			doc.put("timeout_status", 2);
			doc.put("feature", "ip=127.0.0.1;SFID=");
			doc.put("service_fee", 0);
			doc.put("seller_store_id", "23100");
			doc.put("items_value", 23100);
			doc.put("pre_status", 0);
			doc.put("ticket_id", "");
			doc.put("tfs_url", "T1DoBbXctCXXXXXXXX");
			
//			template.getDb().getCollection("my1").insert(doc);
			collection.insert(doc);
			
		}
		
		
		long endTime = new Date().getTime();
		System.out.println("毫秒:"+(endTime - start));//millisecond 千分之一秒
		System.out.println("秒："+(endTime - start) / 1000);
		System.out.println("分："+(endTime - start) / 1000/60);

		System.out.println((endTime - start) / 10000000);
	}	 

	@Test
	public void initys() {
		long start = new Date().getTime();
		//Mongo db = new Mongo("127.0.0.1", 50000);
		//Mongo db = new Mongo("127.0.0.1", 27017);
		Mongo m=null;
		try {
			m = new Mongo( "127.0.0.1" , 27017 );
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		

		DB mydb = m.getDB("record-test");
		
		DBCollection coll = mydb.getCollection("my_orders");
		coll.drop();
		
		for (long i = 1; i <= 100000; i++) {
			BasicDBObject doc = new BasicDBObject();
			doc.put("order_id", i);
			doc.put("company_id", 505 + i);
			doc.put("user_id", 180225429 + i);
			doc.put("fetcher_id", 59803 + i);
			doc.put("fetch_schedule_begin", new Date());
			doc.put("fetch_schedule_end", new Date());
			doc.put("sender_id", 59803 + i);
			doc.put("mail_no", "000000");
			doc.put("mail_type", "301");
			doc.put("order_code", "LP10012700003959" + i);
			doc.put("order_status", 30);
			doc.put("prev_order_id", 0);
			doc.put("trade_id", 2010012706189794L + i);
			doc.put("goods_remark", "");
			doc.put("receiver_name", " 凯撒");
			doc.put("receiver_wangwang_id", "sanglin01");
			doc.put("receiver_mobile_phone", "13021525841");
			doc.put("receiver_zip_code", "650045");
			doc.put("receiver_telephone", "13868117135");
			doc.put("receiver_county_id", 350102);
			doc.put("receiver_address", "福建省^^^福州市^^^鼓楼区^^^的萨芬萨芬萨芬的12号");
			doc.put("gmt_create", new Date());
			doc.put("gmt_modified", new Date());
			doc.put("status_reason", "");
			doc.put("logis_type", 0);
			doc.put("seller_wangwang_id", "tbtest943" + i);
			doc.put("seller_send_confirm", 0);
			doc.put("shipping", 2);
			doc.put("company_code", "");
			doc.put("taobao_trade_id", "2232358300" + i);
			doc.put("options", 2);
			doc.put("shipping2", 0);
			doc.put("order_source", 0);
			doc.put("status_date", new Date());
			doc.put("timeout_status", 2);
			doc.put("feature", "ip=127.0.0.1;SFID=");
			doc.put("service_fee", 0);
			doc.put("seller_store_id", "23100");
			doc.put("items_value", 23100);
			doc.put("pre_status", 0);
			doc.put("ticket_id", "");
			doc.put("tfs_url", "T1DoBbXctCXXXXXXXX");
			coll.insert(doc);
		}
		
		m.close();
		
		long endTime = new Date().getTime();
		System.out.println("毫秒:"+(endTime - start));//millisecond 千分之一秒
		System.out.println("秒："+(endTime - start) / 1000);
		System.out.println("分："+(endTime - start) / 1000/60);

		System.out.println((endTime - start) / 10000000);
	}	 


}
