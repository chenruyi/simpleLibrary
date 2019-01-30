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
<title>用户</title>

</head>
<body>
<div class="top">
<p> 欢迎 </p> 
<p id="userid" ></p> <p id="puserId"><jsp:getProperty name="user" property="userId" /> </p>
<a href="logout"> 退出</a>
</div>
<div >
	<div class="navbox">
	<ul class="nav">
	<li> <a href="javascript:void(0)" onClick="selectBook()">查询图书</a></li>
	<li> <a href="javascript:void(0)" onClick="getBorrowBook()">已借图书</a>
	<li> <a href="javascript:void(0)" onClick="borrowBook()">借书</a>
	
	<li> <a href="javascript:void(0)" onClick="changePassword()">修改密码</a>
	<li> <a href="javascript:void(0)" onClick="changeEmail()">修改邮箱</a>
	</ul>
	</div>
	<div id="content" class="content">
	
	</div>
</div>
</body>
</html>