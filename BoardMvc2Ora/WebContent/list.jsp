<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ include file= "/view/color.jspf" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body bgcolor="${bodyback_c}">
<center><b>글목록(전체 글:${count})</b></center>
<table width="700">
<tr>
<td align="right" bgcolor="${value_c}">
<a href="BoardMvc2Ora/writeForm.do">글쓰기</a>
</td>
</tr>
</table>

<c:if test="${count==0}">
<table width="700" border="1" cellpadding="0" cellspacing="0">
<tr>
<td align="center">
게시판에 저장된 글이 없습니다.
</td>
</tr>
</table>
</c:if>

<c:if test="${count>0}">
<table  width="700" border="1" cellpadding="0" cellspacing="0" align="center">
<tr height = "30" bgcolor="${value_c}">
<td align="center" width="50">번 호</td>
<td align="center" width="250">제     목</td>
<td align="center" width="100">작성자</td>
<td align="center" width="150">작성일</td>
<td align="center" width="50">조 회</td>
<td align="center" width="100">IP</td>
</tr>

<c:forEach var="article" items="${articleList}">

</c:forEach>
</table>
</c:if>
</body>
</html>