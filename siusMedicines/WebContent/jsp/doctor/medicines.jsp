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
			<h2>Manage medicines</h2>
		</div>

		<c:url value="/j_spring_security_logout" var="logoutUrl" />

		<!-- csrt for log out-->
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>

		<p>
			<a class="btn btn-lg btn-default" href="medicines/add" role="button">Add</a>
			<a class="btn btn-lg btn-default"
				href="${pageContext.request.contextPath}/doctor/panel" role="button">Back</a>
		</p>

		<div class="tab-content">
			<div id="delete_medicine" class="tab-pane fade in active">
				<div class="panel panel-default">
					<div class="panel-heading">Medicines</div>
					<table class="table table-hover">
						<col width="95%">
						<col width="5%">
						<tr>
							<th>Medicine name</th>
							<th></th>
						</tr>
						<c:forEach items="${medicines}" var="medicines_list"
							varStatus="loop">
							<tr>
								<td><c:out value="${medicines_list.name}" /></td>
								<td><a class="btn btn-default"
									href="${pageContext.request.contextPath}/doctor/medicines/remove?medicine_name=${medicines_list.name}">
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
