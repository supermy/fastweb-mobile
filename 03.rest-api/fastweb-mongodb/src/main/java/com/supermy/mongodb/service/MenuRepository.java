package com.supermy.mongodb.service;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.supermy.mongodb.domain.Menu;

/**
 * 
 * @author jamesmo
 *
 */
public interface MenuRepository extends MongoRepository<Menu, ObjectId> {
	
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
	 
	 

}
