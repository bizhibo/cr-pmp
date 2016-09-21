$(function() {
	$("#addFile").prettyFile();
	$("#addDescribe").summernote({
		lang : "zh-CN",
		height : 300,
	});
	$(".note-insert").remove();
	$(".note-help").remove();

	$.ajax({
		url : "/user/get-all-user.do",
		type : "post",
		dataType : "json",
		success : function(result) {
			var html = "";
			for (var i = 0; i < result.users.length; i++) {
				html = html + "<option value='" + result.users[i].userName
						+ "'>" + result.users[i].name + "-"
						+ result.users[i].deptName + "-"
						+ result.users[i].position + "</option>";
			}
			$("#addLeader").html(html);
			$("#addLeader").chosen();
		},
		error : function() {
			swal("提示", "系统异常,请联系管理员!", "warning");
			return;
		}
	});

	var check = $("#addForm").validate({
		rules : {
			addName : {
				required : true,
				maxlength : 10
			},
			addCode : {
				required : true,
				maxlength : 10
			}
		}
	});

	$("#addbtn").bind("click", function() {
		if (!check.form()) {
			return;
		}
		var data = {
			"name" : $("#addName").val(),
			"code" : $("#addCode").val(),
			"productDescribe" : encodeURI($("#addDescribe").code()),
			"leader" : $("#addLeader").val()
		};
		$.ajaxFileUpload({
			url : "/product/add-product.do",
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
						cancelButtonText:"留下继续添加",
						confirmButtonColor : "#AEDEF4",
						confirmButtonText : "返回列表页",
						closeOnConfirm : false
					}, function() {
						window.location.href = "/product/index.do";
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
});
