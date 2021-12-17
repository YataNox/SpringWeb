<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/admin/header.jsp" %>
<%@ include file="/resources/admin/sub_menu.jsp"%>

<article>
	<h1>회원리스트</h1>
	<form name="frm" method="post">
		<table id="table_bar">
			<tr>
				<td width="642">아이디 <input type="text" name="key" value="${key}">
				<input class="btn" type="button" name="btn_search" value="검색" onclick="go_search_member();">
				<input class="btn" type="button" name="btn_total" value="전체보기" onclick="go_total_member();">
			</tr>
		</table>
	</form>
	<table id="memberList">
		<tr>
			<th width="50">아이디</th>
			<th width="60">비밀번호</th>
			<th width="50">회원명</th>
			<th width="100">이메일</th>
			<th width="60">우편번호</th>
			<th width="220">주소</th>
			<th width="97">핸드폰번호</th>
			<th>Useyn</th>
			<th width="100">가입일</th>
		</tr>
		<c:forEach items="${memberList}" var="memberVO">
			<tr>
				<td>${memberVO.id}</td>
				<td>${memberVO.pwd}</td>
				<td>${memberVO.name}</td>
				<td>${memberVO.email}</td>
				<td>${memberVO.zip_num}</td>
				<td>${memberVO.address}</td>
				<td>${memberVO.phone}</td>
				<td>
					<c:choose>
						<c:when test='${memberVO.useyn=="n" }'></c:when>
						<c:otherwise>사용</c:otherwise>
					</c:choose>
				</td>
				<td><fmt:formatDate value="${memberVO.indate}"/></td>
			</tr>
		</c:forEach>
		
	</table><br>
	
	<jsp:include page="/resources/paging/paging.jsp">
		<jsp:param name="page" value="${paging.page}"/>
		<jsp:param name="beginPage" value="${paging.beginPage}"/>
		<jsp:param name="endPage" value="${paging.endPage}"/>
		<jsp:param name="prev" value="${paging.prev}"/>
		<jsp:param name="next" value="${paging.next}"/>
		<jsp:param name="command" value="memberList"/>
	</jsp:include>
	
</article>

<%@ include file="/resources/admin/footer.jsp"%>