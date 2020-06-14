<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="check==1">
<meta http-equiv="Refresh" content="0;url=/BoardMvc2Ora/list.do?pageNum=${pageNum}">
</c:if>

<c:if test="${check==0}">
<script>
alert("비밀번호가 다릅니다.");
</script>
<br>
<a href="javascript:history.go(-1)">[글삭제 폼으로 돌아가기]</a>
</c:if>

</body>
</html>