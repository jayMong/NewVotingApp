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

<script>
/* $(window).bind("pageshow", function(event) {
    if (event.originalEvent.persisted) {
        document.location.reload();
    }
}); */
$(document).ready(function(){
                     
        $.ajax({
            url : "<c:url value='/voting/votingUserExemSelect?emp_id=${emp_id}'/>",
            dataType : "json",
	        //data:"", 
            type : "POST",
    	    cache:false,
            success : function(data) {
            	//alert('data.msg: '+data.msg);
            	//alert('data.rows: '+data.rows);
            	//alert('data.records: '+data.records);

	         	// 전송받은 객체들을 테이블에 내용을 동적으로 표시한다.
	         	var list = data["rows"];
	         	var vAppendLi = "";

            	//alert('list.length: '+list.length);
	         	if( list == undefined || list.length == 0 ) {
	                //조회 결과가 없다면, 다음 메세지를 보여준다. 
	                //$("#listView2").find(".messageRow").text("조회 된 데이터가 없습니다.");
	         		vAppendLi = "<li>조회 된 데이터가 없습니다.</li>"
	            	$('#listView').append(vAppendLi);
	         		
	            } else {
	                //데이터가 tbody 에 tr 로서 들어가야 하기 때문에, tbody 내부를 모두 비워야 한다. 기존에 데이터가 이미 조회 돼 있던 경우에도
	                //이 부분에서 tbody가 클리어되면서 새로운 데이터가 들어갈 상태가 된다. 
	                $("#listView").empty();
	            }
	         	
	            $(list).each(
		            function(key,value){
		            	var item = this;  
		            	//alert("=>"+key + ":"+item.exem_nm);
		            	//console.log(key, value);
		            	vAppendLi = "<li><a data-ajax='false' href='./votingQuestionList?exem_id="+ item.exem_id + "&emp_id=${emp_id}'>"+ item.exem_nm +"</a></li>"
		            	//$("#listView2").append("<li>"+ item.exem_nm +"</li>");
		            	$('#listView').append(vAppendLi);
		            	//alert(item.exem_nm);

	   				}
   				);
            	$('#listView').listview("refresh");
            },
	        // ajax 통신 실패시
	        error:function(request,status,error){
	            alert("code : " + request.status + "\r\nmessage : " + request.reponseText);
	        }
        });
});
</script>

</head>
<body>

<div data-role="page" data-theme="b" id="jqm-home"> 
	<header data-role="header">
		<h1>메인</h1>
	</header>
	<div data-role="content"> 		
		<ul id="listView" data-role="listview" data-theme="c" > 
		<!-- display 영역 -->
		</ul> 
	</div> 
	<footer data-role="footer" data-position="fixed">
		<nav data-role="navbar">
			<ul>
				<li><a data-ajax='false' href="./votingExemList?emp_id=${emp_id}" data-icon="star">HOME</a></li>
			</ul>
		</nav>
	</footer>
</div>
</body>
</html>