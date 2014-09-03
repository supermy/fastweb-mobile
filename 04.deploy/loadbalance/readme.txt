1.启动Tomcat MSM的集成环境；
	先手动启动mem-clu1.bat mem-clu2.bat mem-clu3.bat 
2.启动集群
	手动启动tomcat1.bat tomcat2.bat
3.测试集群Session,Session一致，集群环境OK
	http://localhost:8180/examples/jsp/sessions/my.jsp
	http://localhost:8280/examples/jsp/sessions/my.jsp
3.启动nginx.bat,测试返回session与tomcat的session一致
	http://localhost/examples/jsp/sessions/my.jsp
4.压力测试
  #.自己笔记本，6G内存，压力测试，并发3000无碍
  #.扩充集群：
	拷贝tomcat-2到tomcat-3,更改server.xml端口不冲突；
	增加nginx配置
	upstream backend {
	ip_hash;
	server 127.0.0.1:8180 weight=10;
	server 127.0.0.1:8280 weight=10;
	server 127.0.0.1:8380 weight=10;
}

