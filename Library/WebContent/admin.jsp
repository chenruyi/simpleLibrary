<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="user" class="com.demo.bean.UserBean" scope="session" />
    
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/style.css">
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"> </script>
<script src="js/demo.js"></script>   
<title>管理员</title>

</head>
<body>
<p> 欢迎 <jsp:getProperty name="user" property="userId" /> </p>
<a href="logout"> 退出</a>
<div >
	<div class="navbox">
	<ul class="nav">
	<li> <a href="javascript:void(0)" onClick="selectBook()">查询图书</a></li>
	<li> <a href="javascript:void(0)" onClick="addBook()">增加图书</a>
	<li> <a href="javascript:void(0)" onClick="deleteBook()">删除图书</a></li>
	<li> <a href="javascript:void(0)" onClick="addUser()">增加用户</a></li>
	<li> <a href="javascript:void(0)" onClick="deleteUser()">删除用户</a></li>
	<li> <a href="javascript:void(0)" onClick="selectUser()">查询用户</a></li>
	<li> <a href="javascript:void(0)" onClick="getAllBorrowBook()"> 查询借出情况</a></li>
	<li> <a href="javascript:void(0)" onClick="returnBook()">归还图书</a></li>
	</ul>
	</div>
	<div id="content" class="content">
	
	</div>
	
</div>

</body>
</html>