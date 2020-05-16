<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�α���</title>
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
	<label for="password">��ȣ</label>
	<form:password id="password" path="password"/>
	<form:errors path="password" />
</p>
<p>
	<input type="submit" value="�α���" />
</p>
</form:form>
</body>
</html>