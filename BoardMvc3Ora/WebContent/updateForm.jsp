<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>
</head>
<body bgcolor="${bodyback_c}">
<center><b>글쓰기</b></center>
<c:out value="${bean.num}"/>
<br>
<form method="post" name="updateform" action="/BoardMvc3Ora/updatePro.do?pageNum=${pageNum}" 
onsubmit="return writeSave()">
<table width="400" border="1" cellspacing="0" cellpadding="0" align="center">
<tr>
<td align="right" colspan="2" bgcolor="${value_c }">
<a href="/boardMvc3Ora/list.do">글목록</a>
</td>
</tr>
<tr>
<td width="70" bgcolor="${value_c }" align="center">이름</td>
<td width="300">
<input type="text" size="10" maxlength="10" name="writer" value="${bean.writer}">
<input type="hidden" name="num" value="${bean.num}">
</td>
</tr>
<tr>
<td width="70" bgcolor="${value_c }" align="center">제목</td>
<td width="330">
<c:if test="${num==0}">
<input type="text" size="40" maxlength="50" name="subject" value="${bean.subject }"></td>
</c:if>
<c:if test="${num !=0 }">
<input type="text" size="40" maxlength="50" name="subject" value="[답변]"></td>
</c:if>
</tr>
<tr>
<td width="70" bgcolor="${value_c}" align="center">Email</td>
<td><input type="email" name="email"  value="${bean.email }"></td>
</tr>
<tr>
<td width="70" bgcolor="${value_c}" align="center">내용</td>
<td width="330">
<textarea rows="13" cols="40" name="content">${bean.content }</textarea></td>
</tr>
<tr>
<td width="70" bgcolor="${value_c}" align="center">비밀번호</td>
<td width="330">
<input type="password" size="8" maxlength="12" name="passwd">
</td>
</tr>
<tr>
<td colspan=2 bgcolor="${value_c }" align="center">
<input type="submit" value="글수정">
<input type="reset" value="다시작성">
<input type="button" value="목록보기" onclick="window.location='/BoardMvc3Ora/list.do'">
</td></tr></table>
</form>
</body>
</html>