$(function() {
	querySubTaskList();
	var stcheck = $("#addSubTaskForm").validate({
		rules : {
			addstname : {
				required : true,
				maxlength : 15
			},
			addstperformer : {
				required : true
			},
			addstendDate : {
				required : true
			},
			addstremarks : {
				required : true,
				maxlength : 800
			}
		}
	});
	$("#addstbtn").bind("click", function() {
		if (!stcheck.form()) {
			return;
		}
		var data = {
			"pid" : $("#pid").val(),
			"tbid" : $("#tbid").val(),
			"tid" : $("#tid").val(),
			"name" : $("#addstname").val(),
			"performer" : $("#addstperformer").val(),
			"endDate" : $("#addstendDate").val(),
			"remarks" : $("#addstremarks").val()
		};
		$.ajax({
			url : "/project/add-sub-task.do",
			type : "post",
			data : data,
			dataType : "json",
			success : function(result) {
				if (result.resultCode == true) {
					$("#addstclose").trigger("click");
					swal("提示", "添加成功！", "success");
					querySubTaskList();
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
function querySubTaskList() {
	$.ajax({
		url : "/project/sub-task-list.do?tid=" + $("#tid").val(),
		type : "get",
		dataType : "html",
		success : function(result) {
			$("#stlist").html(result);
			$("#stbtn").bind("click", function() {
				$("#addSubTaskModal").modal("show");
			});
		},
		error : function() {
			swal("提示", "系统异常,请联系管理员!", "warning");
			return;
		}
	});
}
function delSubTask(id) {
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
			url : "/project/del-sub-task.do",
			type : "post",
			data : {
				"id" : id
			},
			dataType : "json",
			success : function(result) {
				if (result.resultCode == true) {
					swal("提示", "您已经永久删除了这条信息。", "success");
					querySubTaskList();
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