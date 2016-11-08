$(function() {
	queryBoardList();
	$(".i-checks").iCheck({
		checkboxClass : "icheckbox_square-green",
		radioClass : "iradio_square-green",
	});
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
			"pid" : $("#pid").val()
		};
		$.ajax({
			url : "/project/add-board.do",
			type : "post",
			data : data,
			dataType : "json",
			success : function(result) {
				if (result.resultCode == true) {
					document.getElementById("addBoardForm").reset();
					$("#addclose").trigger("click");
					swal("提示", "添加成功！", "success");
					queryBoardList();
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
	
	var tcheck = $("#addTaskForm").validate({
		rules : {
			addtname : {
				required : true,
				maxlength : 15
			},
			addtperformer : {
				required : true
			},
			addtendDate : {
				required : true
			},
			addtremarks : {
				required : true,
				maxlength : 800
			}
		}
	});
	
	$("#addtaskbtn").bind("click", function() {
		if (!tcheck.form()) {
			return;
		}
		var data = {
			"pid" : $("#pid").val(),
			"tbid" : $("#addtbid").val(),
			"name" : $("#addtname").val(),
			"performer" : $("#addtperformer").val(),
			"endDate" : $("#addtendDate").val(),
			"priority" : $("input[name=addtpriority]:checked").val(),
			"remarks" : $("#addtremarks").val()
		};
		$.ajax({
			url : "/project/add-task.do",
			type : "post",
			data : data,
			dataType : "json",
			success : function(result) {
				if (result.resultCode == true) {
					$("#addtaskclose").trigger("click");
					swal("提示", "添加成功！", "success");
					queryBoardList();
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

function queryBoardList() {
	$.ajax({
		url : "/project/board-list.do?pid=" + $("#pid").val(),
		type : "get",
		dataType : "html",
		success : function(result) {
			$(".board").html(result);
			$(".sortable-list").sortable({
				connectWith : ".connectList"
			}).disableSelection();
			$(".sortable-list").bind("sortreceive", function(event, ui) {
				var tbid = event.target.attributes[1].nodeValue;
				var id = ui.item.context.attributes[2].nodeValue;
				updatetbid(id, tbid);
			});
		},
		error : function() {
			swal("提示", "系统异常,请联系管理员!", "warning");
			return;
		}
	});
}
function updatetbid(id, tbid) {
	$.ajax({
		url : "/project/update-tbid.do",
		type : "post",
		data : {
			"id" : id,
			"tbid" : tbid
		},
		dataType : "json",
		error : function() {
			swal("提示", "系统异常,请联系管理员!", "warning");
			return;
		}
	});
}
function taskModal(tbid,tbname) {
	$("#addtbname").html(tbname);
	$("#addtbid").val(tbid);
	$("#addTaskModal").modal("show");
}