<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공연 일정</title>
<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
    text-align: center;
}
</style>
</head>
<body>
공연 일정: <br/><br />
<table style="width:70%">
<tr>
	<th>순번</th>
	<th>ID</th>
	<th>이름</th>
	<th>종류</th>
	<th>곡명</th>
	<th>공연시간</th>
</tr>
<c:forEach var="member" items="${members}" varStatus="status">
<tr>
	<td>${status.count}</td>
	<td><a href="<c:url value='/members/detail'>
					<c:param name="email" value="${member.email}"/>
				</c:url>">${member.email}</a></td>
	<td>${member.name}</td>
	<td>${member.type}</td>
	<td>${member.song}</td>
	<td>${member.time}</td>
	<td><a href="<c:url value='/members/delete'>
					<c:param name="email" value="${member.email}"/>
				</c:url>">삭제</a></td>
</tr>
</c:forEach>
</table>
<br/>
<a href="member/register/step1">참가 신청</a><br>
</body>
</html>

