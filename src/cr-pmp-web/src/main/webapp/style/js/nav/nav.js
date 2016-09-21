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
				maxlength : 10
			},
			addSequence : {
				required : true,
				maxlength : 10
			}
		}
	});
	$("input[name=addLevel]").on(
			"ifChecked",
			function(event) {
				var level = $("input[name=addLevel]:checked").val();
				if (level > 1) {
					var op = "<option value=''>请选择</option>"
					$.ajax({
						url : "/nav/get-nav-list.do",
						type : "post",
						data : {
							"level" : level - 1
						},
						dataType : "json",
						success : function(result) {
							var array = result.navList;
							for (var i = 0; i < array.length; i++) {
								op = op + "<option value='" + array[i].id
										+ "'>" + array[i].name + "</option>";
							}
							$("#addpid").html(op);
						},
						error : function() {
							swal("提示", "系统异常,请联系管理员!", "warning");
							return;
						}
					});
					$("#addpid").attr("disabled", false);
					$("#addpid").rules("add", {
						required : true,
					});
				} else {
					$("#addpid").attr("disabled", true);
					$("#addpid").rules("remove");
				}
			});
	$("input[name=addType]").on("ifChecked", function(event) {
		var type = $("input[name=addType]:checked").val();
		if (type == "DYNAMIC") {
			$("#addUrl").attr("disabled", false);
			$("#addUrl").rules("add", {
				required : true
			});
		}
		if (type == "STATIC") {
			$("#addUrl").attr("disabled", true);
			$("#addUrl").rules("remove");
		}
	});
	$("#qbtn").bind(
			"click",
			function() {
				var url = "/nav/page-list.do?name=" + $("#qname").val()
						+ "&level=" + $("input[name=qlevel]:checked").val();
				selectPage(url);
			});
	$("#addbtn").bind("click", function() {
		if (!check.form()) {
			return;
		}
		var name = $("#addName").val();
		var icon = $("#addIcon").val();
		var sequence = $("#addSequence").val();
		var level = $("input[name=addLevel]:checked").val();
		var type = $("input[name=addType]:checked").val();
		var url = "";
		var pid = "";
		if (level == 2 || level == 3) {
			pid = $("#addpid").val();
		}
		if (type == "DYNAMIC") {
			url = $("#addUrl").val();
		}
		var data = {
			"name" : name,
			"icon" : icon,
			"sequence" : sequence,
			"level" : level,
			"type" : type,
			"url" : url,
			"parentId" : pid
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