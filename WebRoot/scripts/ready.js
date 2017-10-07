/**
 * 获取cookie中的每页显示多少条记录(rapidsh_cookie_page_size|rapidsh_cookie_page_size_popwindow)，然后显示到页面上输入每页显示记录数的文本框内
 * pageSizeId|pagePopSizeId页面上输入每页显示记录数的文本框ID
 */
$(document).ready(function() {
	$('#pageSizeId').val(rapidsh.getCookie('rapidsh_cookie_page_size'));
	$('#pagePopSizeId').val(rapidsh.getCookie('rapidsh_cookie_page_size_popwindow'));
});

/**
 * 10秒后自动关闭提示信息
 */
$(document).ready(function() {
	setTimeout("$('.notif').fadeTo(500,0).slideUp();",10000); 
});

/**
 * 打开/关闭搜索栏
 * openAdvanceSearchImgId-打开/关闭按钮id
 * pageBarSearchDivId-搜索表单DIV id
 * pageActionBarId-整个列表头栏目DIV id
 * */
$(document).ready(function() {
	$('#openAdvanceSearchImgId').click(function() { 
		if ( !$('#pageActionBarId').hasClass('h100') ){
			$('#pageActionBarId').addClass(" h100");
    		$('#pageBarSearchDivId').attr("style","display:block;");
    		$('#openAdvanceSearchImgId').attr('title', '关闭搜索栏');
    		$('#openAdvanceSearchImgId').attr('src', '../../styles/images/expansion.png');
    		var sh = document.body.scrollHeight;
    		parent.document.getElementById("contentFrameId").style.height = sh+"px";
    	} else {
    		$('#pageActionBarId').removeAttr("class");
			$('#pageActionBarId').addClass("pageActionBar");
			$('#pageBarSearchDivId').attr("style","display:none;");
			$('#openAdvanceSearchImgId').attr('title', '打开搜索栏');
			$('#openAdvanceSearchImgId').attr('src', '../../styles/images/fold.png');
			var sh = document.body.scrollHeight;
			parent.document.getElementById("contentFrameId").style.height = sh+"px";
    	}
	});
});

