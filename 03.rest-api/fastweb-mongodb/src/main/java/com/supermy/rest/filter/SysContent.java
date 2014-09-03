package com.supermy.rest.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SysContent {  
    private static ThreadLocal<HttpServletRequest> requestLocal= new ThreadLocal<HttpServletRequest>();  
    private static ThreadLocal<HttpServletResponse> responseLocal= new ThreadLocal<HttpServletResponse>();  
      
   public static HttpServletRequest getRequest() {  
       return (HttpServletRequest)requestLocal.get();  
   }  
   public static void setRequest(HttpServletRequest request) {  
       requestLocal.set(request);  
   }  
   public static HttpServletResponse getResponse() {  
       return (HttpServletResponse)responseLocal.get();  
   }  
   public static void setResponse(HttpServletResponse response) {  
       responseLocal.set(response);  
   }  
   public static HttpSession getSession() {
	   
       HttpServletRequest httpServletRequest = requestLocal.get();
       if (null==httpServletRequest) {
		return null;
	}
	return (HttpSession)((HttpServletRequest)httpServletRequest).getSession();  
   }  
}