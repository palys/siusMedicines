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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

	<div class="bs-docs-grid">
		<div class="row show-grid">
			<div class="col-md-2 col-md-offset-10">
				<c:if test="${pageContext.request.userPrincipal.name != null}">
			Welcome : ${pageContext.request.userPrincipal.name} | <a href="javascript:formSubmit()"> Logout</a>
				</c:if>
			</div>
		</div>
	</div>
	<div class="container theme-showcase" role="main">
		<div class="jumbotron">
			<h2>Personal Data</h2>
		</div>

		<c:url value="/j_spring_security_logout" var="logoutUrl" />

		<!-- csrt for log out-->
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		
		<p>
			<a class="btn btn-lg btn-default" href="panel" role="button">Back</a>
		</p>
		
		<div class="tab-content" style="margin-left: 30%; margin-right: 30%" align="center">
			<div id="personal_data" class="tab-pane fade in active">
				<div class="panel panel-default">
					<div class="panel-heading">Personal Data</div>
					<table class="table table-hover">
							<col width="50%">
							<col width="50%">
								<tr>
									<td align="right"><b>Name</b></td>
									<td></span>&nbsp; &nbsp;<c:out value="${patient.name}" /></td>
								</tr>
								<tr>
									<td align="right"><b>Surname</b></td>
									<td></span>&nbsp; &nbsp;<c:out value="${patient.surname}" /></td>
								</tr>
								<tr>
									<td align="right"><b>Pesel</b></td>
									<td></span>&nbsp; &nbsp;<c:out value="${patient.pesel}" /></td>
								</tr>
								<tr>
									<td align="right"><b>Phone Number</b></td>
									<td></span>&nbsp; &nbsp;<c:out value="${patient.phoneNumber}" /></td>
								</tr>
								<tr>
									<td align="right"><b>Birthdate</b></td>
									<td></span>&nbsp; &nbsp;<c:out value="${patient.birthdate}" /></td>
								</tr>
						</table>
				</div>
			</div>
		</div>
		
	</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

</body>
</html>
