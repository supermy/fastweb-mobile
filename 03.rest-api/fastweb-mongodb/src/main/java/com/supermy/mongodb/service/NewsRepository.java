package com.supermy.mongodb.service;


import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.supermy.mongodb.domain.News;

/**
 * 
 * @author jamesmo
 *
 */
public interface NewsRepository extends MongoRepository<News, ObjectId> {

	//@Query("{'title':'10000'}")//ok
	//@Query("{'title':?0}")//ok
	/**
	 * 主要用于前端(extjs)生成查询条件，进行组合查询
	 * 
	 * @param filter
	 * @param page
	 * @return
	 */
	@Query(value="?0")
	public Page<News> find(String filter, Pageable page);
	
//	// 查询大于age的数据
//	public Page<News> findByAgeGreaterThan(int age, Pageable page);
//
//	@Query("{ 'name':{'$regex':?0,'$options':'i'}, sales': {'$gte':?1,'$lte':?2}}")
//	public Page<News> findByNameAndAgeRange(String name, double ageFrom,
//			double ageTo, Pageable page);
//
//	/**
//	 * fields :定义返回字段，1返回，0不返回<br/>
//	 * $options:忽略大小写<br/>
//	 * 
//	 * @param name
//	 * @param ageFrom
//	 * @param ageTo
//	 * @param page
//	 * @return
//	 */
//	@Query(value = "{ 'name':{'$regex':?0,'$options':'i'}, sales':{'$gte':?1,'$lte':?2}}", fields = "{ 'name' : 1, 'age' : 1}")
//	public Page<News> findByNameAndAgeRangeMore(String name, double ageFrom,
//			double ageTo, Pageable page);
//	
//	
////	 @Query("{ 'firstname' : ?0 }")
////	 List<Person> findByThePersonsFirstname(String firstname);
//
//	 @Query(value="{ 'firstname' : ?0 }", fields="{ 'firstname' : 1, 'lastname' : 1}")
//	 List<News> findByThePersonsFirstname(String firstname);
	 

//	@Query(value="{ 'name':{'$regex':?2,'$options':'i'}, sales':{'$gte':?1,'$lte':?2}}",fields="{ 'name' : 1, 'age' : 1}")   
//	public Page<Product> findByNameAndAgeRange(String name,double ageFrom,double ageTo,Pageable page);
	
//	@Query("{ 'name':{'$regex':?2,'$options':'i'}, sales': {'$gte':?1,'$lte':?2}}")  
//	public Page<Product> findByNameAndAgeRange(String name,double ageFrom,double ageTo,Pageable page);	
	
	
//	GreaterThan(大于) 
//	findByAgeGreaterThan(int age) 
//	{"age" : {"$gt" : age}}
//
//	LessThan（小于） 
//	findByAgeLessThan(int age) 
//	{"age" : {"$lt" : age}}
//
//	Between（在...之间） 
//	findByAgeBetween(int from, int to) 
//	{"age" : {"$gt" : from, "$lt" : to}}
//
//	IsNotNull, NotNull（是否非空） 
//	findByFirstnameNotNull() 
//	{"age" : {"$ne" : null}}
//
//	IsNull, Null（是否为空） 
//	findByFirstnameNull() 
//	{"age" : null}
//
//	Like（模糊查询） 
//	findByFirstnameLike(String name) 
//	{"age" : age} ( age as regex)
//
//	(No keyword) findByFirstname(String name) 
//	{"age" : name}
//
//	Not（不包含） 
//	findByFirstnameNot(String name) 
//	{"age" : {"$ne" : name}}
//
//	Near（查询地理位置相近的） 
//	findByLocationNear(Point point) 
//	{"location" : {"$near" : [x,y]}}
//
//	Within（在地理位置范围内的） 
//	findByLocationWithin(Circle circle) 
//	{"location" : {"$within" : {"$center" : [ [x, y], distance]}}}
//
//	Within（在地理位置范围内的） 
//	findByLocationWithin(Box box) 
//	{"location" : {"$within" : {"$box" : [ [x1, y1], x2, y2]}}}	

//	//查询大于age的数据   
//  public Page<Product> findByAgeGreaterThan(int age,Pageable page) ;  
}
