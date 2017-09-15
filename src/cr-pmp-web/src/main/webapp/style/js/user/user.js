$(function() {
	$(".i-checks").iCheck({
		checkboxClass : "icheckbox_square-green",
		radioClass : "iradio_square-green",
	});
	$("#goaddbtn").bind("click", function() {
		window.location.href = "/user/go-add-page.do";
	});
	$("#qbtn").bind(
			"click",
			function() {
				var url = "/user/page-list.do?userName=" + $("#quname").val()
						+ "&name=" + $("#qname").val() + "&sex="
						+ $("input[name=qsex]:checked").val();
				selectPage(url);
			});
	$("#addUJBtn").bind("click", function() {
		var ckList = $("#juList input[group='jugroup'");
		var userName = $("#userNameJu").val();
		var ujList = new Array();
		for (var i = 0; i < ckList.length; i++) {
			if (ckList[i].checked == true) {
				var uj = new Object();
				uj.userName = userName;
				uj.jid = ckList[i].value
				ujList.push(uj);
			}
		}
		$.ajax({
			url : "/user/add-user-permissions.do",
			type : "post",
			data : {
				"ujList" : JSON.stringify(ujList),
				"userName" : userName
			},
			dataType : "json",
			success : function(result) {
				if (result.resultCode == false) {
					swal("提示", "添加失败!", "error");
				} else {
					$("#closebtn").trigger("click");
					swal("提示", "添加成功！", "success");
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
	$("#usertb")
			.html(
					'<div class="spiner-example"><div class="sk-spinner sk-spinner-wandering-cubes"><div class="sk-cube1"></div><div class="sk-cube2"></div></div></div>');
	$.ajax({
		url : url,
		type : "get",
		dataType : "html",
		success : function(result) {
			$("#usertb").html(result);
		},
		error : function() {
			swal("提示", "系统异常,请联系管理员!", "warning");
			return;
		}
	});
}
function del(userName) {
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
			url : "/user/del-user.do",
			type : "post",
			data : {
				"userName" : userName
			},
			dataType : "json",
			success : function(result) {
				if (result.resultCode == true) {
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
function getPermissions(userName) {
	$.ajax({
		url : "/user/get-user-permissions.do",
		type : "post",
		data : {
			"userName" : userName
		},
		dataType : "json",
		success : function(result) {
			var juList = result.juList;
			var ujList = result.ujList;
			var juHtml = "";
			for (var i = 0; i < juList.length; i++) {
				var check = "";
				var ju = juList[i];
				for (var j = 0; j < ujList.length; j++) {
					var uj = ujList[j];
					if (ju.id == uj.jid) {
						check = "checked";
					}
				}
				juHtml += "<label><input group='jugroup' type='checkbox' "
						+ check + " value='" + ju.id + "'> <i></i> " + ju.name
						+ "</label>";
			}
			$("#juList").html(
					"<div class='checkbox i-checks'>" + juHtml + "</div>");
			$(".i-checks").iCheck({
				checkboxClass : "icheckbox_square-green",
				radioClass : "iradio_square-green",
			});
		},
		error : function() {
			swal("提示", "系统异常,请联系管理员!", "warning");
			return;
		}
	});
	$("#userNameJu").val(userName);
	$("#juModal").modal("show");
}