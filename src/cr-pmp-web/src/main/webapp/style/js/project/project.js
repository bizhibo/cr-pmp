$(function() {
	$(".i-checks").iCheck({
		checkboxClass : "icheckbox_square-green",
		radioClass : "iradio_square-green",
	});
	var check = $("#addForm").validate({
		rules : {
			addName : {
				required : true,
				maxlength : 20
			}
		}
	});
	$("#qbtn").bind(
			"click",
			function() {
				var url = "/project/page-list.do?name=" + $("#qname").val()
						+ "&status=" + $("input[name=qstatus]:checked").val();
				selectPage(url);
			});

	$("#addbtn").bind("click", function() {
		if (!check.form()) {
			return;
		}
		var data = {
			"name" : $("#addName").val(),
			"status" : 1
		};
		$.ajax({
			url : "/project/add-project.do",
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
function selectPage(url) {
	$(".project-list")
			.html(
					'<div class="spiner-example"><div class="sk-spinner sk-spinner-wandering-cubes"><div class="sk-cube1"></div><div class="sk-cube2"></div></div></div>');
	$.ajax({
		url : url,
		type : "get",
		dataType : "html",
		success : function(result) {
			$(".project-list").html(result);
		},
		error : function() {
			swal("提示", "系统异常,请联系管理员!", "warning");
			return;
		}
	});
}
function openPL(pid, pname) {
	parent.layer.open({
		type : 2,
		title : "所属项目：" + pname,
		shadeClose : true,
		shade : false,
		maxmin : false, // 开启最大化最小化按钮
		area : [ "1000px", "600px" ],
		content : "/project/project-leaguer-index.do?pid=" + pid
	});
}