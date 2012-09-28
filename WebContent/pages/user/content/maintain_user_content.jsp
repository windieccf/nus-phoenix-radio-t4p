<%@ include file="/pages/include/tag_include.jsp" %>

<script>
jQuery(document).ready(function($){
	$('.date-picker').datepicker({format: 'dd-mm-yyyy'});
});
</script>

<div class="well background-white well-shadow" style="height:100%;">
	<form action="${pageContext.request.contextPath}/controller/saveUser.do" class="form-horizontal" method="post">
		<fieldset>
			<legend>User Maintainence</legend>
		</fieldset>
		<jsp:include page="/pages/include/messages.jsp" />
		
		<div class="control-group">
				<c:choose>
				    <c:when test="${user.id == null}">
				        <label class="control-label">User Name</label> 
		                <div class="controls">
		                	<input class="required" type="text" name="user.username" value="${user.username}" />
		                </div>
				    </c:when>
				    <c:otherwise>
				        <label class="control-label required-field">User Name</label> 
		                <div class="controls">
		                    <label>${user.username}</label>
		                    <input type="hidden" name="user.id" value="${user.getId().longValue()}"/>
		                    <input type="hidden" name="user.username" value="${user.username}"/>
		                </div>
				    </c:otherwise>
				</c:choose>
				
               
		 </div>
		 
		 <div class="control-group">
                <label class="control-label">Password</label> 
                <div class="controls">
                	<input class="required" type="password" name="user.password" value="${user.password}" />
                </div>
		 </div>
		 
		  <div class="control-group">
                <label class="control-label">Email</label> 
                <div class="controls">
                	<input class="required" type="text" name="user.email" value="${user.email}" />
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
                	<input class="input-medium date-picker" type="text" name="user.DateOfBirth" value="<fmt:formatDate value="${user.dateOfBirth}" pattern="dd-MM-yyyy" />" />
                </div>
		 </div>

		  <div class="control-group">
                <label class="control-label">Join Date</label> 
                <div class="controls">
                	<input class="input-medium date-picker" type="text" name="user.DateOfBirth" value="<fmt:formatDate value="${user.joinDate}" pattern="dd-MM-yyyy" />" />
                </div>
		 </div>

		  <div class="control-group">
                <label class="control-label">Address</label> 
                <div class="controls">
                	<textarea class="required" rows="3" name="user.address">${user.address}</textarea>
                </div>
		 </div>
		 
		 <div class="control-group">
                <label class="control-label" for="user.status">Delete</label> 
                <div class="controls">
                	<input type="checkbox" name="user.status" id="user.status" value="D" /><br />
                </div>
		 </div>
		 
		 <div class="control-group">
                <label class="control-label">Roles</label> 
                <div class="controls">
                	<select name="roleList" size="4" multiple="multiple">
				        <option value="1" ${Admin!=null?"selected":""}>Admin</option>
				        <option value="2" ${Manager!=null?"selected":""}>Manager</option>
				        <option value="3" ${Presenter!=null?"selected":""}>Presenter</option>
				        <option value="4" ${Producer!=null?"selected":""}>Producer</option>
				    </select>
                </div>
		 </div>
		 
		 <div class="control-group">
                <label class="control-label"></label>  
                <div class="controls align-right" style="width:220px !important;">
                		<input type="hidden" name="status" id="status" value="A"/>
                		<input type="submit" class="btn btn-primary" value="<fmt:message key="button.submit"/>"/> 
                		<input type="button" class="btn btn-danger" value="<fmt:message key="button.cancel"/>" onClick="history.go(-1);return true;"/>
                </div>
		 </div> 
	
	
	
	</form>

</div>