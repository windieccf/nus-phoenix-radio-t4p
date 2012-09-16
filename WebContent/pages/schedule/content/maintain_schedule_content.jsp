<%@ include file="/pages/include/tag_include.jsp" %>

<script>
	jQuery(document).ready(function($){
		
	});
</script>

<div class="well background-white well-shadow" style="height:100%;">
	<form  id="scheduler-form" action="${pageContext.request.contextPath}/controller/saveSchedule.do" class="form-horizontal" method="post">
		<fieldset>
			<legend><fmt:message key="program_slot_maintenance"/></legend>
		</fieldset>
		
		<jsp:include page="/pages/include/messages.jsp" />
		hello world	
	</form>
</div>