<%@ include file="/pages/include/tag_include.jsp" %>

<script>
	jQuery(document).ready(function($){
		
		$('.select-button').click(function(){
			console.log('here');
			$('#radio-program-id').val( $(this).attr('data-id'));
			$('#radio-program-name').val( $(this).attr('data-name'));
			$('#radio-program-desc').val( $(this).attr('data-desc'));
			$('#select-form').submit();
		});
	});
</script>


<div class="well background-white well-shadow" style="height:100%;">
	<form  id="select-form"class="form-horizontal" method="post" action="${pageContext.request.contextPath}/controller/selectRadioProgram.do"  >
		<fieldset>
			<legend><fmt:message key="title.list_radio_program"/></legend>
		</fieldset>

		<jsp:include page="/pages/include/messages.jsp" />

		<input type="hidden" id="call-back-url" name="CALL_BACK" value="${requestScope.CALL_BACK}" />
		<input type="hidden" id="radio-program-id" name="radioProgram.id" value="" />
		<input type="hidden" id="radio-program-name" name="radioProgram.programName" value="" />
		<input type="hidden" id="radio-program-desc" name="radioProgram.programDescription" value="" />
		
		<div class="row-fluid" >
			<div class="span12">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th><fmt:message key="label.radioprogram.name"/></th>
							<th><fmt:message key="label.radioprogram.description"/></th>
							<th><fmt:message key="action"/></th>
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
							<td align="left" valign="top">
								<a class="btn btn-primary select-button" data-id="${item.id}" data-name="${item.programName}" data-desc="${item.programDesc}">
									<fmt:message key="select" />
								</a>
								<!-- 
								href="${pageContext.request.contextPath}/controller/selectRadioProgram.do?radioProgram.id=${item.id}&radioProgram.programName=${item.programName}&radioProgram.programDescription=${item.programDesc}"
								 -->
							</td>
						</tr>
					</c:forEach>
					</tbody>
						 
				</table>
			</div>
		
		</div>
		
		
	
	</form>
</div>