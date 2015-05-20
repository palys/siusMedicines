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
			<h2>Portions</h2>
			<h3>${prescription.medicine.name}</h3>
		</div>

		<c:url value="/j_spring_security_logout" var="logoutUrl" />

		<!-- csrt for log out-->
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>


		<p>
			<a class="btn btn-lg btn-default"
				href="${pageContext.request.contextPath}/doctor/patients/prescriptions?patient_id=${pageContext.request.parameterMap['patient_id'][0]}" role="button">Back</a>
			<a class="btn btn-lg btn-default" href="${pageContext.request.contextPath}/doctor/patients/prescriptions/portions/add?patient_id=${pageContext.request.parameterMap['patient_id'][0]}&prescription_id=${prescription.id}" role="button">&nbsp;<span class="glyphicon glyphicon-plus"></span>&nbsp;</a>
		</p>

		<div class="tab-content">
			<div id="portions_table" class="tab-pane fade in active">
				<div class="panel panel-default">
					<div class="panel-heading">Prescriptions</div>
					<table class="table table-hover">
						<col width="30%">
						<col width="20%">
						<col width="20%">
						<col width="10%">
						<col width="10%">
						<col width="10%">
						<tr>
							<th>Time to take</th>
							<th>Unit</th>
							<th>Size</th>
							<th>Is taken?</th>
							<th>Should have been taken?</th>
							<th></th>
						</tr>
						<c:forEach items="${portions}" var="portion"
							varStatus="loop">
							<tr>
								<td><c:out value="${portion.takeTime}" /></td>
								<td><c:out value="${portion.unit}"/></td>
								<td><c:out value="${portion.size}"/></td>
								<td><c:out value="${portion.taken}" /></td>
								<td><c:out value="${portion.shouldBeTaken}" /></td>
								<td><c:if test="${portion.showWarning}"><span class="glyphicon glyphicon-exclamation-sign"></span></c:if></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>

		
	</div>

</body>
</html>
