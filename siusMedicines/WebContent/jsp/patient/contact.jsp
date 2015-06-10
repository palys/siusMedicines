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
			<h2>Contact</h2>
		</div>

		<c:url value="/j_spring_security_logout" var="logoutUrl" />

		<!-- csrt for log out-->
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		
		<p>
			<a class="btn btn-lg btn-default" href="panel" role="button">Back</a>
		</p>
		
		
		
	</div>
	
	<div class="container">
			<form:form modelAttribute="mail" role="form" method="post"
				action="/siusMedicines/patient/contact">
				<div class="form-group">
					<label for="name">Doctor:</label>
					<form:select path="address" class="form-control" id="address">
						<c:forEach items="${doctors}" var="doc" varStatus="loop"> 
						 	<form:option value="${doc.email}"><c:out value="${doc.name} ${doc.surname}"/></form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="form-group">
					<label for="subject">Subject:</label>
					<form:input path="subject" type="text" class="form-control" id="subject"
						placeholder="Enter subject"></form:input>
				</div>
				<div class="form-group">
					<label for="message">Message:</label>
					<form:input path="message" type="text" class="form-control" id="message"
						placeholder="Enter message here" style="height:300px;"></form:input>
				</div>
				<div class="form-actions">
					<button type="submit" class="btn btn-lg btn-default">Send</button>
				</div>
			</form:form>
		</div>
	
	
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

</body>
</html>
