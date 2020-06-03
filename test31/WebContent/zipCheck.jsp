<%@page import="test31.zipcodeBean"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<jsp:useBean id="dao" class="test31.MemberDAO"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
function loadCheck(){
	if(document.zipCheck.area3.value==""){
		alert("도로명을 입력해주세요.");
		document.zipCheck.area3.focus();
		return;
	}
	document.zipForm.action="zipCheck.jsp";
	document.zipForm.submit();
}
function sendAddress(zipcode,address){
	opener.document.reg.zipcode.value = zipcode;
	opener.document.reg.address.value = address;
	self.close();
}
</script>
<%
	String check = request.getParameter("check");
	String area3 = null;
	Vector<zipcodeBean> vlist = null;
	if(check.equals("y")){
		area3 = request.getParameter("area3");
		vlist = dao.zipcodeRead(area3);
	}
%>
<form method="post" name="zipCheck">
<table>
<tr>
<td>도로명 입력: <input name="area3"><input type="button" value="검색" onclick="loadCheck()"></td>
</tr>
<%
if(check.equals("y")){
	if(vlist.isEmpty()){
	%>
	<tr>
	<td>검색 된 레코드가 없습니다.</td>
	</tr>
<%}else{
	for(int i=0;i<vlist.size();i++){
		zipcodeBean bean = vlist.get(i);
		String rarea1 = bean.getArea1();
		String rarea2 = bean.getArea2();
		String rarea3 = bean.getArea3();
		String rarea4 = bean.getArea4();
		String rzipcode = bean.getZipcode();
		vlist.addElement(bean);
		String address = rarea1+" "+rarea2+" "+rarea3+" "+rarea4+" ";
		%>
		<tr>
		<td>
		<a href="#" onclick="javascript:sendAddress('<%=rzipcode%>','<%=address%>')">
		<%=rzipcode%><%=address%></a></td>
		</tr>
	<%}
	}
}
%>
</table>
<input type="hidden" name="check" value="y">
</form>
</body>
</html>