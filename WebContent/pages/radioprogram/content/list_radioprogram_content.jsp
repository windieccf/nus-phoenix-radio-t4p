<%@ include file="/pages/include/tag_include.jsp" %>


<div class="well background-white well-shadow" style="height:100%;">
	<form  class="form-horizontal" method="post">
		<fieldset>
			<legend><fmt:message key="title.list_radio_program"/></legend>
		</fieldset>

		<jsp:include page="/pages/include/messages.jsp" />
		
		<div class="row-fluid" >
			<div class="span12">
				<table class="table">
					<thead>
						<tr>
							<th><fmt:message key="program_name"/></th>
							<th><fmt:message key="program_description"/></th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="item" items="${requestScope.programList}" varStatus ="status">
						<tr>
							<td align="left" valign="top">
								<div><a href="<c:url value='/controller/displayRadioProgram.do?programName=${item.program_name}'/>">${item.program_name}</a></div>
							</td>
							<td align="left" valign="top">
								<div>${item.program_desc}</div>
							</td>
						</tr>
					</c:forEach>
					</tbody>
						 
				</table>
			</div>
		
		</div>
		
		
	
	</form>
</div>