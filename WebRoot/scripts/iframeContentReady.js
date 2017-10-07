/**
 * 自动调整iframe高度
 */
$(document).ready(function() {
	var sh = document.body.scrollHeight;
	parent.document.getElementById("contentFrameId").style.height = sh+"px";
});