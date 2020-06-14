<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ include file= "/view/color.jspf" %>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<center><b>글목록</b>
<table>
<tr>
<td>
<c:out value="${count}"/>
</td>
</tr>
</table>
<table width="700">
<tr>
<td><a href="/BoardMvc3Ora/writeForm.do">글쓰기</a></td>
</tr>
</table>
<c:if test="${count==0}">
<table width="700">
<tr>
<td align = "center">
등록 된 게시글이 없습니다.
</td>
</tr>
</table>
</c:if>

<c:if test="${count > 0}">
<table width="800">
<tr>
<td align="center" width="50">번 호</td>
<td align="center" width="250">제      목</td>
<td align="center" width="100">작성자</td>
<td align="center" width="150">작성일</td>
<td align="center" width="50">조회수</td>
<td align="center" width="100">IP</td>
</tr>

<c:forEach var="bean" items="${list}">
<tr height="30">
<td align="center" width="50">
<c:out value="${number}"/>
<c:set var = "number" value="${number-1}"/>
</td>
<td width="250" align="center">
<a href="/BoardMvc3Ora/content.do?num=${bean.num}&pageNum=${nowPage}">
${bean.subject}</a>
</td>
<td align="center" width="100">
<a href="mailto:${bean.email}">${bean.writer}</a>
</td>
<td align="center" width="250">${bean.reg_date}</td>
<td align="center" width="50">${bean.readcount}</td>
<td align="center" width="100">${bean.ip}</td>
</tr>
</c:forEach>
</table>
</c:if>
</center>

</body>
</html>