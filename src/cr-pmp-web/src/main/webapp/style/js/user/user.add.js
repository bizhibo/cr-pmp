$(function() {
	$(".i-checks").iCheck({
		checkboxClass : "icheckbox_square-green",
		radioClass : "iradio_square-green",
	});
	$("#addFile").prettyFile();
	$.ajax({
		url : "/dept/get-dept.do",
		type : "post",
		data : "",
		dataType : "json",
		success : function(result) {
			$("#dept_tree").data("jstree", false).empty().jstree({
				"core" : {
					"data" : result.treeData
				}
			});
		},
		error : function() {
			swal("提示", "系统异常,请联系管理员!", "warning");
			return;
		}
	});
	var deptObj = {};
	$("#dept_tree").on("changed.jstree", function(e, data) {
		deptObj.deptName = data.instance.get_node(data.selected[0]).text;
		deptObj.deptId = data.instance.get_node(data.selected[0]).id;

	});
	$("#deptbtn").bind("click", function() {
		$("#deptName").val(deptObj.deptName);
		$("#addDeptId").val(deptObj.deptId);
		$("#closebtn").trigger("click");
	});

	jQuery.validator.addMethod("checkUserExist", function(value, element) {
		var flag = 1;
		$.ajax({
			url : "/user/check-user-exist.do",
			type : "post",
			async : false,
			data : {
				"userName" : value
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
	}, "<i class='fa fa-times-circle'></i>  用户名已经存在");

	var check = $("#addForm").validate({
		rules : {
			addUserName : {
				required : true,
				maxlength : 20,
				checkUserExist : true
			},
			deptName : {
				required : true
			},
			addPassword : {
				required : true,
				maxlength : 20
			},
			addName : {
				required : true,
				maxlength : 10
			},
			addBirthday : {
				required : true
			},
			addPosition : {
				required : true,
				maxlength : 20
			},
			addEmail : {
				required : true,
				email : true,
				maxlength : 50
			},
			addPhone : {
				required : true,
				digits : true,
				maxlength : 11,
				minlength : 11
			}
		}
	});

	$("#addbtn").bind("click", function() {
		if (!check.form()) {
			return;
		}
		var data = {
			"userName" : $("#addUserName").val(),
			"password" : $("#addPassword").val(),
			"name" : $("#addName").val(),
			"birthday" : $("#addBirthday").val(),
			"sex" : $("input[name=addSex]:checked").val(),
			"deptId" : $("#addDeptId").val(),
			"email" : $("#addEmail").val(),
			"phone" : $("#addPhone").val(),
			"position" : $("#addPosition").val()
		};
		$.ajaxFileUpload({
			url : "/user/add-user.do",
			type : "post",
			secureuri : false,
			fileElementId : "addFile",
			data : data,
			dataType : "json",
			success : function(result) {
				if (result.resultCode == true) {
					swal({
						title : "提示",
						text : "添加成功！",
						type : "success",
						showCancelButton : true,
						cancelButtonText : "留下继续添加",
						confirmButtonColor : "#AEDEF4",
						confirmButtonText : "返回列表页",
						closeOnConfirm : false
					}, function() {
						window.location.href = "/user/index.do";
					});

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

	$("#deptName").bind("click", function() {
		$("#deptModal").modal("show");
	});
});
