<%@ include file="/pages/include/tag_include.jsp" %>

<script>


	jQuery(document).ready(function($){
		
		
		
		/** private function ************************************************/
		_updateDateTime = function(inputHidden, date, time){
			inputHidden.val(date + ' ' +time); 
		},
		
		_onDateChange = function(ev){
			var currTarget = ev.currentTarget;
			var date = $(currTarget).val();
			var time = $(':nth-child(2)',  $(currTarget).parent()).val();
			var inputHidden = $(':last-child' , $(currTarget).parent());
			_updateDateTime(inputHidden, date, time);
		},
		
		_onStatusChange = function(){
			if( $(this).hasClass('btn-success') ){
				$(this).removeClass('btn-success');
				$(this).addClass('btn-danger');
				$(this).text('<fmt:message key="delete" />');
				$(':nth-child(2)',  $(this).parent()).val('D');
				//$(':last-child' , $(this).parent() ).val('D');
			}else{
				$(this).removeClass('btn-danger');
				$(this).addClass('btn-success');
				$(this).text('<fmt:message key="active" />');
				$(':nth-child(2)',  $(this).parent()).val('A');
				//$(':last-child' , $(this).parent() ).val('A');
			}
		},
		
		_onTimeChange = function(e){
			var currTarget = e.$element;
			var date = $(':first-child',  $(currTarget).parent()).val();
			var time = e.$element.val() ; 
			var inputHidden = $(':last-child' , $(currTarget).parent());
			_updateDateTime(inputHidden, date, time);
		},
		
		_onRadioProgramClicked = function(){
			_onSelectableClick(this, '${pageContext.request.contextPath}/controller/pickRadioProgram.do');
		},
		
		_onPresenterClicked = function(){
			_onSelectableClick(this, '${pageContext.request.contextPath}/controller/pickPresenter.do');
		},
		
		_onProducerClicked = function(){
			_onSelectableClick(this, '${pageContext.request.contextPath}/controller/pickProducer.do');
		},
		
		_onSelectableClick = function(el,urlPath){
			var tr = $(el).parent().parent().parent();
			var lastTd = $('td:last-child' , tr );
			$(':last-child' , lastTd ).val('Y');
			$('#scheduler-form').attr('action',  urlPath  );
			$('#scheduler-form').submit(); 
		}
		/** private function end************************************************/
		
		/** GLOBAL ********************************/
		var datePickerParameter = {format: 'dd-mm-yyyy',startDate : '${requestScope.weeklySchedule.startDateAsDisplay}',endDate : '${requestScope.weeklySchedule.endDateAsDisplay}'};
		var timePickerParameter = { minuteStep: 30, showInputs: false, disableFocus: true, showMeridian : false , defaultTime: 'value' , onTimeChange : _onTimeChange};
		/** GLOBAL END********************************/

		
		<c:if test="${sessionScope.user.isAdmin() or sessionScope.user.isStationManager()}">
				$('.date-picker').datepicker(datePickerParameter).on('changeDate', _onDateChange);
				$('.time-picker').timepicker(timePickerParameter);
				$('.status-button').click(_onStatusChange);
				
				$('.radio-program-button').click(_onRadioProgramClicked);
				$('.presenter-button').click(_onPresenterClicked);
				$('.producer-button').click(_onProducerClicked);
				
				$('#save-button').click(function(){ 
					$('#scheduler-form').submit(); 
				});
				$('#populate-schedule-button').click(function(){
					$('#scheduler-form').attr('action',  '${pageContext.request.contextPath}/controller/pickScheduledProgram.do'  );
					$('#scheduler-form').submit(); 
				});
		</c:if>
		
		
		
		$('#add-new-button').click(function(){
			var templateInput = $('#template-input').clone();
			//initialise interaction
			
			$('.date-picker',templateInput).datepicker(datePickerParameter).on('changeDate', _onDateChange);
			$('.time-picker', templateInput).timepicker(timePickerParameter);
			
			$('.radio-program-button', templateInput).click(_onRadioProgramClicked);
			$('.presenter-button' , templateInput).click(_onPresenterClicked);
			$('.producer-button', templateInput).click(_onProducerClicked);

			$('.status-button' , templateInput).click(_onStatusChange);
			
			$(templateInput).removeAttr('id');
			$('#program-slot-table').prepend(templateInput);
		});
		
		
	});
