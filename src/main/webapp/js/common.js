
function showModalWindow(url, openWin, width, height, left, top) {
	  if (width == null)
	    width = 400;

	  if (height == null)
	    height = 500;

	  if (left == null)
	    left = 140;

	  if (top == null)
	    top = 100;

	  var property = "status=no;scroll=no;dialogWidth:"+width+"px;dialogHeight:"+height+"px;dialogLeft="+left+";dialogTop="+top+";center=1"
	  return window.showModalDialog(url, openWin, property);
	}


/***************************************************************************
 * setDateFormat(objField)
 * : 날짜 필드에 입력된 값에 구분기호('-')를 추가한다.
 *
 * 파라미터 :
 *   objField - 입력필드 객체
 *
 * 등록 이벤트 :
 *   onBlur="setDateFormat(this);"
 ***************************************************************************/
function setDateFormat(objField) {
  var srcValue  = objField.value;
  var destValue = "";

  // "년-월"로 표시할 경우
  if (srcValue.length == 6) {
	  destValue = srcValue.substring(0, 4);
	  destValue += '-';
	  destValue += srcValue.substring(4, 6);
  }
  // "년-월-일"로 표시할 경우
  else if (srcValue.length == 8) {
	  destValue = srcValue.substring(0, 4);
	  destValue += '-';
	  destValue += srcValue.substring(4, 6);
	  destValue += '-';
	  destValue += srcValue.substring(6, 8);
  }

  if (destValue != "") objField.value = destValue;
}
 
function setDateFormatVal(srcValue) {
	  //var srcValue  = objField.value;
	  var destValue = "";

	  // "년-월"로 표시할 경우
	  if (srcValue.length == 6) {
		  destValue = srcValue.substring(0, 4);
		  destValue += '-';
		  destValue += srcValue.substring(4, 6);
	  }
	  // "년-월-일"로 표시할 경우
	  else {
		  destValue = srcValue.substring(0, 4);
		  destValue += '-';
		  destValue += setNumberFormat(srcValue.substring(4, 6));
		  destValue += '-';
		  destValue += setNumberFormat(srcValue.substring(6, 8));
	  } 
	  if (destValue != "") return destValue;
	}

function setNumberFormat(srcVale){
	 if(srcVale.length ==1){
		 return '0'+srcVale;
	 }else{
		 return  srcVale;
	 }
}
//날짜 차이 계산 함수
//date1 : 기준 날짜(YYYY-MM-DD), date2 : 대상 날짜(YYYY-MM-DD)
function getDateDiff(date1,date2)
{
  var arrDate1 = date1.split("-");
  var getDate1 = new Date(parseInt(arrDate1[0]),parseInt(arrDate1[1])-1,parseInt(arrDate1[2]));
  var arrDate2 = date2.split("-");
  var getDate2 = new Date(parseInt(arrDate2[0]),parseInt(arrDate2[1])-1,parseInt(arrDate2[2]));
  
  var getDiffTime = getDate1  - getDate2 ;
  
  return parseInt(getDiffTime / (1000 * 60 * 60 * 24));
}


Map = function(){
 this.map = new Object();
};   
Map.prototype = {   
    put : function(key, value){   
        this.map[key] = value;
    },   
    get : function(key){   
        return this.map[key];
    },
    containsKey : function(key){    
     return key in this.map;
    },
    containsValue : function(value){    
     for(var prop in this.map){
      if(this.map[prop] == value) return true;
     }
     return false;
    },
    isEmpty : function(key){    
     return (this.size() == 0);
    },
    clear : function(){   
     for(var prop in this.map){
      delete this.map[prop];
     }
    },
    remove : function(key){    
     delete this.map[key];
    },
    keys : function(){   
        var keys = new Array();   
        for(var prop in this.map){   
            keys.push(prop);
        }   
        return keys;
    },
    values : function(){   
     var values = new Array();   
        for(var prop in this.map){   
         values.push(this.map[prop]);
        }   
        return values;
    },
    size : function(){
      var count = 0;
      for (var prop in this.map) {
        count++;
      }
      return count;
    }
};


var message_box = function() {
	 var button = '<input type="button" onclick="message_box.close_message();" value="확인" />';
	 return {
	   show_message: function(title, body) {
	    if($('#message_box').html() === null) {
	     var message = '<div id="message_box"><br/><h1 >' + title + '</h1>' + body + '<br/>' + button + '</div>';
	     $(document.body).append( message );
	     $(document.body).append( '<div id="darkbg"></div>' );
	     $('#darkbg').show();
	     $('#darkbg').css('height', $('html, body').height());
	 
	     $('#message_box').css('top', $('html, body').scrollTop() + 150);
	     $('#message_box').show('slow');
	    } else {
	     var message = '<h1>' + title + '</h1>' + body + '<br/>' + button;
	     $('#darkbg').show();
	     $('#darkbg').css('height', $('html, body').height());
	 
	     $('#message_box').css('top', $('html, body').scrollTop() + 150);
	     $('#message_box').show('slow');
	     $('#message_box').html( message );
	    }
	   },
	   close_message: function() {
	    $('#message_box').hide('fast');
	    $('#darkbg').hide();
	   }
	  }
	 }();
	 
	 $(document).ready(function() {
	  $("a#CLICK").click(function()
	  {
	      message_box.show_message('Hi!', 'Whatup?!');
	  });
	 });
 