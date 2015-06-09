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
			$('#confirmTaking').css("top", "30%");
			$('#disclaimTaking').css("top", "30%");
			$('#testId').on('click',function() {
				var portionId = $(this).data('id');
				$("#inputReject").val(portionId);
			});
			$('#tesId').on('click',function() {
				var portionId = $(this).data('id');
				$("#inputConfirm").val(portionId);
			});
		});
		
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
			<h2>Medicines Manager Patient Panel</h2>
		</div>

		<c:url value="/j_spring_security_logout" var="logoutUrl" />

		<!-- csrt for log out-->
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>

		<p>
			<a class="btn btn-lg btn-default" href="calendar" role="button">
				<span title="Calendar" class="glyphicon glyphicon-calendar"></span>&nbsp;&nbsp;
				Show Calendar
			</a> <a class="btn btn-lg btn-default" href="historical" role="button">
				<span title="History" class="glyphicon glyphicon-check"></span>&nbsp;&nbsp;
				Show History
			</a> <a class="btn btn-lg btn-default" href="medicines" role="button">
				<span title="Emergency" class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;
				Show Medicines
			</a> <a class="btn btn-lg btn-default" href="contact" role="button">
				<span title="Contact" class="glyphicon glyphicon-envelope"></span>&nbsp;&nbsp;
				Contact Doctor
			</a> <a class="btn btn-lg btn-default" href="personal" role="button">
				<span title="Patient" class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;
				Personal Data
			</a> <a class="btn btn-lg btn-default" href="javascript:formSubmit()"
				role="button"> <span title="Logout"
				class="glyphicon glyphicon-off"></span>&nbsp;&nbsp; Logout
			</a> <a class="btn btn-lg btn-default" href="settings" role="button">
				&nbsp;<span title="Settings"
				class="glyphicon glyphicon glyphicon-cog"></span>&nbsp;
			</a>
		</p>
		<br>

		<div class="tab-content">
			<div id="unchecked_list" class="tab-pane fade in active">
				<div class="panel panel-default">
					<div class="panel-heading">Confirmation List</div>
					<c:if test="${unchecked_portions_count == 0}">
						<p>
							<br>&nbsp; &nbsp; No portions to confirm
						</p>
					</c:if>
					<c:if test="${unchecked_portions_count != 0}">
						<table class="table table-hover">
							<col width="5%">
							<col width="30%">
							<col width="35%">
							<col width="22%">
							<col width="3%">
							<col width="5%">
							<tr>
								<th></th>
								<th>Date and Time</th>
								<th>Medicine name</th>
								<th>Quantity</th>
								<th></th>
								<th></th>
							</tr>
							<c:forEach items="${unchecked_portions}" var="portion_item"
								varStatus="loop">
								<tr>
									<td>&nbsp;<span title="Require Confirmation"
										class="glyphicon glyphicon-warning-sigglyphicon glyphicon-flash"></span>
									</td>
									<td><c:out value="${portion_item.takeTime}" /></td>
									<td><c:out
											value="${portion_item.prescription.medicine.name}" /> &nbsp;
										&nbsp; <a href="" data-toggle="popover" title="${portion_item.prescription.medicine.name}" data-content="${portion_item.prescription.medicine.description}"><span
										title="Show Info About Medicine"
										class="glyphicon glyphicon-info-sign"></span></a></td>
									<td><c:out value="${portion_item.size}" /> <c:out
											value="${portion_item.unit}" /></td>
									<td><a href="#" id="tesId" class="btn btn-sm btn-success"
										data-toggle="modal" data-id="${portion_item.id}" data-target="#confirmTaking"><span
											title="Confirm" class="glyphicon glyphicon-ok"></span></a></td>
									<td><a href="#" id="testId" class="btn btn-sm btn-danger"
										data-toggle="modal" data-id="${portion_item.id}" data-target="#disclaimTaking"><span
											title="Reject" class="glyphicon glyphicon-remove"></span></a></td>
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
						<p>
							<br>&nbsp; &nbsp; No medicines scheduled
						</p>
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
							<c:forEach items="${scheduled_portions}" var="portion_item" varStatus="loop">
								<tr>
									<td><span title="Taking Time"
										class="glyphicon glyphicon-time"></span>&nbsp; &nbsp;<c:out
											value="${portion_item.takeTime}" /></td>
									<td><c:out
											value="${portion_item.prescription.medicine.name}" /> &nbsp;
										&nbsp; <a href="" data-toggle="popover" title="${portion_item.prescription.medicine.name}" data-content="${portion_item.prescription.medicine.description}"><span
										title="Show Info About Medicine"
										class="glyphicon glyphicon-info-sign"></span></a></td>
									<td><c:out value="${portion_item.size}" /> <c:out
											value="${portion_item.unit}" /></td>
									<td><span title="Meal Requirement"
										class="glyphicon glyphicon-cutlery"></span>&nbsp; &nbsp;<c:out value="${portion_item.prescription.medicine.mealInfo}" /></td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
				</div>
			</div>
		</div>
		<p style="text-align: right">
			<a class="btn btn-sm btn-default" href="all" role="button">Show
				All</a>
		</p>

		<div class="modal fade" id="confirmTaking" tabindex="-1" role="dialog"
			aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel" align="center">Medicine</h4>
					</div>
					<div class="modal-body">
						<h4 align="center">Are you sure you want to confirm taking
							medicine?</h4>
						<form:form id="cForm" modelAttribute="rejection_reason"
							role="form" method="post" action="/siusMedicines/patient/panel">
							<div class="form-group">
								<form:input id="inputConfirm" path="id" type="hidden"
									name="portion"></form:input>
							</div>
						</form:form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" onclick="document.forms['cForm'].submit();" class="btn btn-primary" data-dismiss="modal">Confirm</button>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="disclaimTaking" tabindex="-1"
			role="dialog" aria-labelledby="largeModal" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel" align="center">Medicine</h4>
					</div>
					<div class="modal-body">
						<h4 align="center">
							Please, provide reason why medicine was not taken.
							</h4>
							<div class="required">
								<form:form id="rForm" modelAttribute="rejection_reason" role="form" method="post"
									action="/siusMedicines/patient/panel">
									<div class="form-group">
										<form:input path="value" type="text" class="form-control"
											id="name" placeholder="Enter reason"></form:input>
										<form:input id="inputReject" path="id" type="hidden" name="portion"></form:input>
									</div>
								</form:form>
							</div></div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="submit" onclick="document.forms['rForm'].submit();" class="btn btn-primary" data-dismiss="modal">Save changes</button>
					</div>
				</div>
			</div>
		</div>
		

		<script>

		</script>

		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		
</body>
</html>
