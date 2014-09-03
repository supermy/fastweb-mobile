<%@ page contentType="text/html; charset=GBK" %> 
 
<%@ page import="java.util.*" %> 
 
<html><head><title>Cluster Test</title></head> 
 
<body> 
 
<% 
 
  //HttpSession session = request.getSession(true); 
  System.out.println(session.getId());
  out.println("<br> SESSION ID:" + session.getId()+"<br>");
%>
 
</body> 
</html>

