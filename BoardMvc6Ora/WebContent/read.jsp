<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<table>
<tr>
<td><c:out value="${bean.subject}"></c:out></td>
</tr>
<tr>
<td><c:out value="${bean.writer}"></c:out></td>
</tr>
<tr>
<td><c:out value="${bean.email}"></c:out></td>
</tr>
<tr>
<td><c:out value="${bean.reg_date}"></c:out></td>
</tr>
<tr>
<td><c:out value="${bean.content}"></c:out></td>
</tr>
<tr>
<td><c:out value="${bean.num}"></c:out></td>
</tr>
<tr>
<td>
<input type="button" value="수정" onclick="location.href='updateForm.do?num=${bean.num}'">
</td>
</tr>
</table>
</center>
</body>
</html>