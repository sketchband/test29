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
	int num = Integer.parseInt(request.getParameter("num"));
%>
<center><h2>게시글 작성</h2>
<form name="reg" method="post" action="/BoardMvc5Ora/updatePro.do">
<input type="hidden" name = "num" value="${num}">
<table>
<tr>
<td>제목</td>
<td><input name="subject" value="${bean.subject}"></td>
</tr>
<tr>
<td>작성자</td>
<td><input name="writer" value="${bean.writer }" readonly="readonly"></td>
</tr>
<tr>
<td>이메일</td>
<td><input name="email" value="${bean.email }"></td>
</tr>
<tr>
<td>비밀번호</td>
<td><input type="password" name="passwd" value="${bean.passwd}"></td>
</tr>
<tr>
<td>내용</td>
<td><textarea rows="15" cols="60" name="content">${bean.content}</textarea></td>
</tr>
<tr>
<td></td>
<td>
<input type="submit" value="수정">
<input type="button" value="다시쓰기">
<input type="button" value="목록" onclick="location.href='list.do'">
</table>
</form>
</center>
</body>
</html>