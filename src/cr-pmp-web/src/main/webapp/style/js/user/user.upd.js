$(function() {
	$(".i-checks").iCheck({
		checkboxClass : "icheckbox_square-green",
		radioClass : "iradio_square-green",
	});
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
		$("#updDeptId").val(deptObj.deptId);
		$("#closebtn").trigger("click");
	});

	var check = $("#updForm").validate({
		rules : {
			deptName : {
				required : true
			},
			updName : {
				required : true,
				maxlength : 10
			},
			updBirthday : {
				required : true
			},
			updPosition : {
				required : true,
				maxlength : 20
			},
			updEmail : {
				required : true,
				email : true,
				maxlength : 50
			},
			updPhone : {
				required : true,
				digits : true,
				maxlength : 11,
				minlength : 11
			}
		}
	});

	$("#updbtn").bind("click", function() {
		if (!check.form()) {
			return;
		}
		var data = {
			"userName" : $("#updUserName").val(),
			"name" : $("#updName").val(),
			"birthday" : $("#updBirthday").val(),
			"sex" : $("input[name=updSex]:checked").val(),
			"deptId" : $("#updDeptId").val(),
			"email" : $("#updEmail").val(),
			"phone" : $("#updPhone").val(),
			"position" : $("#updPosition").val()
		};
		$.ajax({
			url : "/user/upd-user.do",
			type : "post",
			data : data,
			dataType : "json",
			success : function(result) {
				if (result.resultCode == true) {
					swal({
						title : "提示",
						text : "修改成功！",
						type : "success",
						confirmButtonColor : "#AEDEF4",
						confirmButtonText : "返回列表页",
						closeOnConfirm : false
					}, function() {
						window.location.href = "/user/index.do";
					});

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

	$("#deptName").bind("click", function() {
		$("#deptModal").modal("show");
	});
});
