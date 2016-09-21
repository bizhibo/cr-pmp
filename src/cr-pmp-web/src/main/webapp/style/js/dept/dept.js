$(function() {
	var check = $("#addForm").validate({
		rules : {
			addName : {
				required : true,
				maxlength : 10
			}
		}
	});
	var ucheck = $("#updForm").validate({
		rules : {
			updName : {
				required : true,
				maxlength : 10
			}
		}
	});
	$("#qbtn").bind(
			"click",
			function() {
				var url = "/dept/page-list.do?parentId=" + $("#pid").val()
						+ "&name=" + $("#qname").val();
				selectPage(url);
			});
	$("#addbtn").bind("click", function() {
		if (!check.form()) {
			return;
		}
		var data = {
			"name" : $("#addName").val(),
			"parentId" : $("#pid").val()
		};

		$.ajax({
			url : "/dept/add-dept.do",
			type : "post",
			data : data,
			dataType : "json",
			success : function(result) {
				if (result.resultCode == true) {
					document.getElementById("addForm").reset();
					$("#addclose").trigger("click");
					swal("提示", "添加成功！", "success");
					$("#qbtn").trigger("click");
					$("#refresh").trigger("click");
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
		var data = {
			"name" : $("#updName").val(),
			"id" : $("#updId").val()
		};

		$.ajax({
			url : "/dept/upd-dept.do",
			type : "post",
			data : data,
			dataType : "json",
			success : function(result) {
				if (result.resultCode == true) {
					document.getElementById("addForm").reset();
					$("#updclose").trigger("click");
					swal("提示", "修改成功！", "success");
					$("#qbtn").trigger("click");
					$("#refresh").trigger("click");
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
	$("#dept_tree")
			.on(
					"changed.jstree",
					function(e, data) {
						$("#pid").val(
								data.instance.get_node(data.selected[0]).id);
						$("#dqdept")
								.html(
										"当前选中部门："
												+ data.instance
														.get_node(data.selected[0]).text);
						var url = "/dept/page-list.do?parentId="
								+ data.instance.get_node(data.selected[0]).id;
						selectPage(url);
					});
	$("#refresh").bind("click", function() {
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
	});
	$("#refresh").trigger("click");
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
			url : "/dept/del-dept.do",
			type : "post",
			data : {
				"id" : id
			},
			dataType : "json",
			success : function(result) {
				if (result.resultCode == "EXISTNODE") {
					swal("提示", "删除失败！当前部门下还存在子部门请先处理。", "error");
				} else if (result.resultCode == true) {
					swal("提示", "您已经永久删除了这条信息。", "success");
					$("#refresh").trigger("click");
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
function queryById(id) {
	$.ajax({
		url : "/dept/query-by-id.do",
		type : "post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(result) {
			$("#updName").val(result.dept.name);
			$("#updId").val(id);
		},
		error : function() {
			swal("提示", "系统异常,请联系管理员!", "warning");
			return;
		}
	});
}
function selectPage(url) {
	$("#depttb")
			.html(
					'<div class="spiner-example"><div class="sk-spinner sk-spinner-wandering-cubes"><div class="sk-cube1"></div><div class="sk-cube2"></div></div></div>');
	$.ajax({
		url : url,
		type : "get",
		dataType : "html",
		success : function(result) {
			$("#depttb").html(result);
		},
		error : function() {
			swal("提示", "系统异常,请联系管理员!", "warning");
			return;
		}
	});
}