package com.supermy.mongodb;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.supermy.mongodb.domain.Menu;
import com.supermy.mongodb.web.RestClient;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/business.xml" })
@TransactionConfiguration(transactionManager = "myBatisTransactionManager", defaultRollback = false)
public class RestClientTest {

	private Logger log = LoggerFactory.getLogger(RestClientTest.class);

	@Autowired
	private RestClient restClient;

	private Menu m;

	@Test
	public void findMenu() throws Exception {
		log.info("crud menu ...");
		List<Menu> menus = restClient.getMenus(0, 4);
		log.debug("get menu count:{}", menus.size());
	}

	@Test
	public void crudRestMenu() throws Exception {

		Menu newmenu = restClient.addMenu();
		newmenu.setSubtitle("superadmin");
		

		Menu createmenu = restClient.createMenu(newmenu);
		log.debug("new  menu {}", createmenu.getId());

		Menu menu = restClient.getMenu(createmenu.getId());
		log.debug("get menu {}", menu.getId());

		Menu editmenu = restClient.editMenu(createmenu.getId());
		editmenu.setSubtitle("o O oo ");
		String id = restClient.updateMenu(editmenu.getId(), editmenu);
		
		restClient.delMenus(id);

		newmenu.setId(UUID.randomUUID().toString());
		Menu c1 = restClient.createMenu(newmenu);
		newmenu.setId(UUID.randomUUID().toString());
		Menu c2 = restClient.createMenu(newmenu);
		newmenu.setId(UUID.randomUUID().toString());
		Menu c3 = restClient.createMenu(newmenu);
		newmenu.setId(UUID.randomUUID().toString());
		Menu c4 = restClient.createMenu(newmenu);

		// String[] ids={c1.getId(),c2.getId(),c3.getId(),c4.getId()};
		// restClient.batchDeleteMenus(ids);

	}
}