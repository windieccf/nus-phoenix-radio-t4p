<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="programCount" required="true"  %>


<c:choose>
	<c:when test="${(programCount > 0) && (programCount <= 5)}">
		<span class="label label-info"><i class="icon-headphones icon-white"></i>&nbsp;<c:out value="${programCount}"/>&nbsp;</span>
		
	</c:when>
	<c:when test="${(programCount > 5) && (programCount <= 15)}">
		<span class="label label-success"><i class="icon-headphones icon-white"></i>&nbsp;<c:out value="${programCount}"/>&nbsp;</span>
	</c:when>
	<c:when test="${(programCount > 15) }">
		<span class="label label-important"><i class="icon-headphones icon-white"></i>&nbsp;<c:out value="${programCount}"/>&nbsp;</span>
	</c:when>
</c:choose>

