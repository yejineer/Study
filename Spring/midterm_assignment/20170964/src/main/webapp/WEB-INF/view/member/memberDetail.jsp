<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>회원 상세 정보</title>
</head>
<body>
접수 내용:
<br/>
<ul>
	<li>ID: ${member.email}</li>
	<li>이름: ${member.name}</li>
	<li>전화번호: ${member.phone}</li>
	<li>주소: ${member.address.street} ${member.address.city} (우편번호: ${member.address.zipcode})</li>
	<li>공연 종류: ${member.type}</li>
	<li>곡명: ${member.song}</li>
	<li>공연 시간: ${member.time}</li>
	<li>선호 지역: ${member.place}</li>
	<li>첫 공연 여부: ${member.firstTime}</li>
</ul>

<p><a href="<c:url value='/index' />">Go to schedule</a></p>
</body>
</html>