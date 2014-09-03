<%@page contentType="text/html;charset=UTF-8" %>
<%@page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html>
<title>Session超时</title>
<body>
<h2>失效Session</h2>

<p>
你的session已经超时失效了。 请 <a href="<c:url value='/'/>">重新启动</a>.
</p>
</body>
</html>
