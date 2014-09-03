# .bash_profile

# Get the aliases and functions
if [ -f ~/.bashrc ]; then
	. ~/.bashrc
fi

# User specific environment and startup programs

PATH=$PATH:$HOME/bin

export PATH
###############define_for_oracle############################
# Oracle Settings
TMP=/tmp; export TMP
TMPDIR=$TMP; export TMPDIR


PATH=$PATH:$HOME/bin
 
export PATH
 
ORACLE_SID=orcl12c.us.oracle.com
NLS_LANG="Simplified Chinese_china".UTF8; export NLS_LANG
ORACLE_BASE=/home/data/oracle/product/base
ORACLE_HOME=$ORACLE_BASE/12.1.0.1
TNS_ADMIN=$ORACLE_HOME/network/admin
ORA_NLS33=$ORACLE_HOME/ocommon/nls/admin/data
PATH=$PATH:$ORACLE_HOME/bin:$ORACLE_HOME/oracm/bin:$ORACLE_HOME/OPatch:$ORACLE_HOME/jdbc
LD_LIBRARY_PATH=$ORACLE_HOME/lib:$ORACLE_HOME/ctx/lib:$ORACLE_HOME/oracm/lib:/usr/lib
CLASSPATH=$ORACLE_HOME/JRE:$ORACLE_HOME/jlib:$ORACLE_HOME/rdbms/jlib:$ORACLE_HOME/network/jlib:$ORACLE_HOME/jdbc/lib
SQLPATH=$ORACLE_HOME/sqlplus/admin:/home/oracle/admin/sql:$ORACLE_HOME/rdbms/admin
export ORACLE_BASE ORACLE_HOME ORA_NLS33 ORACLE_SID PATH LD_LIBRARY_PATH CLASSPATH TNS_ADMIN SQLPATH
NLS_DATE_FORMAT="yyyy-mm-dd hh24:mi:ss";
export NLS_DATE_FORMAT
 

export LANG="zh_CN.UTF-8"
alias sqlplus="rlwrap sqlplus"

if [ $USER = "oracle" ]; then
 if [ $SHELL = "/bin/ksh" ]; then
    ulimit -p 16384
    ulimit -n 65536
 else
    ulimit -u 16384 -n 65536
 fi
fi

