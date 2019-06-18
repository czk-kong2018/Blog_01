var appConfig = {
    siteName: 'OneBlog开源博客',
    wwwPath: 'http://dblog-web.zhyd.me',
    cmsPath: 'http://dblog-admin.zhyd.me',
    staticPath: 'http://dblog-web.zhyd.me',
    fileStoragePath: 'http://dblog-file.zhyd.me/',
    wxPraiseCode: '',
    zfbPraiseCode: '',
    anonymous: '1',
    editorPlaceholder: '说点什么吧',
    editorAlert: '讲文明、要和谐',

    BasePath: 'http://localhost:8080',
    staticBase: 'http://localhost:8081'

};

var login_user  //登录的人
var commentSave; //保存后台发来的评论结果
var commNum = 0;      //评论的总数;
var article_data;
var total_item;//数据条目总数,默认为0,组件将不加载
var OnePageCount = 10;//每页显示条数,默认为10条
var current_page = 1;//当前页,默认为1
var total_page;



/**
* 显示文章细节
*/

var url = location.href;
//var url = appConfig.BasePath + "/article/2/梅老板";
url = decodeURI(url);
arr = new Array();
arr = url.split('/');
var user_name = "";
var article_id = arr[4];



/**
* 获取文章
*/

$.ajax({
   type: "post",
    data: {
        "article_id":article_id
    },
    url: appConfig.BasePath + "/article/getByArticleId",
    
    success: function (result) {
        article_data = eval(result);
        user_name = article_data.user_name;
        $.ajax({
            method: "get",
            type: "text/markdown",
            url: article_data.url,
            success: function (resp) {
                var htm = compile(resp);
                $('#markdown-body').html(htm);
                $('.blog-info-title').html('<strong>' + article_data.title + '</strong>');
		 $("#markdown-body img").addClass("img-responsive");  //使得图片适应屏幕大小
            },
            error: function (e) {

            }
        });

    }


});
/**
*  转化markdown文件
*  
*/
function test() {
    document.getElementById("write").innerHTML = res;
}

function compile(str) {
    var converter = new showdown.Converter();
    var html = converter.makeHtml(str);
    return html;
}



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
    return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
}


/**
* 设置评论
*/
function CommSet(data, obj_box) {
    $("#none").remove(); //清掉那个  暂时没人评论的一段
    //清空上一次评论
    $(obj_box).children('.page_content').empty();

    var count = data[0].fatherCount;


    for (var i = 0; i < data.length; i++) {
        var tmp = '<li class="article"> <div class="comment-body fade-in" id="comment-' + i + '">'
            + '<div class="cheader">' +
            '<div class="user-img"><img class="userImage" src="' + data[i].head_portrait + '"onerror="this.src="https://static.zhyd.me/static/img/user.png"></div>'
            + '<div class="user-info">' +
            '<div class="nickname"> <a target="_blank" href="' + data[i].head_portrait + '"rel="external nofollow"><strong>' + data[i].user_name + '</strong></a>'
            + ' <i class="icons os-win1" title="Windows 7"></i> <i class="icons browser-chrome title="Chrome 63.0.3239.132"></i></div>'
            + ' <div class="timer"> <i class="fa fa-clock-o fa-fw"></i>' + data[i].create_time + '</div> </div></div>'
            + '<div class="content"> <div>'
            + '<p><a href="' + data[i].head_portrait + '" class="comment-quote">' + '@' + data[i].response_user + '</a>' + data[i].content + '</p> </div></div>'
            + '<div class="sign"> <a href="javascript:void(0);" class="comment-up" onclick="$.comment.praise(572, this)"><i class="fa fa-thumbs-o-up"></i>赞(<span class="count">0</span>)<i'
            + 'class="sepa"></i></a> <a href="javascript:void(0);" class="comment-down" onclick="$.comment.step(572, this)"><i class="fa fa-thumbs-o-down"></i>踩(<span'
            + 'class="count">0</span>)<i class="sepa"></i></a>'
            + '<div style="display:inline;float:right"><a href="javascript:void(0);" class="comment-reply" onclick="getCommentBox(' + i + ',-1,false)"><i class="fa fa-reply"></i>回复</a></div>'
        //如果有子评论
        if (data[i].childList.length != 0) {
            tmp += '<div class="comment-see" style="float:right"><a href="javascript:void(0);" data-toggle="collapse" data-target="#comment' + i + '""><i class="fa fa-eye"></i>查看回复(' + data[i].childList.length + ')</a></div>'
            tmp += '<div class="collapse" id="comment' + i + '" style="margin-left:20px;background-color:#f1f1f1">';
            for (var j = 0; j < data[i].childList.length; j++) {
                tmp += '<div style="padding:10px 10px" child-comment-' + j + '>'
                    + '<div class="comment-header">'
                    + '<span><a href="javascript:void(0);">' + data[i].childList[j].author_name + '</a></span>';
                //判断回复者是不是为空 为空不显示回复了谁谁
                if (data[i].childList[j].response_user != null) {
                    tmp += '<span>回复<a href="#">' + data[i].childList[j].response_user + '</a>：</span>';
                } else {
                    tmp += "<span>:</span>";
                }
                tmp += '<span>' + data[i].childList[j].content + '</span>'
                    + '</div>'
                    + '<div class="comment-footer pull-right">'
                    + '<span>' + data[i].childList[j].create_time + '</span>&emsp;'
                    + '<span><a href="javascript:void(0);" class="child-comment-reply' + j + '" onclick="getCommentBox(' + i + ',' + j + ',true)" ><i class="fa fa-reply"></i>回复</a></span>'
                    + '</div></div><div class="clear"></div>'
            }
            tmp += '</div>'
                + '<div id="CommentBox' + i + '"></div>'
                + '</div>'//sign
        } else {
            tmp += '<div id="CommentBox' + i + '"></div>'
                + '</div>'    //sign
        }

        tmp += ' </div> </li>'

        $(obj_box).children('.page_content').append(tmp);

    }

    $('#commNum').text(commNum);   //显示评论数 




}

