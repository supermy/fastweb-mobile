package com.supermy.mongodb.domain;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * 支持分片：
 * db.runCommand({"enablesharding":"mymobile"}) 
 * <BR/>
 * db.runCommand({"shardcollection":"mymobile.person","key":{"ssn":1}})
 * @author jamesmo
 *
 * @param <T>
 */
@Document(collection="person")
@CompoundIndexes({
    @CompoundIndex(name = "age_idx", def = "{'name': 1, 'age': -1}")
})
//@JsonAutoDetect
public class Person<T extends Address> implements Serializable{

  @Id
  private String id;
  
  @Indexed(unique = true)
  private Integer ssn;
  
  @Indexed
  private String name;
  
  private Integer age;
  
  @Transient
  private Integer accountTotal;
  @DBRef
  private List<Account> accounts;
  private T address;

  
  public Person(Integer ssn) {
    this.ssn = ssn;
  }
  
  @PersistenceConstructor
  public Person(Integer ssn, String name, Integer age, T address) {
    this.ssn = ssn;
    this.name = name;
    this.age = age;
    this.address = address;
  }
  
  @Override
	public String toString() {
		// 开发模式
		return ToStringBuilder.reflectionToString(this);
		// 生产模式
		// return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
		// this.pkId).toString();
	}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public Integer getSsn() {
	return ssn;
}

public void setSsn(Integer ssn) {
	this.ssn = ssn;
}


public Integer getAge() {
	return age;
}

public void setAge(Integer age) {
	this.age = age;
}

public Integer getAccountTotal() {
	return accountTotal;
}

public void setAccountTotal(Integer accountTotal) {
	this.accountTotal = accountTotal;
}

public List<Account> getAccounts() {
	return accounts;
}

public void setAccounts(List<Account> accounts) {
	this.accounts = accounts;
}

public T getAddress() {
	return address;
}

public void setAddress(T address) {
	this.address = address;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}
	

  
}