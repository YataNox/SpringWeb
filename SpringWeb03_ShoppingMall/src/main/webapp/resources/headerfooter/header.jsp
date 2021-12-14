<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Main</title>
		<link href="resources/css/shopping.css" rel="stylesheet">
		<script src="resources/script/member.js"></script>
		<script src="resources/script/mypage.js"></script>
	</head>
	<body>
		<div id="wrap">
			<header>
				<!-- 로고, 주메뉴, 카테고리 메뉴 등이 표시되는 영역 -->
	
				<div id="logo">
					<!-- 메인로고 시작 -->
					<a href="/shop/"> <img src="resources/images/logo.png" width="180" height="100" />
					</a>
				</div>
				<!-- 메인로고 끝 -->
				
				<nav id="top_menu">
					<!-- 상단 메뉴 시작 : 로그인 CART MyPage 등-->
					<ul>
						<c:choose>
							<c:when test="${empty loginUser}">
								<li><a href="loginForm">LOGIN</a></li>
								<li><a href="contract">JOIN</a></li>
							</c:when>
							<c:otherwise>
								<li style="color:yellow; font-weight:bold; font-size: 110%">${loginUser.name}(${loginUser.id})</li>
								<li><a href="memberEditForm">정보수정</a></li>
								<li><a href="logout">LogOut</a></li>
							</c:otherwise>
						</c:choose>
						<li><a href="cartList">CART</a></li>
						<li><a href="myPage">MY PAGE</a></li>
						<li><a href="qnaList" style="border:0px">Q&amp;A (1:1)</a></li>
					</ul>
				</nav>
				<!-- 상단 메뉴 끝 -->
				
				<nav id="catagory_menu">
					<!-- 카테고리 메뉴 시작 Heels Boots Sandals 등-->
					<ul>
						<li><a href="category?kind=1">Heels</a></li>
						<li><a href="category?kind=2">Boots</a></li>
						<li><a href="category?kind=3">Sandals</a></li>
						<li><a href="category?kind=4">Sneakers</a></li>
						<li><a href="category?kind=5">Sleeper</a></li>
						<li><a href="category?kind=6">On Sale</a></li>
					</ul>
				</nav>
				<!-- 카테고리 메뉴 끝 -->
			</header>