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
							<th><fmt:message key="label.radioprogram.name"/></th>
							<th><fmt:message key="label.radioprogram.description"/></th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="item" items="${requestScope.radioprograms}" varStatus ="status">
						<tr>
							<td align="left" valign="top">
								<div>${item.programName}</div>
							</td>
							<td align="left" valign="top">
								<div>${item.programDesc}</div>
							</td>
						</tr>
					</c:forEach>
					</tbody>
						 
				</table>
			</div>
		
		</div>
		
		
	
	</form>
</div>