</script>

<div class="well background-white well-shadow" style="height:100%;">
	<form  id="scheduler-form" action="${pageContext.request.contextPath}/controller/saveSchedule.do" class="form-horizontal" method="post">
		<input type="hidden" name="weeklySchedule.startDate" value="<fmt:formatDate value="${requestScope.weeklySchedule.startDate}" pattern="dd-MM-yyyy HH:mm" /> "/>
		<input type="hidden" name="weeklySchedule.weekNumber" value="${requestScope.weeklySchedule.weekNumber}"/>
									
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
				<c:if test="${sessionScope.user.isAdmin() or sessionScope.user.isStationManager()}">
					<a id="add-new-button" class="btn btn-info"><fmt:message key="add_new_slot" /></a>
					<a id="populate-schedule-button" class="btn btn-info"><fmt:message key="populate_slot" /></a>
					<a id="save-button" class="btn btn-primary"><fmt:message key="save" /></a>
					|
				</c:if>
				<a href="<c:url value='/controller/listSchedule.do'/>" class="btn btn-danger" ><fmt:message key="cancel" /></a>
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
							<th><fmt:message key="status"/></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${requestScope.weeklySchedule.programSlots}" varStatus ="status">
							<tr>
								<td align="left" valign="top" width="20%">
									<c:choose>
										<c:when test="${sessionScope.user.isAdmin() or sessionScope.user.isStationManager()}">
											<input class="input-small date-picker" type="text" value="<fmt:formatDate value="${item.startDateTime}" pattern="dd-MM-yyyy" /> " />
											<input class="time-picker input-mini" type="text" value="<fmt:formatDate value="${item.startDateTime}" pattern="HH:mm" />" />
											<input type="hidden" name="weeklySchedule.programSlots.id" value="${item.id}"/>
											<input type="hidden" name="weeklySchedule.programSlots.startDateTime" value="<fmt:formatDate value="${item.startDateTime}" pattern="dd-MM-yyyy HH:mm" /> "/>
										</c:when>
										<c:otherwise>
											<input class="input-small date-picker" type="text" value="<fmt:formatDate value="${item.startDateTime}" pattern="dd-MM-yyyy" /> " disabled />
											<input class="time-picker input-mini" type="text" value="<fmt:formatDate value="${item.startDateTime}" pattern="HH:mm" />"  disabled />
										</c:otherwise>
									</c:choose>
								</td>
								<td align="left" valign="top" width="20%">
									<c:choose>
										<c:when test="${sessionScope.user.isAdmin() or sessionScope.user.isStationManager()}">
											<input class=" input-small date-picker" type="text" value="<fmt:formatDate value="${item.endDateTime}" pattern="dd-MM-yyyy" /> "/>
											<input class="time-picker input-mini" type="text" value="<fmt:formatDate value="${item.endDateTime}" pattern="HH:mm" />" />
											<input type="hidden" name="weeklySchedule.programSlots.endDateTime" value="<fmt:formatDate value="${item.endDateTime}" pattern="dd-MM-yyyy HH:mm" />"/>
										</c:when>
										<c:otherwise>
											<input class=" input-small date-picker" type="text" value="<fmt:formatDate value="${item.endDateTime}" pattern="dd-MM-yyyy" /> " disabled/>
											<input class="time-picker input-mini" type="text" value="<fmt:formatDate value="${item.endDateTime}" pattern="HH:mm" />" disabled />
											
										</c:otherwise>
									</c:choose>
								
								
								</td>
								<td align="left" valign="top">
									<span><i class="icon-headphones radio-program-button" style="cursor: pointer;"></i>&nbsp;<c:out value="${item.radioProgram.programName}"/>&nbsp;</span>
									<input type="hidden" name="weeklySchedule.programSlots.radioProgramId" value="${item.radioProgramId}"/>
									<input type="hidden" name="weeklySchedule.programSlots.radioProgram.programName" value="${item.radioProgram.programName}"/>
								</td>
								<td align="left" valign="top">
									<span><i class="icon-user presenter-button" style="cursor: pointer;"></i>&nbsp;<c:out value="${item.presenter.firstName}"/>&nbsp;</span>
									<input type="hidden" name="weeklySchedule.programSlots.presenterId" value="${item.presenterId}"/>
									<input type="hidden" name="weeklySchedule.programSlots.presenter.firstName" value="${item.presenter.firstName}"/>
								</td>
								<td align="left" valign="top">
									<span ><i class="icon-user producer-button" style="cursor: pointer;"></i>&nbsp;<c:out value="${item.producer.firstName}"/>&nbsp;</span>
									<input type="hidden" name="weeklySchedule.programSlots.producerId" value="${item.producerId}"/>
									<input type="hidden" name="weeklySchedule.programSlots.producer.firstName" value="${item.producer.firstName}"/>
								</td>
								<td align="left" valign="top">
									<a class="btn btn-success status-button"><fmt:message key="active" /></a>
									<input type="hidden" name="weeklySchedule.programSlots.status" value="${item.status}"/>
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


