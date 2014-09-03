#bash 
if [ "$1"x = x ]; then
	echo "require parameter......"        
        exit 1
fi

if [ "$1"x = "stop"x ]; then
	ps -ef|grep "mongodb-shard"|grep -v grep|awk '{print $2}'|xargs -I @ kill -9 @
	exit 1
fi

if [ "$1"x = "clean"x ]; then
        echo "clear data begin"
        rm /home/mymongo/mongodb-shard/data/ -rf
        mkdir -p /home/mymongo/mongodb-shard/data/db/shard1
        mkdir -p /home/mymongo/mongodb-shard/data/db/shard2
        mkdir -p /home/mymongo/mongodb-shard/data/db/config
        mkdir -p /home/mymongo/mongodb-shard/data/logs
        echo "clear data end"
        exit 1
fi
	
test=$1
echo "do...:"$test

if [ "$test"x = "shard1"x ]; then
mongod --rest -shardsvr -replSet shard1 -port 27021 -dbpath /home/mymongo/mongodb-shard/data/db/shard1  -oplogSize 10000 -logpath  /home/mymongo/mongodb-shard/data/logs/shard1.log -logappend  --maxConns 10000 --quiet -fork --directoryperdb

sleep 2
fi

if [ "$test"x = "shard2"x ]; then
mongod --rest -shardsvr -replSet shard2 -port 27022 -dbpath /home/mymongo/mongodb-shard/data/db/shard2  -oplogSize 10000 -logpath  /home/mymongo/mongodb-shard/data/logs/shard2.log -logappend  --maxConns 10000 --quiet -fork --directoryperdb

ps aux |grep mongo
echo "all mongo started."

fi

if [ "$test"x = "config"x ]; then

mongod --configsvr --dbpath  /home/mymongo/mongodb-shard/data/db/config --port 20000 --logpath /home/mymongo/mongodb-shard/data/logs/config.log --logappend --fork --directoryperdb

ps aux |grep mong 
echo "mongo config start."

fi


if [ "$test"x = "route"x ]; then

#mongos -configdb 100.0.1.2:20000,100.0.1.3:20000,100.0.1.5:20000 -port 30000 -chunkSize 50 -logpath /home/mymongo/mongodb-shard/data/logs/route.log -logappend -fork
mongos -configdb $2:20000 -port 30000 -chunkSize 50 -logpath /home/mymongo/mongodb-shard/data/logs/route.log -logappend -fork

ps aux |grep mong
echo "mongo route start."

fi
