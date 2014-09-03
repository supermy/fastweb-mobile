package com.supermy.mongodb.domain;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 账号
 * @author jamesmo
 *
 */
@Document//MongoDb
//@JsonAutoDetect//JSON
public class Account implements Serializable{

  @Id
  private ObjectId id;
  //@Description("工资")
  private Float wage;
  //@Description("奖金")
  private Float bonus;
  //@Description("其他")
  private Float other;
  //@Description("合计")
  private Float total;
public ObjectId getId() {
	return id;
}
public void setId(ObjectId id) {
	this.id = id;
}
public Float getWage() {
	return wage;
}
public void setWage(Float wage) {
	this.wage = wage;
}
public Float getBonus() {
	return bonus;
}
public void setBonus(Float bonus) {
	this.bonus = bonus;
}
public Float getOther() {
	return other;
}
public void setOther(Float other) {
	this.other = other;
}
public Float getTotal() {
	return total;
}
public void setTotal(Float total) {
	this.total = total;
}
  

}