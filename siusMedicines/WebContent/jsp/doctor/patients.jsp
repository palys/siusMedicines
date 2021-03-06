<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
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
			<h2>Manage patients</h2>
		</div>

		<c:url value="/j_spring_security_logout" var="logoutUrl" />

		<!-- csrt for log out-->
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>





		<p>
			<a class="btn btn-lg btn-default" href="patients/add" role="button">Add</a>
			<a class="btn btn-lg btn-default"
				href="${pageContext.request.contextPath}/doctor/panel" role="button">Back</a>
		</p>

		<div class="tab-content">
			<div id="patients_table" class="tab-pane fade in active">
				<div class="panel panel-default">
					<div class="panel-heading">Patients</div>
					<table class="table table-hover">
					
						<col width="2%">
						<col width="20%">
						<col width="20%">
						<col width="20%">
						<col width="23%">
						<col width="5%">
						<col width="5%">
						<col width="5%">
						<tr>
							<th></th>
							<th>Name</th>
							<th>Surname</th>
							<th>Pesel</th>
							<th>Phone Number</th>
							<th></th>
							<th></th>
							<th></th>
						</tr>
						<c:forEach items="${patients}" var="patients_list"
							varStatus="loop">
							<tr>
								<td></td>
								<td><c:out value="${patients_list.name}" /></td>
								<td><c:out value="${patients_list.surname}" /></td>
								<td><c:out value="${patients_list.pesel}"/></td>
								<td><c:out value="${patients_list.phoneNumber}"/></td>
								<td><a title="Prescriptions" class="btn btn-default"
									href="${pageContext.request.contextPath}/doctor/patients/prescriptions?patient_id=${patients_list.id}">
										<span class="glyphicon glyphicon-list-alt"></span>
								</a></td>
								<td><a title="Edit" class="btn btn-default"
									href="${pageContext.request.contextPath}/doctor/patients/add?patient_id=${patients_list.id}">
										<span class="glyphicon glyphicon-pencil"></span>
								</a></td>
								<td><a title="Delete" class="btn btn-default"
									href="${pageContext.request.contextPath}/doctor/patients/remove?patient_id=${patients_list.id}">
										<span class="glyphicon glyphicon-remove"></span>
								</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>

		</div>
	</div>

</body>
</html>
