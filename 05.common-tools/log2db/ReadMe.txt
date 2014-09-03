1.web是集群，nginx日志分布在不同的服务器上；
2.建立共享目录，在多台服务器之间共享；
3.建立指定用户deploy的定时任务,凌晨4点同步日志【日志时间是每天凌晨3天生成一个文件】
4.建立指定用户oracle的定时任务,凌晨5点导入日志到数据库[oracle and mongodb]
5.配置nginx日志json格式
6.配置nginx日志分卷
7.日志查询
	oracle使用plsql
	mongodb使用rockmongo
8.使用rockmongo进行mongodb的查询
http://rockmongo.com/wiki/installation?lang=zh_cn
