package com.supermy.mongodb.service;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.supermy.mongodb.domain.MenuItem;

/**
 * 
 * @author jamesmo
 *
 */
public interface MenuItemRepository extends MongoRepository<MenuItem, ObjectId> {
}
