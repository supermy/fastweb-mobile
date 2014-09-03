package com.supermy.mongodb.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.supermy.mongodb.domain.Menu;

/**
 * 标准的rest方法列表
 * 
 * <pre>
 * /menu                => index()  
 * /menu/add            => add()  
 * /menu/{id}           => show()  
 * /menu/{id}/edit      => edit()  
 * /menu        POST    => create()  
 * /menu/{id}   PUT     => update()  
 * /menu/{id}   DELETE  => delete()
 * 
 * /menu/list       POST    => batchCreate()  
 * /menu/list       PUT     => batchUpdate()  
 * /menu/list/del   PUT     => batchDelete()
 * </pre>
 * 
 * @author james mo rest 客户端
 */
@Component
public class RestClient {

	@Autowired
	protected RestTemplate restTemplate;

	private static final String BASE_URL = "http://127.0.0.1:8080/fastweb-mongodb/menu";
	private static final String BASE_URLS = "http://127.0.0.1:8080/fastweb-mongodb/menu/list";

	@SuppressWarnings("unchecked")
	public List<Menu> getMenus(int start, int limit) {
		return restTemplate.getForObject(BASE_URLS
				+ "/{currentPage}/{limit}", List.class, start, limit);
	}

	public Menu getMenu(String id) {
		return restTemplate.getForObject(BASE_URL + "/{id}", Menu.class, id);
	}

	public Menu addMenu() {
		return restTemplate.postForObject(BASE_URL + "/add", null,
				Menu.class);
	}

	public Menu createMenu(Menu menu) {
		System.out.println("create menu:"+menu);
		return restTemplate.postForObject(BASE_URL, menu, Menu.class);
	}

	public Menu editMenu(String id) {
		return restTemplate.getForObject(BASE_URL + "/{id}/edit",
				Menu.class, id);
	}

	public String updateMenu(String id, Menu menu) {
		restTemplate.put(BASE_URL + "/{id}", menu, id);
		return menu.getId();
	}

	@SuppressWarnings("unchecked")
	public void delMenus(String id) {
		restTemplate.delete(BASE_URL + "/{id}", id);
	}

	@SuppressWarnings("unchecked")
	public void batchDeleteMenus(String[] ids) {
		restTemplate.put(BASE_URLS + "/del", ids);
	}

}
