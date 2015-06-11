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
			<h2>Medicines</h2>
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
			<div id="medicines" class="tab-pane fade in active">
				<div class="panel panel-default">
					<div class="panel-heading">Medicines</div>
					<c:if test="${medicines_count == 0}">
						<p> <br>&nbsp; &nbsp; No medicines</p>
					</c:if>
					<c:if test="${medicines_count != 0}">
						<table class="table table-hover">
							<col width="5%">
							<col width="20%">
							<col width="60%">
							<col width="15%">
							<tr>
								<th></th>
								<th>Medicine name</th>
								<th>Description</th>
								<th>Meal Requirement</th>
							</tr>
							<c:forEach items="${medicines}" var="medicine_item"
								varStatus="loop">
								<tr>
									<td></td>
									<td>&nbsp; &nbsp;<c:out value="${medicine_item.name}" /></td>
									<td>&nbsp; &nbsp;<c:out value="${medicine_item.description}" /></td>
									<td>&nbsp; &nbsp;<c:out value="${medicine_item.mealInfo}" /></td>
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
