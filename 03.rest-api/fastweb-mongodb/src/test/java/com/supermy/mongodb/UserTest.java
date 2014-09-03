package com.supermy.mongodb;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.BasicDBObject;
import com.supermy.mongodb.domain.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/mongo-config.xml")
public class UserTest {
	
	@Autowired
	MongoOperations mongoOperation;
	
	@Test
	public void crud() {
		List<Map<String,String>> l=new ArrayList<Map<String,String>>();
		//Map 构造数据 
		Map<String,String> o=new HashMap<String,String>();
		o.put("testmap1", "testmap");
		l.add(o);
		Map<String,String> o1=new HashMap<String,String>();
		o1.put("testmap2", "testmap");
		l.add(o1);
		Map<String,String> o2=new HashMap<String,String>();
		o2.put("testmap3", "testmap");
		l.add(o2);
		mongoOperation.save(o2, "testmap");
		mongoOperation.dropCollection("testmap");
		
		
		BasicDBObject newobj = new BasicDBObject ();
		newobj.put("a","a");
		newobj.put("b","b");
		

		User user = new User("jamesmo", "password");

		// 把user保存到user集合中
		mongoOperation.save(user, "users");

		// find
		User savedUser = mongoOperation.findOne(
				new Query(Criteria.where("username").is("jamesmo")), User.class,
				"users");

		System.out.println("savedUser : " + savedUser);

		// update
		mongoOperation.updateMulti(
				new Query(Criteria.where("username").is("jamesmo")),
				Update.update("password", "new password"), "users");

		// find
		User updatedUser = mongoOperation.findOne(
				new Query(Criteria.where("username").is("jamesmo")), User.class,
				"users");

		System.out.println("updatedUser : " + updatedUser);

		// delete
		mongoOperation.remove(
				new Query(Criteria.where("username").is("jamesmo")), "users");

		// List
		List<User> listUser = mongoOperation.findAll(User.class, "users");
		System.out.println("Number of user = " + listUser.size());

	}

}

