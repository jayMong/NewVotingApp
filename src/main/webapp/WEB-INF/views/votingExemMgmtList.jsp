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
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/jquery-ui/custom/css/ui-lightness/jquery-ui-1.10.3.custom.css" />
<script src="<%=request.getContextPath() %>/js/jquery-1.9.1.min.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-ui/custom/js/jquery-ui-1.10.3.custom.js" ></script>

<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/js/jqGrid-4.5.2/css/ui.jqgrid.css" />

<script src="<%=request.getContextPath()%>/js/jqGrid-4.5.2/js/i18n/grid.locale-kr.js"></script>
<script src="<%=request.getContextPath()%>/js/jqGrid-4.5.2/js/jquery.jqGrid.min.js"></script>

<script>

$(function() {
    $("#mgrid").jqGrid({
        url:'<c:url value="/mgmt/votingExemMgmtSelect"/>',
        editurl:'<c:url value="/mgmt/votingExemMgmtUpdate"/>',            
        datatype:"json",
        postData:{
          exem_id:function() {return $("#exem_id option:selected").val(); }
        },
        colNames:['문제보기','Exem ID', 'Exem 이름','Exem Type', '문제 수', 'Exem 상태', 'Exem 시작일', 'Exem 종료일', 'Exem 만든 사람'],
        colModel:[
            {name:'exem_link',index:'exem_link', width:40, align:'center',editable:false, formatter:ExemLink},
            {name:'exem_id',index:'exem_id', width:40, align:'center', editable:true, editoptions:{readonly:'readonly'}},
            {name:'exem_nm', index:'exem_nm', align:'center',editable:true, editrules:{required:true}, editoptions: { maxlength: 200 }},
            {name:'exem_type', index:'exem_type', align:'center', editable:true, edittype:'select', editoptions:{value:"Q:문제;S:설문"}}, 
            {name:'question_total_count', index:'question_total_count', align:'center', editable:true, editoptions: { maxlength: 9 }, editrules:{number:true}, sorttype:'number', formatter:'integer' },       
            {name:'exem_status', index:'exem_status', align:'center', editable:true, edittype:'select', editoptions:{value:"N:시행전;P:진행중;E:종료"}},                
            {name:'start_date', index:'start_date', align:'center', editable:true, editrules:{date:true}, formatter: 'date', formatoptions: { newformat: 'Y-m-d'}},               
            {name:'end_date', index:'end_date', align:'center', editable:true, editrules:{date:true}, formatter: 'date', formatoptions: { newformat: 'Y-m-d'}},
            {name:'modifier', index:'modifier', align:'center', editable:true, editoptions: { maxlength: 9 }},
        ],
        //width:900,
        autowidth:true,
        height:225,
        rowNum:10,
        rowList:[10,20,50],
        pager:'#pager2',
        sortname:'exem_id',
        sortorder:"desc", 
        rownumbers: true,
        rownumWidth:40,
        caption:"Exem관리",
        viewrecords: true,
        gridview: true,         ////처리속도를 빠르게 함. 다음 모듈엔 사용할 수 없다!! ==> treeGrid, subGrid, afterInsertRow(event)
        shrinkToFit:true,       //우측스크롤바 위의 조그만 공간 없애고 거기까지 width채움  default:true
        jsonReader:{
            repeatitems:false
        },
        mtype : 'POST',
        multiselect:true,
        loadui:'block',
        gridComplete :function() {     //실행완료시!
            var tm = jQuery("#mgrid").jqGrid('getGridParam','totaltime');  //load time가져오기!
            $("#load_time").html("Render time:"+ tm+" ms ");
        },
        loadError :function(xhr,st,err) {      //server load error message 뿌리기
            jQuery("#mgrid").html("Type:"+st+"; Response:"+ xhr.status + " "+xhr.statusText);
        },
        onSelectRow: function(ids) {
            sel_exem_id = $("#mgrid").getRowData(ids)['exem_id'];
            //alert(sel_m_model_id)
            //$("#sgrid").jqGrid('appendPostData',{m_model_id:sel_m_model_id});
            //$("#sgrid").jqGrid('setGridParam',{url:"<%//=request.getContextPath()%>/mgmt/votingMgmtSelectDetail?exem_id=" + sel_exem_id, page:1}); --%>
            $("#sgrid").jqGrid('setGridParam',{postData:{exem_id:sel_exem_id}});
            
            $("#sgrid").jqGrid('setCaption','Exem_id Detail : ' + sel_exem_id).trigger('reloadGrid');
            
            $('#selected_exem_id').val(sel_exem_id);
            //sel_sub_code_id = sel_exem_id;
        }
    }).jqGrid('navGrid', '#pager2',
        {edit:true, add:true, del:true, search:true, view:true, refesh:true}, //options  //search:true, 버튼 2개 나옴 ㅜㅜ
        {closeAfterEdit:true, reloadAfterSubmit:true}, // default settings for edit
        {closeAfterAdd:true, reloadAfterSubmit:true}, // default settings for add
        { //del
            onclickSubmit:function (options, postdata) {
                var s;
                s = $("#mgrid").jqGrid('getGridParam','selarrrow');
                var parameters = new Array();
        
                for(var i = 0; i < s.length; i++) {
                    var ret = $("#mgrid").jqGrid('getRowData',s[i]);
                    parameters[i] = ret.exem_id;
                }
                return { exem_id:parameters.join()};
            }
        },
        {sopt : ['eq','in']}, 
        /*
         * http://www.trirand.com/jqgridwiki/doku.php?id=wiki:singe_searching
        Use this option to set common search rules. If not set all the available options will be used. 
        jqGrid "sopt" option are:(sopt) ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc','nu','nn'] 
        The corresponding texts are in language file and mean the following:(odata) ['equal','not equal', 'less', 'less or equal','greater','greater or equal', 'begins with','does not begin with','is in','is not in','ends with','does not end with','contains','does not contain','is null','is not null'] 
        Note that the elements in sopt array can be mixed in any order.
        */
        {} // view options    //jqModal:false, width:400
    );
    //.jqGrid('inlineNav',"#pager2")
    //.jqGrid("destroyFrozenColumns")
    //.jqGrid("setColProp", "exem_id", { frozen: true,width:80 })
    //.jqGrid("setFrozenColumns")
    //.trigger("reloadGrid", [{ current: true}])
    /* .jqGrid('navButtonAdd', '#pager2', {caption:"",title:"검색", buttonicon :'ui-icon-search',
        onClickButton:function(){
            //$('#pager2')[0].toggleToolbar();
        } 
    }); */
    //$('#pager2')[0].toggleToolbar();  

});

function ExemLink(cellvalue, options, rowObject){
    return "<a href='<%=request.getContextPath()%>/mgmt/votingQuestionMgmtList?exem_id="+cellvalue+"'>"+cellvalue+"</a>";
 }
 
function myelem(value,options){
    return $('<input type="hidden" value="'+value+'" disabled="disabled"/>');
 }

 function myval(elem){
     return elem.val();
 }
</script>

</head>
<body>
<div id="main" style="width:90%">
    <form name="searchForm" id="searchForm" method="post" action="<%=request.getContextPath()%>/mgmt/votingExemMgmtSelect">
    <input type="hidden" name="selected_exem_id" id="selected_exem_id" value="${selected_exem_id}">
    <table id="mgrid" style='width:400px'></table>
    <div id="pager2"></div>
    <div id="load_time"></div>
    </form>
</div>

</body>
</html>