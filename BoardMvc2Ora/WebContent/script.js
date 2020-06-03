function writeSave(){
	if(document.writeform.writer.value==""){
		alert("이름을 입력하세요.");
		document.writeform.writer.focus();
		return false;
	}
	if(document.writeform.subject.value==""){
		alert("제목을 입력하세요.");
		document.writeform.subject.focus();
		return false;
	}
	if(document.writeform.content.value==""){
		alert("내용을 입력하세요.");
		document.writeform.content.focus();
		return false;
	}
	if(document.writeform.passwd.value==""){
		alert("암호를 입력하세요.");
		document.writeform.passwd.focus();
		return false;
	}
}