<%@ include file="/pages/include/tag_include.jsp" %>
<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span2"> <a class="brand" href="<c:url value='/'/>"> <fmt:message key="title.application"/> </a> </div>
				
				<div class="span10 align-right">
					<c:if test="${not empty sessionScope.user}">
						<span class="nav-title align-right"><i class="icon-user icon-white"></i>&nbsp;&nbsp;${sessionScope.user.username}</span>
		      		</c:if>
				</div>
			</div>
		</div>
	</div>
</div>