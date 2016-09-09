$(function() {
	$(".i-checks").iCheck({
		checkboxClass : "icheckbox_square-green",
		radioClass : "iradio_square-green",
	});
	var check = $("#addForm").validate({
		rules : {
			addName : {
				required : !0,
				maxlength : 10
			}
		}
	});
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
	$("input[name=addLevel]").on(
			"ifChecked",
			function(event) {
				var level = $("input[name=addLevel]:checked").val();
				if (level > 1) {
					var op = "<option value=''>请选择</option>"
					$.ajax({
						url : "/nav/getNavList.do",
						type : "post",
						data : {
							"level" : level-1
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
							toastr.warning("系统异常,请联系管理员", "提示");
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
				var url = "/nav/pageList.do?name=" + $("#qname").val()
						+ "&level=" + $("input[name=qlevel]:checked").val();
				selectPage(url);
			});
	$("#qbtn").trigger("click");
	$("#addbtn").bind("click", function() {
		if (!check.form()) {
			return;
		}
		var name = $("#addName").val();
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
			"level" : level,
			"type" : type,
			"url" : url,
			"parentId" : pid
		};
		$.ajax({
			url : "/nav/addNav.do",
			type : "post",
			data : data,
			dataType : "json",
			success : function(result) {
				if (result.resultCode == true) {
					document.getElementById("addForm").reset(); 
					$("#addclose").trigger("click");
					toastr.success("添加成功!", "提示");
					$("#qbtn").trigger("click");
				} else {
					toastr.error("添加失败!", "提示");
				}
			},
			error : function() {
				toastr.warning("系统异常,请联系管理员", "提示");
				return;
			}
		});
	});
});
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
			toastr.warning("系统异常,请联系管理员", "提示");
			return;
		}
	});
}