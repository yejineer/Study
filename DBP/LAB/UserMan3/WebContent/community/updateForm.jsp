<%@page contentType="text/html; charset=utf-8" %>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>커뮤니티 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/community.css' />" type="text/css">
<script>
function commModify() {
	if (form.name.value == "") {
		alert("이름을 입력하십시오.");
		form.name.focus();
		return false;
	} 
	if (form.desc.value == "") {
		alert("설명을 입력하십시오.");
		form.desc.focus();
		return false;
	}	
	form.submit();
}

function commList(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br>
<!-- Update Form  -->
<form name="form" method="POST" action="<c:url value='/community/update' />">
  <input type="hidden" name="commId" value="${community.id}"/>	  
  <table style="width: 100%">
	<tr>
	  <td width="20"></td>
	  <td>
	    <table>
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>커뮤니티 관리 - 수정</b>&nbsp;&nbsp;</td>
		  </tr>
	    </table>  
	    <br>	  
	    <table style="background-color: YellowGreen">
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">커뮤니티 ID</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				${community.id}
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">이름</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="name" value="${community.name}">
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">설명</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="desc" value="${community.description}">
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">개설일자</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				${community.startDate}
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">회원 수</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				${community.numOfMembers}
			</td>
		  </tr>	
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">회장</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
	 			<select name="chairId" style="width: 240" >
					<option value="">없음</option>
					<c:forEach var="member" items="${community.memberList}">
						<option value="${member.userId}"
							<c:if test="${member.userId eq community.chairId}">selected="selected"</c:if>
							>${member.userId}</option>
					</c:forEach>
				</select>			
			</td>
		  </tr>		
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">회원</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<c:forEach var="member" items="${community.memberList}">
					${member.userId} &nbsp;
				</c:forEach>
			</td>			
		  </tr>	
	    </table>
	    <br>	  
	    <table style="width: 100%">
		  <tr>
			<td align="left">
			<input type="button" value="수정" onClick="commModify()"> &nbsp;
			<input type="button" value="목록" onClick="commList('<c:url value='/community/list' />')">
			</td>
		  </tr>
	    </table>
	  </td>
	</tr>
  </table>  
</form>
</body>
</html>