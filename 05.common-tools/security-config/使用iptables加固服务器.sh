iptables -F 
iptables -X	
iptables -L -n
/etc/rc.d/init.d/iptables save
service iptables restart 
iptables -A INPUT -p tcp --dport 22 -j ACCEPT
iptables -A INPUT -p tcp --dport 40022 -j ACCEPT
iptables -A INPUT -p icmp -j ACCEPT
iptables -A INPUT -p udp --sport 53 -j ACCEPT
/sbin/iptables -I INPUT -p tcp --dport 80 -j ACCEPT 
iptables -A INPUT -i lo -j ACCEPT   
iptables -A OUTPUT -o lo -j ACCEPT 
iptables -A INPUT -m state --state ESTABLISHED,RELATED -j ACCEPT
iptables -A INPUT -s 192.168.0.1 -j ACCEPT 
iptables -A INPUT -s 192.168.0.2 -j ACCEPT
iptables -A INPUT -s 192.168.0.3 -j ACCEPT
iptables -A INPUT -s 192.168.0.4 -j ACCEPT
iptables -A INPUT -s 192.168.0.5 -j ACCEPT

iptables -P INPUT DROP  

/etc/rc.d/init.d/iptables save
service iptables restart
