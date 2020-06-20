<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
ddddddddddd
<table>
<tr>
<td>${bean.num}</td>
</tr>
<tr>
<td>${bean.writer}</td>
</tr>
<tr>
<td>${bean.subject}</td>
</tr>
<tr>
<td><input type="button" value="수정" onclick="location.href='updateForm.do?num=${bean.num}'"></td>
<td><input type="button" value="삭제" onclick="location.href='delete.do?num=${bean.num}'"></td>
</tr>
</table>
</body>
</html>