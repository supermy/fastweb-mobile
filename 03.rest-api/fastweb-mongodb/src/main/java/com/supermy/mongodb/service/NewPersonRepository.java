package com.supermy.mongodb.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.supermy.mongodb.domain.Address;
import com.supermy.mongodb.domain.Person;

/**
 * 新的分页查询类
 * @author jamesmo
 *
 */
public interface NewPersonRepository extends PagingAndSortingRepository<Person, String> {

    List<Person> findByLastname(String lastname);

    Page<Person> findByFirstname(String firstname, Pageable pageable);

    Person findByShippingAddresses(Address address);

}    
