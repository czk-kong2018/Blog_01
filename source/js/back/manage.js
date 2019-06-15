$(function () {
		

	var cook={
		"user_name":getCookie("user_name"),
		"user_id":getCookie("user_id")
	}	
	$("#userName").html(cook.user_name);



	var obj = $('#content');
	// 基本信息
	$('#basic-info').click(function () {
		var user_name = "";
		var head_portrait = "";//头像地址
		var user_profile = "";//简介
		if (cook.user_name == null || cook.user_name.trim() == "") {
			alert("请重新登录");
		}
		$.ajax({
			type: "post",
			url: "http://localhost:8080/userMessage/get",
			data: JSON.stringify({
				"user_name": cook.user_name
			}),
			dataType: "json",
			async: false,
			contentType: 'application/json;charset=UTF-8',
			success: function (resp) {
				user_name = resp.user_name;
				head_portrait = resp.head_portrait;
				user_profile = resp.user_profile;
			}
		});

		obj.html("");
		var content = '<div class="row">\n' +
			'<div class="contain">\n' +
			'<div class="col-sm-2 ">\n' +
			'<div align="center" class="user-img">\n' +
			'<div style="border: 5px solid lightblue;width: 150px;height:150px;border-radius: 50%;">\n' +
			'<img src="' + head_portrait + '" alt="选择并上传头像" id="avatar_img" 　 style="width: 140px;height: 140px;left:0;top: 0;border-radius: 50%;" />\n' +
			'<input type="file" id="avatar_file" name="avatar_file" accept="image/jpg,image/png,image/gif" style="width: 100%;height:100%;opacity: 0;position:absolute;;left:0;top: 0;" />\n' +
			'</div>\n' +
			'</div>\n' +
			'\n' +
			'<div align="center">\n' +
			'<button id="btn" class="btn btn-default">修改头像</button>\n' +
			'</div>\n' +
			'</div>\n' +
			'</div>\n' +
			'<div class="col-sm-5 ">\n' +
			'<div class="content">\n' +
			'<form role="form">\n' +
			'<div class="form-group">\n' +
			'<label for="exampleInputName">昵称:</label><input type="text" class="form-control" id="exampleInputName" width="20%"\n' +
			'height="auto" value="' + user_name + '" />\n' +
			'</div>\n' +
			'<div class="form-group">\n' +
			'\n' +
			'</div>\n' +
			'<div class="form-group">\n' +
			'<label for="exampleInputName">简介:</label> <textarea class="form-control" rows="4" >' + user_profile + '</textarea>\n' +
			'</div>\n' +
			'<div class="form-group" style="float:right;">\n' +
			'<button type="submit" class="btn btn-default">保存设置</button>\n' +
			'</div>\n' +
			'</form>\n' +
			'</div>\n' +
			'</div>\n' +
			'</div>'
		obj.append(content);
	});



	//账号安全
	$('#account-secure').click(function () {
		obj.html("");
		var content = '<input type="text" />';
		obj.append(content);
	})
	// 文章管理
	$('#original-article').click(function () {
		var articles = new Array();
		$.ajax({
			type: "get",
			url: "http://localhost:8080/article/getAllArticlesById",
			data: {
				"user_id": cook.user_id
			},
			async: false,
			success: function (resp) {
				articles = resp;
			}
		});
		obj.html("");
		var content = '<table class="table table-hover table-striped">\n' +
			'<thead>\n' +
			'<tr>\n' +
			'<th>标题</th>\n' +
			'<th>发布时间</th>\n' +
			'<th>评论功能</th>\n' +
			'<th>点击量</th>\n' +
			'<th>点赞数</th>\n' +
			'<th>操作</th>\n' +
			'</thead>\n' +
			'<tbody>\n'
		if (articles.length > 0) {
			for (var i = 0; i < articles.length; i++) {
				articles[i].create_time=formatDate(new Date(articles[i].create_time));
				content += '<tr class="">\n' +
					'<td>《' + articles[i].title + '》</td>\n' +
					'<td>' + articles[i].create_time + '</td>\n' +
					'<td>\n' +
					'<div class="ckbx-style-8 ckbx-medium">\n' +
					'<input type="checkbox" id="ckbx-size-2" value="" checked="" name="ckbx-square-1">\n' +
					'<label for="ckbx-size-2"></label>\n' +
					'</div>\n' +
					'</td>\n' +
					'<td>5</td>\n' +
					'<td>5</td>\n' +
					'<td>\n' +
					'<div style="display: inline;padding: 5px"><a href="#"><i class="fa fa-eye" aria-hidden="true"></i>查看</a></div>\n' +
					'<div style="display: inline;padding: 5px"><a href="#"><i class="fa fa-trash" aria-hidden="true"></i>删除</a></div>\n' +
					'<div style="display: inline;padding: 5px"><a href="#"><i class="fa fa-edit" aria-hidden="true"></i>修改</a></div>\n' +
					'</td>\n' +
					'</tr>\n'
			}
		}
		content += '</tbody>\n' +
			'</table>'
		obj.append(content);
	});

	//系统通知
	$("#system-notify").click(function () {
		obj.html("");
		var content = '<table class="table table-hover table-striped">\n' +
			'<tr>\n' +
			'<th>来源</th>\n' +
			'<th>链接</th>\n' +
			'<th>内容</th>\n' +
			'<th>日期</th>\n' +
			'<th>操作</th>\n' +
			'</tr>\n' +
			'<tr>\n' +
			'<td><a href="#">root</a></td>\n' +
			'<td>\n' +
			'<a href="#">java线程池</a>' +
			'</td>\n' +
			'<td>\n' +
			'该文章被举报，经核实举报通过' +
			'</td>\n' +
			'<td>2019-02-11</td>\n' +
			'<td><a href="#"><i class="fa fa-fw fa fa-trash"></i>删除</a></td>\n' +
			'</tr>\n' +
			'</table>'
		obj.append(content);
	})

	//文章推送
	$("#article-push").click(function () {

		obj.html("");
		var content = '<table class="table table-hover table-striped">\n' +
			'<tr>\n' +
			'<th>来源</th>\n' +
			'<th>链接</th>\n' +
			'<th>内容</th>\n' +
			'<th>日期</th>\n' +
			'<th>操作</th>\n' +
			'</tr>\n' +
			'<tr>\n' +
			'<td><a href="#">阿扎尔</a></td>\n' +
			'<td>\n' +
			'<a href="#">java线程池</a>' +
			'</td>\n' +
			'<td>\n' +
			'发布了新文章' +
			'</td>\n' +
			'<td>2019-02-11</td>\n' +
			'<td><a href="#"><i class="fa fa-fw fa fa-trash"></i>删除</a></td>\n' +
			'</tr>\n' +
			'</table>'
		obj.append(content);
	})

	//评论通知
	$("#comment-notify").click(function () {
		obj.html("");
		var content = '<table class="table table-hover table-striped">\n' +
			'<tr>\n' +
			'<th>来源</th>\n' +
			'<th>链接</th>\n' +
			'<th>内容</th>\n' +
			'<th>日期</th>\n' +
			'<th>操作</th>\n' +
			'</tr>\n' +
			'<tr>\n' +
			'<td><a href="#">梅西</a></td>\n' +
			'<td>\n' +
			'<a href="#">java线程池</a>' +
			'</td>\n' +
			'<td>\n' +
			'感谢博主分享' +
			'</td>\n' +
			'<td>2019-02-11</td>\n' +
			'<td><a href="#"><i class="fa fa-fw fa fa-trash"></i>删除</a></td>\n' +
			'</tr>\n' +
			'</table>'
		obj.append(content);
	})

	//我的关注
	$('#my-focus').click(function () {
		obj.html("");
		var content = '<table class="table table-hover table-striped">\n' +
			'<tr>\n' +
			'<th>名称</th>\n' +
			'<th>文章推送</th>\n' +
			'<th>最近更新</th>\n' +
			'<th>操作</th>\n' +
			'</tr>\n' +
			'<tr>\n' +
			'<td><a href="#">阿扎尔</a></td>\n' +
			'<td>\n' +
			'<div class="ckbx-style-8 ckbx-medium">\n' +
			'<input type="checkbox" id="ckbx-size-2" value="" checked="" name="ckbx-square-1">\n' +
			'<label for="ckbx-size-2"></label>\n' +
			'</div>\n' +
			'</td>\n' +
			'<td>2019-02-11</td>\n' +
			'<td><a href="#"><i class="fa fa-fw fa fa-minus"></i>取消关注</a></td>\n' +
			'</tr>\n' +
			'</table>'
		obj.append(content);
	})

})
/**
* 后台传来的时间转日期
* @param {} now 
*/
function formatDate(now) {
    var year = now.getFullYear();
    var month = now.getMonth() + 1;
    var date = now.getDate();
    var hour = now.getHours();
    var minute = now.getMinutes();
    var second = now.getSeconds();
    return year + "年" + month + "月" + date + "日" ;
}

