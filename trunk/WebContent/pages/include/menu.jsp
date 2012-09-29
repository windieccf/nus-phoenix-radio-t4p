<%@ include file="/pages/include/tag_include.jsp" %>
<div>
	<ul class="nav nav-list bs-docs-sidenav">
		<li><a href="#"><fmt:message key="caption.menu"/></a></li>
		<c:choose>
      		<c:when test="${empty sessionScope.user}">
				<li><a href="<c:url value='/pages/login/login.jsp'/>"><i class="icon-chevron-right"></i><fmt:message key="caption.menu.login"/></a></li>
      		</c:when>
      		<c:otherwise>
      			<c:if test="${sessionScope.user.isAdmin()}">
      				<li><a href="<c:url value='/controller/listUser.do'/>"><i class="icon-chevron-right"></i> <fmt:message key="caption.menu.user"/></a></li>
      			</c:if>
		      	<li><a href="<c:url value='/controller/listSchedule.do'/>"><i class="icon-chevron-right"></i> <fmt:message key="caption.menu.schedule"/></a></li>
				<li><a href="<c:url value='/controller/logout.do'/>"><i class="icon-chevron-right"></i> <fmt:message key="caption.menu.logout"/></a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>