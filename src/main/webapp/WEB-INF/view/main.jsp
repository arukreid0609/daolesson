<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User,java.util.List" %>
<%
List<User> list = (List<User>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DAOの練習</title>
</head>
<body>
	<h1>DAOパターンの練習</h1>
	<form action="Main" method="post">
		名前：<input type="text" name="name"><br>
		年齢：<input type="number" name="age"><br>
		<input type="submit" value="送信">
	</form>
	<table border="">
		<tr><th>名前</th><th>年齢</th></tr>
		<% for(User user : list){ %>
			<tr><td><%= user.getName() %></td><td><%= user.getAge() %></td></tr>
		<% } %>
	</table>
	<script src="test.js"></script>
</body>
</html>