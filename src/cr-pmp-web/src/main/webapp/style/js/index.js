$(function() {
	jQuery.validator.addMethod("checkOldPW", function(value, element) {
		var flag = 1;
		$.ajax({
			url : "/user/check-password.do",
			type : "post",
			async : false,
			data : {
				"userName" : $("#userName").val(),
				"password" : value
			},
			dataType : "json",
			success : function(result) {
				if (result.resultCode == false) {
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
	}, "<i class='fa fa-times-circle'></i>  原密码不正确");

	var check = $("#updPWForm").validate({
		rules : {
			newPW : {
				required : true,
				maxlength : 20
			},
			oldPW : {
				required : true,
				maxlength : 20,
				checkOldPW : true
			}
		}
	});

	$("#updPWbtn").bind("click", function() {
		if (!check.form()) {
			return;
		}
		var data = {
			"userName" : $("#userName").val(),
			"password" : $("#newPW").val()
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
						text : "修改成功,请重新登录！",
						type : "success",
						confirmButtonColor : "#AEDEF4",
						confirmButtonText : "确定",
						closeOnConfirm : false
					}, function() {
						window.location.href = "/logout.do";
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

	$("#updPW").bind("click", function() {
		$("#updPWModal").modal("show");
	});
});
