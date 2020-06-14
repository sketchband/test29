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

<% int num = Integer.parseInt(request.getParameter("num"));
   int pageNum = Integer.parseInt(request.getParameter("pageNum"));
%>
<center>
<table width="500">
<tr>
<td>제목</td>
<td><c:out value="${bean.subject}"/></td>
</tr>
<tr>
<td>작성자</td>
<td><c:out value="${bean.writer}"/></td>
</tr>
<tr>
<td>이메일</td>
<td><c:out value="${bean.email}"/></td>
</tr>
<tr>
<td>날짜</td>
<td><c:out value="${bean.reg_date}"/></td>
<td>조회수</td>
<td><c:out value="${bean.readcount}"/></td>
</tr>
<tr>
<td>내용</td>
<td><c:out value="${bean.content}"/></td>
</tr>
<tr>
<td>
<input type="button" value="답글" onclick="#">
<input type="button" value="수정" onclick="location.href='updateForm.do?num=${bean.num}&pageNum=${pageNum}'">
<input type="button" value="삭제" onclick="location.href='deleteForm.do?num=${bean.num}&pageNum=${pageNum}'">
<input type="button" value="목록" onclick="location.href='list.do'">
</table>
</center>
</body>
</html>