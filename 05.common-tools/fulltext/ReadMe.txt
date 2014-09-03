https://fastweb-mobile.googlecode.com/svn/trunk/05.common-tools/fulltext/

http://127.0.0.1:8983/solr/
http://127.0.0.1:8983/solr/browse


1.配置更改,按照自己的路径更改之后才能正常运行
05.common-tools\fulltext\apache-tomcat-7.0.47\webapps\solr\WEB-INF\web.xml
更改 
<env-entry>
       
	<env-entry-name>solr/home</env-entry-name>
       
	<env-entry-value>I:/env-myopensource/fastweb-mobile/05.common-tools/fulltext/solr-home</env-entry-value>
       	<env-entry-type>java.lang.String</env-entry-type>
    
</env-entry>
更改
   <lib dir="I:/env-myopensource/fastweb-mobile/05.common-tools/fulltext/solr-home/bin/contrib/extraction/lib" regex=".*\.jar" />
  <lib dir="I:/env-myopensource/fastweb-mobile/05.common-tools/fulltext/solr-home/bin/dist/" regex="solr-cell-\d.*\.jar" />

  <lib dir="I:/env-myopensource/fastweb-mobile/05.common-tools/fulltext/solr-home/bin/contrib/clustering/lib/" regex=".*\.jar" />
  <lib dir="I:/env-myopensource/fastweb-mobile/05.common-tools/fulltext/solr-home/bin/dist/" regex="solr-clustering-\d.*\.jar" />

  <lib dir="I:/env-myopensource/fastweb-mobile/05.common-tools/fulltext/solr-home/bin/contrib/langid/lib/" regex=".*\.jar" />
  <lib dir="I:/env-myopensource/fastweb-mobile/05.common-tools/fulltext/solr-home/bin/dist/" regex="solr-langid-\d.*\.jar" />

  <lib dir="I:/env-myopensource/fastweb-mobile/05.common-tools/fulltext/solr-home/bin/contrib/velocity/lib" regex=".*\.jar" />
  <lib dir="I:/env-myopensource/fastweb-mobile/05.common-tools/fulltext/solr-home/bin/dist/" regex="solr-velocity-\d.*\.jar" />

2.中文分词配置完成，fieldtype:text；
测试地址http://localhost:8983/solr/#/collection1/analysis

词库扩展IKAnalyzer.cfg.xml（搜狗词库，google拼音词库，自定义扩展词库）
<properties>  
	<comment>IK Analyzer 扩展配置</comment>
	<!--用户可以在这里配置自己的扩展字典 -->
	<entry key="ext_dict">SogouLabDic.dic;mygoogle.dic;ext.dic;</entry> 
	
	<!--用户可以在这里配置自己的扩展停止词字典-->
	<entry key="ext_stopwords">stopword.dic;</entry> 
	
</properties>


3.增加文档测试
测试地址http://localhost:8983/solr/#/collection1/documents

4.查询测试
http://localhost:8983/solr/#/collection1/query

5.数据库定时导入数据

solrconfig.xml
<requestHandler name="/dataimport" class="org.apache.solr.handler.dataimport.DataImportHandler">
	<lst name="defaults">
		<str name="config">data-config.xml</str>
	</lst>
</requestHandler>


data-config.xml
<dataConfig>
    <dataSource driver="oracle.jdbc.driver.OracleDriver" url="jdbc:oracle:thin:@127.0.0.1:1528:orcl" user="gbjwo" password="gbjwo"/>
    <document>
		<!--transformer="ClobTransformer" 
		deltaImportQuery="select * from VIEW_DETAIL where DETAIL_ID='${dih.delta.DETAIL_ID}'"
        deltaQuery="select DETAIL_ID from VIEW_DETAIL where PUBLISH_TIME &gt; to_date('${dih.last_index_time}','yyyy-mm-dd hh:mi:ss')"
		-->
        <entity name="contact" query="select pkid id,name from contact">
            <field column="TEXT" name="id"/>
            <field column="TEXT" name="name"/>
			<!--clob="true"-->
		</entity>
    </document>
</dataConfig>

批量导入
http://localhost:8983/solr/dataimport?command=full-import&commit=true 
增量导入
http://localhost:8983/solr/dataimport?command=delta-import&commit=true


todo 任务
1.solr搜索智能提示Suggest
2.solr 对拼音搜索和拼音首字母搜索的支持
3.产品如何使用SOLR
a.搜索引擎规划设计
a1 定制好业务模型
a2 定制好索引结构
a3 定制好搜索策略
b.搜索引擎配置
根据搜索引擎的规划，配置solr的schema.xml等配置文件。
c.构建索引并定时更新索引
通过调用索引接口进行索引的构建与更新。
d.搜索
通过调用搜索接口进行搜索。
e.定期维护和改进
