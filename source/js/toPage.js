
var appConfig = {
          
    BasePath:'http://localhost:8080',
    user_id:null,
    head_portrait:null,
    user_profile:null,

    tagCount:0,
    articleCount:0,
    lastUpdateTime:'暂无更新',
};

var url=location.href;　
url = decodeURI(url); 
arr=new Array();
arr=url.split('/');
/**
 * 个人信息
 */
var username=arr[3];


/**
     * 后台传来的时间转日期
     * @param {} now 
     */
    function formatDate(now) { 
        
        var year=now.getFullYear(); 
        var month=now.getMonth()+1; 
        var date=now.getDate(); 
        var hour=now.getHours(); 
        var minute=now.getMinutes(); 
        var second=now.getSeconds(); 
        return year+"-"+month+"-"+date; 
        } 
    function formatLastUpdateTime(now) { 
        
            var year=now.getFullYear(); 
            var month=now.getMonth()+1; 
            var date=now.getDate(); 
            var hour=now.getHours(); 
            var minute=now.getMinutes(); 
            var second=now.getSeconds(); 
            return year+"年"+month+"月"+date+"日"+hour+"点"; 
            } 

/**
  * 根据url得到的名称获取用户的详细信息
  *  
  */
 var commitData={
    "user_name":username
}
/**
 * 用户信息
 */

        $.ajax({
            type: "post",
            url: appConfig.BasePath+"/userMessage/get",
            data: JSON.stringify(commitData),
            dataType: "json",
            async:false,
            contentType:'application/json;charset=UTF-8',
            success: function (resp) {
               var res=eval(resp);
                appConfig.head_portrait=res.head_portrait;
                appConfig.user_profile=res.user_profile;
                appConfig.user_id=res.user_id;
                 
            $(".about-name").html(username+"的个人博客");
            $(".BlogTitle").html(username+"的个人博客");
            $("#head_portrait").attr("src",appConfig.head_portrait);
            $("#messages").html('<p>'+appConfig.user_profile+'</p>');
            },
            error:function(e){
                alert(e);
            }
        });



/**
 * 
 * 文章展示及其分页模块
 */
