package com.supermy.mongodb.web;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.supermy.mongodb.domain.News;
import com.supermy.mongodb.service.CommonDao;
import com.supermy.mongodb.service.NewsRepository;
import com.supermy.rest.web.MyFilter;
import com.supermy.rest.web.MySort;

/**
 * 新闻管理
 * 
 * @author james mo
 * 
 */
@Controller
@RequestMapping("news")
public class NewsController {
	private final Logger logger = LoggerFactory
			.getLogger(NewsController.class);

	@Autowired
	private NewsRepository cs;

	@Autowired
	public  HttpServletRequest request;
	
	/**
	 * 分页查询
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public @ResponseBody
	Page<News> getNewsList(
			@RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "limit", defaultValue = "20") int limit,
			@RequestParam(value = "page", defaultValue = "1") int curPage,
			@RequestParam(value = "sort", defaultValue = "[{\"property\":\"content\",\"direction\":\"ASC\"}]") MySort sort,			
			@RequestParam(value = "filter", defaultValue = "") MyFilter filter			
			){
		
		logger.debug("get news called");
		
		
		//取得“search_”开头的请求参数，并放入searchParams容器中，search_*参数描述数据的过滤条件。
		//Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		PageRequest pageRequest = new PageRequest(curPage-1, limit,sort.getSort());
		
		logger.debug("get news called:"+filter);
		//filter="";
		
		//TODO 逻辑层
		Page<News> result = null;
		if (StringUtils.isEmpty(filter)) {
			result = cs.findAll(pageRequest);
			
		} else {
			result = cs.find(filter,pageRequest);

		}

		
		return result;
	}
	

	/**
	 * 更新
	 * 
	 * @param persons
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	News updatePersons(@RequestBody News news, @PathVariable String id) {
		logger.debug("updatePersons called");
		cs.save(news);
		return news;

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
		logger.debug("delete News called" + id);
		cs.delete(new ObjectId(id));
		return true;
	}
	
	
	
	/**
	 * 单个对象查询
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public @ResponseBody
	News getPerson(@PathVariable("id") String id) {
		return cs.findOne(new ObjectId(id));
	}
	
	/**
	 * 创建
	 * 
	 * @param news
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public @ResponseBody
	News createPerson(@RequestBody News news) {
		logger.debug("create News called" + news);
		logger.debug("create News called", news);

		cs.save(news);

		return news;
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
