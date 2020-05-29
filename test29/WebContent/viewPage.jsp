<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String encType = "UTF-8";
	String saveFolder = "C:/Users/최봉진/Desktop/새폴더";
	int maxSize = 5*1024*1024;
	try{
			MultipartRequest multi = null;
			multi = new MultipartRequest(request,saveFolder,maxSize,encType,new DefaultFileRenamePolicy());
			
	}catch(Exception e){
		e.printStackTrace();
	}
%>
</body>
</html>