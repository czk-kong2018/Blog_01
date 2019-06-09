---
title: JSP笔记
date: 2018-06-05 22:39:33
categories: JavaWeb
tags:
    - servlet
    - jsp
---
### 表达式转换
表达式<%=Counter.getCount()%>会被容器转为out.print(Counter.getCount());所以表达式里不需要分号。 若里面方法没有返回值会报错。
### 声明实例变量
在jsp里声明实例变量如 int i=1; 要想每次都让i没有被初始化。只能这么声明
<%! int i=0; %>  转为servlet时，会成为一个实例变量。
### jsp转为servlet
.jsp->.java->加载->.class->调用jspInit()->servlet，创建一个线程
运行_jspService()。过程转换太多，需要提前编译转换不然让客户等太久了。

1.查看指令，得到转换时可能需要的信息
2.创建HttpServlet子类
3.导入 page指令的import的类
4.将<%! %>声明写到类文件，在服务方法的前面。
5.会生成 服务方法 _jspServlet(),覆盖servlet超类的service()方法，并由它调用
6.将普通html文本和表达式放到服务方法中，完成格式化，写到PrintWriter响应输出
### 九大内置对象
 out（JspWriter）：与之对应的对象JspWriter，用来向客户端发送文本数据，简单理解JSPWriter就是带缓存的PrintWriter;
 config（ServletConfig）：对应“真身”中的ServletConfig；
 page（当前JSP的真身类型）：当前JSP页面的“this”，即当前对象；
 pageContext（PageContext）：页面上下文对象，它是最重要的一个内置对象。它封装了其他8大内置对象的引用！也就是说可以通过它获得其他8大对象。
 ```
    pageContext.getException();
    pageContext.getRequest();
    pageContext.getResponse();
    pageContext.getServletConfig();
    pageContext.getServletContext();
    pageContext.getSession();
 ```
作用域仅限本页面（好像可以改试过了没成功，等用到再说吧- -）;
```
page1.jsp
 <%
    pageContext.setAttribute("user","kong");
  %>

<%
  String name =(String) pageContext.getAttribute("user");
  out.println(name);
%>
```

 exception（Throwable）：封装了JSP页面抛出的异常信息，如该页面有空指针异常，则会把异常信息封装在里面。
 request（HttpServletRequest）：即HttpServletRequest类的对象；
 response（HttpServletResponse）：即HttpServletResponse类的对象；
 application（ServletContext）：即ServletContext类的对象；
 session（HttpSession）：即HttpSession类的对象，不是每个JSP页面中都可以使用，如果在某个JSP页面中设置<%@page session=”false”%>，说明这个页面不能使用session。

在这9个对象中有很多是极少会被使用的，例如：config、page、exception基本不会使用

在这9个对象中有两个对象不是每个JSP页面都可以使用的：exception、session。

