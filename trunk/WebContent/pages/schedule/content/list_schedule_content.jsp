<%@ include file="/pages/include/tag_include.jsp" %>


<div class="well background-white well-shadow" style="height:100%;">
	<form  class="form-horizontal" method="post">
		<!-- 
		<fieldset>
			<legend><fmt:message key="title.program_slot"/></legend>
		</fieldset>
		 -->
		<jsp:include page="/pages/include/messages.jsp" />
		
		<div class="row-fluid" >
			<div class="span12">
				<h1 class="year">${requestScope.monthlySchedule.year}</h1>
				&nbsp;
				<h3 class="month">${requestScope.monthlySchedule.monthAsText}</h3>
				<table class="calendar">
					<thead><tr><th>Sunday</th><th>Monday</th><th>Tuesday</th><th>Wednesday</th><th>Thursday</th><th>Friday</th><th>Saturday</th></tr></thead>
					<tbody>
					<c:forEach var="item" items="${requestScope.monthlySchedule.weekSchedules}" varStatus ="status">
						<tr>
							<td class="weekend" align="left" valign="top">
								<div>${item.getDateDisplay(0)}</div>
								<div class="calendar-entry"></div>
							</td>
							<td align="left" valign="top">
								<div>${item.getDateDisplay(1)}</div>
								<div class="calendar-entry">
								12:00 - 23:00 
								</div>
							</td>
							<td align="left" valign="top">
								<div>${item.getDateDisplay(2)}</div>
								<div class="calendar-entry"></div>
							</td>
							<td align="left" valign="top">
								<div>${item.getDateDisplay(3)}</div>
								<div class="calendar-entry"></div>
							</td>
							<td align="left" valign="top">
								<div>${item.getDateDisplay(4)}</div>
								<div class="calendar-entry"></div>
							</td>
							<td align="left" valign="top">
								<div>${item.getDateDisplay(5)}</div>
								<div class="calendar-entry"></div>
							</td>
							<td class="weekend" align="left" valign="top">
								<div>${item.getDateDisplay(6)}</div>
								<div class="calendar-entry"></div>
							</td>
						</tr>
					</c:forEach>
					</tbody>
					
				</table>
			</div>
		
		</div>
		
		
	
	</form>
</div>