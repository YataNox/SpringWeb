<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Board Check Pass</title>
		<link rel="stylesheet" type="text/css" href="resources/css/board.css">
		<script type="text/javascript" src="resources/script/board.js"></script>
	</head>
	<body>
		<script type="text/javascript">
			if(window.name == "update"){
				window.opener.location.href="boardUpdateForm?num=${num}";
			}else if(window.name == "delete"){
				window.opener.location.href="boardDelete?num=${num}";
			}
			self.close();
		</script>
	</body>
</html>