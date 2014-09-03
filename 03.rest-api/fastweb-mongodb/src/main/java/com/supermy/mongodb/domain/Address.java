package com.supermy.mongodb.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;


/**
 * 地址
 * 
 * @author jamesmo
 *
 */
@Document//MongoDb
//@JsonAutoDetect//JSON
public class Address implements Serializable{

//  @Id
//  private ObjectId id;
  
  private String address;
  
  private String qq;
  
  private String tel;
  
  private String weibo;
  
  
  
public Address(String address) {
	super();
	this.address = address;
}
//public ObjectId getId() {
//	return id;
//}
//public void setId(ObjectId id) {
//	this.id = id;
//}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getQq() {
	return qq;
}
public void setQq(String qq) {
	this.qq = qq;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public String getWeibo() {
	return weibo;
}
public void setWeibo(String weibo) {
	this.weibo = weibo;
}
  
  

}