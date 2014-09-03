package com.supermy.mongodb.web;


import java.net.URI;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFSDBFile;
import com.supermy.mongodb.service.CommonDao;
import com.supermy.mongodb.service.OAuth2Repository;

/**
*资源所有者（resource owner）<br/>
*资源服务器（resource server）<br/>
*客户端 （ client ）<br/>
*授权服务器（authorization server）<br/>
 * 
 * @author james mo
 * 
 */
@Controller
@RequestMapping("oauth2")
public class OAuth2Controller {
	private final Logger logger = LoggerFactory
			.getLogger(OAuth2Controller.class);
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private OAuth2Repository database;

	/**
	 * GET /authorize?response_type=code&client_id=s6BhdRkqt3&state=xyz&redirect_uri=https%3A%2F%2Fclient%2Eexample%2Ecom%2Fcb<br>
	 * https://client.example.com/cb?code=SplxlOBeZQQYbYS6WxSbIA&state=xyz
	 */
	@RequestMapping(value = "/authorize/{response_type}", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public 	@ResponseBody Object authz(@PathVariable String response_type) throws Exception {
		
		logger.debug("authorize begin ......");
//		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
//		Page<News> result = oar.findAll(new PageRequest(currentPage, limit));
//		return result.getContent();
        try {
        	System.out.println("-----------"+response_type);
            OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
            OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());

            //build response according to response_type
            String responseType = oauthRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
            System.out.println("responseType"+responseType);
            OAuthASResponse.OAuthAuthorizationResponseBuilder builder =
                    OAuthASResponse.authorizationResponse(request, HttpServletResponse.SC_FOUND);

            if (responseType.equals(ResponseType.CODE.toString())) {
                final String authorizationCode = oauthIssuerImpl.authorizationCode();
                database.addAuthCode(authorizationCode);
                builder.setCode(authorizationCode);
            }
            if (responseType.equals(ResponseType.TOKEN.toString())) {
                final String accessToken = oauthIssuerImpl.accessToken();
                database.addToken(accessToken);

                builder.setAccessToken(accessToken);
                builder.setExpiresIn(3600l);
            }

            String redirectURI = oauthRequest.getParam(OAuth.OAUTH_REDIRECT_URI);
            System.out.println("redirectURI"+redirectURI);
            
            final OAuthResponse response = builder.location(redirectURI).buildQueryMessage();
            URI url = new URI(response.getLocationUri());

            //FIXMEs
//            Response build = Response.status(response.getResponseStatus()).location(url).build();
//            System.out.println("build"+build.getEntity());
//			return build;

            return null;
            
        } catch (OAuthProblemException e) {
            final Response.ResponseBuilder responseBuilder = Response.status(HttpServletResponse.SC_FOUND);
            
            String redirectUri = e.getRedirectUri();

            if (OAuthUtils.isEmpty(redirectUri)) {
//                throw new WebApplicationException(
//                        responseBuilder.entity("OAuth callback url needs to be provided by client!!!").build());
            	return "OAuth callback url needs to be provided by client!!!";
            }
            final OAuthResponse response =
                    OAuthASResponse.errorResponse(HttpServletResponse.SC_FOUND)
                    .error(e).location(redirectUri).buildQueryMessage();
            final URI location = new URI(response.getLocationUri());
            return responseBuilder.location(location).build();
        }
		
	}

	/**
	 * POST grant_type=authorization_code&code=SplxlOBeZQQYbYS6WxSbIA&redirect_uri=https%3A%2F%2Fclient%2Eexample%2Ecom%2Fcb
	 * 
	 */
//	@RequestMapping(value = "/token")
//	@Transactional(readOnly = true)
//	public @ResponseBody
//	String token(@PathVariable("id") String id) {
//		return oar.findOne(new ObjectId(id));
//	}

	/**
	 * 新增
	 * 
	 * @return
	 */
//	@RequestMapping(value = "/resource")
//	@Transactional(readOnly = false)
//	public @ResponseBody
//	String  resource() {
//		Integer integer = new Integer(new Date().getMinutes());
//		News person = new News("a",integer);
////		person.put("person", integer);
//		return person;
//	}

	/**
	 * 创建
	 * 
	 * @param person
	 * @return
	 */
//	@RequestMapping(method = RequestMethod.POST)
//	@Transactional(readOnly = false)
//	public @ResponseBody
//	News createPerson(@RequestBody News person) {
//		logger.debug("create News called" + person);
//		logger.debug("create News called", person);
//
//		oar.save(person);
//
//		return person;
//	}

	/**
	 * 创建
	 * 
	 * @param person
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public @ResponseBody
	HashMap<String, Object> createPerson4Map(
			@RequestBody HashMap<String, Object> person) {
		System.out
				.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>create News called"
						+ person);
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>create News called",
				person);

		// cs.addPerson(person);
		//
		return person;

	}

	/**
	 * 编辑
	 * 
	 * @param id
	 * @return
	 */
//	@RequestMapping(value = "/{id}/edit")
//	@Transactional(readOnly = true)
//	public @ResponseBody
//	News edit(@PathVariable("id") String id) {
//		return oar.findOne(new ObjectId(id));
//
//	}

	/**
	 * 更新
	 * 
	 * @param persons
	 * @return
	 */
//	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//	public @ResponseBody
//	News updatePersons(@RequestBody News person, @PathVariable String id) {
//		logger.debug("updatePersons called");
//		oar.save(person);
//		return person;
//
//	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//	public @ResponseBody
//	boolean deletePersons(@PathVariable String id) {
//		logger.debug("delete News called" + id);
//		oar.delete(new ObjectId(id));
//		return true;
//	}

	/** 批量删除 */
//	@RequestMapping(value = "/list/del", method = RequestMethod.PUT)
//	public @ResponseBody
//	boolean batchDelete(@RequestBody String[] items) {
//		for (String e : items) {
//			oar.delete(new ObjectId(e));
//		}
//		return true;
//	}

	@Autowired
	private CommonDao personDao;

	/**
	 ** 上传单多文档
	 */
	@RequestMapping(value = "/upload")
	public String upload2(@RequestParam("file") MultipartFile files[], 	@RequestParam("text") String text 	) throws Exception {
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>upload start:");

		logger.debug("[ text ] : " + text); // 打印 页面上的控件值
		
		//List<MultipartFile> files = file.getrequest.getFiles("file");
		
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>upload file count:"+files.length);
		
		for (int i = 0; i < files.length; i++) {
			if (!files[i].isEmpty()) {
				//存储文件到数据库
				String filename=StringUtils.isBlank(text)?files[i].getOriginalFilename():text;
				String id = personDao.save(files[i].getInputStream(),files[i].getContentType(),filename);
				//查询文件
				GridFSDBFile file1 = personDao.get(id);
				logger.debug(file1.getMetaData().toString());				
				
				//查询数据表里面的所有文件
				List<GridFSDBFile> file2s = personDao.listFiles();

				for (GridFSDBFile file2: file2s) {
					logger.debug(">>>>>>>>>>>>>>>>>>:"+file2);
				}

			}
			logger.debug("Ok");
		}
		return "index";
	}

}
