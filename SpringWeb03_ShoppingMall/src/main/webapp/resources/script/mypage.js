function go_cart(){
	if(document.formm.quantity.value==""){
		alert("수량을 입력하여 주세요.");
		document.formm.quantity.focus();
	}else{
		document.formm.action = "cartInsert";
		document.formm.submit();
	}
}

function go_cart_delete(){
	// document.formm.cseq[]
	
	// 자바스크립트에서 바라본 체크박스들은 같은 이름(name)의 체크박스들에 한해 배열로 인식이 됩니다.
	// 그것은 라디오 버튼에도 적용이 되며, 배열로 인식되었다는 말은 그 배열의 length(크기) 속성이
	// 존재한다는 뜻입니다.
	// 다만, 같은 이름으로 구성된 체크박스 내지는 라디오 버튼이 한 개라면 그 배열의 length는
	// undefind 값을 갖습니다. 두 개 이상이라면 당연히 배열의 갯수를 갖습니다.
	
	// 아래 코드는 체크박스(name:cseq)의 갯수가 한 개일 때와 두 개 이상일 때를 구분하여
	// 각각 체킹된 checkbox가 몇 개인지 갯수를 세고 value 값을 얻는 코드 입니다.
	
	var count = 0; // 체크된 체크박스의 갯수를 카운트 하기 위한 변수
	if(document.formm.cseq.length==undefined){ // 장바구니에 물건이 하나일 때, 체크박스가 하나일 때
		if(document.formm.cseq.checked == true) // 그 체크박스만 체크되어 있는지 확인
			count++;
	}else{
		for(var i =0; i < document.formm.cseq.length; i++){
			if(document.formm.cseq[i].checked == true)
				count++;
		}
	}
	
	// 지금의 스크립트 명령은 체크박스가 하나도 체크되지 않았다면 원래로 되돌아 가기위한 코드들입니다.
	if(count == 0){
		alert("삭제할 항목을 선택해주세요.");
	}else{
		document.formm.action="cartDelete";
		document.formm.submit();
	}
}

function go_order_insert(){
	document.formm.action="orderInsert";
	document.formm.submit();
}