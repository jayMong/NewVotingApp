<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%//@ page import="com.ktdsframework.foundation.util.Global,com.ktdsframework.foundation.util.HTMLUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>문제관리</title>
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
        url:'<c:url value="/mgmt/votingQuestionMgmtSelect?exem_id=${param.exem_id}"/>',
        editurl:'<c:url value="/mgmt/votingQuestionMgmtUpdate?exem_id=${param.exem_id}"/>',            
        datatype:"json",
        postData:{
          //question_id:function() {return $("#question_id option:selected").val(); }
        },
        colNames:['문제 ID', '문제명', '문제순번','문제유형', '문제정답', '수정자'],
        colModel:[
            {name:'question_id',index:'question_id', width:90, align:'center',editable:true, editoptions:{readonly:'readonly'}},
            {name:'question_nm', index:'question_nm', align:'center',editable:true, editrules:{required:true}, editoptions: { maxlength: 200 }},
            {name:'question_order', index:'question_order', align:'center', editable:true, editrules:{required:true,number:true}, sorttype:'number', formatter:'integer'},               
            {name:'question_type', index:'question_type', align:'center', editable:true, edittype:'select', editoptions:{value:"다지선일:다지선일;다지선다:다지선다;단답형:단답형;주관식:주관식"}},                
            {name:'correct_answer', index:'correct_answer', align:'center', editable:true, editrules:{required:true}, editoptions: { maxlength: 200 }},
            {name:'modifier', index:'modifier', align:'center', editable:true, editoptions: { maxlength: 9 }},
        ],
        //width:900,
        autowidth:true,
        height:225,
        rowNum:10,
        rowList:[10,20,50],
        pager:'#pager2',
        sortname:'question_order',
        sortorder:"desc", 
        rownumbers: true,
        rownumWidth:40,
        caption:"문제관리",
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
            sel_question_id = $("#mgrid").getRowData(ids)['question_id'];
            //alert(sel_m_model_id)
            //$("#sgrid").jqGrid('appendPostData',{m_model_id:sel_m_model_id});
            //$("#sgrid").jqGrid('setGridParam',{postData:{question_id:sel_question_id}});
            $("#sgrid").jqGrid('setGridParam',{url:"<%=request.getContextPath()%>/mgmt/votingItemMgmtSelect?question_id=" + sel_question_id, page:1});
            $("#sgrid").jqGrid('setCaption','Question id Detail : ' + sel_question_id).trigger('reloadGrid');
            
            $('#selected_question_id').val(sel_question_id);
            selected_question_id = sel_question_id;
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
                    parameters[i] = ret.question_id;
                }
                return { question_id:parameters.join()};
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
    //.jqGrid("setColProp", "question_id", { frozen: true,width:80 })
    //.jqGrid("setFrozenColumns")
    //.trigger("reloadGrid", [{ current: true}])
    /* .jqGrid('navButtonAdd', '#pager2', {caption:"",title:"검색", buttonicon :'ui-icon-search',
        onClickButton:function(){
            //$('#pager2')[0].toggleToolbar();
        } 
    }); */
    //$('#pager2')[0].toggleToolbar();  
   
    //Sub Code Setting
    $("#sgrid").jqGrid({
        url:'<c:url value="/mgmt/votingItemMgmtSelect?sel_question_id=${sel_question_id}"/>' ,
        editurl:'<c:url value="/mgmt/votingItemMgmtUpdate?sel_question_id=${sel_question_id}"/>',
        datatype: "json",
        mtype:'POST',
        colNames:['문제ID', '항목ID', '항목명', '항목순번', '수정자'],
        colModel:[{name:'question_id',index:'question_id', editrules:{edithidden:false}, hidden:true, editable:true, edittype:'custom',editoptions:{custom_element:myelem,custom_value:myval}},
                  {name:'item_id', index:'item_id', align:'center', editable:true, editoptions: { readonly:'readonly' }, sortble:true},
                  {name:'item_nm', index:'item_nm', align:'center', editable:true, editrules: { required: true }, sortble:true, editoptions: { maxlength: 200 }},
                  {name:'item_order', index:'item_order', align:'center', editable:true, editrules:{required:true, number:true}, sorttype:'number', formatter:'integer' },
                  {name:'modifier', index:'modifier', editable:true, editoptions: { maxlength: 9 }}
        ],
        //width:900,
        autowidth:true,
        height:225,
        rowNum:10,
        rowList:[10,20,50],
        sortname: 'item_order',
        sortorder: 'asc',
        pager: '#pager3',
        rownumbers:true,
        rownumWidth:40,
        caption:"항목관리",
        multiselect: true,
        gridview :true,
        viewrecords:true,
        //scroll:false,
        jsonReader:{
            repeatitems:false
        },
        gridComplete :function() {     //실행완료시!
            var tm = jQuery("#sgrid").jqGrid('getGridParam','totaltime');  //load time가져오기!
            $("#load_time2").html("Render time:"+ tm+" ms ");
        },
        loadError :function(xhr,st,err) {      //server load error message 뿌리기
            jQuery("#sgrid").html("Type:"+st+"; Response:"+ xhr.status + " "+xhr.statusText);
        }
    });
    $("#sgrid").jqGrid("setFrozenColumns");

    $("#sgrid").jqGrid(
            'navGrid',
            '#pager3',
            {edit:true, add:true, del:true, view:true, search:true},
            {closeAfterEdit:true, reloadAfterSubmit:true,
                afterShowForm: function(formid){
                    // change the selector appropriately
                    sel_question_id = $("#selected_question_id").val();
                    $('#jqGrid input[name=question_id]').attr('type','hidden');
                    $('#jqGrid input[name=question_id]').attr('value', sel_question_id);
                   // or $('#jqGrid input[name=game_id]').attr('disabled','disabled');
                },
                onclickSubmit:function(options,postdata){
                    sel_question_id = $("#selected_question_id").val();
                    $('#jqGrid input[name=question_id]').attr('type','hidden');
                    $('#jqGrid input[name=question_id]').attr('value', sel_question_id);
                    return { question_id:sel_question_id}; 
                }
            }, // default settings for edit
            {closeAfterAdd:true, reloadAfterSubmit:true,
                afterShowForm:function(formid){
                    sel_question_id = $("#selected_question_id").val();
                    if(sel_question_id == '' || sel_question_id == null){
                        alert("상단의 문제ID를 먼저 선택하세요.");
                        jQuery('.ui-jqdialog-titlebar-close').click();
                        return;
                    }
                    $('#jqGrid input[name=question_id]').attr('type','hidden');
                    $('#jqGrid input[name=question_id]').attr('value', sel_question_id);
                    return { question_id:sel_question_id};
                },
                onclickSubmit:function(options,postdata){
                    sel_question_id = $("#selected_question_id").val();
                    $('#jqGrid input[name=question_id]').attr('type','hidden');
                    $('#jqGrid input[name=question_id]').attr('value', sel_question_id);
                    return { question_id:sel_question_id}; 
                }
            }, // default settings for add
            { 
                onclickSubmit:function (options, postdata) {
                    var s;
                    s = jQuery("#sgrid").jqGrid('getGridParam','selarrrow');
                    var parameter1 = new Array();
                    var parameter2 = new Array();
                    var parameter3 = new Array();

                    for(var i = 0; i < s.length; i++) {
                        var ret = jQuery("#sgrid").jqGrid('getRowData',s[i]);
                        parameter1[i] = ret.question_id;
                        parameter2[i] = ret.item_id;
                        parameter3[i] = ret.modifier;

                    }

                    return { question_id:parameter1.join(),
                             item_id:parameter2.join(),
                             modifier:parameter3.join()
                    };
                }
            },//Delete
            {sopt : ['eq','in']} //Search
       );
});

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
    <form name="searchForm" id="searchForm" method="post" action="<%=request.getContextPath()%>/mgmt/votingQuestMgmtList?exem_id=<%=request.getParameter("exem_id")%>">
    <input type="hidden" name="selected_question_id" id="selected_question_id" value="${selected_question_id}">
    <table id="mgrid" style='width:400px'></table>
    <div id="pager2"></div>
    <div id="load_time"></div>
    <br />
    <table id="sgrid" style='width:400px'></table>
    <div id="pager3"></div>
    <div id="load_time2"></div>
    </form>
</div>

</body>
</html>