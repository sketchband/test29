<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
</head>
<body>
<center><h2>게시글 작성</h2>
<form name="reg" method="post" action="/BoardMvc5Ora/postPro.do">
<input type="hidden" name = "num" value="${num}">
<input type="hidden" name = "ref" value="${ref}">
<input type="hidden" name = "pos" value="${pos}">
<input type="hidden" name = "depth" value="${depth}">
<table>
<tr>
<td>제목</td>
<td><input name="subject"></td>
</tr>
<tr>
<td>작성자</td>
<td><input name="writer"></td>
</tr>
<tr>
<td>이메일</td>
<td><input name="email"></td>
</tr>
<tr>
<td>비밀번호</td>
<td><input type="password" name="passwd"></td>
</tr>
<tr>
<td>내용</td>
<td><textarea rows="15" cols="60" name="content">gg</textarea></td>
</tr>
<tr>
<td></td>
<td>
<input type="submit" value="글 작성">
<input type="button" value="다시쓰기">
<input type="button" value="목록" onclick="location.href='list.jsp'">
</table>
</form>
</center>
</body>
</html>