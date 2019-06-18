$(function() {
	var appConfig={
	   'basePath':'http://localhost:8080/'	
	}
	var dataCommit = {
		"tag": "All", //默认为All
		"num": 0 //默认为0
	}

	/* 文章加载功能 */
	function getArticle() {
		/* 通过ajax获取后台数据 */
		$.ajax({
			url: '/article/getIndexArticle2',
			type: 'get',
			data: dataCommit,
			dataType: 'json',
			async: false,
			cache: false,
			success: function(data) {
				var obj = $('#article-list');
				for (var i = 0; i < data.length; i++) {
// 					console.log(data[i]);
// 					var item = '<li>' +
// 						'<article>' +
// 						'<div class="article-header">' +
// 						'<h4 class="article-title pull-left"> <a href="#">' + data[i].article.title + '</a></h4>' +
// 						'<span class="article-tag pull-right"><i class="fa fa-tag"></i>' + data[i].tag.tag_name + '</span>' +
// 						'<div style="clear: both;"></div>' +
// 						'</div>' +
// 						'<div class="article-content">' +
// 						data[i].article.article_profile +
// 						'</div>' +
// 						'<div class="article-info">' +
// 						'<span><img src="' +
// 						data[i].userMessage.head_portrait + '" alt="userImg" style="width:30px;height:30px;border-radius:30px"></span>' +
// 						'<span>' +
// 						data[i].userMessage.user_name + '</span>' +
// 						'<span title="" data-toggle="tooltip" data-placement="bottom" data-original-title="文章发表日期">' +
// 						'<i class="fa fa-fw fa fa-calendar"></i>&nbsp;' + data[i].article.create_time + '</span>' +
// 						'<span title="" data-toggle="tooltip" data-placement="bottom" data-original-title="文章阅读次数">' +
// 						'<i class="fa fa-fw fa fa-eye fa-fw"></i>&nbsp;' + data[i].article.watch + '</span>' +
// 						'<span title="" data-toggle="tooltip" data-placement="bottom" data-original-title="点赞">' +
// 						'<i class="fa fa-fw fa fa-thumbs-o-up"></i>&nbsp;' + data[i].article.click_like + '</span>' +
// 						'</div>' +
// 						'</article>' +
// 						'</li>';
// 					obj.append(item);
					console.log(data[i]);
data[i].create_time=formatDate(new Date(data[i].create_time));
					var ttag="";
var AticleUrl=appConfig.basePath+"article/"+data[i].article_id+"/"+data[i].userMessage.user_name;
var AuthorUrl=appConfig.basePath+data[i].userMessage.user_name;
					$.each(data[i].tag,function(i,tag){
						ttag+=tag.tag_name+" ";
					})
					var item = '<li>' +
						'<article>' +
						'<div class="article-header">' +
						'<h4 class="article-title pull-left"> <a href="'+AticleUrl+'"  target="_blank">' + data[i].title + '</a></h4>' +
						'<span class="article-tag pull-right"><i class="fa fa-fw fa fa-tag"></i>'+
						ttag +
						'</span>'+
						'<div style="clear: both;"></div>' +
						'</div>' +
						'<div class="article-content">' +
						data[i].article_profile +
						'</div>' +
						'<div class="article-info">' +
						'<span><img src="' +
						data[i].userMessage.head_portrait + '" alt="userImg" style="width:30px;height:30px;border-radius:30px"></span>' +
						'<a href='+AuthorUrl+' target="_blank">' +
						data[i].userMessage.user_name + '</a>&nbsp;&nbsp;'+
						'<span title="" data-toggle="tooltip" data-placement="bottom" data-original-title="文章发表日期">' +
						'<i class="fa fa-fw fa fa-calendar"></i>&nbsp;' + data[i].create_time + '</span>' +
						'<span title="" data-toggle="tooltip" data-placement="bottom" data-original-title="文章阅读次数">' +
						'<i class="fa fa-fw fa fa-eye fa-fw"></i>&nbsp;' + data[i].watch + '</span>' +
						'<span title="" data-toggle="tooltip" data-placement="bottom" data-original-title="点赞">' +
						'<i class="fa fa-fw fa fa-thumbs-o-up"></i>&nbsp;' + data[i].click_like + '</span>' +
						'</div>' +
						'</article>' +
						'</li>';
					obj.append(item);
				}
			},
			error: function() {
				alert("服务器内部错误");
			}
		})
	}
	
	$.ajax({
		url:"/userMessage/getIndexBlogger",
		type:"get",
		dataType:"json",
		async:false,
		cache:true,
		success:function(data){
			var obj=$('#blogger-recommend');
			for(var i=0;i<data.length;i++){
				var item='<div class="panel">'+
						'<div class="panel-body">'+
							'<div class="row">'+
								'<div class="col-sm-4">'+
									'<img src="'+data[i].headPortrait+'" alt="用户头像" />'+
								'</div>'+
								'<div class="col-sm-8">'+
									'<div><span>'+data[i].userName+'</span><span class="pull-right"><a href="#">+关注</a></span></div>'+
									'<div>文章：<span>'+data[i].articleNum+'</span></div>'+
									'<div>粉丝：<span>'+data[i].fansNum+'</span></div>'+
								'</div>'+
							'</div>'+
						'</div>'+
					'</div>'
					obj.append(item);
			}
		},
		error:function(){
			alert("服务器内部错误");
		}
	})

	getArticle();

	$('.tag').click(function() {
		var tagName = $(this).attr('data-name'); //当前标签名
		dataCommit.tag = tagName;
		var obj = $('#article-list');
		obj.html("");
		getArticle();
	})

	$('#load-more').click(function() {
		dataCommit.num+=10;
		getArticle();
	})


	/*begin-------------------------------------------------------------------------返回顶部按钮显示*/
	// 	$(window).scroll(function() {
	// 		var scrollTop = $(this).scrollTop();
	// 		var scrollHeight = $(document).height();
	// 		var windowHeight = $(this).height();
	// 		// alert(scrollTop+"+"+windowHeight+"="+scrollHeight);
	// 		if (scrollTop >= windowHeight / 2) {
	// 			$(".my-up").show();
	// 		} else {
	// 			$(".my-up").hide();
	// 		}
	// 	});
	/*end--返回顶部按钮显示*/


	/* begin----------------------------------------------------------------------------------导航栏当前样式 */
	$(".content-lf li a").click(function() {
		$(".content-lf li .active").removeClass("active");
		$(this).addClass("active");
	})

	/* begin-------------------------------------------------------------------------加载bootstrap中的tooltip功能*/
	$("[data-toggle='tooltip']").tooltip();
	/* end----加载bootstrap中的tooltip功能*/

	/*begin-------------------------------------------------------------------------鼠标点击事件*/
	$.extend({
		bubble: {
			_tip: ['法制', '爱国', '敬业', '诚信', '友善', '富强', '民主', '文明', '和谐', '自由', '平等', '公正'],
			init: function() {
				var bubbleIndex = 0;
				$('body').click(function(e) {
					bubbleIndex = bubbleIndex >= $.bubble._tip.length ? 0 : bubbleIndex;
					if (!e.originalEvent) {
						return;
					}
					var x = e.originalEvent.x || e.originalEvent.layerX || 0;
					var y = e.originalEvent.y || e.originalEvent.layerY || 0;
					var html = '<span style="position: fixed;z-index:9999;left: ' + x + 'px;top: ' + y + 'px;"></span>';
					var $box = $(html).appendTo($(this));
					$box.effectBubble({
						y: -100,
						className: 'thumb-bubble',
						fontSize: 0.5,
						content: '<i class="fa fa-smile-o"></i>' + $.bubble._tip[bubbleIndex]
					});
					setTimeout(function() {
						$box.remove();
					}, 1002);
					bubbleIndex++;
				});
			},
			unbind: function(duration) {
				$("body").unbind('click');
				if (duration && !isNaN(duration = parseInt(duration))) {
					setTimeout(function() {
						$.bubble.init();
					}, duration);
				}
			}
		}
	});
	/* 鼠标点击弹出提示动画 */

	$.fn.extend({
		// 文字向上冒泡
		effectBubble: function(options) {
			var op = $.extend({
				content: '+1',
				y: -100,
				duration: 1000,
				effectType: 'ease',
				className: '',
				fontSize: 2
			}, options);

			return this.each(function() {
				var $box = $(this),
					flyId = 'effect-fly-' + (new Date().getTime());

				var tpl =
					'<span id="#flyId#" class="effect-bubble-fly #class#" style="opacity: 1;top:#top#px;left:#left#px;font-size: #fontSize#rem;">#content#</span>';
				var html = tpl.replaceAll('#left#', 12).replaceAll('#top#', -8)
					.replaceAll('#flyId#', flyId).replaceAll('#content#', op.content)
					.replaceAll('#class#', op.className).replaceAll('#fontSize#', op.fontSize);

				var $fly = $(html).appendTo($box);
				$fly.fadeIn(100, "swing", function() {
					$fly.animate({
						top: op.y,
						opacity: 0
					}, 100, function() {
						$fly.fadeOut(op.duration, function() {
							$fly.remove();
						});
					});
				});
			});
		}
	});

	/**
	 * 扩展String方法
	 */
	$.extend(String.prototype, {
		trim: function() {
			return this.replace(/(^\s*)|(\s*$)|\r|\n/g, "");
		},
		startsWith: function(str) {
			return new RegExp("^" + str).test(this);
		},
		endsWith: function(str) {
			return new RegExp(str + "$").test(this);
		},
		replaceAll: function(os, ns) {
			return this.replace(new RegExp(os, "gm"), ns);
		}
	});


	$.bubble.unbind(1);
	/*end--鼠标点击事件*/

	$("#form-test").bootstrapValidator({
		// 参数设置
		live: 'enabled', //验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
		excluded: [':disabled', ':hidden', ':not(:visible)'], //排除无需验证的控件，比如被禁用的或者被隐藏的
		submitButtons: '#btn-submit', //指定提交按钮，如果验证失败则变成disabled
		message: '通用的验证失败消息', //好像从来没出现过
		feedbackIcons: { //根据验证结果显示的各种图标
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		// 验证区域
		fields: {
			// 用户名对应的name属性
			email: {
				validators: {
					notEmpty: {
						message: "邮箱不能为空"
					},
					emailAddress: {
						message: "邮箱格式错误"
					}
				}
			},
			password: {
				validators: {
					notEmpty: {
						message: "密码不能为空"
					},
					stringLength: {
						min: 6,
						max: 20,
						message: "输入6-20位的密码"
					},
				}
			}
		}
	})
})

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
