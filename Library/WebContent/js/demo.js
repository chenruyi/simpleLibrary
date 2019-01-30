/**
 * 
 */

function deleteUser() {
	var s = "<form > " + "用户名" + "<input id =\"userId\" type=\"text\" >"
			+ "<input type=\"submit\" required >" + "</form>"
	// add form
	$("#content").empty();
	$("#content").append(s);
	$("form").submit(function() {
		event.preventDefault();
		$.post("UserServlet", {
			"Operation" : "DELETE",
			"userId" : $("#userId").val(),
		}, function(result) {
			alert(result);
			// $("#content").append(result);

		});
	});

}

function selectUser() {
	var s = "<form id=\"serachForm\"  > " + "用户名"
			+ "<input id =\"userId\" name=\"userId\" type=\"text\" required  >"
			+ "<input type=\"submit\" required >" + "</form>"
	// add form
	$("#content").empty();
	$("#content").append(s);
	$("#serachForm").submit(
			function(event) {
				event.preventDefault();
				$.post("UserServlet", {
					"Operation" : "SELECT",
					"userId" : $("#userId").val(),
				}, function(result) {
				
					var rs = $("#tablers");
					if (rs.size() === 0) {
						s = "<table id=\"tablers\" class=\"table-7\"></table>";
								
						$("#content").append(s);
						rs = $("#tablers");
					}else{
						rs.empty();
					}
					
					
					var res = JSON.parse(result);
					if(res.hasOwnProperty("result"))
					{    i = res["result"];
					var tmps = "<tr><th>用户名 </th> <th> 邮箱</th></tr>";
						 {
						tmps += "<tr><td>" + i["userId"]
								+ "</td><td>"
								+  i["email"]
								
								+ "</td></tr>";
					}
					rs.append(tmps);
					}
					else{
						alert("没找到");
					}
					
				});
			});
}

function selectBook() {
	var s = "<form id=\"serachForm\"> " + "书名"
			+ "<input id =\"title\" name=\"title\" type=\"text\" required >"
			+ "<input type=\"submit\" required >" + "</form>";
	// add form
	$("#content").empty();
	$("#content").append(s);
	$("#serachForm").submit(
	function(event) {
	event.preventDefault();
	$.post("BookServlet",
	{ "Operation" : "SELECT",
	"title" : $("#title").val(),},
		function(result) {
		var rs = $("#tablers");
		if (rs.size() === 0) {
		 var s = "<table id=\"tablers\" class=\"table-7\"> </table>"
		
				$("#content").append(s);
				rs = $("#tablers");
			}else{
				rs.empty();
			}
			var res = JSON.parse(result);
			if(res.hasOwnProperty("result"))
			{    i = res["result"];
			var tmps = "<tr><th>书籍编号 </th> <th> 标题</th> <th>作者</th><th>出版社</th></tr>";
			
				 {
				tmps += "<tr><td>" + i["bookid"]
						+ "</td><td>"
						+  i["title"]
						+ "</td><td>"
						+  i["author"]
						+ "</td><td>"
						+  i["publisher"]
						+ "</td><tr>";
			}
			rs.append(tmps);
			}
			else{
				alert("没找到");
			}
		});
});

}

function addUser() {
	var s = "<form id=\"serachForm\"  > " + "用户名"
			+ "<input id =\"userId\" type=\"text\" required >" + "密码"
			+ "<input id=\"password\" name=\"password\" type=\"password\" required >"
			+ "邮箱" + "<input id=\"email\" name=\"email\" type=\"text\" required >"
			+ "<input type=\"submit\">" + "</form>"

	// add form
	$("#content").empty();
	$("#content").append(s);
	$("#serachForm").submit(function(event) {
		event.preventDefault();
		$.post("UserServlet", {
			"Operation" : "INSERT",
			"userId" : $("#userId").val(),
			"password" : $("#password").val(),
			"email" : $("#email").val(),
		}, function(result) {
			alert(result);
			// $("#content").append(result);

		});
	});

}

function addBook() {
	var s = "<form id=\"serachForm\" > " + "编号"
			+ "<input id=\"bookid\" name=\"bookid\" type=\"text\" required > " + "书名"
			+ "<input id =\"title\" name =\"title\" type=\"text\" required > " + "作者"
			+ "<input id=\"author\" name=\"author\" type=\"text\" required >" + "出版社"
			+ "<input id=\"publisher\" name=\"publisher\" type=\"text\" required >"
			+ "<input type=\"submit\">" + "</form>"
	// add form
	$("#content").empty();
	$("#content").append(s);
	// add form
	$("#content").empty();
	$("#content").append(s);
	$("#serachForm").submit(function(event) {
		event.preventDefault();
		$.post("BookServlet", {
			"Operation" : "INSERT",
			"title" : $("#title").val(),
			"bookid" : $("#bookid").val(),
			"author" : $("#author").val(),
			"publisher" : $("#publisher").val(),

		}, function(result) {
			// $("#content").append(result);
			alert(result);
		});
	});

}