function goToComment() {
    $('#sendCommentToOwn').animatescroll();
    console.log('123');
}
/**
* 控制评论的窗口
* @param {*} father 第几条父评论   若是父评论  不使用子评论参数 默认为-1
* @param {*} child  第几条子评论
* @param {*} ifChild 是否是子评论
*/

var lastBox;
var lastFather = -1;
var lastChild = -1;
function getCommentBox(father, child, ifChild) {
    //第一次点击回复
    if (lastFather == -1 && lastChild == -1) {
        var x;
        x = $('#CommentBox' + father);
        var response;
        ifChild ? response = commentSave[father].childList[child].author_name : response = commentSave[father].user_name;

        var box = '<i class="fa fa-commenting-o fa-fw icon"></i><strong class="responsePerson">' + '@' + response + '</strong>'
            + '<form> <textarea class="commentContent" style="width:100%;" rows=5 placeholder="说点什么吧,最多200字左右哦^_^"></textarea>'
            + '<div style="float:right"> <button type="button" class="btn btn-success btn-sm" onclick="sendComm(' + father + ')">提交评论</button>'
            + '</div></form>'
        x.append(box);
        lastBox = x;
        lastChild = child;
        lastFather = father;
    }
    //在同一个回复里点击两次
    else if (lastFather == father && lastChild == child) {
        lastBox.empty();
        lastBox = null;
        lastFather = -1;
        lastChild = -1;
    }
    //点击所属楼层的子评论的回复
    else if (lastFather == father && lastChild != child) {
        x = $('#CommentBox' + father);
        var response;
        ifChild ? response = commentSave[father].childList[child].author_name : response = commentSave[father].user_name;
        var box = '<i class="fa fa-commenting-o fa-fw icon"></i><strong class="responsePerson">' + '@' + response + '</strong>'
            + '<form> <textarea class="commentContent" style="width:100%;" rows=5 placeholder="说点什么吧,最多200字左右哦^_^"></textarea>'
            + '<div style="float:right"> <button type="button" class="btn btn-success btn-sm" onclick="sendComm(' + father + ')">提交评论</button>'
            + '</div></form>'
        x.html(box);
    }
    //点击了别的楼层的回复
    else if (lastFather != father) {
        lastBox.empty();
        x = $('#CommentBox' + father);
        var response;
        ifChild ? response = commentSave[father].childList[child].author_name : response = commentSave[father].user_name;

        var box = '<i class="fa fa-commenting-o fa-fw icon"></i><strong class="responsePerson">' + '@' + response + '</strong>'
            + '<form> <textarea class="commentContent" style="width:100%;" rows=5 placeholder="说点什么吧,最多200字左右哦^_^"></textarea>'
            + '<div style="float:right"> <button class="btn btn-success btn-sm" onclick="sendComm(' + father + ')">提交评论</button>'
            + '</div></form>'
        x.append(box);
        lastBox = x;
        lastChild = child;
        lastFather = father;
    }



}


