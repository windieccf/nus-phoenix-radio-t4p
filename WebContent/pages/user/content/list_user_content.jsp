<%@ include file="/pages/include/tag_include.jsp" %>


<div class="well background-white well-shadow" style="height:100%;">
	<form  class="form-horizontal" method="post">
		<fieldset>
			<legend><fmt:message key="title.list_user"/></legend>
		</fieldset>

		<jsp:include page="/pages/include/messages.jsp" />
		
		<div class="row-fluid" >
			<div class="span12">
				<table class="table">
					<thead>
						<tr>
							<th><fmt:message key="username"/></th>
							<th><fmt:message key="email"/></th>
							<th><fmt:message key="home_number_abbr"/></th>
							<th><fmt:message key="mobile_number_abbr"/></th>
							<th><fmt:message key="user_addr"/></th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="item" items="${requestScope.users}" varStatus ="status">
						<tr>
							<td align="left" valign="top">
								<div><a href="<c:url value='/controller/initUser.do?username=${item.username}'/>">${item.username}</a></div>
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
							<td align="left" valign="top">
								<div>${item.address}</div>
							</td>
						</tr>
					</c:forEach>
					</tbody>
						 
				</table>
			</div>
		
		</div>
		
		
	
	</form>
</div>