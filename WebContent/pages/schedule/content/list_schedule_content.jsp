<%@ include file="/pages/include/tag_include.jsp" %>

<script>
	jQuery(document).ready(function($){
		$('#previous-month-id').click(function(){
			$('#curr-month-id').val( $('#curr-month-id').val() - 1 );
			$('#scheduler-form').submit();
		});
		
		$('#next-month-id').click(function(){
			$('#curr-month-id').val( parseInt($('#curr-month-id').val()) + 1 );
			$('#scheduler-form').submit();
		});
		
		$('.selectable').click(function(){
			$('#curr-week-id').val( $(this).attr('data-week-number') );
			$('#scheduler-form').attr('action',  '${pageContext.request.contextPath}/controller/maintainSchedule.do'  );
			$('#scheduler-form').submit();
		});
		
	});
</script>

<div class="well background-white well-shadow" style="height:100%;">
	<form  id="scheduler-form" action="${pageContext.request.contextPath}/controller/listSchedule.do" class="form-horizontal" method="post">
		<fieldset>
			<legend><fmt:message key="title.program_slot"/></legend>
		</fieldset>
		
		<jsp:include page="/pages/include/messages.jsp" />
		
		<!-- HIDDEN VARIABLE -->
		<input type="hidden" id="curr-month-id" name="monthlySchedule.month" value="${requestScope.monthlySchedule.month}" />
		<input type="hidden" id="curr-year-id" name="monthlySchedule.year" value="${requestScope.monthlySchedule.year}"/>
		<input type="hidden" id="curr-week-id" name="weeklySchedule.weekNumber" value="-1" />
		
		
		
		<div class="row-fluid" >
			<div class="span12">
				<h1 class="year">${requestScope.monthlySchedule.year}</h1>
			</div>
		</div>
		<br/> 
		<div class="row-fluid" >
			<div class="span8">
				<h3 class="month">${requestScope.monthlySchedule.monthAsText}</h3>
			</div>
		</div>
		
		<div class="row-fluid" >
			<div class="span8">
				<div class="btn-toolbar">
					<div class="btn-group">
						<a id="previous-month-id" class="btn btn-info" href="#" title="<fmt:message key="previous"/>"><i class="icon-arrow-left"></i></a>
						<a class="btn ${requestScope.monthlySchedule.isMonthlyView()? 'active' : ''}" href="#" title="<fmt:message key="month_view"/>" ><i class="icon-calendar"></i></a>
						<!-- 
						<a class="btn ${not requestScope.monthlySchedule.isMonthlyView()? 'active' : ''} " href="#" title="<fmt:message key="week_view"/>"><i class="icon-list-alt"></i></a>
						
						 -->
						
						<a id="next-month-id" class="btn btn-info" href="#" title="<fmt:message key="next"/>"><i class="icon-arrow-right"></i></a>
					</div>
				</div>
			</div>

			<div class="span4 align-right margin-bottom-10px"> 
				<a class="btn btn-info" href="#"><fmt:message key="add_new_slot"/></a>
			</div>
			
		</div>
		
		
		<div class="row-fluid" >
			<div class="span12">
				<table class="calendar">
					<thead>
						<tr>
							<th><fmt:message key="monday"/></th><th><fmt:message key="tuesday"/></th><th><fmt:message key="wednesday"/></th>
							<th><fmt:message key="thrusday"/></th><th><fmt:message key="friday"/></th><th><fmt:message key="saturday"/></th>
							<th><fmt:message key="sunday"/></th>
						</tr></thead>
					<tbody>
					<c:forEach var="item" items="${requestScope.monthlySchedule.weekSchedules}" varStatus ="status">
						<tr class="selectable" data-week-number="${item.weekNumber}">
							<td class="${item.isSameMonth(0,requestScope.monthlySchedule.month) ?'' :'not-in-month' }" align="left" valign="top">
								<div>${item.getDateDisplay(0)}</div>
								<div class="calendar-entry">
									<t4s:monthBadge programCount="${item.programCount[0]}"/>
								</div>
							</td>
							<td class="${item.isSameMonth(1,requestScope.monthlySchedule.month) ?'' :'not-in-month' }" align="left" valign="top">
								<div>${item.getDateDisplay(1)}</div>
								<div class="calendar-entry">
									<t4s:monthBadge programCount="${item.programCount[1]}"/>
								</div>
							</td>
							
							<td class="${item.isSameMonth(2,requestScope.monthlySchedule.month) ?'' :'not-in-month' }" align="left" valign="top">
								<div>${item.getDateDisplay(2)}</div>
								<div class="calendar-entry">
									<t4s:monthBadge programCount="${item.programCount[2]}"/>
								</div>
							</td>
							<td class="${item.isSameMonth(3,requestScope.monthlySchedule.month) ?'' :'not-in-month' }" align="left" valign="top">
								<div>${item.getDateDisplay(3)}</div>
								<div class="calendar-entry">
									<t4s:monthBadge programCount="${item.programCount[3]}"/>
								</div>
							</td>
							<td class="${item.isSameMonth(4,requestScope.monthlySchedule.month) ?'' :'not-in-month' }" align="left" valign="top">
								<div>${item.getDateDisplay(4)}</div>
								<div class="calendar-entry">
									<t4s:monthBadge programCount="${item.programCount[4]}"/>
								</div>
							</td>
							
							<td class="holiday ${item.isSameMonth(5,requestScope.monthlySchedule.month) ?'' :'not-in-month'}" align="left" valign="top">
								<div>${item.getDateDisplay(5)}</div>
								<div class="calendar-entry">
									<t4s:monthBadge programCount="${item.programCount[5]}"/>
								</div>
							</td>
							<td class="holiday  ${(item.isSameMonth(6,requestScope.monthlySchedule.month)) ?'' :'not-in-month'} " align="left" valign="top">
								<div>${item.getDateDisplay(6)}</div>
								<!-- ${item.startDate} -->
								<div class="calendar-entry">
									<t4s:monthBadge programCount="${item.programCount[6]}"/>
								</div>
							</td>
							
						</tr>
					</c:forEach>
					</tbody>
					
				</table>
			</div>
		
		</div>
		
		
	
	</form>
</div>