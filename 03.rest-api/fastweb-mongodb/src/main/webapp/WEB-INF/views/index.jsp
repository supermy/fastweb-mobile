<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<myweb:override name="head">
	<title>增删改查</title>

</myweb:override>

<myweb:override name="leftcontent">
   <div id="crud-grid">
   
   		hello,world
   		
   		<br/>
   		
		
   </div> 
   
</myweb:override>

<myweb:override name="rightcontent">

<script src="http://127.0.0.1/jqm/js/jquery-1.9.1.min.js"></script>
<script src="http://127.0.0.1/jqm/js/json2.js"></script>

<script>
//返回结果处理
function callback(data){
	console.debug("此方法可以预定义......");
	console.debug(data);
}

//提交数据基础方法
function sendData(jsonurl,jsondata,ajaxtype,callback) {
	$.ajax({
	    type:ajaxtype,
	    url: jsonurl,
	    data:JSON.stringify(jsondata), // or JSON.stringify ({name: 'jonas'}),//data:data
	    success: callback,
	    contentType: "application/json",
	    dataType: 'json'
	});    
}

//POST方式提交数据
function postData(url,data) {
	sendData(url,data,"POST",callback);
}

//更新数据
function updateData(url,data) {
	sendData(url,data,"PUT",callback);
}

//删除数据
function deleteData(url) {
	sendData(url,{},'DELETE',callback);
}
//批量删除数据
function deletelist(url,data) {
	sendData(url,data,'DELETE',callback);
}



server_url='http://127.0.0.1:8080/fastweb-mongodb/';

menu_url=server_url+'menu/';
menu_url_hashmap=server_url+'menu/create/';

//需要更新的数据可以 $("form").serialize() 组织
data= '{"subtitle":"jonas"}';
arr= {"subtitle":"jonas"};

put_url=server_url+'menu/51edfadfe4bb86d3b4c13c81';
updata={"subtitle":"me update"};

delete_url=server_url+'menu/51e94db96d60735192a2b3fe';


delete_list_url=server_url+'menu/delete';
deldata=["51e94fb56d60735192a2b3ff"];



</script>

   <div id="crud-grid">
   
   		<a href="http://localhost:8080/fastweb-mongodb/menu/list/0/100.jsonp?callback=?">分页查询所有菜单</a>
   		<a href="http://localhost:8080/fastweb-mongodb/menu/51dbbcc517f416991a71775a.jsonp?callback=?">获取菜单</a>
   		<br/>
   		<a href="http://localhost:8080/fastweb-mongodb/menu/add.jsonp?callback=?">新增菜单</a>
   		<!-- nginx 反响影射解决将前端和后端整合到一个域，解决跨域post提交的问题-->
   		<a href="#" onclick="postData(menu_url,arr);">创建菜单</a>
   		<a href="#" onclick="postData(menu_url_hashmap,arr);">创建菜单hashmap</a>
   		<br/>
   		<a href="http://localhost:8080/fastweb-mongodb/menu/51e7d484ad17a136d4ddb625/edit.jsonp?callback=?">编辑菜单</a>
   		<a onclick="updateData(put_url,updata);" href="#">更新菜单</a>
   		<a onclick="deleteData(delete_url);" href="#">删除菜单</a>
   		<a onclick="deletelist(delete_list_url,deldata);" href="#">删除列表菜单</a>
   		
   		
 		<br/>
   		<a href="http://localhost:8080/fastweb-mongodb/person/list/2/2">用户分页查询</a>
   		<a href="http://localhost:8080/fastweb-mongodb/person/list/2/2.jsonp?callback=aa">用户分页查询jsonp,支持跨域</a>
   		
		
   </div> 
   
</myweb:override>

<%@ include file="/WEB-INF/views/base.jsp" %>