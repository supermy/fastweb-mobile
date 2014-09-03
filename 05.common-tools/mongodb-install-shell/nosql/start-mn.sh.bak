sh /home/mymongo/mongodb-shard/start-shard.sh stop
sh /home/mymongo/mongodb-shard/start-shard.sh clean
sh sync-mongodb.sh 100.0.1.2 stop
sh sync-mongodb.sh 100.0.1.2 clean
sh sync-mongodb.sh 100.0.1.3 stop
sh sync-mongodb.sh 100.0.1.3 clean
sh sync-mongodb.sh 100.0.1.4 stop
sh sync-mongodb.sh 100.0.1.4 clean
sh sync-mongodb.sh 100.0.1.5 stop
sh sync-mongodb.sh 100.0.1.5 clean


sh /home/mymongo/mongodb-shard/start-shard.sh shard1 
sh sync-mongodb.sh 100.0.1.2 shard1
sh sync-mongodb.sh 100.0.1.3 shard2
sh sync-mongodb.sh 100.0.1.5 shard2
sh sync-mongodb.sh 100.0.1.4 config
sh sync-mongodb.sh 100.0.1.4 route 100.0.1.4

#ssh 100.0.1.2 "sh /home/mymongo/mongodb-shard/start-shard.sh  config"
#ssh 100.0.1.3 "sh /home/mymongo/mongodb-shard/start-shard.sh  config"
#ssh 100.0.1.5 "sh /home/mymongo/mongodb-shard/start-shard.sh  config"

#sh /home/mymongo/mongodb-shard/start-shard.sh  route
