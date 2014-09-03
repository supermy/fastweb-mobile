<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="taglibs.jsp" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>

<head>
	<%@ include file="/commons/meta-cache.jsp" %>
	<base href="<%=basePath%>">
	<myweb:block name="head"/>
</head>
<body>
  <div class="container">
      <div class="span-24">
	      	菜单
      </div>
      <div class="span-24">
	      	标题
      </div>
      <div class="span-24">
      <h2 class="alt">
			<%@ include file="messages.jsp" %>
      </h2>
      </div>
      <hr class="space">
      <div class="span-10">
       	<myweb:block name="leftcontent"/>
      </div>
      <div class="span-14">
       	<myweb:block name="rightcontent"/>
      </div>
      <hr class="space">
      <div class="span-24">
	      <h3>页面尾部</h3>
      </div>      
  </div>
</body>
</html>	