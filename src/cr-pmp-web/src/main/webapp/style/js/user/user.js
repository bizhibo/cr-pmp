$(function() {
	$(".i-checks").iCheck({
		checkboxClass : "icheckbox_square-green",
		radioClass : "iradio_square-green",
	});
	$("#goaddbtn").bind("click", function() {
		window.location.href = "/user/go-add-page.do";
	});
	$("#qbtn").bind(
			"click",
			function() {
				var url = "/user/page-list.do?userName=" + $("#quname").val()
						+ "&name=" + $("#qname").val() + "&sex="
						+ $("input[name=qsex]:checked").val();
				selectPage(url);
			});
	$("#qbtn").trigger("click");
});
function selectPage(url) {
	$("#usertb")
			.html(
					'<div class="spiner-example"><div class="sk-spinner sk-spinner-wandering-cubes"><div class="sk-cube1"></div><div class="sk-cube2"></div></div></div>');
	$.ajax({
		url : url,
		type : "get",
		dataType : "html",
		success : function(result) {
			$("#usertb").html(result);
		},
		error : function() {
			swal("提示", "系统异常,请联系管理员!", "warning");
			return;
		}
	});
}
function getInfo(userName) {
	window.location.href = "/user/user-Info.do?userName=" + userName;
}
function del(userName) {
	swal({
		title : "您确定要删除这条信息吗",
		text : "删除后将无法恢复，请谨慎操作！",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "是的，我要删除！",
		cancelButtonText : "让我再考虑一下",
		closeOnConfirm : false
	}, function() {
		$.ajax({
			url : "/user/del-user.do",
			type : "post",
			data : {
				"userName" : userName
			},
			dataType : "json",
			success : function(result) {
				if (result.resultCode == true) {
					swal("提示", "您已经永久删除了这条信息。", "success");
					$("#qbtn").trigger("click");
				} else {
					swal("提示", "删除失败！", "error");
				}
			},
			error : function() {
				swal("提示", "系统异常,请联系管理员!", "warning");
				return;
			}
		});
	});
}