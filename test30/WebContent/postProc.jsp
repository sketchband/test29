<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dao" class="test30.BoardDAO"/>
<jsp:useBean id="bean" class="test30.BoardBean"/>
<jsp:setProperty property="*" name="bean"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	dao.postProc(bean);
%>
<script>
location.href="list.jsp";
</script>
</body>
</html>