<%@ include file="/pages/include/tag_include.jsp" %>

<div class="well background-white well-shadow" style="height:100%;">
	<form action="${pageContext.request.contextPath}/controller/saveUser.do" class="form-horizontal" method="post">
		<fieldset>
			<legend>User Maintainence</legend>
		</fieldset>
		<jsp:include page="/pages/include/messages.jsp" />
		
		<div class="control-group">
                <label class="control-label required-field">User Name</label> 
                <div class="controls">
                    <label>${user.username}</label>
                    <input type="hidden" name="user.id" value="${user.getId().longValue()}"/>
                </div>
		 </div>
		 
		 <div class="control-group">
                <label class="control-label">First Name</label> 
                <div class="controls">
                	<input class="required" type="text" name="user.firstName" value="${user.firstName}" />
                </div>
		 </div>
		 
		  <div class="control-group">
                <label class="control-label">Last Name</label> 
                <div class="controls">
                	<input class="required" type="text" name="user.lastName" value="${user.lastName}" />
                </div>
		 </div>
		 
		  <div class="control-group">
                <label class="control-label">Date of Birth</label> 
                <div class="controls">
                	<input class="required" type="text" name="user.DateOfBirth" value="${user.getDateOfBirth().toString()}" />
                </div>
		 </div>
		 
		  <div class="control-group">
                <label class="control-label">Email</label> 
                <div class="controls">
                	<input class="required" type="text" name="user.email" value="${user.email}" />
                </div>
		 </div>
		 
		  <div class="control-group">
                <label class="control-label">Address</label> 
                <div class="controls">
                	<input class="required" type="text" name="user.address" value="${user.address}" />
                </div>
		 </div>
		 
		 <div class="control-group">
                <label class="control-label">Roles</label> 
                <div class="controls">
                	<c:forEach var="item" items="${roles}" varStatus ="status">
                		<div>${item.role}</div>
                	</c:forEach>
                </div>
		 </div>
		 
		 <div class="control-group">
                <label class="control-label"></label>  
                <div class="controls align-right" style="width:220px !important;">
                		<input type="submit" class="btn btn-danger" value="<fmt:message key="button.submit"/>"/> 
                		<input type="button" class="btn btn-primary" value="<fmt:message key="button.cancel"/>" onClick="history.go(-1);return true;"/>
                </div>
		 </div> 
	
	
	
	</form>

</div>