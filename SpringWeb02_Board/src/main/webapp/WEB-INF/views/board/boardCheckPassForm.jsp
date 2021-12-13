<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Board Check Pass Form</title>
		<link rel="stylesheet" type="text/css" href="resources/css/board.css">
		<script type="text/javascript" src="resources/script/board.js"></script>
	</head>
	<body>
		<div align="center">
			<form action="boardEdit" name="frm" method="get">
				<input type="hidden" name="num" value="${num}">
				<table style="width: 80%">
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="pass" size="20">
					</tr>
				</table>
				<br>
				<input type="submit" value="확인" onclick="return passCheck()">
				<br><br>${message}
			</form>
		</div>
	</body>
</html>