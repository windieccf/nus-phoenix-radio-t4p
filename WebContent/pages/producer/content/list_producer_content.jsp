<%@ include file="/pages/include/tag_include.jsp" %>

<script>
	jQuery(document).ready(function($){
		
		$('.select-button').click(function(){
			console.log('here');
			$('#producer-id').val( $(this).attr('data-id'));
			$('#producer-username').val( $(this).attr('data-name'));
			$('#producer-firstname').val( $(this).attr('data-firstName'));
			$('#producer-lastname').val( $(this).attr('data-lastname'));
			$('#producer-email').val( $(this).attr('data-email'));
			$('#select-form').submit();
		});
	});
</script>


<div class="well background-white well-shadow" style="height:100%;">
	<form  id="select-form"class="form-horizontal" method="post" action="${pageContext.request.contextPath}/controller/selectProducer.do"  >
		<fieldset>
			<legend><fmt:message key="title.list_producer"/></legend>
		</fieldset>

		<jsp:include page="/pages/include/messages.jsp" />

		<input type="hidden" id="call-back-url" name="CALL_BACK" value="${requestScope.CALL_BACK}" />
		<input type="hidden" id="producer-id" name="user.id" value="" />
		<input type="hidden" id="producer-username" name="user.username" value="" />
		<input type="hidden" id="producer-firstname" name="user.firstName" value="" />
		<input type="hidden" id="producer-lastname" name="user.lastName" value=""/>
		<input type="hidden" id="producer-email" name="user.email" value=""/>
		
		<div class="row-fluid" >
			<div class="span12">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th><fmt:message key="label.producer.username"/></th>
							<th><fmt:message key="label.producer.firstname"/></th>
							<th><fmt:message key="label.producer.lastname"></fmt:message></th>
							<th><fmt:message key="label.producer.email"></fmt:message></th>
							<th><fmt:message key="action"/></th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="item" items="${requestScope.producers}" varStatus ="status">
						<tr>
							<td align="left" valign="top">
								<div>${item.username}</div>
							</td>
							<td align="left" valign="top">
								<div>${item.firstName}</div>
							</td>
							<td align="left" valign="top">
								<div>${item.lastName}</div>
							</td>
							<td align="left" valign="top">
								<div>${item.email}</div>
							</td>
							<td align="left" valign="top">
								<a class="btn btn-primary select-button" data-id="${item.id}" data-name="${item.username}" data-firstName="${item.firstName}">
									<fmt:message key="select" />
								</a>
							</td>
						</tr>
					</c:forEach>
					</tbody>
						 
				</table>
			</div>
		
		</div>
		
		
	
	</form>
</div>