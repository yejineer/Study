<%@page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%
	@SuppressWarnings("unchecked") 
	List<Community> commList = (List<Community>)request.getAttribute("commList");
%> --%>
<html>
<head>
<title>커뮤니티 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/community.css' />" type="text/css">
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br>
<table style="width:100%">
  <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
  <tr>
	<td width="20"></td>
	<td>
	  <table>
		<tr>
		  <td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>커뮤니티 리스트</b>&nbsp;&nbsp;</td>
		</tr>
	  </table>  
	  <br>		  
	  <table style="background-color: YellowGreen">
		<tr>
		  <!-- <td width="200" align="center" bgcolor="E6ECDE" height="22">커뮤니티 ID</td> -->
		  <td width="200" align="center" bgcolor="E6ECDE">이름</td>
		  <td width="200" align="center" bgcolor="E6ECDE">설명</td>
		  <td width="200" align="center" bgcolor="E6ECDE">회원 수</td>
		</tr>
		<c:forEach var="comm" items="${commList}">
			<tr>
			  <td width="200" bgcolor="FFFFFF" style="padding-left: 10" height="20">			
				  <a href="<c:url value='/community/view'>
						   <c:param name='commId' value='${comm.id}'/>
						 </c:url>">
				  ${comm.name}</a>
			  </td>
			  <td width="300" bgcolor="FFFFFF" style="padding-left: 10">
				  ${comm.description}
			  </td>
			  <td width="20" align="center" bgcolor="FFFFFF">
				  ${comm.numOfMembers}
			  </td>
			</tr>
		</c:forEach>
	  </table>	  	 
	  <br>   
	  <a href="<c:url value='/community/create/form' />">커뮤니티 추가</a> 
	  <br>
	  <a href="<c:url value='/user/list' />">사용자 목록</a>
	</td>
  </tr>
</table>  
</body>
</html>