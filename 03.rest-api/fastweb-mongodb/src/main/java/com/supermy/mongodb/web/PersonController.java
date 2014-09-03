package com.supermy.mongodb.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFSDBFile;
import com.supermy.mongodb.domain.Person;
import com.supermy.mongodb.service.CommonDao;
import com.supermy.mongodb.service.PersonRepository;

/**
 * 增删改查；所有的数据都是以对象集的形式传递。
 * 
 * @author james mo
 * 
 */
@Controller
@RequestMapping("person")
public class PersonController {
	private final Logger logger = LoggerFactory
			.getLogger(PersonController.class);

	@Autowired
	private PersonRepository cs;

	/**
	 * 分页查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list/{currentPage}/{limit}", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public @ResponseBody
	List<Person> getPersons(@PathVariable int currentPage,
			@PathVariable int limit) throws Exception {
		logger.debug("get Persons called");
		Page<Person> result = cs.findAll(new PageRequest(currentPage, limit));
		return result.getContent();
	}

	/**
	 * 单个对象查询
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}")
	@Transactional(readOnly = true)
	public @ResponseBody
	Person getPerson(@PathVariable("id") String id) {
		return cs.findOne(new ObjectId(id));
	}

	/**
	 * 新增
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add")
	@Transactional(readOnly = false)
	public @ResponseBody
	Person addPerson() {
		Person person = new Person(new Integer(new Date().getMinutes()));
		return person;
	}

	/**
	 * 创建
	 * 
	 * @param person
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public @ResponseBody
	Person createPerson(@RequestBody Person person) {
		logger.debug("create Person called" + person);
		logger.debug("create Person called", person);

		cs.save(person);

		return person;
	}

	/**
	 * 创建
	 * 
	 * @param person
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public @ResponseBody
	HashMap<String, Object> createPerson4Map(
			@RequestBody HashMap<String, Object> person) {
		System.out
				.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>create Person called"
						+ person);
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>create Person called",
				person);

		// cs.addPerson(person);
		//
		return person;

	}

	/**
	 * 编辑
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/edit")
	@Transactional(readOnly = true)
	public @ResponseBody
	Person edit(@PathVariable("id") String id) {
		return cs.findOne(new ObjectId(id));

	}

	/**
	 * 更新
	 * 
	 * @param persons
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	Person updatePersons(@RequestBody Person person, @PathVariable String id) {
		logger.debug("updatePersons called");
		cs.save(person);
		return person;

	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	boolean deletePersons(@PathVariable String id) {
		logger.debug("delete Person called" + id);
		cs.delete(new ObjectId(id));
		return true;
	}

	/** 批量删除 */
	@RequestMapping(value = "/list/del", method = RequestMethod.PUT)
	public @ResponseBody
	boolean batchDelete(@RequestBody String[] items) {
		for (String e : items) {
			cs.delete(new ObjectId(e));
		}
		return true;
	}

	@Autowired
	private CommonDao personDao;

	/**
	 ** 上传单多文档
	 */
	@RequestMapping(value = "/upload")
	public String upload2(@RequestParam("file") MultipartFile files[], 	@RequestParam("text") String text 	) throws Exception {
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>upload start:");

		logger.debug("[ text ] : " + text); // 打印 页面上的控件值
		
		//List<MultipartFile> files = file.getrequest.getFiles("file");
		
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>upload file count:"+files.length);
		
		for (int i = 0; i < files.length; i++) {
			if (!files[i].isEmpty()) {
				//存储文件到数据库
				String filename=StringUtils.isBlank(text)?files[i].getOriginalFilename():text;
				String id = personDao.save(files[i].getInputStream(),files[i].getContentType(),filename);
				//查询文件
				GridFSDBFile file1 = personDao.get(id);
				logger.debug(file1.getMetaData().toString());				
				
				//查询数据表里面的所有文件
				List<GridFSDBFile> file2s = personDao.listFiles();

				for (GridFSDBFile file2: file2s) {
					logger.debug(">>>>>>>>>>>>>>>>>>:"+file2);
				}

			}
			logger.debug("Ok");
		}
		return "index";
	}

}
