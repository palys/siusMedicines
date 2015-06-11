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
						<col width="5%">
						<col width="15%">
						<col width="5%">
						<col width="5%">
						<col width="5%">
						<col width="5%">
						<col width="30%">
						<tr>
							<th></th>
							<th>Time to take</th>
							<th>Unit</th>
							<th>Size</th>
							<th>Taken</th>
							<th>Declined</th>
							<th>Reason</th>
						</tr>
						<c:forEach items="${portions}" var="portion"
							varStatus="loop">
							<tr>
								<td><c:if test="${portion.showWarning}"><span class="glyphicon glyphicon-exclamation-sign"></span></c:if></td>
								<td><c:out value="${portion.takeTime}" /></td>
								<td><c:out value="${portion.unit}"/></td>
								<td><c:out value="${portion.size}"/></td>
								<td><c:choose>
										<c:when test="${portion.taken}">
											<span class="glyphicon glyphicon-ok"></span>
										</c:when>
										<c:otherwise>
											<span class="glyphicon glyphicon-remove"></span>
										</c:otherwise>
									</c:choose>
								</td>
								<td><c:choose>
										<c:when test="${portion.declined}">
											<span class="glyphicon glyphicon-ok"></span>
										</c:when>
										<c:otherwise>
											<span class="glyphicon glyphicon-remove"></span>
										</c:otherwise>
									</c:choose>
								</td>
								<td><c:if test="${portion.declined}"><c:out value="${portion.reason}"/></c:if></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>

		
	</div>

</body>
</html>
