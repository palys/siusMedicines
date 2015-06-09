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
	<script>
		$(document).ready(function() {
			$('[data-toggle="popover"]').popover({
				target : "hover"
			}).on('mouseleave', function() {
				$('[data-toggle="popover"]').popover('hide');
			}).on('mouseenter', function() {
				$(this).popover('show');
			});
		});
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
			<h2>Historical Medicines</h2>
		</div>

		<c:url value="/j_spring_security_logout" var="logoutUrl" />

		<!-- csrt for log out-->
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		
		<p>
			<a class="btn btn-lg btn-default" href="panel" role="button">Back</a>
		</p>
		
		<div class="tab-content">
			<div id="today_schedule" class="tab-pane fade in active">
				<div class="panel panel-default">
					<div class="panel-heading">Historical Medicines</div>
					<c:if test="${historical_portions_count == 0}">
						<p> <br>&nbsp; &nbsp; No historical medicines</p>
					</c:if>
					<c:if test="${historical_portions_count != 0}">
						<table class="table table-hover">
							<col width="25%">
							<col width="32%">
							<col width="15%">
							<col width="20%">
							<col width="8%">
							<tr>
								<th>Date and Time</th>
								<th>Medicine name</th>
								<th>Quantity</th>
								<th></th>
								<th>Taken</th>
							</tr>
							<c:forEach items="${historical_portions}" var="portion_item"
								varStatus="loop">
								<tr>
									<td><span title="Taking Time" class="glyphicon glyphicon-time"></span>&nbsp; &nbsp;<c:out value="${portion_item.takeTime}" /></td>
									<td><c:out value="${portion_item.prescription.medicine.name}" /> &nbsp; &nbsp; <a href="" data-toggle="popover" title="${portion_item.prescription.medicine.name}" data-content="${portion_item.prescription.medicine.description}"><span
										title="Show Info About Medicine"
										class="glyphicon glyphicon-info-sign"></span></a></td>
									<td><c:out value="${portion_item.size}" /> <c:out value="${portion_item.unit}" /></td>
									<td><span title="Meal Requirement" class="glyphicon glyphicon-cutlery"></span>&nbsp; &nbsp;<c:out value="${portion_item.prescription.medicine.mealInfo}" /></td>
									<c:if test="${portion_item.taken == true}">
										<td>&nbsp; &nbsp;<span title="Taken" class="glyphicon glyphicon-ok" style="color:#5CB85C"></span></td>
									</c:if>
									<c:if test="${portion_item.declined == true}">
									<td>&nbsp; &nbsp;<a data-toggle="popover" title="Declined" data-content="${portion_item.declineReason}"><span
										class="glyphicon glyphicon-remove" style="color:#ff2f2f"></span></a></td>
									</c:if>
									<c:if test="${portion_item.declined == false && portion_item.taken == false}">
										<td>&nbsp; &nbsp;<span title="Unknown" class="glyphicon glyphicon-exclamation-sign"></span></td>
									</c:if>
								</tr>
							</c:forEach>
						</table>
					</c:if>
				</div>
			</div>
		</div>
		
	</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

</body>
</html>
