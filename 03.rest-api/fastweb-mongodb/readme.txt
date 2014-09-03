逻辑分层和json、分页
	spring-rest

数据存储和大数据、实时数据
	spring-mongodb

mvn clean         		--> cleans the project
mvn clean test    		--> cleans the project and runs all tests
mvn clean package 		--> cleans the project and builds the WAR
mvn tomcat:run	 		--> web run
mvn eclipse:eclipse		--> gen eclipse .project and .classpath config file
mvn jar:jar				--> only build jar file.
mvn javadoc:javadoc     --> gen api files;

mvn dependency:copy-dependencies  拷贝jar
mvn dependency:tree

mvn test -Dtest={测试文件名称}

support:
	service junit test
	junit test

默认规范	
	service:包的默认规范  **.service

!!!*------------------------------------------------------------------------------------*!!!
20131015
    mongodb rest and extjs test crud ok
    增加之后的id与grid同步的问题还没有处理。
    
20130715
	json 方式的增删改查方式
	RestTemplate 测试通过
	手动测试通过
	
	todo ajax :实现   Post  使用反向代理整合地址，解决跨域不能Post的问题。
	
20130709
	1.MenuRepositoryTest初始化菜单数据；
	2.http://localhost:8080/fastweb-mongodb/menu/list/0/100.jsonp?callback=aa 获取Json数据；
	3.与前端框架联调left-panel-menu.lbi(Dreamweaver) common.js：原有的json替换为可跨域获取的url地址；
	4.刷新新的菜单在页面上动态刷新。
	

20130703
	1.增加测试更新内容
		template.updateFirst(new Query(where("name").is("张三")),
                new Update().inc("age", 80),
                Person.class);
		template.updateMulti(new Query(where("name").is("张三")),
                new Update().inc("age", 80),
                Person.class);                
    2.增加mongodb数据库调试日志
	
20130629

	http://localhost:8080/fastweb-mongodb/
	http://localhost:8080/fastweb-mongodb/person/list/2/10
	http://localhost:8080/fastweb-mongodb/person/51ce5c8a6538969a01f3a972
	
	http://localhost:8080/fastweb-mongodb/menu/list/0/2
	
	菜单构造:domain,service,test
	
	map 可以存储。
	测试类都ok
	//	@DateTimeFormat(iso = ISO.DATE_TIME)
	
	

20130625
	api 方式提供json数据，支持html5跨域调用；
	
	

20130228
domain 非必要的属性：@JsonAutoDetect
domain 必须要有get set 方法

2013-02-25

MongoDB并发测试，报出上述错误。究其原因，是数据库连接数太少，资源耗尽。查看com.mongodb.MongoOptions源代码，其中有connectionsPerHost和threadsAllowedToBlockForConnectionMultiplier两个重要的属性。

connectionsPerHost：每个主机的连接数

threadsAllowedToBlockForConnectionMultiplier：线程队列数，它以上面connectionsPerHost值相乘的结果就是线程队列最大值。如果连接线程排满了队列就会抛出“Out of semaphores to get db”错误。

connectionsPerHost默认是10，threadsAllowedToBlockForConnectionMultiplier默认是5，也就是线程池有50个连接数可供使用。因此只要将这个属性的值加大就可以避免上述错误。

 

其它属性设置：
maxWaitTime:最大等待连接的线程阻塞时间
connectTimeout：连接超时的毫秒。0是默认和无限
socketTimeout：socket超时。0是默认和无限
autoConnectRetry：这个控制是否在一个连接时，系统会自动重试
	