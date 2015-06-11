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
			Welcome : ${pageContext.request.userPrincipal.name} | <a
						href="javascript:formSubmit()"> Logout</a>
				</c:if>
			</div>
		</div>
	</div>

	<div class="container theme-showcase" role="main">

		<div class="jumbotron">
			<h2>Add patient</h2>
		</div>

		<c:url value="/j_spring_security_logout" var="logoutUrl" />

		<!-- csrt for log out-->
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>

		<div class="container">
			<form:form modelAttribute="patient" role="form" method="post"
				action="/siusMedicines/doctor/patients/add">
				<div class="form-group">
					<label for="name">Name:</label>
					<form:input path="name" type="text" class="form-control" id="name"
						placeholder="Enter name" value="${patient.name}"></form:input>
				</div>
				<div class="form-group">
					<label for="name">Surname:</label>
					<form:input path="surname" type="text" class="form-control" id="surname"
						placeholder="Enter surname" value="${patient.surname}"></form:input>
				</div>
				<div class="form-group">
					<label for="pesel">Pesel:</label>
					<form:input path="pesel" type="text" class="form-control" id="pesel"
						placeholder="Enter pesel" value="${patient.pesel}"></form:input>
				</div>
				<div class="form-group">
					<label for="phoneNumber">Phone number:</label>
					<form:input path="phoneNumber" type="text" class="form-control" id="phoneNumber"
						placeholder="Enter phone number" value="${patient.phoneNumber}"></form:input>
				</div>
				<div class="form-group">
					<label for="phoneNumber">Birth date:</label>
					<form:input path="birthdate" type="date" class="form-control" id="birthdate"
						placeholder="Enter birth date in format yyyy-mm-dd" value="${patient.birthdate}"></form:input>
				</div>
				<c:if test="${patient.name == null}">
					<div class="form-group">
						<label for="username">Login:</label>
						<form:input path="user.username" type="text" class="form-control" id="username"
							placeholder="Enter login" value="${patient.user.username}"></form:input>
					</div>
					<div class="form-group">
						<label for="password">Password:</label>
						<form:input path="user.password" type="password" class="form-control" id="password"
							placeholder="Enter password" value="${patient.user.password}"></form:input>
					</div>
				</c:if>
				<c:if test="${patient.name != null}">
					<div class="form-group">
						<form:input path="user.username" type="hidden" class="form-control" id="username"
							placeholder="Enter login" value="${patient.user.username}"></form:input>
					</div>
					<div class="form-group">
						<form:input path="user.password" type="hidden" class="form-control" id="password"
							placeholder="Enter password" value="${patient.user.password}"></form:input>
					</div>
				</c:if>
				<div class="form-group">
					<form:input path="id" type="hidden" class="form-control" value="${patient.id}"/>
				</div>
				<div class="form-actions">
					<c:if test="${patient.name == null}">
						<button type="submit" class="btn btn-lg btn-default">Add</button>
					</c:if>
					<c:if test="${patient.name != null}">
						<button type="submit" class="btn btn-lg btn-default">Update</button>
					</c:if>
					<a class="btn btn-lg btn-default"
						href="${pageContext.request.contextPath}/doctor/patients"
						role="button">Cancel</a>
				</div>
			</form:form>
		</div>

	</div>

</body>
</html>