在这9个对象中有很多前面已经学过的对象：out、request、response、application、session、config。
### 可以覆盖的方法
容器根据jsp生产一个类，这个类实现了HttpJspPage接口。这是生产servlet的API里的一个接口只需要了解这个。 有3个关键方法 jspInit() 可以覆盖  jspDestroy()可以覆盖
_jspService()不要覆盖。
### include行为
静态包含;<#@ include file="head.jsp"%>会将该页代码***包含进来,在编译****
动态包含:<jsp:include page="head.jsp"/>,其行为封装了request.getRequestDispatcher(url).include(request,response);***先编译，再将结果包含进来**, 动态包含比较好,如果你使用静态包含，两个页面有相同的变量，就凉了，动态包含就不用担心。
### forward行为
<jsp:forward page="" />本质封装了request.getRequestDispatcher(url).forward(request,response);请求转发，若想传参  
```
<jsp:forward page="errorPage.jsp">
    <jsp:param name="kong" value="1231"/>
  </jsp:forward>
```
### javaBean
一个普通的java类，不过要满足一定的规范
1.有无参构造方法; 
2.成员属性必须是private; 
3.封装属性要有公有getter和setter方法;
### jsp的javaBean动作
1. <jsp:useBean>在jsp页面中查找javaBean对象或者实例化对象，存在则返回该引用，不存在则新建对象并返回引用。
class表示类所在的地方，scope可选表示作用范围，id是实例引用名。
```
  <jsp:useBean id="person" class="demo.Person" scope="page"/>
  <%
      person.setName("kong");
      out.println(person.getName());
  %>
  没有用动作则是这样的
    <%
    Person person1=new Person();
    person1.setName("hai");
    out.println(person1.getName());
  %>
```
2. <jsp:setProperty>设置javaBean属性，会自动帮我们把表单传递的数据封装到javaBean对象中，**前提是javaBean属性和表单中name属性一样**
```
<%--<jsp:setProperty name="person" property="name"/>--%>
<jsp:setProperty name="person" property="*"/>  
<%
    byte[] bytes=person.getName().getBytes("ISO-8859-1");  //对付乱码的万能法
    String name=new String(bytes,"utf-8");
    out.println(name);
    out.println(person.getId());
%>
```
3.  <jsp:getProperty> 获取javaBean属性,会自动打印
```
<jsp:getProperty name="person" property="name"/>
<jsp:getProperty name="person" property="id"/>
<%--<jsp:getProperty name="person" property="*"/>--%>
```
4.只有当新建了bean才会设置属性值，若bean已存在则忽略setProperty
```
<jsp:useBean id="person" class="demo.Person"  scope="request">
      <jsp:setProperty name="person" property="id" value="123"/>
  </jsp:useBean>
```
### 建立多态
work接口有个work方法。person类实现接口，用type指定引用，class中的对象必须是type中的子类或者具体实现。
```
<jsp:useBean id="work" type="demo.Work"  class="demo.Person" scope="page"/>

  <%
      String s=work.work();
      out.println(s);
  %>
```



### EL表达式
在1.jsp页面
```
  <%
    session.setAttribute("name","xiaobai");
  %>
```
在2.jsp页面
```
传统办法
<%
    out.println((String)session.getAttribute("name"));
%>
使用EL,简单几个字母就可输出
${name}
```
**EL若找不到对象属性值会返回空字符串而不是null**
EL表达式执行本质调用servletContext.findAttribute(String name)，分别依次从  page-> request-> session-> application四个域中查找对象,也可遍历集合。（具体用法用到在说。。逃
### JSTL标签库和自定义标签库和简单标签

- -同样，感觉会很少用到。。具体用到再来学语法。还是能稍微看得懂的（逃

### 常见面试题
1.静态包含和动态包含的区别;
2.jsp内置对象有哪些,作用是什么;
3.jsp和servlet的区别,共同点,各自的应用;（tip: jsp侧重视图,servlet侧重控制逻辑
4.属性作用域
5.jsp只是第一次执行比sevlet慢了,后面都差不多。
6. CGI和servlet的区别;
 CGI:来一个请求创建一个进程，用完就销毁，效率低
 servlet: 只有一个实例，每次请求创建一个线程完成请求。

 ### 设计模式
1.JSP Model1第一代 
JSP Model1是JavaWeb早期的模型，它适合小型Web项目，开发成本低！Model1第一代时期，服务器端只有JSP页面，所有的操作都在JSP页面中，连访问数据库的API也在JSP页面中完成。也就是说，所有的东西都耦合在一起，对后期的维护和扩展极为不利。

2.JSP Model1第二代 
JSP Model1第二代有所改进，把业务逻辑的内容放到了JavaBean中，而JSP页面负责显示以及请求调度的工作。虽然第二代比第一代好了些，但还让JSP做了过多的工作，JSP中把视图工作和请求调度（控制器）的工作耦合在一起了。

3.MVC
JSP：视图层，用来与用户打交道。负责接收用来的数据，以及显示数据给用户； 
Servlet：控制层，负责找到合适的模型对象来处理业务逻辑，转发到合适的视图； 
JavaBean：模型层，完成具体的业务工作，例如：开启、转账等。