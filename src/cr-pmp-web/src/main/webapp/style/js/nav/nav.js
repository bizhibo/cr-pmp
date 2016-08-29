$(function() {
	$("#qbtn").bind("click", function() {
		var url = "/nav/pageList.do";
		selectPage(url);
	});
	$("#qbtn").trigger("click");
	$("#addbtn").bind("click", function() {
		var name = $("#addName").val();
		var level = $("input[name='addLevel']:checked").val();
		var type = $("input[name='addType']:checked").val();
		var url = "";
		var pid = "";
		if (level == 2 || level == 3) {
			pid = $("#addpid").val();
		}
		if (type == "DYNAMIC") {
			url = $("#addUrl").val();
		}
		var data = {
			"name" : name,
			"level" : level,
			"type" : type,
			"url" : url,
			"pid" : pid
		};
		$.ajax({
			url : "/nav/addNav.do",
			type : "post",
			data : data,
			dataType : "json",
			success : function(result) {
				$("#navtb").html(result);
			},
			error : function() {
				alert("系统异常,请联系管理员");
				return;
			}
		});
	});
});
function selectPage(url) {
	$.ajax({
		url : url,
		type : "get",
		dataType : "html",
		success : function(result) {
			$("#navtb").html(result);
		},
		error : function() {
			alert("系统异常,请联系管理员");
			return;
		}
	});
}