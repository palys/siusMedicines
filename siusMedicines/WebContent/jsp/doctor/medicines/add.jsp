<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
</head>

<body>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<div class="bs-docs-grid">
		<div class="row show-grid">
			<div class="col-md-2 col-md-offset-10">
				<c:if test="${pageContext.request.userPrincipal.name != null}">
			Welcome : ${pageContext.request.userPrincipal.name} | <a href="javascript:formSubmit()"> Logout</a>
				</c:if>
			</div>
		</div>
	</div>

	<h2>Add Medicine</h2>

	<c:url value="/j_spring_security_logout" var="logoutUrl" />

	<!-- csrt for log out-->
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	
	<div class="container">
		<form:form modelAttribute="medicine" role="form" method="post" action="/siusMedicines/doctor/medicines/add">
			<div class="form-group">
				<label for="name">Name:</label>
				<form:input path="name" type="text" class="form-control" id="name" placeholder="Enter name"></form:input>
			</div>
			<div class="form-actions">
				<button type="submit" class="btn btn-lg btn-default">Add</button>
				<a class="btn btn-lg btn-default" href="${pageContext.request.contextPath}/doctor/medicines" role="button">Cancel</a>
			</div>
		</form:form>
	</div>

</body>
</html>