$(function() {
	jQuery.validator.addMethod("checkExist", function(value, element) {
		var flag = true;
		$.ajax({
			url : "/permissions/check-permissions-exist.do",
			type : "post",
			async : false,
			data : {
				"code" : value
			},
			dataType : "json",
			success : function(result) {
				if (result.resultCode == "exist") {
					flag = false;
				}
			},
			error : function() {
				swal("提示", "系统异常,请联系管理员!", "warning");
				return;
			}
		});
		return flag;
	}, "<i class='fa fa-times-circle'></i>  编码已经存在");
	
	jQuery.validator.addMethod("updCheckExist", function(value, element) {
		var flag = true;
		if(value!=element.exCode){
			$.ajax({
				url : "/permissions/check-permissions-exist.do",
				type : "post",
				async : false,
				data : {
					"code" : value
				},
				dataType : "json",
				success : function(result) {
					if (result.resultCode == "exist") {
						flag = false;
					}
				},
				error : function() {
					swal("提示", "系统异常,请联系管理员!", "warning");
					return;
				}
			});
		}
		return flag;
	}, "<i class='fa fa-times-circle'></i>  编码已经存在");
	
	var check = $("#addForm").validate({
		rules : {
			addName : {
				required : true,
				maxlength : 10
			},
			addCode : {
				required : true,
				maxlength : 20,
				checkExist : true
			}
		}
	});
	var ucheck = $("#updForm").validate({
		rules : {
			updName : {
				required : true,
				maxlength : 10
			},
			updCode : {
				required : true,
				maxlength : 20,
				updCheckExist : true
			}
		}
	});
	$("#addbtn").bind("click", function() {
		if (!check.form()) {
			return;
		}
		var name = $("#addName").val();
		var code = $("#addCode").val();
		var data = {
			"name" : name,
			"code" : code
		};
		$.ajax({
			url : "/permissions/add-permissions.do",
			type : "post",
			data : data,
			dataType : "json",
			success : function(result) {
				if (result.resultCode == true) {
					document.getElementById("addForm").reset();
					$("#addclose").trigger("click");
					swal("提示", "添加成功！", "success");
					selectPage("/permissions/page-list.do");
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
	
	$("#updbtn").bind("click", function() {
		if (!ucheck.form()) {
			return;
		}
		var name = $("#updName").val();
		var code = $("#updCode").val();
		var id = $("#updId").val();
		var data = {
			"id" : id,
			"name" : name,
			"code" : code
		};
		$.ajax({
			url : "/permissions/upd-permissions.do",
			type : "post",
			data : data,
			dataType : "json",
			success : function(result) {
				if (result.resultCode == true) {
					document.getElementById("updForm").reset();
					$("#updclose").trigger("click");
					swal("提示", "修改成功！", "success");
					selectPage("/permissions/page-list.do");
				} else {
					swal("提示", "修改失败!", "error");
				}
			},
			error : function() {
				swal("提示", "系统异常,请联系管理员!", "warning");
				return;
			}
		});
	});
	selectPage("/permissions/page-list.do");
});
function del(id) {
	swal({
		title : "您确定要删除这条信息吗",
		text : "删除后所有用户将失去该权限!",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "是的，我要删除！",
		cancelButtonText : "让我再考虑一下",
		closeOnConfirm : false
	}, function() {
		$.ajax({
			url : "/permissions/del-permissions.do",
			type : "post",
			data : {
				"id" : id
			},
			dataType : "json",
			success : function(result) {
				if (result.resultCode == true) {
					swal("提示", "您已经永久删除了这条信息。", "success");
					selectPage("/permissions/page-list.do");
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
	$("#jtb")
			.html(
					'<div class="spiner-example"><div class="sk-spinner sk-spinner-wandering-cubes"><div class="sk-cube1"></div><div class="sk-cube2"></div></div></div>');
	$.ajax({
		url : url,
		type : "get",
		dataType : "html",
		success : function(result) {
			$("#jtb").html(result);
		},
		error : function() {
			swal("提示", "系统异常,请联系管理员!", "warning");
			return;
		}
	});
}
function queryById(id,exCode) {
	$.ajax({
		url : "/permissions/query-by-id.do",
		type : "post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(result) {
			$("#updName").val(result.permissions.name);
			$("#updCode").val(result.permissions.code);
			$("#updId").val(result.permissions.id);
			$("#updCode").attr("exCode",exCode);
		},
		error : function() {
			swal("提示", "系统异常,请联系管理员!", "warning");
			return;
		}
	});
}