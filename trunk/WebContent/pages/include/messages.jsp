<%@ include file="/pages/include/tag_include.jsp" %>

<c:if test="${not empty requestScope.INF}">
	<div class="alert alert-block alert-success  fade in">
		<button type="button" class="close" data-dismiss="alert">×</button>
		${requestScope.INF}
	</div>
</c:if>


<c:if test="${not empty requestScope.ERR}">
	<div class="alert alert-block alert-error fade in">
		<button type="button" class="close" data-dismiss="alert">×</button>
		${requestScope.ERR}
	</div>
</c:if>


