package com.supermy.mongodb;

import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.supermy.mongodb.domain.Location;
import com.supermy.mongodb.service.LocationDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/mongo-config.xml")
public class LocationTest {
	@Autowired
	LocationDao locationDao;
 
	@Autowired
	MongoTemplate template;
  
	@Before
	public void setUp() {
		// 等同db.location.ensureIndex( {position: "2d"} )
		template.indexOps(Location.class).ensureIndex(new GeospatialIndex("position"));
		// 初始化数据
		template.save(new Location("A", 0.1, -0.1));
		template.save(new Location("B", 1, 1));
		template.save(new Location("C", 0.5, 0.5));
		template.save(new Location("D", -0.5, -0.5));
	}
 
	@Test
	public void findCircleNearTest() {
		List<Location> locations = locationDao.findCircleNear(new Point(0, 0), 0.7);
		print(locations);
		System.err.println("-----------------------");
		locations = locationDao.findCircleNear(new Point(0, 0), 0.75);
		print(locations);
	}
 
	@Test
	public void findBoxNearTest() {
		List<Location> locations = locationDao.findBoxNear(new Point(0.2, 0.2), new Point(1, 1));
		print(locations);
	}
 
	public static void print(Collection<Location> locations) {
		for (Location location : locations) {
			System.err.println(location);
		}
	}
 
}