<div  class="hide">
	<table id="template-table" class="table table-bordered">
		<tr id="template-input">
			<td align="left" valign="top" width="20%">
				<input class="input-small date-picker" type="text" value="${requestScope.weeklySchedule.startDateAsDisplay}" pattern="dd-MM-yyyy" /> 
				<input class="time-picker input-mini" type="text" value="00:00" pattern="HH:mm" action="_onTimeChange" />
				<input type="hidden" name="weeklySchedule.programSlots.id" value=""/>
				<input type="hidden" name="weeklySchedule.programSlots.startDateTime" value="${requestScope.weeklySchedule.startDateAsDisplay} 00:00"/>
			</td>
			<td align="left" valign="top" width="20%">
				<input class="input-small date-picker" type="text" value="${requestScope.weeklySchedule.startDateAsDisplay}" pattern="dd-MM-yyyy" /> 
				<input class="time-picker input-mini" type="text" value="00:00" pattern="HH:mm" action="_onTimeChange" />
				<input type="hidden" name="weeklySchedule.programSlots.endDateTime" value="${requestScope.weeklySchedule.startDateAsDisplay} 00:00"/>
			</td>
			<td align="left" valign="top">
				<span><i class="icon-headphones radio-program-button" style="cursor: pointer;"></i>&nbsp;&nbsp;</span>
				<input type="hidden" name="weeklySchedule.programSlots.radioProgramId" />
				<input type="hidden" name="weeklySchedule.programSlots.radioProgram.programName" />
			</td>
			<td align="left" valign="top">
				<span><i class="icon-user presenter-button" style="cursor: pointer;"></i>&nbsp;&nbsp;</span>
				<input type="hidden" name="weeklySchedule.programSlots.presenterId" />
				<input type="hidden" name="weeklySchedule.programSlots.presenter.firstName" />
			</td>
			<td align="left" valign="top">
				<span ><i class="icon-user producer-button" style="cursor: pointer;"></i>&nbsp;&nbsp;</span>
				<input type="hidden" name="weeklySchedule.programSlots.producerId" />
				<input type="hidden" name="weeklySchedule.programSlots.producer.firstName" />
			</td>
			<td align="left" valign="top">
				<a class="btn btn-success status-button"><fmt:message key="active" /></a>
				<input type="hidden" name="weeklySchedule.programSlots.status" value="A"/>
				<input type="hidden" name="weeklySchedule.programSlots.selected" value="N"/>					
			</td>
		</tr>
	
	</table>
</div>
