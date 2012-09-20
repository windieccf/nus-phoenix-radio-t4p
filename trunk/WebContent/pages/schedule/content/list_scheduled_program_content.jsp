<%@ include file="/pages/include/tag_include.jsp" %>

<script>
var datePickerParameter = {format: 'dd-mm-yyyy'};
	jQuery(document).ready(function($){
		$('.date-picker').datepicker(datePickerParameter);
		
		$('#search-button').click(function(){
			$('#scheduler-form').submit();
		});
		
		$('#submit-button').click(function(){
			$('#scheduler-form').attr('action' , '${pageContext.request.contextPath}/controller/selectScheduledProgram.do' );
			$('#scheduler-form').submit();
		});
		
		$('.select-button').click(function(){
			if( $(this).hasClass('btn-success') ){
				$(this).removeClass('btn-success');
				$(this).addClass('btn-danger');
				$(this).text('<fmt:message key="not_selected" />');
				$(':last-child' , $(this).parent() ).val('N');
			}else{
				$(this).removeClass('btn-danger');
				$(this).addClass('btn-success');
				$(this).text('<fmt:message key="selected" />');
				$(':last-child' , $(this).parent() ).val('Y');
			}
		});
		
		$('.select-all-button').click(function(){
			$('.select-button').each(function(){
				if( $(this).hasClass('btn-danger') ) {
					$(this).click();
				}
			});
		});
		
		
	});
</script>

<div class="well background-white well-shadow" style="height:100%;">
	<form  id="scheduler-form" action="${pageContext.request.contextPath}/controller/pickScheduledProgramList.do" class="form-horizontal" method="post">
		<!-- 
		<input type="hidden" name="weeklySchedule.startDate" value="<fmt:formatDate value="${requestScope.weeklySchedule.startDate}" pattern="dd-MM-yyyy HH:mm" /> "/>
		<input type="hidden" name="weeklySchedule.weekNumber" value="${requestScope.weeklySchedule.weekNumber}"/>
		
		 -->							
		<fieldset>
			<legend><fmt:message key="scheduled_program_slot"/></legend>
		</fieldset>
		
		<jsp:include page="/pages/include/messages.jsp" />
		
		<input type="hidden" id="call-back-url" name="CALL_BACK" value="${requestScope.CALL_BACK}" />
		<div class="row-fluid" >
			<div class="span5">
				 <fmt:message key="start_date" /> 
				 <input class="input-small date-picker" type="text" name="weeklySchedule.startDate" value="<fmt:formatDate value="${requestScope.weeklySchedule.startDate}" pattern="dd-MM-yyyy" /> "> 
				 <a id="search-button" class="btn btn-info"> <fmt:message key="search" /> </a>	
			</div>
			<div class="span7 align-right">
				<a id="submit-button" class="btn btn-primary"><fmt:message key="submit" /></a>
				<a id="cancel-button" href="<c:url value='/controller/listSchedule.do'/>" class="btn btn-danger" ><fmt:message key="cancel" /></a>
			</div>
		</div>
		<br/>
		
		<div class="row-fluid" >
			<div class="span12">
				<table id="program-slot-table" class="table table-bordered ">
					<thead>
						<tr>
							<th><fmt:message key="start"/></th>
							<th><fmt:message key="end"/></th>
							<th><fmt:message key="radio_program"/></th>
							<th><fmt:message key="presented_by"/></th>
							<th><fmt:message key="produced_by"/></th>
							<th><a class="btn select-all-button"><fmt:message key="select_all"/></a></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${requestScope.weeklySchedule.programSlots}" varStatus ="status">
							<tr>
								<td align="left" valign="top" width="20%">
									<fmt:formatDate value="${item.startDateTime}" pattern="dd-MM-yyyy HH:mm" />
									<input type="hidden" class="input-medium" name="weeklySchedule.programSlots.startDateTime" value="<fmt:formatDate value="${item.startDateTime}" pattern="dd-MM-yyyy HH:mm" /> "  />
								</td>
								<td align="left" valign="top" width="20%">
									<fmt:formatDate value="${item.endDateTime}" pattern="dd-MM-yyyy HH:mm" />
									<input type="hidden" class="input-medium" name="weeklySchedule.programSlots.endDateTime" value="<fmt:formatDate value="${item.endDateTime}" pattern="dd-MM-yyyy HH:mm" />"  />
								</td>
								<td align="left" valign="top">
									<span><i class="icon-headphones radio-program-button"></i>&nbsp;<c:out value="${item.radioProgram.programName}"/>&nbsp;</span>
									<input type="hidden" name="weeklySchedule.programSlots.radioProgramId" value="${item.radioProgramId}"/>
									<input type="hidden" name="weeklySchedule.programSlots.radioProgram.programName" value="${item.radioProgram.programName}"/>
								</td>
								<td align="left" valign="top">
									<span><i class="icon-user presenter-button"></i>&nbsp;<c:out value="${item.presenter.firstName}"/>&nbsp;</span>
									<input type="hidden" name="weeklySchedule.programSlots.presenterId" value="${item.presenterId}"/>
									<input type="hidden" name="weeklySchedule.programSlots.presenter.firstName" value="${item.presenter.firstName}"/>
								</td>
								<td align="left" valign="top">
									<span ><i class="icon-user producer-button"></i>&nbsp;<c:out value="${item.producer.firstName}"/>&nbsp;</span>
									<input type="hidden" name="weeklySchedule.programSlots.producerId" value="${item.producerId}"/>
									<input type="hidden" name="weeklySchedule.programSlots.producer.firstName" value="${item.producer.firstName}"/>
								</td>
								<td align="left" valign="top"> 
									<a class="btn btn-danger select-button"><fmt:message key="not_selected" /></a>
									<input type="hidden" name="weeklySchedule.programSlots.selected" value="N"/>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
	</form>
</div>