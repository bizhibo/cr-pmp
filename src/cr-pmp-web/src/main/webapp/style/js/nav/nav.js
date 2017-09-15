$(function() {
	$(".i-checks").iCheck({
		checkboxClass : "icheckbox_square-green",
		radioClass : "iradio_square-green",
	});
	var check = $("#addForm").validate({
		rules : {
			addName : {
				required : true,
				maxlength : 10
			},
			addIcon : {
				required : true,
				maxlength : 20
			},
			addSequence : {
				required : true,
				maxlength : 10
			},
			addUrl : {
				required : true,
				maxlength : 150
			}
		}
	});
	$("#qbtn").bind(
			"click",
			function() {
				var url = "/nav/page-list.do?name=" + $("#qname").val();
				selectPage(url);
			});
	$("#addbtn").bind("click", function() {
		if (!check.form()) {
			return;
		}
		var name = $("#addName").val();
		var icon = $("#addIcon").val();
		var sequence = $("#addSequence").val();
		var url = $("#addUrl").val();
		var data = {
			"name" : name,
			"icon" : icon,
			"sequence" : sequence,
			"url" : url
		};
		$.ajax({
			url : "/nav/add-nav.do",
			type : "post",
			data : data,
			dataType : "json",
			success : function(result) {
				if (result.resultCode == true) {
					document.getElementById("addForm").reset();
					$("#addclose").trigger("click");
					swal("提示", "添加成功！", "success");
					$("#qbtn").trigger("click");
				} else {
					swal("提示", "添加失败!", "error");
				}
			},
			error : function() {
				swal("提示", "系统异常,请联系管理员!", "warning");
				return;
			}
		});
	});
	$("#qbtn").trigger("click");
});
function del(id) {
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
			url : "/nav/del-nav.do",
			type : "post",
			data : {
				"id" : id
			},
			dataType : "json",
			success : function(result) {
				if (result.resultCode == "EXISTNODE") {
					swal("提示", "删除失败！当前菜单下还存在子菜单请先处理。", "error");
				} else if (result.resultCode == true) {
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
function selectPage(url) {
	$("#navtb")
			.html(
					'<div class="spiner-example"><div class="sk-spinner sk-spinner-wandering-cubes"><div class="sk-cube1"></div><div class="sk-cube2"></div></div></div>');
	$.ajax({
		url : url,
		type : "get",
		dataType : "html",
		success : function(result) {
			$("#navtb").html(result);
		},
		error : function() {
			swal("提示", "系统异常,请联系管理员!", "warning");
			return;
		}
	});
}