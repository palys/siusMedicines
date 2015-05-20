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
	<div class="container theme-showcase" role="main">
		<div class="jumbotron">
			<h2>Medicines Manager Patient Panel</h2>
		</div>

		<c:url value="/j_spring_security_logout" var="logoutUrl" />

		<!-- csrt for log out-->
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		
		<div class="tab-content">
			<div id="unchecked_list" class="tab-pane fade in active">
				<div class="panel panel-default">
					<div class="panel-heading">Confirmation List</div>
					<c:if test="${unchecked_portions_count == 0}">
						<p> <br>&nbsp &nbsp No portions to confirm</p>
					</c:if>
					<c:if test="${unchecked_portions_count != 0}">
						<table class="table table-hover">
							<col width="5%">
							<col width="30%">
							<col width="35%">
							<col width="20%">
							<col width="5%">
							<col width="5%">
							<tr>
								<th></th>
								<th>Date and Time</th>
								<th>Medicine name</th>
								<th>Quantity</th>
								<th></th>
							</tr>
							<c:forEach items="${unchecked_portions}" var="portion_item"
								varStatus="loop">
								<tr>
									<td>
										&nbsp<span title="Require Confirmation" class="glyphicon glyphicon-warning-sigglyphicon glyphicon-flash"></span>
									</td>
									<td><c:out value="${portion_item.takeTime}" /></td>
									<td><c:out value="${portion_item.prescription.medicine.name}" /> &nbsp &nbsp <span title="${portion_item.prescription.medicine.name}" class="glyphicon glyphicon-info-sign"></span></td>
									<td><c:out value="${portion_item.size}" /> <c:out value="${portion_item.unit}" /></td>
									<td>
										<span title="Confirm" class="glyphicon glyphicon-ok"></span>
									</td>
									<td>
										<span title="Reject"class="glyphicon glyphicon-remove"></span>
									</td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
				</div>
			</div>
		</div>
		
		<div class="tab-content">
			<div id="today_schedule" class="tab-pane fade in active">
				<div class="panel panel-default">
					<div class="panel-heading">Scheduled Medicines</div>
					<c:if test="${scheduled_portions_count == 0}">
						<p> <br>&nbsp &nbsp No medicines scheduled</p>
					</c:if>
					<c:if test="${scheduled_portions_count != 0}">
						<table class="table table-hover">
							<col width="30%">
							<col width="35%">
							<col width="20%">
							<col width="15%">
							<tr>
								<th>Date and Time</th>
								<th>Medicine name</th>
								<th>Quantity</th>
								<th></th>
							</tr>
							<c:forEach items="${scheduled_portions}" var="portion_item"
								varStatus="loop">
								<tr>
									<td><span title="Taking Time" class="glyphicon glyphicon-time"></span>&nbsp &nbsp<c:out value="${portion_item.takeTime}" /></td>
									<td><c:out value="${portion_item.prescription.medicine.name}" /> &nbsp &nbsp <span title="${portion_item.prescription.medicine.name}" class="glyphicon glyphicon-info-sign"></span></td>
									<td><c:out value="${portion_item.size}" /> <c:out value="${portion_item.unit}" /></td>
									<td><span title="Meal Requirement" class="glyphicon glyphicon-cutlery"></span>&nbsp &nbsp 2h After Meal</td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
				</div>
			</div>
		</div>
		
	</div>


</body>
</html>
