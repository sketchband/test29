<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
function zipCheck(){
	url = "zipCheck.jsp?check=n";
	window.open(url,"주소검색","width=500,height=150,scrollbars=yes");
}
</script>
<center><h2>주소입력</h2></center>
<div align="center">
<table>
<tr>
<td>우편번호</td><td><input name="zipcode" readonly="readonly"><input type="button" value="우편번호 검색" onclick="zipCheck()"></td>
</tr>
<tr>
<td>주소</td><td><input name="address"></td>
</tr>
</table>
</div>
</body>
</html>