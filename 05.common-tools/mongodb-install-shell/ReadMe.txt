快速部署mongodb的分布式集群
1.首先安装moggodb；
2.将当前脚本复制到/home/mymongo/目录下；.bash_profile文件内容添加到当前用户配置文件；
3.机器集群的mymongo用户可以无密码登陆；
4.start-mn.sh启动mongodb分布式集群
5.访问集群 mongo 192.168.0.4 -port 30000 
6.系统的优化和分片机制的激活步骤详见:config-step.txt
7.mongodb数据统计 MapReduce的使用详见：mongodb-统计分析脚本.txt

https://fastweb-mobile.googlecode.com/svn/trunk/05.common-tools/mongodb-install-shell/