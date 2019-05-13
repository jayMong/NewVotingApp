<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.example.demo.VotingVO"%>
<%//@ page import="com.ktdsframework.foundation.util.Global,com.ktdsframework.foundation.util.HTMLUtil"%>
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
<script src="<%=request.getContextPath() %>/js/jquery-ui/custom/js/jquery-ui-1.10.3.custom.js" ></script>

<script>
/* $(window).bind("pageshow", function(event) {
if (event.originalEvent.persisted) {
    document.location.reload();
}
}); */
$(document).ready(function(){          
        $.ajax({
            url : "<c:url value='/voting/votingUserItemSelect?question_id=${question_id}&emp_id=${emp_id}'/>",
            dataType : "json",
	        data:"", 
            type : "POST",
    	    cache:false,
            success : function(data) {
            	//alert('a: '+data.msg);
            	//alert('a: '+data.rows);
            	//alert('a: '+data.records);
            	
				$("#labelMessage").text("${question_nm}");
				
	         	// 전송받은 객체들을 테이블에 내용을 동적으로 표시한다.
	         	var list = data["rows"];
	         	var questionItemArr = $("#questionItems");
	         	questionItemArr.empty();
	         	//questionItemArr.append("<legend>${question_nm}</legend>");
	         	
	         	var vAppendLi = "";	//"<select name='${question_id}' id='${question_id}'>";
	         	//alert('exemid: ${exem_id}');var myselect = $("#questionItems");
	         	
	         	if( list == undefined || list.length == 0 ) {
	                //조회 결과가 없다면, 다음 메세지를 보여준다. 
	                //$("#listView2").find(".messageRow").text("조회 된 데이터가 없습니다.");
	         		vAppendLi = "<label><input type='radio' name='radioItem' value='' />조회 된 데이터가 없습니다.</label>";
	         		questionItemArr.append(vAppendLi);
	         		
	            } else {
	                //데이터가 tbody 에 tr 로서 들어가야 하기 때문에, tbody 내부를 모두 비워야 한다. 기존에 데이터가 이미 조회 돼 있던 경우에도
	                //이 부분에서 tbody가 클리어되면서 새로운 데이터가 들어갈 상태가 된다. 
	                
	            }
	         	
	            $(list).each(
		            function(key,value){
		            	var item = this;  
		            	//alert("=>"+key + ":"+item.exem_nm);
		            	//console.log(key, value);
		            	vAppendLi = "<label><input type='radio' name='radioItem' value='"+ item.item_id + "' />"+ item.item_nm +"</label><br>";
		            	//$("#listView2").append("<li>"+ item.exem_nm +"</li>");
		            	questionItemArr.append(vAppendLi);
		            	//alert(vAppendLi);
		            }
   				);
            	//$('#questionItems').listview("refresh");
            	$("input[type='radio']").attr("checked",true).checkboxradio("refresh");
            },
	        // ajax 통신 실패시
	        error:function(request,status,error){
	            alert("code : " + request.status + "\r\nmessage : " + request.reponseText);
	        }
        });
});

function radio1() {
	//$("#labelMessage").text("Catis selected.");
}
 
function radio2() {
	//$("#labelMessage").text("Dogis selected.");
}
 
function radio3() {
	//$("#labelMessage").text("Hamsteris selected.");
}
 
function findSelected(form) {
	for(var i = 0; i < form.radioItem.length;i++) {
		if(form.radioItem[i].checked == true) {
			var message = form.radioItem[i].value; 	// +"  is selected.";
			//$("#labelMessage").text(message);
			$('#answer').val(message);
		}
	}
	$('#QuestionHistorySubmit').submit();
	
}


function initSelected() {
	$("input[type='radio']").attr("checked",true).checkboxradio("refresh");
}
 
</script>
  
</head>
<body class="theme" id="ItemForm">

<div data-role="page" data-theme="b" id="jqm-home3"> 
	<header data-role="header">
		<h1>메인</h1>
	</header>
	<div data-role="content" data-theme="b" data-role="fieldcontain">
		<p><h2><span id="labelMessage">Press Button.</span></h2></p>
		<form id="QuestionHistorySubmit" data-ajax='false' method="post" action="./votingQuestionHistoryUpdate">
		
		<input type="hidden" name="emp_id" id="emp_id" value="${emp_id}">
		<input type="hidden" name="exem_id" id="exem_id" value="${exem_id}">
		<input type="hidden" name="question_id" id="question_id" value="${question_id}">
		<input type="hidden" name="answer" id="answer" value="">
		<fieldset>
    	<fieldset data-role="fieldcontain" data-theme="b" id="questionItems"> 
		</fieldset>
        <br> 
		<input type="button" data-theme="b" data-inline="true" value="선택" onClick="findSelected(this.form);">

	</div> 
	<footer data-role="footer" data-position="fixed">
		<nav data-role="navbar">
			<ul>
				<li><a data-ajax='false' href="./votingExemList?emp_id=${emp_id}" data-icon="star">HOME</a></li>
			</ul>
		</nav>
	</footer>
</div>
 
</form>
 
</body>
</html>