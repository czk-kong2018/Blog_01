---
title: Servlet入门(二)
date: 2018-05-18 22:39:33
categories: JavaWeb
tags:
    - servlet
---
**前言**
&nbsp;&nbsp;万恶的夏天,出去回来一身汗.好了接下来算是正式开始学Servlet。
***
## 前提知识
- Tomcat是一个容器
首先理解Tomcat本质上是一个容器,它能够帮我们处理很多内部细节问题,诸如管理servlet,监听端口啊等等.先明白这点就行了,它帮我们做了许多工作让我们可以把精力放到业务逻辑代码上面.
- http请求
  http请求常用的Get和Post,接下来玩玩抓包,看看http请求里有什么东西.用chrome浏器
打开知乎登录界面,按F12打开开发者工具,找到Network,然后刷新一下(相当于重新发送一次请求),接下来你就能看到如下.
{% asset_img 1.png %}
Request URL:https://www.zhihu.com/signup?next=%2F(请求地址)
Request Method:GET (请求方式)
Status Code:200     (状态码)
Remote Address:127.0.0.1:54498  (请求的远程地址)
Accept: text/html,image/*(浏览器可以接收的类型) 
Accept-Charset: ISO-8859-1(浏览器可以接收的编码类型) 
Accept-Encoding: gzip,compress(浏览器可以接收压缩编码类型) 
Accept-Language: en-us,zh-cn(浏览器可以接收的语言和国家类型) 
Host: www.it315.org:80(浏览器请求的主机和端口) 
Referer: (请求来自于哪个页面) 
User-Agent: Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0)(浏览器相关信息) 
Cookie：(浏览器暂存服务器发送的信息) 
Connection: close(1.0)/Keep-Alive(1.1)(HTTP请求的版本的特点) 
Date: Tue, 11 Jul 2000 18:23:51 GMT(请求网站的时间) 
***
 大致先了解这些吧,爬虫需要了解抓包,等博主学了py考虑再写爬虫,用java写爬虫太蛋疼.
(逃) 
- 访问我们的servlet,先到达Tomcat容器.Tomcat去找servlet
{% asset_img 2.png %}
&nbsp;&nbsp;还记得之前我们写的helloworld配置的web.xml吗,Tomcat是如何找到servlet的.
> 首先在servlet-mapping中找到url-patteren里的外界地址,然后找servlet-name,根据servlet-name的内容找到<servlet>中有相同的servlet-name的,这就是为何要保持两个servlet-name相同的原因.然后在找到servlet-class里的便是我们所要的servlet.
> 
{% asset_img 3.jpg %} 
&nbsp;&nbsp;生成请求和响应对象,实例化servlet并创建一个线程,将请求响应对象传给servlet.容器调用Servlet的service方法.
{% asset_img 3.png %}
&nbsp;&nbsp;你可能会有疑惑.之前写的helloworld中并没有什么doGet,doPost方法啊.
> 那是因为之前我们实现的是Servlet接口,这里是继承HttpServlet类.HttpServlet是我们比较常用的.HttpServlet实现了servlet接口,并把servlet接口中的方法实现了.继承Httpservlet实际上也就实现了servlet接口,但是我们没必要再去实现servlet中定义的生命周期方法,因为在httpservlet中已经有了默认实现,并且这些默认实现也挺规范和实用.我们一般只要要重写doGet和doPost方法就行了.(后面再仔细讲解先大致有个了解)
>
{% asset_img 4.png %}
&nbsp;&nbsp;Get请求调用doGet(),Post请求调用doPost().返回响应对象,作为Http响应传给客户端.
{% asset_img 5.png %}
***
## 编写第一个Servlet
&nbsp;&nbsp;以一个简单登录系统为例.
- 先在首页模拟出一个登陆表单.提交方式为post.
{% asset_img 6.png %}
- 编写处理登陆的Servlet类,继承Httpservlet,只需重写doPost方法,因为我们的登陆是Post方式提交.service方法会调用doPost().为了简化操作我们简单判断账号密码是否等于123就行了.
&nbsp;&nbsp;**重要的是"text/html;charset=utf-8",不加会出现中文乱码问题.**
{% asset_img 7.png %}
- 好了跑一下程序打开F12开发者工具抓包看看,你会看到我们发送出去的http报文.这些都是浏览器帮我们弄好的.还可以看到我们post过去的账号密码.
{% asset_img 8.png %}
&nbsp;&nbsp;后面就不放图了.输入账号密码123就会显示登录成功,否则登陆失败.
##Servlet的生命周期  
1. 加载Servlet类。当Tomcat第一次访问Servlet时,通过访问web.xml将Servlet加载并创建Servlet实例对象.
2. 对Servlet初始化,调用init方法。将ServletConfig此时成为一个真正的Servlet 在此之前只能算一个普通的java类。
3. 调用Servlet的destroy()方法。销毁Servlet,让该实例释放所占资源,一般是Tomcat关闭或者Servlet长时间未被使用时销毁。
4. 卸载。当调用完destroy()方法时,等待垃圾回收,若有需要再次使用时会重新调用init()方法进行初始化操作。
