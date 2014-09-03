sh /home/mymongo/mongodb-shard/start-shard.sh stop
sh /home/mymongo/mongodb-shard/start-shard.sh clean
sh sync-mongodb.sh 192.168.0.2 stop
sh sync-mongodb.sh 192.168.0.2 clean
sh sync-mongodb.sh 192.168.0.3 stop
sh sync-mongodb.sh 192.168.0.3 clean
sh sync-mongodb.sh 192.168.0.4 stop
sh sync-mongodb.sh 192.168.0.4 clean
sh sync-mongodb.sh 192.168.0.5 stop
sh sync-mongodb.sh 192.168.0.5 clean


sh /home/mymongo/mongodb-shard/start-shard.sh shard1 
sh sync-mongodb.sh 192.168.0.2 shard1
sh sync-mongodb.sh 192.168.0.3 shard2
sh sync-mongodb.sh 192.168.0.5 shard2
sh sync-mongodb.sh 192.168.0.4 config
sh sync-mongodb.sh 192.168.0.4 route 192.168.0.4

#ssh 192.168.0.2 "sh /home/mymongo/mongodb-shard/start-shard.sh  config"
#ssh 192.168.0.3 "sh /home/mymongo/mongodb-shard/start-shard.sh  config"
#ssh 192.168.0.5 "sh /home/mymongo/mongodb-shard/start-shard.sh  config"

#sh /home/mymongo/mongodb-shard/start-shard.sh  route
