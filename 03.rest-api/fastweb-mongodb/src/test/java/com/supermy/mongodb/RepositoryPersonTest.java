package com.supermy.mongodb;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.gridfs.GridFSDBFile;
import com.supermy.mongodb.domain.Address;
import com.supermy.mongodb.domain.Person;
import com.supermy.mongodb.service.CommonDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/mongo-config.xml")
public class RepositoryPersonTest {

	@Autowired
	private MongoRepository personRepository;

	@Autowired
	private CommonDao personDao;

	@Autowired
	MongoTemplate template;

	@Autowired
	GridFsTemplate gridTemplate;

	@Test
	public void shouldListExistingFiles() {
		// template.store(content, filename);

		List<GridFSDBFile> files = gridTemplate.find(null);

		for (GridFSDBFile file : files) {
			System.out.println(file);
		}
	}

	@Test
	public void fileops() {
		InputStream resourceAsStream = getClass().getClassLoader()
				.getResourceAsStream("test.doc");
		System.out.println("<<<<<<<<<:" + resourceAsStream);
		String id = personDao.save(resourceAsStream, "doc", "test.doc");

		GridFSDBFile file1 = personDao.get(id);
		System.out.println(file1.getMetaData());

		GridFSDBFile file = personDao.getByFilename("test.doc");
		System.out.println(file.getMetaData());

		List<GridFSDBFile> files = personDao.listFiles();

		for (GridFSDBFile file2 : files) {
			System.out.println(file2);
		}
	}

	@Test
	public void init() {

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>:"
				+ personRepository.toString());

		personRepository.deleteAll();

		List<Person> ps = new ArrayList();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 30; i++) {
			// Person p1 = new Person(i);
			Address address = new Address("朝阳区");
			address.setWeibo("springclick@gmail.com");

			Person p1 = new Person(i, "张三", 30, address);
			ps.add(p1);
			System.out.println(">>>>>>>>>>>>>>>>>count:" + i);
			if (i % 10000 == 1) {
				personRepository.save(ps);
				ps = new ArrayList();
				System.out.println(">>>>>>>>>>>>>>>>>count:"
						+ personRepository.count());
			}
		}
		List save = personRepository.save(ps);

		
		//template.updateFirst(query, update, entityClass);
		//template.updateMulti(query, update, entityClass);
		//person.name  集合.属性
//		template.updateMulti(new Query(where("name").is("张三")),
//                new Update().inc("age", 80),
//                Person.class);
		template.updateFirst(new Query(where("name").is("张三")),
                new Update().inc("age", 80),
                Person.class);
		
		
		long end = System.currentTimeMillis();

		System.out.println(">>>>count:" + personRepository.count()
				+ " have time:" + (end - start) + " MS.");

		Page<Person> page = personRepository.findAll(new PageRequest(2, 2));
		List<Person> persons = page.getContent();
		System.out.println(">>>>>>>>>>>>>>>>>count:" + persons.size());
		for (Person line : persons) {
			System.out.println(">>>>>>>>>>>>>>>>>:" + line.getAge());
		}
		// then

		Assert.assertNotNull(persons);
		Assert.assertEquals("Mismatch user count", 2, persons.size());

	}
	

}
