package com.supermy.mongodb;

import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.Box;
import org.springframework.data.mongodb.core.geo.Distance;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.supermy.mongodb.domain.Location;
import com.supermy.mongodb.service.LocationRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/mongo-config.xml")
public class RepositoryLocationTest {
	
	@Autowired
	LocationRepository locationRepository;
 
	@Autowired
	MongoTemplate template;
 
	@Before
	public void setUp() {
		
		
		// 等同db.location.ensureIndex( {position: "2d"} )
		template.indexOps(Location.class).ensureIndex(new GeospatialIndex("position"));
		// 初始化数据
		locationRepository.save(new Location("A", 0.1, -0.1));
		locationRepository.save(new Location("B", 1, 1));
		locationRepository.save(new Location("C", 0.5, 0.5));
		locationRepository.save(new Location("D", -0.5, -0.5));
	}
 
	@Test
	public void findCircleNearTest() {
		Point p=new Point(0, 0);
		Distance d=new Distance(0.7);
		List<Location> findByPositionNear = locationRepository.findByPositionNear(p, d);
		print(findByPositionNear);
		System.err.println("-----------------------");
		
		List<Location> findByPositionNear2 = locationRepository.findByPositionNear(new Point(0, 0), new Distance(0.75));
		print(findByPositionNear2);
	}
 
	@Test
	public void findBoxNearTest() {
		Box b= new Box(new Point(0.2, 0.2), new Point(1, 1));
		List<Location> locations=locationRepository.findByPositionWithin(b);
		print(locations);
	}
 
	public static void print(Collection<Location> locations) {
		for (Location location : locations) {
			System.err.println(location);
		}
	}
 
}

