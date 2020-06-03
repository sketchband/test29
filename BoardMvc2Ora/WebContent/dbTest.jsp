<%@page import="java.sql.Connection"%>
<%@page import="my.controller.BoardDBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	BoardDBBean bean = BoardDBBean.getInstance();
	java.sql.Connection conn = bean.getconConnection();
%>
<%=conn.toString() %>

</body>
</html>