/**
* 只是发送评论给博主,盖父评论楼层
*/
function setSendToOwn() {
    var userName = getCookie("user_name") ;//$("#login_user").text()
    var resp = user_name   //评论＠的人　即博主
    var create_time = new Date();
    var content = $("#commentToOwn")[0].value;
    if(userName==null||userName==""){
	alert("cookie失效 请重新登录");
	 location.reload();
	return;
    }
    var commentData = {
        "article_id": article_id,         //文章id
        "user_name": userName,
        "response_user": resp,
        "create_time": create_time,
        "content": content,
    }
    
    $.ajax({
        type: "post",
        url: appConfig.BasePath + "/comments/commitFather",
        data: JSON.stringify(commentData),
        dataType: "text",
        async: false,
        contentType: "application/json;character:utf-8",
        success: function (e) {
            var result = JSON.parse(e);
            for (var key in result) {
                alert(result[key]);
                if (key == "0") {
		    clearCookie();
                    location.reload();
                } //登录失效刷新
            }
            getComm(getObj_box); //局部刷新评论
            $("#commentToOwn")[0].value = "";//清空评论内容
            if (current_page == 1) {
                $("#commNum").animatescroll();  //如果第一页滚动到评论顶部
            }
        },
        error: function () {
            alert("提交评论失败,原因未知,可能服务器崩溃,也可能您的网络出了问题,请稍后再试");
            getComm(getObj_box);
        }
    });
}

/**
* 提交评论, 盖子评论楼层
* Totaldata是从一开始获取评论时保留下来的data信息,保存在全局变量中方便使用,不用取标签中取
* @param father 回复属于第几条父评论
*/
function sendComm(father) {
    var comment_id = commentSave[father].comment_id;
    console.log($($(".commentContent")));
    var content = $($(".commentContent"))[0].value;
    var response_user = $($(".responsePerson")).text();

    var userName = getCookie("user_name") ;//$("#login_user").text()  
    response_user = response_user.substring(1, response_user.length);   //要去先去掉头再撒上孜然   去掉@
    if(userName==null||userName==""){
	alert("请先重新登录");
	 location.reload();
	return;
    }
    var commentData = {
        "article_id": article_id,
        "content": content,
        "response_user": response_user,
        "author_name":userName,
        "comment_id": comment_id
    }
    //TODO 编写检验的代码 
    $.ajax({
        type: "post",
        url: appConfig.BasePath + "/comments/commitChild",
        data: JSON.stringify(commentData),
        dataType: "text",
        contentType: "application/json;character:utf-8",
        success: function (e) {
            var result = JSON.parse(e);
            for (var key in result) {
                alert(result[key]);
                if (key == "0") {
 		    clearCookie();
                    location.reload();
                } //登录失效刷新
            }
            getComm(getObj_box);
        },

        error: function () {
            alert("提交评论失败,原因未知,可能服务器崩溃,也可能您的网络出了问题,请稍后再试");
            getComm(getObj_box);
        }
    });


}

