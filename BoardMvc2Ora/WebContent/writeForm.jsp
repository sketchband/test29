<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ include file = "/view/color.jspf" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<center><b>글쓰기</b></center>
<br>
<form method="post" name="writeform" action="/boardMvc20ra/writePro.do" onsubmit="return writeSave()">
<input type="hidden" name="num" value="${num}">
<input type="hidden" name="ref" value="${ref}">
<input type="hidden" name="pos" value="${pos}">
<input type="hidden" name="depth" value="${depth}">

<table width="800px">
<tr>
<td><a href="boardMvc20ra/list.do">글 목록</a></td>
</tr>
<tr>
<td>이름</td>
<td><input type="text" size="10" maxlength="10" name="writer"></td>
</tr>
<tr>
<td>제목</td>
<td>
<c:if test="${num==0}">
<input type="text" size="40" maxlength="50" name="subject"></td>
</c:if>
<c:if test="${num!=0}">
<input type="text" size="40" maxlength="50" name="subject" value="[답변]"></td>
</c:if>
</tr>
<tr>
<td>이메일</td>
<td><input type="text" size="40" maxlength="30" name="email"></td>
</tr>
<tr>
<td>내용</td>
<td><textarea rows="13" cols="40" name="content"></textarea></td>
</tr>
<tr>
<td>비밀번호</td>
<td><input type="password" size="8" maxlength="12" name="passwd">
</td>
</tr>
<tr>
<td>
<input type="submit" value="글쓰기">
<input type="reset" value="다시작성">
<input type="button" value="목록" onclick="window.location='boardMvc20ra/list.do'">
</td>
</tr>
</table>
</form>
</body>
</html>