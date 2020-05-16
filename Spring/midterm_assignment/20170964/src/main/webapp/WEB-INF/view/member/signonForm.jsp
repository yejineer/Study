<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>로그인</title>
</head>
<body>
<form:form modelAttribute="signonCommand" action="signon">
	<c:if test="${!empty signonForwardAction}">
      <input type="hidden" name="forwardAction"
        value='<c:url value="${signonForwardAction}"/>' />
    </c:if>
<form:errors />

<p>
	<label for="id">ID</label>
	<form:input id="id" path="id" value="${signonCommand.id}"/>
	<form:errors path="id" />
</p>
<p>
	<label for="password">암호</label>
	<form:password id="password" path="password"/>
	<form:errors path="password" />
</p>
<p>
	<input type="submit" value="로그인" />
</p>
</form:form>
</body>
</html>