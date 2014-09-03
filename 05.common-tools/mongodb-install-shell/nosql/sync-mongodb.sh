#server='192.168.0.2'
server=$1
echo $server
ssh $server "mkdir -p /home/mymongo/mongodb-shard/"
rsync -avz -e ssh /home/mymongo/.bash_profile $server:/home/mymongo/.bash_profile 
rsync -avz -e ssh /home/mymongo/mongodb-shard/start-shard.sh  $server:/home/mymongo/mongodb-shard/start-shard.sh
ssh $server "sh /home/mymongo/mongodb-shard/start-shard.sh $2 $3"
#ssh $server "sh /home/mymongo/mongodb-shard/start-shard.sh  config"
#ssh $server "sh /home/mymongo/mongodb-shard/start-shard.sh  route"
