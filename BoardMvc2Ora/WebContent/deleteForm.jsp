<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
<center><b>글삭제</b>
<br>
<form method="post" name="delPorm" action="/BoardMvc2Ora/deletePro.do?pageNum=${pageNum}" onsubmit="return deldeteSave()">
<table>
<tr>
<td><b>비밀번호를 입력해주세요</b></td>
</tr>
<tr>
<td>비밀번호:<input name="passwd" size="8">
		   <input type="hidden" name="num" value="${num}"></td>
</tr>
<tr>
<td>
<input type="submit" value="글삭제">
<input type="button" value="글목록" onclick="document.location.href='/BoardMvc2Ora/list.do?pageNum=${pageNum}'">
</td>
</tr>
</table>
</form>
</center>
</body>
</html>