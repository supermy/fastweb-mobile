oracle将来将来再架构里面用来与事务相关的业务；
利用好oracle的分区机制；
利用好oracle的dblink机制和谓语机制；

1.从官网下载最新版的oracle12c;
2.执行install_oracle配置默认安装环境；
3.检查.bash_profile与给定的配置文件是否一致；
4.按照【oracle的静默安装.txt】的步骤进行静默安装,并且创建数据库和建立数据库用户；
https://fastweb-mobile.googlecode.com/svn/trunk/05.common-tools/oracle-install/


mkdir /home/data/
chown oracle:oinstall /home/data/    

./runInstaller oracle.install.option=INSTALL_DB_SWONLY \
    ORACLE_BASE=/home/data/oracle/product/base \
    ORACLE_HOME=/home/data/oracle/product/base/12.1.0.1 \
    UNIX_GROUP_NAME=oinstall \
    oracle.install.db.DBA_GROUP=dba \
    oracle.install.db.OPER_GROUP=oper \
    oracle.install.db.BACKUPDBA_GROUP=dba \
    oracle.install.db.DGDBA_GROUP=dba \
    oracle.install.db.KMDBA_GROUP=dba \
    oracle.install.db.config.starterdb.characterSet=AL32UTF8 \
    oracle.install.db.config.starterdb.memoryOption=true \
    FROM_LOCATION=../stage/products.xml \
    INVENTORY_LOCATION=/home/data/oracle/oraIventory \
    SELECTED_LANGUAGES=en,zh_CN \
    oracle.install.db.InstallEdition=EE \
    DECLINE_SECURITY_UPDATES=true  -silent -ignoreSysPrereqs -ignorePrereq -waitForCompletion
 
 
    sed -n '/^[^#]/p' response/netca.rsp


        1. /home/data/oracle/oraIventory/orainstRoot.sh
        2. /home/data/oracle/product/base/12.1.0.1/root.sh
安装监听
netca -silent -responseFile /home/oracle/database/response/netca.rsp  
安装实例数据库
dbca -silent -createDatabase -responseFile /home/oracle/database/response/dbca.rsp 
sys/sys  system/system

全局数据库名:orcl12c.us.oracle.com
系统标识符 (SID):orcl12c


lsnrctl status
 


