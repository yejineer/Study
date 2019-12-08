<%@page contentType="text/html; charset=utf-8" %>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>커뮤니티 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/community.css' />" type="text/css">
<style>
.commTable {
  color: blue;
  background: YellowGreen;
}
.commHead {
  width: 120px;
  height: 22px;
  text-align: center;
  background: "E6ECDE";  
}
.commCell {
  width: 470px;
  text-align: left;
  padding-left: 10px;
  background: "FFFFFF";  
}
</style>
<script>
function communityRemove() {
	return confirm("정말 삭제하시겠습니까?");		
}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
  <br>
  <table style="width:100%">
    <tr>
	  <td width="20"></td>
	  <td>
	    <table>
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>커뮤니티 정보 보기</b>&nbsp;&nbsp;</td>
		  </tr>
	    </table>  
	    <br>	  	    
	  	<table class="commTable">
	  	  <tr>
			<td class="commHead">커뮤니티 ID</td>
			<td class="commCell">
				${community.id}
			</td>
		  </tr>
		  <tr>
			<td class="commHead">이름</td>
			<td class="commCell">
				${community.name}
			</td>
		  </tr>
		  <tr>
			<td class="commHead">설명</td>
			<td class="commCell">
				${community.description} 
			</td>
		  </tr>		  
		  <tr>
			<td class="commHead">개설일자</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				${community.startDate} 
			</td>
		  </tr>		  
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">회원 수</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				${community.numOfMembers}
			</td>
		  </tr>
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">회장</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<a href="<c:url value='/user/view'>
					   		<c:param name='userId' value='${community.chairId}'/>
			 		 	 </c:url>">
					${community.chairId} 
				</a>
			</td>
		  </tr>	
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">회원</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<c:forEach var="member" items="${community.memberList}">
					<a href="<c:url value='/user/view'>
					   			<c:param name='userId' value='${member.userId}'/>
			 		 		 </c:url>">
					${member.userId} 
					</a> &nbsp;
				</c:forEach>
			</td>
		  </tr>
	 	</table>
	    <br>
	    <a href="<c:url value='/community/update/form'>
	     		   <c:param name='commId' value='${community.id}'/>
			 	 </c:url>">수정</a> &nbsp;
 	    <a href="<c:url value='/community/delete'>
				   <c:param name='commId' value='${community.id}'/>
			 	 </c:url>" onclick="return communityRemove();">삭제(미구현)</a> &nbsp;
 	    <a href="<c:url value='/community/list' />">커뮤니티 목록</a>
 	    <br>
	  	<a href="<c:url value='/user/list' />">사용자 목록</a>
 	    <br><br>	   
 	    
 	    <!-- 수정이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
        <c:if test="${updateFailed}">
	      <font color="red"><c:out value="${exception.getMessage()}" /></font>
	    </c:if>    
	  </td>
	</tr>
  </table>  
</body>
</html>