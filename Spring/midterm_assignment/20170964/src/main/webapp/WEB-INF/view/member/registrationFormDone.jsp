<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>공연 참가 신청</title>
</head>
<body>
${regReq.name}님의 공연 신청을 접수했습니다.
<br/>
<br/>
접수 내용:
<br/>
<ul>
	<li>ID: ${regReq.email}</li>
	<li>이름: ${regReq.name}</li>
	<li>전화번호: ${regReq.phone}</li>
	<li>주소: ${regReq.address.street} ${regReq.address.city} (우편번호: ${regReq.address.zipcode})</li>
	<li>공연 종류: ${regReq.type}</li>
	<li>곡명: ${regReq.song}</li>
	<li>공연 시간: ${regReq.time}</li>
	<li>선호 지역: ${regReq.area}</li>
	<li>첫 공연 여부: ${regReq.firstTime}</li>

</ul>
<br/>
접수 일시: ${registerDate}
<p><a href="<c:url value='/index' />">Go to schedule</a></p>
</body>
</html>