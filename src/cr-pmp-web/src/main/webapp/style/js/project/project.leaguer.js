$(function() {
	queryPLList();
	jQuery.validator.addMethod("checkLeaguerExist", function(value, element) {
		var flag = 1;
		$.ajax({
			url : "/project/check-leaguer-exist.do",
			type : "post",
			async : false,
			data : {
				"userName" : value,
				"pid" : $("#pid").val()
			},
			dataType : "json",
			success : function(result) {
				if (result.resultCode == "exist") {
					flag = 0;
				}
			},
			error : function() {
				swal("提示", "系统异常,请联系管理员!", "warning");
				return;
			}
		});
		if (flag == 0) {
			return false;
		} else {
			return true;
		}
	}, "<i class='fa fa-times-circle'></i>  该用户已经加入,请重新选择");
	var check = $("#addForm").validate({
		rules : {
			addUserName : {
				required : true,
				checkLeaguerExist : true
			}
		}
	});
	$("#addbtn").bind("click", function() {
		if (!check.form()) {
			return;
		}
		var data = {
			"pid" : $("#pid").val(),
			"userName" : $("#addUserName").val()
		};
		$.ajax({
			url : "/project/add-project-leaguer.do",
			type : "post",
			data : data,
			dataType : "json",
			success : function(result) {
				if (result.resultCode == true) {
					$("#addclose").trigger("click");
					swal("提示", "添加成功！", "success");
					queryPLList();
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
});
function queryPLList() {
	$.ajax({
		url : "/project/project-leaguer-list.do?pid=" + $("#pid").val(),
		type : "get",
		dataType : "html",
		success : function(result) {
			$("#pllist").html(result);
			$("#plbtn").bind("click", function() {
				$("#addModal").modal("show");
			});
		},
		error : function() {
			swal("提示", "系统异常,请联系管理员!", "warning");
			return;
		}
	});
}
function delPL(id) {
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
			url : "/project/del-project-leaguer.do",
			type : "post",
			data : {
				"id" : id
			},
			dataType : "json",
			success : function(result) {
				if (result.resultCode == true) {
					swal("提示", "您已经永久删除了这条信息。", "success");
					queryPLList();
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