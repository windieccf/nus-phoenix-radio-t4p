<%@ include file="/pages/include/tag_include.jsp" %>

<div class="well background-white well-shadow" style="height:100%;">
	<form action="${pageContext.request.contextPath}/controller/modifyUser.do" class="form-horizontal" method="post">
		<fieldset>
			<legend>User Maintainence</legend>
		</fieldset>
		<jsp:include page="/pages/include/messages.jsp" />
		
		<div class="control-group">
                <label class="control-label required-field">User Name</label> 
                <div class="controls">
                    <label>${user.username}</label>
                    <input type="hidden" name="user.username" value="${user.username}"/>
                </div>
		 </div>
		 
		 <div class="control-group">
                <label class="control-label">Email</label> 
                <div class="controls">
                	<input class="required" type="text" name="user.email" value="${user.email}" />
                </div>
		 </div>
		 
		 <div class="control-group">
                <label class="control-label"></label>  
                <div class="controls align-right" style="width:220px !important;">
                		<input type="submit" class="btn btn-danger" value="Submit"/> 
                		<input type="button" class="btn btn-primary" value="Cancel" onClick="history.go(-1);return true;"/>
                </div>
		 </div> 
	
	
	
	</form>

</div>