/**
* 获取评论
* @param {*} data_obj 
*/
function getComm(obj_box) {

    var data = {
        'current_page': current_page,
        'OnePageCount': OnePageCount
    }
    $.ajax({
        type: "post",
        url: appConfig.BasePath + "/comments/" + article_id + '/getByPage',
        data: data,
        async: false,
        success: function (result) {
            if (result.length == 0) {
                $(".page_ctrl").attr("style", "display:none;");
                var htm =
                    '<li id="none">'
                    + '<div class="list-comment-empty-w fade-in">'
                    + '<div class="empty-prompt-w"><span class="prompt-null-w">还没有评论，快来抢沙发吧！</span></div>'
                    + '</div>'
                    + '</li>'
                $('#setComm').append(htm);   //插入 暂时无人评论
            } else {
                commNum = result[0].commentCount;
                total_item = result[0].fatherCount;
                total_page = Math.ceil(total_item / OnePageCount);
                for (var i = 0; i < result.length; i++) {
                    var d = new Date(result[i].create_time);
                    result[i].create_time = formatDate(d);
                    for (var j = 0; j < result[i].childList.length; j++) {
                        c = new Date(result[i].childList[j].create_time);
                        result[i].childList[j].create_time = formatDate(c);
                    }

                }
                commentSave = result;   //保存评论相关信息
                CommSet(result, obj_box);
            }
        },
        fail: function () {
            console.log("失败");
        }
    });
}
/**
* 
* 文章展示及其评论分页模块
*/
var getObj_box;
function page_ctrl(data_obj) {

    var obj_box = (data_obj.obj_box !== undefined) ? data_obj.obj_box : function () {
        return;
    };//翻页容器dom对象,必要参数
    getObj_box = obj_box;
    //在指定容器内加载分页数据
    $(obj_box).append('<div class="row page_content"></div>');
    //在指定容器内加载分页插件
    $(obj_box).append('<div class="page_ctrl"></div>');

    var change;
    function page_even() {
        /*加载数据*/
        function change_content() {
            /*此处根据项目实际自行编写页面显示内容的方法,举例说明:*/

            var page_content = "";
            getComm(obj_box);
        }
        change = change_content;
        change_content();
        var inp_val = current_page//跳转页数,input默认显示值
        var append_html = '<button class="prev_page">上一页</button>';
        for (var i = 0; i < total_page - 1; i++) {
            if (total_page > 8 && current_page > 6 && i < current_page - 3) {
                if (i < 2) {
                    append_html += '<button class="page_num">' + (i + 1) + '</button>';
                }
                else if (i == 2) {
                    append_html += '<span class="page_dot">•••</span>';
                }
            }
            else if (total_page > 8 && current_page < total_page - 3 && i > current_page + 1) {
                if (current_page > 6 && i == current_page + 2) {
                    append_html += '<span class="page_dot">•••</span>';
                } else if (current_page < 7) {
                    if (i < 8) {
                        append_html += '<button class="page_num">' + (i + 1) + '</button>';
                    } else if (i == 8) {
                        append_html += '<span class="page_dot">•••</span>';
                    }
                }
                //append_html+='<span class="page_dot">•••</span>';
            }
            else {
                if (i == current_page - 1) {
                    append_html += '<button class="page_num current_page">' + (i + 1) + '</button>';
                }
                else {
                    append_html += '<button class="page_num">' + (i + 1) + '</button>';
                }
            }
        }
        if (current_page == total_page) {
            append_html += '<button class="page_num current_page">' + (i + 1) + '</button>';
        } else {
            append_html += '<button class="page_num">' + (i + 1) + '</button>';
        }
        append_html += '<button class="next_page">下一页</button><span class="page_total">共 ' + total_page + ' 页, 到第</span><input class="input_page_num" type="text" value="' + inp_val + '"><span class="page_text">页</span><button class="to_page_num">确定</button>';
        $(obj_box).children('.page_ctrl').append(append_html);
        if (current_page == 1) {
            $(obj_box + ' .page_ctrl .prev_page').attr('disabled', 'disabled').addClass('btn_dis');
        } else {
            $(obj_box + ' .page_ctrl .prev_page').removeAttr('disabled').removeClass('btn_dis');
        }
        if (current_page == total_page) {
            $(obj_box + ' .page_ctrl .next_page').attr('disabled', 'disabled').addClass('btn_dis');
        } else {
            $(obj_box + ' .page_ctrl .next_page').removeAttr('disabled').removeClass('btn_dis');
        }
    }


    page_even();


    $(obj_box + ' .page_ctrl').on('click', 'button', function () {
        var that = $(this);
        if (that.hasClass('prev_page')) {
            if (current_page != 1) {
                current_page--;
                that.parent('.page_ctrl').html('');
                page_even();
            }
        }
        else if (that.hasClass('next_page')) {
            if (current_page != total_page) {
                current_page++;
                that.parent('.page_ctrl').html('');
                page_even();
            }
        }
        else if (that.hasClass('page_num') && !that.hasClass('current_page')) {
            current_page = parseInt(that.html());
            that.parent('.page_ctrl').html('');
            page_even();
        }
        else if (that.hasClass('to_page_num')) {
            current_page = parseInt(that.siblings('.input_page_num').val());
            that.parent('.page_ctrl').html('');
            page_even();
        }
    });
}





