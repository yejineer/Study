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
<h2>공연 참가 신청 - Step2</h2>

	<form:form modelAttribute="regReq" method="post" action="step3">
		<label for="type">공연 종류</label>: 
		<form:select path="type" items="${typeCodes}"/>
		<br/>

		<label for="song">곡명</label>: 
		<form:input path="song" />
		<form:errors path="song" />
		<br/>

		<label for="time">공연 시간</label>: 
		<form:input path="time" />
		<form:errors path="time" />
		<br/>
		
		<label for="area">선호 지역</label>: 
		<form:radiobuttons items="${areas}" path="area" />
		<form:errors path="area" />
		<br/>
		
		<label for="firstTime"> <form:checkbox path="firstTime" />
			첫 공연?
		</label>
		<br/>

		<br/>
		<a href="step1">[이전 단계로]</a> &nbsp;&nbsp;
		<input type="submit" value="다음 단계로" />

	</form:form>
</body>
</html>