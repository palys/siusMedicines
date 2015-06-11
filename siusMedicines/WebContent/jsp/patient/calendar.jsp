<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href="<c:url value='/resources/css/responsive-calendar.css'/>" rel="stylesheet">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>

<body>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
	<script>
	$(document).ready(function () {
		
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var time = year + "-" + month;
		
		var dailyCountsString = $("[id^='cal']").data('counts');
		var map = {};
		
		var t = dailyCountsString.replace(new RegExp("=","g"),":");
		t = t.replace(new RegExp("{","g"),"");
		t = t.replace(new RegExp("}","g"),"");
		t = t.replace(new RegExp(" ","g"),"");
		var items = t.split(",");
		
		for(var i in items){
			var splited = items[i].split(":");
			var key = splited[0];
			var value = splited[1];
			map[key] = value;
		}
		
		var events = {};
		
		for(var i in map){
			events[i] = {"number" : map[i]};
		}

		var dailyPortions = $("[id^='cal']").data('portions');
		
		function addLeadingZero(num) {
		    if (num < 10) {
		      return "0" + num;
		    } else {
		      return "" + num;
		    }
		  }
		
		
        $(".responsive-calendar").responsiveCalendar({
          time: time,
          allRows : false,
          events: events,
          onActiveDayClick: function(events) {
            var thisDayEvent, key;

            key = $(this).data('year')+'-'+addLeadingZero( $(this).data('month') )+'-'+addLeadingZero( $(this).data('day') );
            thisDayEvent = events[key];
            $('#modal2').trigger('openModal');
            $('#dateID').attr("title", key);
            document.getElementById("myHperlink").innerHTML = key;
          }
        });
      });
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
			<h2>Calendar</h2>
		</div>

		<c:url value="/j_spring_security_logout" var="logoutUrl" />

		<!-- csrt for log out-->
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		
		<p>
			<a class="btn btn-lg btn-default" href="panel" role="button">Back</a>
		</p>
		
		<div class="container">
      <!-- Responsive calendar - START -->
    	<div id="cal" class="responsive-calendar" data-counts="${dailyCounts}" data-portions="${dailyPortions}">
        <div class="controls">
            <a class="pull-left" data-go="prev"><div class="btn btn-primary">Previous Month</div></a>
            <h4><span data-head-year></span> <span data-head-month></span></h4>
            <a class="pull-right" data-go="next"><div class="btn btn-primary">Next Month</div></a>
        </div><hr/>
        <div class="day-headers">
          <div class="day header">Mon</div>
          <div class="day header">Tue</div>
          <div class="day header">Wed</div>
          <div class="day header">Thu</div>
          <div class="day header">Fri</div>
          <div class="day header">Sat</div>
          <div class="day header">Sun</div>
        </div>
        <div class="days" data-group="days">
          
        </div>
      </div>
      <!-- Responsive calendar - END -->
    </div>
		
	</div>
	 
	<div class="easy-modal" id="modal2">
		<h3><span id="myHperlink"></span></h3>
		<table class="table table-hover">
							<col width="15%">
							<col width="30%">
							<col width="25%">
							<col width="30%">
							<tr>
								<th>Time</th>
								<th>Medicine Name</th>
								<th>Quantity</th>
								<th>Meal Requirement</th>
							</tr>
							<c:set var="dayq" value='2015-06-11'></c:set>
							<c:forEach items="${dailyPortions[dayq]}" var="por">
								<tr>
									<td><fmt:formatDate value="${por.takeTime}" pattern="HH:mm"/></td>
									<td><c:out value="${por.prescription.medicine.name}"/></td>
									<td><c:out value="${por.size} ${por.unit}"/></td>
									<td><c:out value="${por.prescription.medicine.mealInfo}"/></td>
								</tr>
							</c:forEach>
							
		</table>
	</div>
	
	<script type="text/javascript">
	$(function() {
		$('.easy-modal').easyModal({
			top: 200,
			overlay: 0.2
		});

		$('.easy-modal-open').click(function(e) {
			var target = $(this).attr('href');
			$(target).trigger('openModal');
			e.preventDefault();
		});

		$('.easy-modal-close').click(function(e) {
			$('.easy-modal').trigger('closeModal');
		});

		$('.easy-modal-animated').easyModal({
			top: 200,
			overlay: 0.2,
			transitionIn: 'animated bounceInLeft',
			transitionOut: 'animated bounceOutRight',
			closeButtonClass: '.animated-close'
		});
	});
	</script>
	<style type="text/css">
	body 
	.easy-modal,
	.easy-modal-animated {
		width: 600px;
		padding: 2em;
		box-shadow: 1px 1px 3px rgba(0,0,0,0.35);
		background-color: white;
	}
	</style>

<script src="<c:url value='/resources/js/responsive-calendar.js'/>"></script>
<script src="<c:url value='/resources/js/jquery.easyModal.js'/>"></script>
</body>
</html>
