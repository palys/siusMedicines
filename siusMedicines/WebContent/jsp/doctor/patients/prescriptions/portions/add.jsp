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
			<h2>Add portions</h2>
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
				href="${pageContext.request.contextPath}/doctor/patients/prescriptions/portions?patient_id=${pageContext.request.parameterMap['patient_id'][0]}&prescription_id=${pageContext.request.parameterMap['prescription_id'][0]}" role="button">Back</a>
		</p>
		
		<div class="container">
			<form:form modelAttribute="portionWithFrequencyHolder" role="form" method="post"
				action="/siusMedicines/doctor/patients/prescriptions/portions/add">
				<div class="form-group">
					<label for="unit">Unit:</label>
					<form:input path="unit" type="text" class="form-control" id="unit"
						placeholder="Enter unit"></form:input>
				</div>
				<div class="form-group">
					<label for="size">Size:</label>
					<form:input path="size" type="text" class="form-control" id="size"
						placeholder="Enter size"></form:input>
				</div>
				<div class="form-group">
					<label for="portionsNumber">Number of portions:</label>
					<form:input path="portionsNumber" type="text" class="form-control" id="portionsNumber"
						placeholder="Enter number of portions"></form:input>
				</div>
				<div class="form-group">
					<label for="firstPortionDate">FirstPortionDate:</label>
					<form:input path="firstPortionDate" type="datetime-local" class="form-control" id="firstPortionDate"></form:input>
				</div>
				<div class="form-group">
					<form:input path="patientId" type="hidden" class="form-control" id="patientId"
						value="${pageContext.request.parameterMap['patient_id'][0]}"></form:input>
				</div>
				<div class="form-group">
					<form:input path="prescriptionId" type="hidden" class="form-control" id="prescriptionId"
						value="${pageContext.request.parameterMap['prescription_id'][0]}"></form:input>
				</div>
				<div class="form-actions">
					<button type="submit" class="btn btn-lg btn-default">Add</button>
				</div>
			</form:form>
		</div>

		
	</div>

</body>
</html>