function page_ctrl(data_obj) {
    var obj_box=(data_obj.obj_box!== undefined)?data_obj.obj_box:function () {
        return;
    };//翻页容器dom对象,必要参数
    var total_item;//数据条目总数,默认为0,组件将不加载
    var OnePageCount=2;//每页显示条数,默认为10条
    var current_page=1;//当前页,默认为1
    var total_page;
    //在指定容器内加载分页数据
    $(obj_box).append('<div class="row page_content"></div>');
    //在指定容器内加载分页插件
    $(obj_box).append('<div class="page_ctrl"></div>');

    function page_even() {
        /*加载数据*/
        function change_content() {
            // 通过ajax向服务器请求当前页面数据
//             $.ajax({
//                 type:'post',
//                 url:'pageContentAction',
//                 data:'currentPage='+current_page,
//                 success:function (data) {
//                     var page_content='<table class="col-sm-12 table table-hover" style="background-color: white;">';//当前页内容
//                     page_content+='<tr><th hidden="hidden">选择</th></th><th>学号</th><th>姓名</th><th>性别</th><th>学院</th><th>专业</th><th>班级</th><th>操作</th></tr>';
//                     var student=$.parseJSON(data);
//                     for(var i=0;i<student.length;i++){
//                         var checkbox='<td hidden="hidden"><input type="checkbox" /></td>';
//                         var id='<td hidden="hidden">'+student[i].id+'</td>';
//                         var stuID='<td>'+student[i].stuID+'</td>';
//                         var stuName='<td>'+student[i].stuName+'</td>';
//                         var gender='<td>'+student[i].gender+'</td>';
//                         var academy='<td>'+student[i].academy+'</td>';
//                         var profession='<td>'+student[i].profession+'</td>';
//                         var clazz='<td>'+student[i].clazz+'</td>';
//                         var operator='<td>\n' +
//                             '<a href="#" class="my-delete"><i class="fa fa-trash"></i>删除</a>&emsp;\n' +
//                             '<a href="#" class="my-modify"><i class="fa fa-pencil"></i>修改</a>\n' +
//                             '</td>';
//                         page_content+='<tr>'+checkbox+id+stuID+stuName+gender+academy+profession+clazz+operator+'</tr>';
//                     }
//                     page_content+='</table>';
//                     $(obj_box).children('.page_content').html(page_content);
//                 },
//                 error:function () {
//                     alert("服务器内部错误");
//                 }
//             })
            /*此处根据项目实际自行编写页面显示内容的方法,举例说明:*/
	
                        var page_content="";
                        var data={
                            'current_page':current_page,
                            'OnePageCount':OnePageCount
                        }
                        $.ajax({
                            type: 'POST',
                            url: appConfig.BasePath+"/article/"+username+"/getArticleToPage",
                         //   contentType:'application/json;charset=UTF-8',
                            data:data,
                           // dataType: "json",
                            async:false,
                            success: function (resp) {
                                if(resp.length!=0){
                                appConfig.articleCount=resp[0].articleCount;
                                appConfig.lastUpdateTime=formatLastUpdateTime(new Date(resp[0].userArticles.article.create_time));
                                total_item=resp[0].articleCount;
                                total_page=Math.ceil(total_item/OnePageCount);
                                console.log(resp);
                               for(var i=0;i<resp.length;i++){
                                var d=new Date(resp[i].userArticles.article.create_time);
                                resp[i].userArticles.article.create_time=formatDate(d);
                                var articleURL=appConfig.BasePath+"/article/"+resp[i].article_id+'/'+username;
                                var res='<article class="fade-in">'+
                                '<div style="float:right"> <span class="date" title="" data-toggle="tooltip" data-placement="bottom" data-original-title="点赞"><i class="fa fa-tag"></i>'+resp[i].userArticles.tag_name+'</span></div>'
                                +'<header class="entry-header">'
                                +'<h2 class="entry-title"> <a  href="'+articleURL+'" target="_blank" rel="bookmark" title="" data-toggle="tooltip" data-placement="bottom" data-original-title="点击查看文章详情">'+resp[i].userArticles.article.title+'</a></h2>'
                                +'</header>'
                                +'<div class="entry-content">'
                                +'<div class="archive-content">'
                                +resp[i].userArticles.article.article_profile+ '</div>'
                                +'<span class="entry-meta"> '
                                +'<span class="username" title="" data-toggle="tooltip" data-placement="bottom" data-original-title="用户名">'
                                +'<img src="'+resp[i].userArticles.head_portrait+'" alt="hhh" style="width:30px;height:30px;border-radius:30px">&nbsp;<a href="">'+username+'</a></i></span>'
                                +' <span class="date" title="" data-toggle="tooltip" data-placement="bottom" data-original-title="文章发表日期"><i class="fa fa-calendar"></i>'
                                +resp[i].userArticles.article.create_time+'</span>' 
                                +' <span class="views" title="" data-toggle="tooltip" data-placement="bottom" data-original-title="文章阅读次数"><i class="fa fa-eye fa-fw"></i>浏览('+resp[i].userArticles.article.watch+')</span>'
                                +' <span class="like" title="" data-toggle="tooltip" data-placement="bottom" data-original-title="点赞"><i class="fa fa-thumbs-o-up"></i>点赞('+resp[i].userArticles.article.click_like+')</span>'
                                +'<span class="report" title="" data-toggle="tooltip" data-placement="bottom" data-original-title="举报"><a href="javascript:void(0)" onclick="">举报</a></span></span>'     
                                +' <span class="entry-more"><a href="'+articleURL+'" target="_blank" rel="bookmark" title="" data-toggle="tooltip" data-placement="bottom" data-original-title="点击查看文章详情" onclick="test();return false">阅读全文</a> </span>'                
                                +' </div></article>'
                    
                                page_content+=res;


                                $(obj_box).children('.page_content').html(page_content);
                               }
                            }else{
                                $(".page_ctrl").attr("style","display:none;");
                            }
                    
                            },
                            fail:function(){
                                console.log("失败");
                            }
                        });
                    
												
        }
        change_content();
        var inp_val=current_page//跳转页数,input默认显示值
        var append_html='<button class="prev_page">上一页</button>';
        for(var i=0;i<total_page-1;i++){
            if(total_page>8&&current_page>6&&i<current_page-3){
                if(i<2){
                    append_html+='<button class="page_num">'+(i+1)+'</button>';
                }
                else if(i==2){
                    append_html+='<span class="page_dot">•••</span>';
                }
            }
            else if(total_page>8&&current_page<total_page-3&&i>current_page+1){
                if(current_page>6&&i==current_page+2){
                    append_html+='<span class="page_dot">•••</span>';
                }else if(current_page<7){
                    if(i<8){
                        append_html+='<button class="page_num">'+(i+1)+'</button>';
                    }else if(i==8){
                        append_html+='<span class="page_dot">•••</span>';
                    }
                }
                //append_html+='<span class="page_dot">•••</span>';
            }
            else{
                if(i==current_page-1){
                    append_html+='<button class="page_num current_page">'+(i+1)+'</button>';
                }
                else{
                    append_html+='<button class="page_num">'+(i+1)+'</button>';
                }
            }
        }
        if(current_page==total_page){
            append_html+='<button class="page_num current_page">'+(i+1)+'</button>';
        }else{
            append_html+='<button class="page_num">'+(i+1)+'</button>';
        }
        append_html+='<button class="next_page">下一页</button><span class="page_total">共 '+total_page+' 页, 到第</span><input class="input_page_num" type="text" value="'+inp_val+'"><span class="page_text">页</span><button class="to_page_num">确定</button>';
        $(obj_box).children('.page_ctrl').append(append_html);
        if(current_page==1){
            $(obj_box+' .page_ctrl .prev_page').attr('disabled','disabled').addClass('btn_dis');
        }else{
            $(obj_box+' .page_ctrl .prev_page').removeAttr('disabled').removeClass('btn_dis');
        }
        if(current_page==total_page){
            $(obj_box+' .page_ctrl .next_page').attr('disabled','disabled').addClass('btn_dis');
        }else{
            $(obj_box+' .page_ctrl .next_page').removeAttr('disabled').removeClass('btn_dis');
        }
    }

//     $.ajax({
//         type:'post',
//         url:'totalItemAction',
//         success:function (data) {
//             total_item=data;
//             total_page=Math.ceil(total_item/per_num);
//             page_even();
//         },
//         error:function () {
//             alert("服务器内部错误");
//         }
//     })
		page_even();


    $(obj_box+' .page_ctrl').on('click','button',function () {
        var that=$(this);
        if(that.hasClass('prev_page')){
            if(current_page!=1){
                current_page--;
                that.parent('.page_ctrl').html('');
                page_even();
            }
        }
        else if(that.hasClass('next_page')){
            if(current_page!=total_page){
                current_page++;
                that.parent('.page_ctrl').html('');
                page_even();
            }
        }
        else if(that.hasClass('page_num')&&!that.hasClass('current_page')){
            current_page=parseInt(that.html());
            that.parent('.page_ctrl').html('');
            page_even();
        }
        else if(that.hasClass('to_page_num')){
            current_page=parseInt(that.siblings('.input_page_num').val());
            that.parent('.page_ctrl').html('');
            page_even();
        }
    });
}



window.onload = function(){

 $.ajax({
     type: "get",
     url: appConfig.BasePath+"/tags/"+appConfig.user_id+'/get',
     async:false,
     success: function (resp) {
         var res=resp
         appConfig.tagCount=res.length;
         var htm="";
         for(var i=0;i<res.length;i++){
         var url=appConfig.BasePath+"/tags/"+res[i].tag_id;
         var tag='<a style="font-size: 12.39px;margin: 5px;"'
         +'href="'+url+'" title="" data-toggle="tooltip" data-placement="bottom"'
         +'data-original-title="'+res[i].tag_name+'"> '+res[i].tag_name+' </a>' 
         htm+=tag;
         }
         $("#tags").html(htm);
     }

     
 }); 
 $('#articleCount').html(appConfig.articleCount);
 $('#tagCount').html(appConfig.tagCount);
 $('#lastUpdateTime').html(appConfig.lastUpdateTime);   
 
   } 
