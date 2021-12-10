<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Board View</title>
		<link rel="stylesheet" type="text/css" href="resources/css/board.css">
		<script type="text/javascript" src="resources/script/board.js"></script>
	</head>
	<body>
		<div id="wrap" align="center">
			<h1>게시글 상세보기</h1>
			<table>
				<tr>
					<th>작성자</th>
					<td>${board.userid}</td>
					<th>이메일></th>
					<td>${board.email}</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td><fmt:formatDate value="${board.writedate}"/></td>
					<th>조회수</th>
					<td>${board.readcount}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="3">${board.title}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="2"><pre>${board.content}</pre></td>
					<td width="300" align="center">
						<c:choose>
							<c:when test="${empty board.imagename}">
								<img style="width: 250px;" src="resources/upload/noname.jpg">
							</c:when>
							<c:otherwise>
								<img src="resources/upload/${board.imagename}">
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</table><br><br>
			<input type="button" value="게시글 리스트" onclick="location.href='main'">
			<input type="button" value="게시글 수정" onclick="open_win('boardEditForm?num=${board.num}', 'update')">
			<input type="button" value="게시글 삭제" onclick="open_win('boardDeleteForm?num=${board.num}', 'delete')">
		</div>
	</body>
</html>