<%@ include file="/pages/include/tag_include.jsp" %>


<div class="well background-white well-shadow" style="height:100%;">
	<form  class="form-horizontal" method="post">
		<!-- 
		<fieldset>
			<legend><fmt:message key="title.program_slot"/></legend>
		</fieldset>
		 -->
		<jsp:include page="/pages/include/messages.jsp" />
		
		<div class="row-fluid" >
			<div class="span12">
				<table class="calendar">
					<thead><tr><th>User Name</th><th>Email</th><th>Home No.</th><th>Mobile No.</th></tr></thead>
					<tbody>
					<c:forEach var="item" items="${requestScope.users}" varStatus ="status">
						<tr>
							<td align="left" valign="top">
								<div><a href="<c:url value='/controller/displayUser.do?username=${item.username}'/>">${item.username}</a></div>
							</td>
							<td align="left" valign="top">
								<div>${item.email}</div>
							</td>
							<td align="left" valign="top">
								<div>${item.contactHome}</div>
							</td>
							<td align="left" valign="top">
								<div>${item.contactMobile}</div>
							</td>
						</tr>
					</c:forEach>
					</tbody>
						 
				</table>
			</div>
		
		</div>
		
		
	
	</form>
</div>