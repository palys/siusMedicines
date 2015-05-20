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
			<h2>Prescripions</h2>
		</div>

		<c:url value="/j_spring_security_logout" var="logoutUrl" />

		<!-- csrt for log out-->
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>





		<p>
			<a class="btn btn-lg btn-default"
				href="${pageContext.request.contextPath}/doctor/patients" role="button">Back</a>
		</p>
		
		<div class="container">
			<form:form modelAttribute="medicine" role="form" method="post"
				action="/siusMedicines/doctor/patients/prescriptions/add?patient_id=${patient_id}">
				<div class="form-group">
					<label for="name">Name:</label>
					<form:select path="name" class="form-control" id="name">
						<c:forEach items="${medicines}" var="med" varStatus="loop"> 
						 	<form:option value="${med.name}"><c:out value="${med.name}"/></form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="form-actions">
					<button type="submit" class="btn btn-lg btn-default">Add</button>
				</div>
			</form:form>
		</div>

		<div class="tab-content">
			<div id="patients_table" class="tab-pane fade in active">
				<div class="panel panel-default">
					<div class="panel-heading">Prescriptions</div>
					<table class="table table-hover">
						<col width="30%">
						<col width="30%">
						<col width="15%">
						<col width="15%">
						<col width="5%">
						<col width="5%">
						<tr>
							<th>Medicine</th>
							<th>Next portion</th>
							<th>Portions left</th>
							<th>Total number of portions</th>
							<th></th>
						</tr>
						<c:forEach items="${prescriptions}" var="prescriptions_list"
							varStatus="loop">
							<tr>
								<td><c:out value="${prescriptions_list.prescription.medicine.name}" /></td>
								<td><c:out value="${prescriptions_list.nextPortion}"/></td>
								<td><c:out value="${prescriptions_list.portionsLeft}"/></td>
								<td><c:out value="${prescriptions_list.totalPortionsNumber}" /></td>
								<td><a class="btn btn-default"
									href="${pageContext.request.contextPath}/doctor/patients/prescriptions/portions?patient_id=${patient_id}&prescription_id=${prescriptions_list.prescription.id}">
										<span class="glyphicon glyphicon-pencil"></span>
								</a></td>
								<td><a class="btn btn-default"
									href="${pageContext.request.contextPath}/doctor/patients/prescriptions/remove?patient_id=${patient_id}">
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
