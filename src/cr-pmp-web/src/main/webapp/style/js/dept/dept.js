$(function() {
	toastr.options = {
		"closeButton" : true,
		"debug" : false,
		"progressBar" : true,
		"positionClass" : "toast-top-center",
		"onclick" : null,
		"showDuration" : "400",
		"hideDuration" : "1000",
		"timeOut" : "3000",
		"extendedTimeOut" : "1000",
		"showEasing" : "swing",
		"hideEasing" : "linear",
		"showMethod" : "fadeIn",
		"hideMethod" : "fadeOut"
	};
	var check = $("#addForm").validate({
		rules : {
			addName : {
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
			"parentId" : $("#pid").val()
		};
	});

	$("#using_json").on("changed.jstree", function(e, data) {
		//alert(data.instance.get_node(data.selected[0]).id);
	});
	$("#refresh").bind("click", function() {
		$.ajax({
			url : "/dept/getDept.do",
			type : "post",
			data : "",
			dataType : "json",
			success : function(result) {
				$("#using_json").data("jstree", false).empty().jstree({
					"core" : {
						"data" : result.treeData
					}
				});
			},
			error : function() {
				toastr.warning("系统异常,请联系管理员", "提示");
				return;
			}
		});
	});
	$("#refresh").trigger("click");
});