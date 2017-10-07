rapidsh = {};
/**
 * 閫夋嫨浼犲叆鐨刦orm鍒楄〃閲屾墍鏈夌殑checkbox
 * theForm-浼犲叆鐨勮〃鍗曞璞�
 */
rapidsh.checkAllBox = function(theForm) {
	for (var i=0;i<theForm.elements.length;i++) {
	    var e = theForm.elements[i];
			var eName = e.name;
	    	if (eName != 'allbox' && 
	            (e.type.indexOf("checkbox") == 0)) {
	        	e.checked = theForm.allbox.checked;	        	
			}
	}; 
};

/**
 * 璁剧疆cookie
 * name-cookie鐨勫悕瀛�
 * value-cookie鐨勫�
 * expire-cookie杩囨湡鏃堕棿
 */
rapidsh.setCookies = function(name, value, expire){
	var date=new Date(); 
	var expireDays=expire; 
	date.setTime(date.getTime()+expireDays*24*3600*1000); 
	var str=name+"="+escape(value);  
	str+="; expires="+date.toGMTString();
	document.cookie=str;
};

/**
 * 鑾峰彇cookie
 * name-cookie鐨勫悕瀛�
 */
rapidsh.getCookie = function(name){
	 var cookieArray=document.cookie.split("; "); 
	 for(var i=0;i<cookieArray.length;i++){
	  var arr=cookieArray[i].split("=");    
	  if(arr[0]==name)return unescape(arr[1]); 
	 }
	 return "";
	};

/**
 * 鍒犻櫎cookie
 * name-cookie鐨勫悕瀛�
 */	
rapidsh.deleteCookie = function(name){
	 this.setCookie(name,"",{expireDays:-1}); 
};

/**
 * 鍩烘湰鐨凙jax璇锋眰
 * type-璇锋眰绫诲瀷(POST|GET)
 * url-璇锋眰鍦板潃
 * data-闇�浼犲叆鐨勫弬鏁帮紝e.g:name=John&location=Boston
 * msg-璇锋眰鎵ц瀹屾垚鏄剧ず鎻愮ず淇℃伅
 */
rapidsh.BasicAjaxRequest = function(type, url, data, msg){
	$.ajax({
		   type: type,
		   url: url,
		   data: data,
		   success: function(msg){
		     alert( msg );
		   }
	});
};

/**
 * 浣跨敤css鍏冪礌鐨�display:none' 灞炴�瀹炵幇闅愯棌鍜屾樉绀�
 * 璇ユ柟娉曢殣钘忓悗鍦ㄩ〉闈㈠北涓嶄細鍗犳嵁鍏冪礌绌洪棿澶у皬鐨勪綅缃�
 * targetId-浼犲叆鐨勫厓绱爄d
 * */
rapidsh.toggleDisplay = function(targetId){
    if (document.getElementById) {
        target = document.getElementById(targetId);
    	if (target.style.display == "none"){
    		target.style.display = "";
    	} else {
    		target.style.display = "none";
    	}
    }
};

/**
 * 浣跨敤css鍏冪礌鐨�visibility:hidden' 灞炴�瀹炵幇闅愯棌鍜屾樉绀�
 * 璇ユ柟娉曢殣钘忓厓绱犲悗锛屽叾鍏冪礌鍘熸潵鐨勭┖闂翠綅缃笉鍙橈紝鍙槸涓嶈兘鐪嬪埌璇ュ厓绱狅紝鏄剧ず涓虹┖鐧�
 * */
rapidsh.toggleVisibility = function(targetId) {
	if (document.getElementById) {
		target = document.getElementById(targetId);
		if (target.style.visibility == "hidden"){
			target.style.visibility = "visible";
		} else {
			target.style.visibility = "hidden";
		}
	}
};

/**
 * 瀵瑰垪琛ㄧ殑璁板綍杩涜鍒犻櫎銆佹煡鐪嬨�淇敼鐨勬搷浣�
 * formId-form鐨処D
 * url-璇锋眰URL鍦板潃
 * type-鎿嶄綔绫诲瀷锛屽垹闄�del)銆佹煡鐪�view)銆佷慨鏀�edit)
 * */
rapidsh.actionPerformance = function(formId, url, type) {
	if( type =="del" || type =="send"){
		 if( $("input:checked").length >= 1 ){
			 $('#'+formId+'').attr("action", url);
			 $('#'+formId+'').attr("method", "post");
			 $('#'+formId+'').submit();
		 }else{
			 alert("请至少勾选一条记录!!");
			 return;
		 }
	 }
	 if( type =="edit" || type =="view"){		 
		 if( $("input:checked").length==1 ){
			 $('#'+formId+'').attr("action", url);
			 $('#'+formId+'').attr("method", "get");
			 $('#'+formId+'').submit();
		 }else{
			 //alert("请勾选一条记录!!");
			 if($("input:checked").length < 1)
				 alert("请勾选一条记录!!");
			 else
				 alert("[修改]/[查看]最多只能选中一条记录!!!");
			 return;
		 }
	 }
};

