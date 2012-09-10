<%@ include file="/pages/include/tag_include.jsp" %>
<script>
	jQuery(document).ready(function($){
		
		$('.required').blur(function(){
			console.log( $.trim( $(this).val() ).length);	
			if( $.trim( $(this).val() ).length === 0)
				$(this).addClass('inputError');
			else
				$(this).removeClass('inputError');
		});
		
		
	});
</script>
<div class="well background-white well-shadow" style="height:100%;">
	<form action="${pageContext.request.contextPath}/controller/authenticate.do" class="form-horizontal" method="post">
		<fieldset>
			<legend>Login</legend>
		</fieldset>
		
		<c:if test="${not empty requestScope.ERR}">
			<div class="alert alert-block alert-error fade in">
				 <button type="button" class="close" data-dismiss="alert">×</button>
				${requestScope.ERR}
			</div>
		</c:if>
		
		
		
		<div class="control-group">
                <label class="control-label required-field"><fmt:message key="fieldLabel.username"/></label> 
                <div class="controls">
                    <input class="required" type="text" id="user_name_id" name="user.username" placeholder="Username"/>
                </div>
		 </div>
		 
		 <div class="control-group">
                <label class="control-label"><fmt:message key="fieldLabel.password"/></label> 
                <div class="controls">
                	<input class="required" type="password" name="user.password" placeholder="Password">
                </div>
		 </div>
		 
		 <div class="control-group">
                <label class="control-label"></label>  
                <div class="controls align-right" style="width:220px !important;">
                		<input type="reset" class="btn btn-danger" value="<fmt:message key="button.reset"/>"/> 
                		<input type="submit" class="btn btn-primary" value="<fmt:message key="button.login"/>"/>
                </div>
		 </div> 
	
	
	
	</form>

</div>