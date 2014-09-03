package com.supermy.mongodb.service;

import java.io.InputStream;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

/**
 * GridFS
 * 
 * @author jamesmo 
 */

@Repository
public class CommonDao {
	
	@Autowired
	public MongoTemplate template;
	
	@Autowired
	public GridFsTemplate gridTemplate;
	
	@Autowired
	public GridFsOperations gridOperation;

	/**
	 * 保存文件到数据库
	 * 
	 * @param inputStream
	 * @param contentType
	 * @param filename
	 * @return
	 */
	public String save(InputStream inputStream, String contentType, String filename) {
	
		DBObject metaData = new BasicDBObject();
		metaData.put("meta1", filename);
		metaData.put("meta2", contentType);
	
		GridFSFile file = gridOperation.store(inputStream, filename, metaData);
	
		return file.getId().toString();
	}

	/**
	 * 
	 * 返回数据库文件
	 * 
	 * @param id
	 * @return
	 */
	public GridFSDBFile get(String id) {	
		System.out.println("Finding by ID: " + id);
		return gridOperation.findOne(new Query(Criteria.where("_id").is(new ObjectId(id))));
	}

	/**
	 * 
	 * 文件列表
	 * 
	 * @return
	 */
	public List listFiles() {	
		return gridOperation.find(null);
	}

	/**
	 * 用文件名查找
	 * 
	 * @param filename
	 * @return
	 */
	public GridFSDBFile getByFilename(String filename) {
		return gridOperation.findOne(new Query(Criteria.where("filename").is(filename)));
	}
	 
}