function deleteBook() {
	var s = "<form id=\"deletebookForm\" > " + "编号"
			+ "<input id =\"bookid\" name=\"bookid\" type=\"text\" required >"
			+ "<input type=\"submit\">" + "</form>";
	// add form
	$("#content").empty();
	$("#content").append(s);
	$("#deletebookForm").submit(function(event) {
		event.preventDefault();
		$.post("BookServlet", {
			"Operation" : "DELETE",
			"bookid" : $("#bookid").val(),

		}, function(result) {
			alert(result);
			// $("#content").append(result);

		});
	});
}

function getBorrowBook() {
	$("#content").empty();
	event.preventDefault();

	$.post("BorrowedBooksServlet", {
		"userId" : getUserId(),
		"Operation" : "SELECT",
	}, function(result) {
	
			var rs = $("#tablers");
			if (rs.size() === 0) {
				s = "<table id=\"tablers\" class=\"table-7\" > </table>";
						+
				$("#content").append(s);
				rs = $("#tablers");
			}else{
				rs.empty();
			}
			
			
			var res = JSON.parse(result);
			if(res.hasOwnProperty("result")){
				
				var tmps = "<tr><th>书籍编号 </th> <th> 借出者</th> <th>借出时间</th></tr>";
				
				for ( var i in res["result"]) {
				tmps += "<tr><td>" + res["result"][i]["bookid"] + "</td><td>" + res["result"][i]["userId"]
				+ "</td><td>" + res["result"][i]["time"] + "</td><tr>";
				}
				rs.append(tmps);
			}else{
				alert("没找到");
			}


	});
}

function getAllBorrowBook() {
	$("#content").empty();
	event.preventDefault();
	$.post("BorrowedBooksServlet", {"userId" : "*",	"Operation" : "SELECTALL",},
			function(result) {
		var rs = $("#tablers");
		if (rs.size() === 0) {
			s = "<table id=\"tablers\" class=\"table-7\">"
					+"<tr><th>书籍编号 </th> <th> 借出者</th> <th>借出时间</th></tr></table>";
			$("#content").append(s);
			rs = $("#tablers");
		}else{
			rs.empty();
		}
		var res = JSON.parse(result);
		if(res.hasOwnProperty("result")){   
			var tmps = "";
			for ( var i in res["result"]) {
			tmps += "<tr><td>" + res["result"][i]["bookid"] + "</td><td>" + res["result"][i]["userId"]
			+ "</td><td>" + res["result"][i]["time"] + "</td><tr>";
			}
			rs.append(tmps);
		}else{alert("没找到");}
	
	});

}

function returnBook() {
	var s = "<form id=\"bookForm\" > "
			+ "编号"
			+ "<input id =\"bookid\" name=\"bookid\" type=\"text\" required >"
			+ "<input type=\"submit\" required >"
			+ "</form>";
	// add form
	$("#content").empty();
	$("#content").append(s);
	$("#bookForm").submit(function(event) {
		event.preventDefault();
		$.post("BorrowedBooksServlet", {
			"bookid" : $("#bookid").val(),
			"Operation" : "DELETE",
		}, function(result) {

			alert(result);
			// $("#content").append(result);

		});
	});
}
function borrowBook() {
	var s = "<form id=\"bookForm\" > "
			+ "编号"
			+ "<input id =\"bookid\" name=\"bookid\" type=\"text\" required >"
			+ "<input type=\"submit\">"
		+ "</form>";
	// add form
	$("#content").empty();
	$("#content").append(s);
	$("#bookForm").submit(function(event) {
		event.preventDefault();
		$.post("BorrowedBooksServlet", {
			"bookid" : $("#bookid").val(),
			"userId" : getUserId(),
			"Operation" : "INSERT",
		}, function(result) {
			alert(result);
			// $("#content").append(result);

		});
	});
}

function changePassword() {
	var s = "<form id=\"changeForm\"  > "
			+ "原密码"
			+ "<input id=\"oldpassword\" name=\"oldpassword\" type=\"password\" required >"
			+ "新密码"
			+ "<input id=\"newpassword1\" name=\"newpassword1\" type=\"password\" required >"
			+ "再次确认"
			+ "<input id=\"newpassword2\" name=\"newpassword2\" type=\"password\" required >"
			+ "<input type=\"submit\" >" + "</form>"

	$("#content").empty();
	$("#content").append(s);
	$("#changeForm").submit(function(event) {
		event.preventDefault();
		var newpwd1 = $("#newpassword1").val();
		var newpwd2 = $("#newpassword2").val();
		
		if (newpwd1 === newpwd2) {
			$.post("UserServlet", {
				"Operation" : "CHANGEPASSWORD",
				"userId" : getUserId(),
				"oldpassword" : $("#oldpassword").val(),
				"newpassword" : newpwd1,

			}, function(result) {
				location.reload();
				
			});
		} else {
			alert("密码不相同");
		}
	});
}

function changeEmail() {
	var s = "<form id=\"changeForm\"  > " + "新邮箱"
			+ "<input id=\"email\" name=\"email\" type=\"text\" required >"
			+ "<input type=\"submit\">" + "</form>"

	// add form
	$("#content").empty();
	$("#content").append(s);
	$("#changeForm").submit(function(event) {
		event.preventDefault();
		$.post("UserServlet", {
			"Operation" : "CHANGEEMAIL",
			"userId" : getUserId(),
			"email" : $("#email").val(),

		}, function(result) {

			alert(result);
		});
	});

}

function getUserId() {

	return $("#puserId").text();
}
