另外一个spring view 可以设定在这个目录
org.springframework.web.servlet.DispatcherServlet

web.xml
	<servlet>
		<servlet-name>ReportDispatcherServlet</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>classpath*:/spring/servlet-context.xml</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>

	<servlet-mapping>
		<servlet-name>ReportDispatcherServlet</servlet-name>
		<url-pattern>/report/*</url-pattern>
	</servlet-mapping>


路径是：项目路径/report/