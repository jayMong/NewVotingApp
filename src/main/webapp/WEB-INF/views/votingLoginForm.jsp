<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.example.demo.VotingVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Voting관리</title>
<!-- jQuery 사용을 위한 js, css파일 로드 -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/themes/default/jquery.mobile-1.3.1.min.css" />
<script src="<%=request.getContextPath() %>/js/jquery-1.9.1.min.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery.mobile-1.3.1.min.js"></script>

<script>
/* $(window).bind("pageshow", function(event) {
    if (event.originalEvent.persisted) {
        document.location.reload();
    }
}); */
</script>

</head>

<body class="theme" id="loginForm">

<div data-role="page" data-theme="b" id="jqm-Login"> 
	<header data-role="header" data-theme="b">
		<h1>Voting App</h1>
	</header>
	<div data-role="main" class="ui-content" align="center"><br><br>	
	<form class="ui-body ui-body-a ui-corner-all" data-ajax='false' method="post" action="./votingExemList">
	<fieldset>
    	<div data-role="fieldcontain">	
			<label for="emp_id">ID :</label><input type="text" name="emp_id" id="emp_id" >
		</div>
        <input type="submit" data-theme="b" data-inline="true" value="Submit">
    </fieldset>		
	</form>
	</div> 
</div>

</body>
</html>