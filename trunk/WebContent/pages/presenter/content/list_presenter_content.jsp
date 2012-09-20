<%@ include file="/pages/include/tag_include.jsp" %>

<script>
	jQuery(document).ready(function($){
		
		$('.select-button').click(function(){
			console.log('here');
			$('#presenter-id').val( $(this).attr('data-id'));
			$('#presenter-username').val( $(this).attr('data-name'));
			$('#presenter-firstname').val( $(this).attr('data-firstName'));
			$('#presenter-lastname').val( $(this).attr('data-lastname'));
			$('#presenter-email').val( $(this).attr('data-email'));
			$('#select-form').submit();
		});
	});
</script>


<div class="well background-white well-shadow" style="height:100%;">
	<form  id="select-form"class="form-horizontal" method="post" action="${pageContext.request.contextPath}/controller/selectPresenter.do"  >
		<fieldset>
			<legend><fmt:message key="title.list_presenter"/></legend>
		</fieldset>

		<jsp:include page="/pages/include/messages.jsp" />

		<input type="hidden" id="call-back-url" name="CALL_BACK" value="${requestScope.CALL_BACK}" />
		<input type="hidden" id="presenter-id" name="user.id" value="" />
		<input type="hidden" id="presenter-username" name="user.username" value="" />
		<input type="hidden" id="presenter-firstname" name="user.firstName" value="" />
		<input type="hidden" id="presenter-lastname" name="user.lastName" value=""/>
		<input type="hidden" id="presenter-email" name="user.email" value=""/>
		
		<div class="row-fluid" >
			<div class="span12">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th><fmt:message key="label.presenter.username"/></th>
							<th><fmt:message key="label.presenter.firstname"/></th>
							<th><fmt:message key="label.presenter.lastname"></fmt:message></th>
							<th><fmt:message key="label.presenter.email"></fmt:message></th>
							<th><fmt:message key="action"/></th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="item" items="${requestScope.presenters}" varStatus ="status">
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