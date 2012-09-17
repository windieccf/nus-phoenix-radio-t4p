<%@ include file="/pages/include/tag_include.jsp" %>

<script>
	jQuery(document).ready(function($){
		$('.date-picker').datepicker({
			format: 'dd-mm-yyyy',
			startDate : '${requestScope.weeklySchedule.startDateAsDisplay}',
			endDate : '${requestScope.weeklySchedule.endDateAsDisplay}'
			
		});
		
		$('.time-picker').timepicker({
            minuteStep: 30,
            showInputs: false,
            disableFocus: true,
            showMeridian : false
        });
		
		$('.status-button').click(function(){
			if( $(this).hasClass('btn-success') ){
				$(this).removeClass('btn-success');
				$(this).addClass('btn-danger');
				$(this).text('<fmt:message key="delete" />');
			}else{
				$(this).removeClass('btn-danger');
				$(this).addClass('btn-success');
				$(this).text('<fmt:message key="active" />');
			}
			
		});
		
	});
</script>

<div class="well background-white well-shadow" style="height:100%;">
	<form  id="scheduler-form" action="${pageContext.request.contextPath}/controller/saveSchedule.do" class="form-horizontal" method="post">
		<fieldset>
			<legend><fmt:message key="program_slot_maintenance"/></legend>
		</fieldset>
		
		<jsp:include page="/pages/include/messages.jsp" />
		
		
		<div class="row-fluid" >
			<div class="span5">
				 <fmt:message key="from" /> <span class="label label-info">${requestScope.weeklySchedule.startDateAsFullMonthDisplay}</span>
				<fmt:message key="to" /> <span class="label label-info"> ${requestScope.weeklySchedule.endDateAsFullMonthDisplay}</span>
			</div>
			<div class="span7 align-right">
				<a class="btn btn-info"><fmt:message key="add_new_slot" /></a>
				<a class="btn btn-primary"><fmt:message key="save" /></a>
			</div>
			
		</div>
		<br/>
		<div class="row-fluid" >
			<div class="span12">
				<table class="table table-bordered ">
					<thead>
						<tr>
							<th><fmt:message key="start"/></th>
							<th><fmt:message key="end"/></th>
							<th><fmt:message key="radio_program"/></th>
							<th><fmt:message key="presented_by"/></th>
							<th><fmt:message key="produced_by"/></th>
							<th><fmt:message key="status"/></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${requestScope.weeklySchedule.programSlots}" varStatus ="status">
							<tr>
								<td align="left" valign="top" width="20%">
									<input class="input-small date-picker" type="text" value="<fmt:formatDate value="${item.startDateTime}" pattern="dd-MM-yyyy" /> ">
									<input class="time-picker input-mini" type="text" value="<fmt:formatDate value="${item.startDateTime}" pattern="hh:mm" />" />
								</td>
								<td align="left" valign="top" width="20%">
									<input class=" input-small date-picker" type="text" value="<fmt:formatDate value="${item.endDateTime}" pattern="dd-MM-yyyy" /> ">
									<input class="time-picker input-mini" type="text" value="<fmt:formatDate value="${item.endDateTime}" pattern="hh:mm" />" />
								</td>
								<td align="left" valign="top">
									<span><i class="icon-headphones" style="cursor: pointer;"></i>&nbsp;Radio program&nbsp;</span>
								</td>
								<td align="left" valign="top">
									<span><i class="icon-user" style="cursor: pointer;"></i>&nbsp;<c:out value="${item.presenter.firstName}"/>&nbsp;</span>
								</td>
								<td align="left" valign="top">
									<span ><i class="icon-user" style="cursor: pointer;"></i>&nbsp;<c:out value="${item.producer.firstName}"/>&nbsp;</span>
								</td>
								<td align="left" valign="top">
									<a class="btn btn-success status-button"><fmt:message key="active" /></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					
				</table>
			</div>
		</div>
		
	</form>
</div>