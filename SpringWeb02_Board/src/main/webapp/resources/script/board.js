function loginCheck(){
	if(document.frm.id.value==""){
		alert("아이디를 입력하세요.");
		document.frm.id.focus();
		return false;
	}else if(document.frm.pw.value==""){
		alert("비밀번호를 입력하세요.");
		document.frm.pw.focus();
		return false;
	}else{
		return true;
	}
}

function idCheck(){
	if(document.frm.id.value==""){
		alert("아이디를 입력하여 주십시오.");
		document.frm.id.focus();
		return;
	}
	var id = document.frm.id.value;
	var opt = "toolbar=no, menubar=no, resizable=no, width=450, height=200";
	window.open("idcheck?id=" + id, "중복체크", opt);
}