rapidsh.actionWriteReport = function(formId, url) {
	if( $("input:checked").length==1 ){
		 $('#'+formId+'').attr("action", url);
		 $('#'+formId+'').attr("method", "get");
		 $('#'+formId+'').submit();
	 }else{
		 //alert("请勾选一条记录!!");
		 if($("input:checked").length < 1)
			 alert("请勾选一条记录!!");
		 else
			 alert("最多只能选中一条记录!!!");
		 return;
	 }
};

/**
 * lookup 寮瑰嚭绐楀彛锛岃繑鍥炴暟鎹簱閲宺eturnfields瀹氫箟鐨勫瓧娈�
 * */
rapidsh.lookup = function(urlToOpen){  
	var window_width = screen.availWidth/2;  
	var window_height = screen.availHeight/2;  
	var window_left = (screen.availWidth/2)-(window_width/2);  
	var window_top = (screen.availHeight/2)-(window_height/2);  
	var winParms = "status=no,location=no,directories=no,menubar=no" + ",resizable=yes" + ",height="+window_height+",width="+window_width + ",left="+window_left+",top="+window_top;  
	var newwindow = window.open(urlToOpen,'_blank',winParms);  
	newwindow.focus();  
};
/**
 * 寮瑰嚭涓婁紶鏂囦欢绐楀彛
 * */
rapidsh.popUpload = function (urlToOpen){
	var window_width = screen.availWidth/2;  
	var window_height = screen.availHeight/2;  
	var window_left = (screen.availWidth/2)-(window_width/2);  
	var window_top = (screen.availHeight/2)-(window_height/2);  
	var winParms = "status=no,location=no,directories=no,menubar=no" + ",resizable=yes" + ",height="+window_height+",width="+window_width + ",left="+window_left+",top="+window_top;  
	var newwindow = window.open(urlToOpen,'_blank',winParms);  
	newwindow.focus();  
};

rapidsh.popNewForm = function(urlToOpen){
	var window_width = screen.availWidth * 4 / 5;  
	var window_height = screen.availHeight * 4 / 5;  
	var window_left = (screen.availWidth/2)-(window_width/2);  
	var window_top = (screen.availHeight/2)-(window_height/2);  
	var winParms = "status=no,location=no,directories=no,menubar=no" + ",resizable=yes" + ",height="+window_height+",width="+window_width + ",left="+window_left+",top="+window_top;  
	var newwindow = window.open(urlToOpen,'_blank',winParms);  
	newwindow.focus();  
}

rapidsh.popVisitForm = function(formId, url){
	if( $("input:checked").length==1 ){
		var newUrl = url + "?visitId=" + $("input:checked").val();
		var window_width = screen.availWidth * 4 / 5;  
		var window_height = screen.availHeight * 4 / 5;  
		var window_left = (screen.availWidth/2)-(window_width/2);  
		var window_top = (screen.availHeight/2)-(window_height/2);  
		var winParms = "status=no,location=no,directories=no,menubar=no" + ",resizable=yes" + ",height="+window_height+",width="+window_width + ",left="+window_left+",top="+window_top;  
		var newwindow = window.open(newUrl,'_blank',winParms);  
		newwindow.focus();  
	 }else{
		 //alert("请勾选一条记录!!");
		 if($("input:checked").length < 1)
			 alert("请勾选一条记录!!");
		 else
			 alert("[修改]/[查看]最多只能选中一条记录!!!");
		 return;
	 }
}

rapidsh.popConRecordInfoForm = function(formId, url){
	if( $("input:checked").length==1 ){
		var newUrl = url + "?recordId=" + $("input:checked").val();
		var window_width = screen.availWidth * 4 / 5;  
		var window_height = screen.availHeight * 4 / 5;  
		var window_left = (screen.availWidth/2)-(window_width/2);  
		var window_top = (screen.availHeight/2)-(window_height/2);  
		var winParms = "status=no,location=no,directories=no,menubar=no" + ",resizable=yes" + ",height="+window_height+",width="+window_width + ",left="+window_left+",top="+window_top;  
		var newwindow = window.open(newUrl,'_blank',winParms);  
		newwindow.focus();  
	 }else{
		 //alert("请勾选一条记录!!");
		 if($("input:checked").length < 1)
			 alert("请勾选一条记录!!");
		 else
			 alert("[修改]/[查看]最多只能选中一条记录!!!");
		 return;
	 }
}
