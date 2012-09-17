
<%@ include file="/pages/include/tag_include.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Phoenix Radio Management System</title>

<linkhref="<c:url value='/resource/css/bootstrap-responsive.css'/>"	rel="stylesheet" type="text/css" />
<link href="<c:url value='/resource/css/bootstrap.css'/>"	rel="stylesheet" type="text/css" />
<link href="<c:url value='/resource/css/core.css'/>"	rel="stylesheet" type="text/css" />
<link href="<c:url value='/resource/css/datepicker.css'/>"	rel="stylesheet" type="text/css" />
<link href="<c:url value='/resource/css/timepicker.css'/>"	rel="stylesheet" type="text/css" />

<script src="<c:url value='/resource/js/jquery-1.8.0.min.js'/>"></script>
<script src="<c:url value='/resource/js/bootstrap.min.js'/>"></script>

<script src="<c:url value='/resource/js/bootstrap-datepicker.js'/>"></script>
<script src="<c:url value='/resource/js/bootstrap-timepicker.js'/>"></script>

</head>
<body>
	<fmt:setBundle basename="ApplicationResources" />
	<div class="container-fluid">

		<div >
			<div class="row-fluid">
				<div class="span12">
					<tiles:insertAttribute name="header" />
				</div>
			</div>

			<div class="row-fluid">
				<div class="span2">
					<tiles:insertAttribute name="menu" />
				</div>

				<div class="span10">
					<tiles:insertAttribute name="content" />
				</div>
			</div>
		</div>


		<div class="row-fluid footer">
			<div class="span12 align-right">
				<tiles:insertAttribute name="footer" />
			</div>
		</div>
	</div>




</body>
</html>