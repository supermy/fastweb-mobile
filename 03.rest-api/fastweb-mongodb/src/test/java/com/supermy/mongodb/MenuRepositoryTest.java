package com.supermy.mongodb;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.supermy.mongodb.domain.Menu;
import com.supermy.mongodb.domain.MenuItem;
import com.supermy.mongodb.service.CommonDao;
import com.supermy.mongodb.service.MenuItemRepository;
import com.supermy.mongodb.service.MenuRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/mongo-config.xml")
public class MenuRepositoryTest { 

	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private MenuItemRepository menuItemRepository;

	@Autowired
	private CommonDao commonDao;

	@Test
	public void init() {

		System.out.println(">>>:" + menuRepository.toString());

		menuRepository.deleteAll();

		List<Menu> ps = new ArrayList<Menu>();
		long start = System.currentTimeMillis();
				
		Menu m1 = genSubMenu("新闻","新闻子菜单");
		Menu m2 = genSubMenu("体育","体育子菜单");
		Menu m3 = genSubMenu("财经","财经子菜单");
		Menu m4 = genSubMenu("活动","活动子菜单");
		ps.add(m1);
		ps.add(m2);
		ps.add(m3);
		ps.add(m4);
		
		menuRepository.save(ps);
//		menuRepository.save(m2);
//		menuRepository.save(m3);
//		menuRepository.save(m4);

		long end = System.currentTimeMillis();

		System.out.println(">>>>count:" + menuRepository.count()
				+ " have time:" + (end - start) + " MS.");

		Page<Menu> page = menuRepository.findAll(new PageRequest(0, 2));
		List<Menu> results = page.getContent();

		System.out.println(">>>count:" + results.size());
		for (Menu line : results) {
			System.out.println(">>>:" + line.getId()+line.getSubtitle());
			List<MenuItem> items = line.getItems();
			System.out.println(">>>:" + items);
			for (MenuItem menuItem : items) {
				System.out.println(">>>menu item:" + menuItem.getTitle());	
			}
		}

		Assert.assertNotNull(results);
		Assert.assertEquals(" count", 2, results.size());

	}

	private Menu genSubMenu(String title,String subtitle) {
		Menu m = new Menu();
		m.setSubtitle(title);
		List<MenuItem> r=new ArrayList<MenuItem>();
		
		for (int i = 0; i < 3; i++) {
			MenuItem n = new MenuItem();
			n.setTitle(subtitle+i);
			n.setUrl("http://url"+i);
			m.add(n);
		}
		
		menuItemRepository.save(m.getItems());
		 
		return m;
	}

}
