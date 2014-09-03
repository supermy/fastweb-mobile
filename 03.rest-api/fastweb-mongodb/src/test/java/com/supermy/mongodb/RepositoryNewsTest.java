package com.supermy.mongodb;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.supermy.mongodb.domain.News;
import com.supermy.mongodb.domain.Person;
import com.supermy.mongodb.service.CommonDao;
import com.supermy.mongodb.service.NewsRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/mongo-config.xml")
public class RepositoryNewsTest {

	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private CommonDao commonDao;

	@Test
	public void findByFilter() {

//		String filter="10000";
//		String filter="title:'10000'";
		String filter="{'title':'10000'}";
			Pageable page= new PageRequest(0,30);
			Page<News> find = newsRepository.find(filter, page);
			System.out.println("+++++++++++++++:"+find.getContent());

	}
	@Test
	public void shouldListExistingFiles() {
		// template.store(content, filename);

		List<GridFSDBFile> files = commonDao.gridTemplate.find(null);

		for (GridFSDBFile file : files) {
			System.out.println(file);
		}
	}

	@Test
	public void fileops() {
		InputStream resourceAsStream = getClass().getClassLoader()
				.getResourceAsStream("test.doc");
		System.out.println("<<<<<<<<<:" + resourceAsStream);
		String id = commonDao.save(resourceAsStream, "doc", "test.doc");

		GridFSDBFile file1 = commonDao.get(id);
		System.out.println(file1.getMetaData());

		GridFSDBFile file = commonDao.getByFilename("test.doc");
		System.out.println(file.getMetaData());

		List<GridFSDBFile> files = commonDao.listFiles();

		for (GridFSDBFile file2 : files) {
			System.out.println(file2);
		}
	}

	@Test
	public void init() {

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>:"
				+ newsRepository.toString());

		newsRepository.deleteAll();
		
		

		List<News> ps = new ArrayList<News>();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 30000; i++) {
			News n=new News(i+"",i+"内容",i+"author");
			
			ps.add(n);
			if (i % 10000 == 1) {
				newsRepository.save(ps);
				ps = new ArrayList<News>();
				System.out.println(">>>>>>>>>>>>>>>>>count:"
						+ newsRepository.count());
			}
		}
		
		newsRepository.save(ps);
		
		long end = System.currentTimeMillis();

		System.out.println(">>>>count:" + newsRepository.count()
				+ " have time:" + (end - start) + " MS.");

		Page<News> page = newsRepository.findAll(new PageRequest(2, 2));
		List<News> results = page.getContent();
		
		System.out.println(">>>>>>>>>>>>>>>>>count:" + results.size());
		for (News line : results) {
			System.out.println(">>>>>>>>>>>>>>>>>:" + line);
		}

		Assert.assertNotNull(results);
		Assert.assertEquals(" count", 2, results.size());

	}
	

}
