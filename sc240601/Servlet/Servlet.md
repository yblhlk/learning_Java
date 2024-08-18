## Servlet

### 0. 为什么要学习Servlet？

> 因为Servlet是SpringMVC框架的底层基础。
> 就像JD♭C是Mybatis框架的底层基础。

> 可以用什么技术实现控制层、dao层？

#### 0.1 什么是框架？

> ​		  Java框架（Java Framework）是预先封装、构建好的一套结构化的、可重用的代码库，开发人员可以在此基础上构建或扩展他们的应用程序，而无需从头开始编写所有必要的代码。
> ​			框架不但是一个半成品，它还提供一个完成这个半成品所需要的一些工具。

###  1. 软件架构B/S和C/S

#### 1.1 C/S架构

>  client客户端/server服务端，基于服务端和客户端的软件架构(缺一不可)，凡是C/S必须安装指定的客户端连接服务器才可以使用，注意完全在本地运行，不依赖于远程服务器的软件不是C/S架构。
>
> - 优点: 交互性更强(卡顿和延迟因为有些数据可以在本地运算，而不是全交给服务器来运算)，数据安全性更高，可以支持离线操作。
> - 缺点: 用户使用不方便(需要下载客户端)、客户端需要针对性开发、维护和版本变更非常不灵活。

#### 1.2 B/S架构

>  browser浏览器/server服务端  基于浏览器和服务端软件架构    只需要安装浏览器即可使用  所有程序都部署在服务端。如网页版的学习通。
>
> - 优点:  使用方便    维护方便  版本变更 成本非常低  分布性非常强   
> - 缺点:  对网络的依赖性强、  数据安全不强 、 对服务器要求很高  

### 2.JSP  

#### 2.0 是什么？

> jsp(java  server  pages)就是java服务器页面，jsp = html + java，它就是在传统的html页面中   植入一些java代码  实现页面数据动态的功能。

#### 2.1 jsp的底层

> jsp执行原理:   jsp页面 -> ==翻译成*.java== -> 编译成*.class ->==执行== -> 结果返回到浏览器。
>
> 翻译成的.java代码文件和.class字节码文件可以在Tomcat的镜像的Work包中找到。
>
> JSP页面经过`编译`之后，将创建一个sevlet(就是一个Java文件，这个Java文件是一个标准的Servlet类)，然后编译为.class字节码文件。

#### 2.1 jsp的使用

##### a. 指令

> 用来指定整个jsp页面的相关属性。
>
>- `page指令`: 设置页面整体属性 比如：文件类型、编码、==启用EL表达式== ，一般会写在页面中的第一行。
>
>  ```jsp
>  <%@page  属性名=属性值   %>
>  <%@page contentType="text/html;charset=utf-8" %>
>  ```
>
>- `include指令`: 在jsp中引入其他jsp资源的。
>
>  ```jsp
>  <%@include file="xxx.jsp"  %>
>  ```
>
>- `taglib指令`:  是为了引用外部库资源(核心标签库   格式化标签库  函数库)   ==前提: 需要导入一个依赖JSTL==
>
>  > **taglib指令主要是在JSP页面中使用**。它是JSP规范的一部分，用于引入标签库，使得开发者可以在JSP页面中使用自定义的标签。
>
>  ```jsp
>  <@taglib uri="网址" prefix="前缀" %>
>  <@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
>  <%-- prefix属性用于指定标签库中标签的前缀。它的值和下面标签中指令中的前缀相同，可以随便写 --%>   
>  <%-- 写一个<c:forEach>就会自动帮助我们导入，注意导入的是jsp/jstl下的 --%>
>  ```
>
>  > 使用taglib指令：
>  >
>  > a. 在后端把数据存储到一个作用域中（存储方式类似于键值对，给数据一个键）。
>  > b. 在maven项目的pom.xml文件中导入servlet\jsp\JSTL三个依赖。
>  > c. 在jsp中(taglib指令主要在JSP中使用)先导入taglib标签和指定它的前缀。==还要设置支持EL表达式==
>  > d. 使用指定的前缀去使用taglib标签，用items属性获取作用域中的指定数据，使用var属性将其取出。
>  >
>  > ![image-20240801085532263](D:\Desktop\gitee\java-learning\sc240601\Servlet\img\使用taglib指令.png)

##### b. 脚本和表达式

```java
脚本语言:   <% 任意的java代码  %>
表达式语言:  <%=表达式%> 输出表达式的结果
```

##### c. 注释

```java
<!-- 只能注释html标签 不可以注释脚本语言 -->
<%-- 注释jsp所有内容 --%>
```

#### 2.2 使用jsp实现网页之间的数据传递

##### 0. 前提

>maven项目要导入jsp\servlet\jstl依赖。
>
>```xml
><!--jsp依赖-->
><dependency>
><groupId>javax.servlet.jsp</groupId>
><artifactId>javax.servlet.jsp-api</artifactId>
><version>2.3.3</version>
><scope>provided</scope>
></dependency>
>
><!--servlet依赖-->
><dependency>
><groupId>javax.servlet</groupId>
><artifactId>javax.servlet-api</artifactId>
><version>4.0.1</version>
><scope>provided</scope>
></dependency>
>
><!--jstl依赖-->
><dependency>
><groupId>javax.servlet</groupId>
><artifactId>jstl</artifactId>
><version>1.2</version>
></dependency>
>```
>
>前端页面要读取jsp发来的数据：
>a. 使用taglib指令导入核心标签库： <@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
>b. 使用page指令设置不忽略EL表达式。

##### A. 使用jsp发送数据给前端页面

> a. 把数据保存到作用域中(用resquest对象的setAttribute()，jsp页面自带这个对象不需要创建，直接对象.方法)
>
> ```jsp
> <%-- 编写脚本语言 --%>
> <%
> 	List<String> list = new ArrayList<>();
> 	list.add("aaa");
> 	list.add("bbb");
> 	list.add("ccc");
> 	//把list存储到一个作用域中（这里使用request作用域）
> 	//存储方式类型于map集合(key,value)，前端页面通过Taglib指令和key来取出作用域中的数据
> 	request.setAttribute("testList",list);
> %>
> ```
>
> b. 在前端页面使用taglib指令来接收。
>
> ```jsp
> <%page isELIgnored="false"%>
> <%-- jsp默认会忽略EL表达式(就是下面${}的写法，所以一定要在page指令中关闭忽略 --%>
> <C:forEach var="s" items="${testList}">
> 	<li>${s}</li>
> </C:forEach>
> ```
>
> ![image-20240803170123709](D:\Desktop\gitee\java-learning\sc240601\Servlet\img\使用jsp发送数据给前端页面.png)

##### B.使用jsp接收前端页面发来的数据

> a. 要传递数据的页面使用form表单提交数据到指定页面
>通过action标签设置提交到的指定页面，通过method标签设置提交方式。
> 
>  ```html
>  <!-- form标签的常见属性 -->
>    <form action="提交的地址"
>    method="请求方式 post/get"
>    enctype="做文件上传必须添加 数据提交方式:默认按照字符串提交 或者改成附件提交"
>    onsubmit="表单提交事件 表单提交了就会触发的事件 验证数据是否正确 表单验证">
>  ```
> 
>  c. 接收数据的页面用脚本 + `request.getParameter("表单中的name标签")` 来接收数据
>  注意上面的request是jsp自带的对象。

#### 2.3 通过jsp实现注册功能流程图

> * 1.先转码
> * 2.获取页面提交的数据
> * 3.调用dao层（jdbc)(jsp控制层 -> servlet控制层 -> springmvc控制层)
> * 4.根据控制层返回成功和失败
>
> ![image-20240801135946402](D:\Desktop\gitee\java-learning\sc240601\Servlet\img\通过jsp实现注册功能流程图.png)

#### 2.4 jsp实现转发(forward)和重定向(redirect)

> ```jsp
> 1. 重定向
> response.sendRedirect("/XXX/xxx");
> 2. 转发(必须写相对路径)
> request.getRequestDispatcher("../相对于当前jsp页面的地址").forward(request,response);
> ```
>
> ![image-20240801201453886](D:\Pictures\servlet\jsp实现转发和重定向.png)

> ```jsp
> 测试通过转发和重定向来获取request作用域的数据和在WEB-INF目录下的内部资源
> <%@ page contentType="text/html;charset=UTF-8" language="java" %>
> <html>
> <head>
>     <title>One</title>
> </head>
> <body>
> <% //测试代码
> // 转发适合相对路径/包/页面  一次请求  内部访问，只能访问内部资源
>     // 通过request作用域 存储的数据  会在一次请求内有效。
>     // 存储方式： request.setAttribute("msg", "requestMag");
>     // 转发只能访问内部资源 不能访问外部资源（项目以外的内容）
>     // 因为转发是相对于当前的目录在访问。（就是在访问路径后面直接加上要访问的路径，如/day1/www.baidu.com）
>     request.setAttribute("msg", "requestMag"); //将数据存储在request作用域
>     request.getRequestDispatcher("/day3/two.jsp").forward(request, response);//转发可以获取request作用域中存储的数据
> 	request.getRequestDispatcher("/WEB-INF/in.jsp").forward(request, response);//转发可以访问WEB-INF中的内部资源
>     
> // 重定向适合决定路径/项目/包/页面  两次请求
>     // 1.重定向为什么不能获取request作用域中存储的数据？
>     // 地址栏变了，因为是两次请求 所以request作用域 存储的数据会失效
>     // 2.重定向为什么不能访问WEB-INF?
>     // 第一次请求是临时重定向302， 第二次请求是get请求 表示最终跳转
>     // 是有第二次请求 不能通过地址直接访问。
>     response.sendRedirect("/day3/two.jsp"); // 1.重定向不能获取request作用域中存储的数据
>     response.sendRedirect("/WEB-INF/in.jsp"); // 2.重定向不能访问WEB-INF中的内部资源
> %>
> </body>
> </html>
> 
> ```

### 3.Http协议

> http协议是超文本传输协议，是实现服务器和浏览器做超文本(文本 视频 音频 文件 数据...)数据传输的传输协议，是基于请求request和响应response的。 Http3以前是基于TCP的。

#### 3.1 Http协议特点

> - 无连接:    每次只会处理一个请求  服务器处理完后自动断开  没有连接这个概念 
> - 无状态:    每次请求都是独立的  不会单独保存请求前后的数据  如果下一个请求还需要之前的数据 需要重新传递   除非将数据存储作用域里面  或者存储到缓存Cookie
> - 灵活:   可以传输任何数据  而且传输的内容 页通过content-type设置文档类型来控制

#### 3.2 Http协议请求方式 --- 面试题

> - post请求: 用于向服务器提交数据(类似于新增)  通过表单提交的数据 一般都要设置成post请求。
>   POST请求的参数不会附加在URL的后面，而是包含在请求体中，以二进制形式传输。
> - get请求:   用于向服务器获取数据(类似于查询)  应用范围最广的  绝大部分的请求都是get方式    比如: 在浏览器编写网址发送请求,  超链接发送请求 通过js  导入css   img导入图片 ... 
>   GET请求的参数会附加在URL的后面，以问号（?）分隔。
> - put请求：用于修改服务器数据的(类似于修改)
> - delete请求：用于删除服务器数据的(类似于删除)

#### 3.3 get和post请求区别  --- 面试题

> - 应用场景不同:  get用于获取服务器数据的   post用于向服务器提交数据的
> - 数据传输方式:  get请求会将数据在地址栏通过?显示出来；
>       post请求属于隐式提交，他不会在地址栏中显示数据，但是可以查看浏览器控制台 看到数据
> - 数据传输长度: get请求是在地址栏显示 传输长度会受到浏览器限制而决定 大约是1-2k 左右   post请求理论上没有长度限制
> - 安全性:   post方式相比get方式 更安全   所以说一般传输一些敏感信息   只能使用post   比如: 密码  信用卡    get方式适合传递不重要的信息   比如: 传递搜索的内容  传输分页的页面数 
> - ==上传文件功能  只能使用post方式。== 

##### get请求为什么能提交数据？

> 本质上不是get请求在传递数据，是因为URL能传递少量的数据（就是在地址栏拼接数据来传递）
> HTTP协议早期设计时，GET请求就是被用于请求数据，POST请求就是用于提交数据的。
> GET请求通过将数据附加到URL的末尾（查询字符串）来发送数据。这些数据以键值对的形式存在，并通过URL编码进行传输。这使得GET请求可以携带一些简单的、非敏感的数据。

#### 3.4 Http请求状态码  --- 面试题

> http状态码用来唯一标识  请求目前处于的状态  
>
> - 200:  请求成功
> - 301:   永久请求重定向
> - 302:   临时请求重定向
> - 400:   客户端数据异常  前端提交的数据和后端接收的数据 类型不一致 而且也无法转换（最常见的情况：springmvc项目中前端传字符串，后端用日期格式接收，解决方法：加@DateTimeFormat注解)
> - 403:    请求被拒绝(没有权限)   添加了权限功能  
> - 404:    地址不对 ,或者 服务器启动编译报错 导致项目没有成功部署
>       或者 把访问的资源放入WEB-INF里  因为它不能直接访问
> - 405：不支持错误，因为重写doGet()和doPost()时不去掉super()使用了父类的方法，就会报错405 。如果什么都不写就是一片空白。
> - 406：表明服务器无法根据客户端请求的内容特性（Accept 请求头）来生成响应。这通常意味着服务器无法提供客户端所请求的内容格式或语言版本。
>   如浏览器发送的是json类型数据（如 `application/json`），但服务器可能只支持其他类型（如 `text/html`）。
> - 500:    服务器异常。  

> HTTP请求状态码（HTTP Status Code）是由三位数字组成的代码，用于表示HTTP请求是否成功完成以及请求的结果状态。这些状态码由RFC 2616规范定义，并得到后续规范的扩展。
> HTTP状态码可以分为五大类：信息响应（100–199）、成功响应（200–299）、重定向（300–399）、客户端错误（400–499）和服务器错误（500–599）。
>
> 以下是一些常见的HTTP请求状态码及其含义：
>
> ### 1. 信息响应（1xx）
>
> - **100 Continue**：服务器已初步接受了请求，客户端应继续发送请求的其余部分。
> - **101 Switching Protocols**：服务器根据客户端的请求切换协议，例如，升级到WebSocket协议。
>
> ### 2. 成功响应（2xx）
>
> - **200 OK**：请求成功。这是最常见的状态码，表示请求已成功被服务器处理。
> - **204 No Content**：服务器成功处理了请求，但没有返回任何内容。
> - **206 Partial Content**：客户端进行了范围请求，而服务器成功执行了这部分的GET请求。
>
> ### 3. 重定向（3xx）
>
> - **301 Moved Permanently**：请求的资源已永久移动到新的URL，应使用新的URL进行访问。
> - **302 Found**：临时性重定向。请求的资源临时从不同的URL响应请求。
> - **304 Not Modified**：客户端缓存的资源是最新的，无需再次发送请求到服务器。
> - **307 Temporary Redirect**：临时重定向，和302有相同的含义，但307不会强制浏览器将POST方法改为GET方法。
>
> ### 4. 客户端错误（4xx）
>
> - **400 Bad Request**：客户端请求的语法错误，服务器无法理解。
> - **401 Unauthorized**：请求需要身份验证。
> - **403 Forbidden**：服务器理解请求，但是拒绝执行它。
> - **404 Not Found**：服务器无法找到请求的资源。
> - **405 Method Not Allowed**：请求中使用的HTTP方法不被允许。
>
> ### 5. 服务器错误（5xx）
>
> - **500 Internal Server Error**：服务器遇到了一个未曾预料的情况，导致其无法完成对请求的处理。
> - **502 Bad Gateway**：作为网关或代理工作的服务器尝试执行请求时，从上游服务器接收到无效的响应。
> - **503 Service Unavailable**：由于临时的服务器维护或者过载，服务器当前无法处理请求。
> - **504 Gateway Timeout**：作为网关或者代理工作的服务器没有从上游服务器收到及时的响应。
>
> HTTP状态码是网络通信中的重要组成部分，掌握它们的含义和用途对于优化网络交互、提高用户体验具有重要意义。当在浏览网页或使用网络应用时遇到问题时，可以查看返回的状态码，并根据状态码的含义来采取相应的解决措施。

### 4.Servlet  --- 重点  springmvc底层核心

> ​        Servlet是一段java程序,用于处理前端发送的请求(注册  登录请求) 并且同时还可以给浏览器响应结果  类似于之前实现的jspController页面的功能 ，用于实现控制层(controller)。

#### 4.0 控制层能做什么？

> 用于处理前端发送的请求(注册  登录请求) 并且同时还可以给浏览器响应结果。

#### 4.1 Servlet实现方式 

> 主要有两种：
>
> - 继承HttpServlet抽象类, 重写doGet()  doPost()  doPut()  doDelete()
>
>   底层实现其实通过实现Servlet接口封装出来的方法
>
> - 实现Servlet接口  重写三个主要的方法(init()\service()\destroy()) --- 了解 Servlet生命周期

#### 4.2 HttpServlet实现步骤

> - 继承HttpServlet
>
> - 重写doGet()  doPost()  ...
>
>   > 重写doGet方法获取前端数据：
>   >
>   > ![image-20240812072259486](D:\Desktop\gitee\java-learning\sc240601\Servlet\img\重写doGet方法.png)
>   >
>   > 重写doGet方法发送数据给前端：
>   > ![image-20240812184011179](D:\Desktop\gitee\java-learning\sc240601\Servlet\img\重写doGet方法发送数据给前端.png)
>
>   > 一个Servlet负责处理对一个实体类/dao接口的所有请求。
>   > 一个Servlet只有一个doGet()，那怎么知道如何去处理哪个具体的请求？
>   > 前端发送请求时通过？拼接一个type键值对来控制doGet()具体要处理哪个请求。
>
> - 配置Servlet (让Servlet真正初始化 实例化)
>
>   - 通过web.xml配置文件 --- 推荐（web.xml在启动tomcat服务器的时候会默认读取。
>
>     ```xml
>     一个servlet标签可以对应多个servlet-mapping标签，但是至少对应一个，表示controller对象要处理哪些请求。
>     <servlet>
>         <!--servlet名称任意写 但是不要和别的Servlet重名-->
>         <servlet-name>user</servlet-name>
>                                 
>         <!--告诉配置文件 哪个类实现了Servlet 要把这个类的唯一的地址给我(全类名)-->
>         <!--底层:通过全类名获取Class对象  通过CLass对象创建Servlet实例 原理就是反射-->
>         <servlet-class>controller.UserController</servlet-class>
>     </servlet>
>                             
>     <servlet-mapping>
>         <!--必须跟Servlet的名称对应-->
>         <servlet-name>user</servlet-name>
>         <!--配置请求地址:发送什么请求可以进入该Servlet处理
>            写法: /地址  必须加斜杠
>         -->
>         <url-pattern>/user</url-pattern>
>     </servlet-mapping>
>     ```
>
>     
>
>   - 通过@WebServlet注解来配置 --- 了解
>
>     ```
>                             
>     ```
>

#### 实现HttpServlet的实例

> 控制层: 负责处理用户表所有的请求: 登录 注册  新增 删除 搜索 修改...
> 1.继承 HttpServlet
> 2.重写 doGet  doPost ……
>
> > a. request: 请求对象 ==可以设置转发跳转==  可以提交的数据  可以设置中文编码
> > ==表示前端发送的一次请求 每次请求request都是独立的==
> > 类型HttpServletRequest 父类是ServletRequest
> >
> > b.response: 响应对象  ==可以设置重定向跳转==  可以添加缓存Cookie
> > 表示请求处理后 响应结果 通常是给前端响应
> > 类型HttpServletResponse 父类是ServletResponse
>
> ```java
> public class UserController extends HttpServlet{
>  //get请求进入该方法处理
>  //request: 请求对象 可以设置转发跳转  可以提交的数据  可以设置中文编码
>  //表示前端发送的一次请求 每次请求request都是独立的
>  //类型HttpServletRequest 父类是ServletRequest
> 
>  //response: 响应对象  可以设置重定向跳转  可以添加缓存Cookie
>  //表示请求处理后 响应结果 通常是给前端响应
>  //类型HttpServletResponse 父类是ServletResponse
> 
>  UserDao ud=new UserDaoImpl();
>  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
>      //1.设置中文编码: bug:这行代码必须在获取数据前面写否则会失效
>      //告诉服务器，这个请求中的数据是以UTF-8编码的，服务器应该使用UTF-8来解码这些数据。
>      req.setCharacterEncoding("utf-8");
>      //如何知道请求来源  后面就可以控制不同的请求做不同的处理
>      String type=req.getParameter("type");
>      if(type==null){ //查询所有用户信息请求
>          show(req,resp);
>      }else if("login".equals(type)){//登录页面来的 只能处理登录请求
>          login(req,resp);
>      }
>  }
>  //post请求进入该方法处理
>  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
>      doGet(req,resp);
>  }
> 
>  private void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
>      //做什么?
>      //查数据   存储作用域  跳转到show页面
>      List<User> list=ud.show();
>      //作用域 一共有四种  page request session application
>      req.setAttribute("userList",list);
>      //跳转到userShow  不能使用重定向 底层是两次请求 跨域请求了
>      //上面 通过request作用域存储的数据就丢失了
>      //只能使用转发转发 因为转发只属于一次请求 属于内部跳转 一定不能写绝对路径
>      //  /day2/show.jsp   必须写相对路径  相当于当前servlet去找show页面
>      req.getRequestDispatcher("day2/userShow.jsp")
>              .forward(req,resp);
>  }
> 
> 
>  protected void login(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
>      //2.获取前端提交的数据
>      String phone=req.getParameter("phone");
>      String pw=req.getParameter("pw");
>      //3.调用dao层 根数据库交互  (后期会调用业务层service)
>      User u=ud.login(phone,pw);
>      //4.通过dao层返回的结果控制成功和失败
>      if(u!=null){//登录成功
>          resp.sendRedirect("/day2/home.jsp");
>      }else{//登录失败
>          resp.sendRedirect("/day2/login.jsp");
>      }
>  }
> }
> 
> ```
>
> 3.配置Servlet (在WEB-INF下的web.xml中配置)
> 一个<servlet>标签至少对应一个<servlet-mapping>标签通过<servlet-name>标签进行对应
>
> ```xml
> <web-app>
>     <display-name>Archetype Created Web Application</display-name>
>     <!--配置Servlet-->
>     <servlet>
>         <!--servlet名称任意写 但是不要和别的Servlet重名-->
>         <servlet-name>user</servlet-name>
>         <!--告诉配置文件 哪个类实现了Servlet 要把这个类的唯一的地址给我(全类名)-->
>         <!--底层:通过全类名获取Class对象  通过CLass对象创建Servlet实例 原理就是反射-->
>         <servlet-class>controller.UserController</servlet-class>
>     </servlet>
>     <servlet-mapping>
>         <!--必须跟Servlet的名称对应-->
>         <servlet-name>user</servlet-name>
>         <!--配置请求地址:发送什么请求可以进入该Servlet处理
>      		写法: /地址  必须加斜杠
>  			-->
>         <url-pattern>/user</url-pattern>
>     </servlet-mapping>
>     <!-- 一般Servlet可以对应 1个或者多个mapping标签-->
>     <servlet-mapping>
>         <servlet-name>user</servlet-name>
>         <url-pattern>/user2</url-pattern>
>     </servlet-mapping>
> </web-app>
> ```

#### 4.3 doGet\doPost方法中如何知道要处理的是什么请求？

> 1. 在在表单提交请求时在后面加一个type键值对（<a href = "/user?type=del">
> 2. 在doGet中使用req.getParameter("type");来获取这个值从而得知要处理的是什么请求，然后去调用对应的方法。

#### 4.4 初始化参数

将Servlet经常修改的内容，通过配置文件(web.xml)的方式进行编写，以后切换了不同的环境，只需要修改配置文件即可，类似于之前的jdbc.properties 
缺点只针对一个Servlet有效。

```xml
设置初始化参数：
<!--因为只对一个servlet有效所以是写在<servlet>标签中-->
<servlet>
	<servlet-name>...</servlet-name>
	<servlet-class>...</servlet-class>
	<!-- 注意初始化参数的子标签的顺序不能颠倒 而且可以设置无数组-->
	<init-param>
		<param-name>名称</param-name>
		<param-value>值</param-value>
	</init-param>
	<!--    设置servlet启动优先级
		默认值是负数:表示第一次发送请求初始化.
		设置整数 就表示服务器启动才会初始化
		取值范围1-10 值越小优先级越高
		如果两个都是1则按顺序来先读取到谁谁先加载
	-->
    <load-on-startup>1</load-on-startup>
</servlet>
```

```java
在servlet代码文件中获取初始化参数：
	//HttpServlet实现类中：通过HttpServletRequest或HttpServletResponse对象来获取
	String reqValue = getInitParameter("web.xml的<servlet>的<init-param>中设置的名称");

	//Servlet实现类的init()中：通过ServletConfig对象来获取
	String reqValue = servletConfig.getInitParameter("reqCharSet");
	System.out.println("CycleServlet:" + reqValue);
```

#### 4.5 全局参数

使用方法和含义几乎和初始化参数一致，唯一不同的地方是它可以针对所有的servlet都有效 适用于针对整个项目设置通用属性，比如通用的编码方式

```xml
设置全局参数：
<!--  配置全局参数必须放在web.xml中<servlet>标签的前面-->
<!--如果不知道标签的顺序可以将鼠标悬浮在<web-app></web-app>标签上面,看它的提示.-->
<context-param>
	<param-name>名称</param-name>
	<param-value>值</param-value>
</context-param>
```

```java
在servlet代码文件中获取全局参数：
// 如何获取全局参数： 要通过ServletContext对象（application作用域）
// 1.HttpServlet实现类中：
	// a.先获取ServletContext对象(application作用域)通过HttpServletRequest或Response对象来获取
	ServletContext app = req.getServletContext();
	// b.通过ServletContext对象来获取全局参数
	String respValue = app.getInitParameter("<init-param>中设置的名称");
// 2.Servlet实现类的init()中:
	// a.先获取ServletContext对象通过ServletConfig对象来获取
	ServletContext app2 = config.getServletContext();
	// b.通过ServletContext对象来获取全局参数
	String respValue = app2.getInitParameter("<init-param>中设置的名称");

```

#### 4.6 Servlet生命周期 

> * 初始化：servlet`默认第一次发送请求时不是先初始化`，而是先实例化Servlet(`饿汉式单例`只实例化一次，有不止一个Servlet对象所以使用惰性加载来提高性能) 调用`init()`进行初始化。
>   缺点是第一次发送请求可能会有延迟 。所以一般可以在配置文件添加一个标签`<load-on-startup>1</load-on-startup>`==目的是让其在服务器启动的时候做实例化== 然后进行初始化实例化和初始化都只进行一次。
> * 请求处理：每次发送请求 都会进入service()来处理 会执行多次。==service()是Servlet程序的入口点==
> * 销毁：`服务器关闭时`执行destroy()来销毁，只执行一次。

>Servlet的生命周期由Web容器（例如Tomcat）负责管理，并且主要划分为初始化、请求处理和销毁这三个核心阶段。
>
>1. **初始化阶段**：
>  - Servlet在首次被请求时，会由Web容器进行实例化（对于单例模式的Servlet，实例化过程仅发生一次）。
>  - 实例化完成后，容器会立即调用Servlet的`init()`方法进行初始化操作。
>  - 在`init()`方法中，Servlet可以执行如读取配置文件、初始化所需资源等预备工作。
>  - 默认情况下，Servlet的初始化是在其首次被请求时进行的。但为了优化用户体验，避免首次请求时的潜在延迟，我们可以在`web.xml`配置文件中利用`<load-on-startup>`标签来预设Servlet的加载顺序，使其在服务器启动时即完成实例化和初始化。
>
>2. **请求处理阶段**：
>  - 当客户端向服务器发送请求时，Web容器会负责调用Servlet的`service()`方法来处理这些请求。
>  - `service()`方法会根据请求的具体类型（如GET、POST等），进一步调用相应的`doGet()`、`doPost()`等方法来处理业务逻辑。
>  - 在此阶段，Servlet会执行核心的业务逻辑，并生成相应的响应数据返回给客户端。
>    ==service()是Servlet程序的入口点==
>
>3. **销毁阶段**：
>  - 当Web容器关闭或Web应用被卸载时，容器会调用Servlet的`destroy()`方法来执行销毁操作。
>  - 在`destroy()`方法中，Servlet应释放其占用的所有资源，例如关闭数据库连接等。
>  - 需要注意的是，`destroy()`方法在Servlet的生命周期中仅会被执行一次，它标志着Servlet生命周期的终结。
>
>综上所述，通过合理管理Servlet的初始化、请求处理和销毁这三个阶段，我们可以确保Servlet的高效稳定运行，并实现资源的正确释放。这对于维护Web应用的性能和稳定性至关重要。

#### 4.7 使用`<load-on-startup>`标签来控制多个Servlet的加载顺序

> 这也是减少首次请求时的延迟、提高用户体验的一种手段。
>
> `<load-on-startup>`标签在`web.xml`配置文件中用于预设不同Servlet的加载顺序。这个标签可以指定Servlet在应用启动时的加载优先级。其值是一个整数，表示Servlet的加载顺序，数值越小，Servlet的优先级越高，也就越早被加载和初始化。
>
> 如果你有多个Servlet，并且希望它们按照特定的顺序在应用启动时进行加载和初始化，那么你可以为每个Servlet配置`<load-on-startup>`标签，并赋予不同的整数值来实现这个目的。
>
> 例如：
>
> ```xml
> <servlet>  
>     <servlet-name>servlet1</servlet-name>  
>     <servlet-class>com.example.Servlet1</servlet-class>  
>     <load-on-startup>1</load-on-startup>  
> </servlet>  
> <servlet>  
>     <servlet-name>servlet2</servlet-name>  
>     <servlet-class>com.example.Servlet2</servlet-class>  
>     <load-on-startup>2</load-on-startup>  
> </servlet>
> ```
>
> 在这个例子中，`Servlet1`的加载优先级高于`Servlet2`，因为`Servlet1`的`<load-on-startup>`值为1，而`Servlet2`的值为2。所以，在应用启动时，`Servlet1`会先于`Servlet2`被加载和初始化。

#### 4.8 Servlet的底层实现

> Servlet使用HTTP协议在服务器与客户端之间进行通信。HTTP协议是在TCP/IP协议之上进一步封装的一层协议，它关注数据传输的格式是否规范。
> Servlet接收和发送的数据最终都是通过HTTP报文实现的，而这些报文在底层是通过Socket进行传输的。
>
> * Servlet：使用http协议在服务器与客户端之间通信的技术。是Socket的一种应用。
> * http协议：是在tcp/ip协议之上进一步封装的一层协议，关注的事数据传输的格式是否规范，底层的数据传输还是运用了socket和tcp/ip协议。
> * tcp/ip协议：关注的是客户端与服务器之间的数据传输是否成功（三次握手，传输失败会重发）

#### 4.9`req.getAttribute`和`req.getParameter`

> 在Java Servlet编程中，`req.getAttribute`和`req.getParameter`是两个常用的方法，它们都用于从`HttpServletRequest`对象中获取数据，但它们的用途和来源有所不同。
>
> ### req.getAttribute
>
> `req.getAttribute(String name)`方法用于从请求（request）的属性（attributes）集合中获取与指定名称相关联的对象。这些属性是在Servlet中通过`req.setAttribute(String name, Object o)`方法设置的，它们可以在请求的处理过程中被存储和检索，用于在同一请求的不同部分（如不同的Servlet或JSP页面）之间共享数据。
>
> - **用途**：主要用于在请求的处理过程中共享数据。
> - **作用域**：请求范围（request scope），即数据仅在当前的请求中有效。
> - **数据来源**：通过`req.setAttribute`方法设置的数据。
>
> ### req.getParameter
>
> `req.getParameter(String name)`方法用于获取客户端发送的请求参数的值。这些参数是通过HTTP请求发送的，通常是通过URL的查询字符串（query string）或HTML表单的输入字段。例如，在URL `http://example.com/app?name=John` 中，`name`是一个请求参数，其值为`John`。
>
> - **用途**：主要用于获取客户端发送的请求参数。
> - **作用域**：不是作用域的概念，因为参数是随请求一起发送的。
> - **数据来源**：客户端通过HTTP请求发送的数据。
>
> ### 比较
>
> - **数据来源**：`req.getAttribute`获取的是通过`req.setAttribute`在服务器端设置的数据；而`req.getParameter`获取的是客户端通过HTTP请求发送的数据。
> - **用途**：`req.getAttribute`主要用于请求处理过程中的数据共享；而`req.getParameter`主要用于获取客户端的输入数据。
> - **作用域**：`req.getAttribute`的数据仅在请求范围内有效；而`req.getParameter`获取的数据是随请求一起发送的，不存在作用域的概念。
>
> ### 示例
>
> **Servlet中设置属性和获取参数**
>
> ```java
> protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
>     // 设置请求属性  
>     request.setAttribute("userRole", "admin");  
>   
>     // 假设这是从客户端发送的请求参数  
>     String userName = request.getParameter("userName");  
>   
>     // ... 处理请求  
> }
> ```
>
> 在JSP页面中，你可以使用EL表达式（Expression Language）来访问这些数据和参数：
>
> ```jsp
> <%-- 访问请求属性 --%>  
> <p>User Role: ${userRole}</p>  
>   
> <%-- 访问请求参数（如果参数存在） --%>  
> <p>User Name: ${param.userName}</p>
> ```
>
> 注意，在JSP中，`${param.xxx}`是EL表达式的一个快捷方式，用于直接访问请求参数。

> 上面正确地解释了 `req.getParameter(String name)` 和 `req.getAttribute(String name)` 在 Java Servlet 编程中的用途和区别。这两个方法各自处理不同类型的请求数据，并服务于不同的目的。
>
> ### 深入理解 `req.getParameter(String name)`
>
> - **类型与编码**：`getParameter` 方法返回的数据类型为 `String`，即使原始数据是数值或布尔值。如果需要，开发者需要将返回的字符串转换为相应的数据类型。此外，对于表单数据，URL 编码（也称为百分比编码）会自动应用，并且需要在处理前进行解码（尽管大多数情况下，Servlet 容器会处理这部分工作）。
> - **数组和列表**：如果参数名在请求中多次出现（如多选框或复选框），`getParameter` 方法将返回最后一次出现的值。为了获取所有值，应使用 `getParameterValues(String name)`，该方法返回一个字符串数组。
>
> ### 深入理解 `req.getAttribute(String name)`
>
> - **范围**：除了 `req.getAttribute(String name)`（在请求范围内），Servlet API 还提供了其他方法以在不同的作用域（如会话、应用程序）中设置和检索属性。这些方法包括 `request.setAttribute(String name, Object o)`、`session.setAttribute(String name, Object o)` 和 `ServletContext.setAttribute(String name, Object o)`。
> - **类型安全**：与 `getParameter` 不同，`getAttribute` 方法返回的对象是 `Object` 类型，这意味着开发者需要在使用之前进行类型转换或检查，以确保类型安全。
> - **持久性**：属性一旦设置，就会在指定的作用域内持续存在，直到显式移除或作用域结束（如请求完成、会话超时或应用程序关闭）。这使得属性成为在应用程序的不同部分之间持久存储数据的有效方式。
>
> ### 结论
>
> `req.getParameter(String name)` 和 `req.getAttribute(String name)` 在 Java Servlet 编程中扮演着不同的角色，但都是处理 HTTP 请求时不可或缺的工具。理解它们之间的区别和用途，对于编写高效、可维护的 Web 应用程序至关重要。

### 5.转发(forward)和重定向(redirect)

#### 5.0 是什么？

> * 请求重定向: 将原来的请求重新定位到新的地址，一共发送两次请求：302、get请求跳转页面。（所以必须把数据保存到session以上的作用域中才能传递到新地址）==重定向没有默认起点。==
> * 请求转发：通过服务器把请求转发到另一个地址，相当于访问的是服务器，由服务器来帮我们访问目标页面，最终将结果返回。==转发默认是以代码的文件的当前目录为起点进行转发。==

#### 5.1 注意点

> * 注意servlet中实现转发和重定向的对象是不一样的。
> * 转发和重定向无论是使用相对路径还是绝对路径都可以，具体使用哪一种路径取决于要访问的资源是内部资源还是外部资源，外部资源只能使用重定向访问，WEB-INF下的内部资源只能使用转发访问。
> * 无论是请求还是转发，写路径时要注意：以./开头代表当前目录。 以/开头代表根目录(三合一)。不写则要看跳转方式。
> * 转发地址栏不变，刷新页面会进行重复操作。

#### 5.2 不同控制层的转发方式

```java
//servlet重定向，使用response对象的sendRedirect()
response.sendRedirect("")
//springmvc框架重定向
return "redirect:地址"
    
//servlet转发,使用request对象的getRequestDispatcher()的forw()
request.getRequestDispatcher("").forward(request,response);   
//springmvc框架转发
return "forward:地址";  //方式1
return "地址" //方式2  默认方式就是转发 是由于存储Request是最多的
```

```jsp
// jsp的转发和重定向
<%
//测试代码
// 通过request作用域 存储数据   会在一次请求之内有效的
	request.setAttribute("msg","requestMsg");
//    转发适合相对路径   包/页面     一次请求
//    request.getRequestDispatcher("../day3/two.jsp")
//            .forward(request,response);
//   转发只能访问内部资源  不能外放资源(项目以外的内容) 因为它是从当前地址开始进行访问。
//    request.getRequestDispatcher("http://www.baidu.com")
//            .forward(request,response);

//    重定向适合绝对路径 :  /项目前缀/包/页面   二次请求
//    response.sendRedirect("/day3/two.jsp");
//    重定向为什么不能访问WEB-INF 因为它属于两次请求
//    第一次请求是临时重定向302  第二次请求是get请求 表示最终跳转的地址
//    是有第二次请求 不能通过地址直接访问
//    response.sendRedirect("/WEB-INF/in.jsp");
    response.sendRedirect("http://www.baidu.com");
%>
```

#### ==5.3 转发(forward)和重定向(redirect)的区别==

> - 请求次数:  转发只会发送一次 ，重定向会发送两次请求(第一次302 第二次重新定位到新的地址get请求)
> - 地址栏状态:   转发访问时地址不会发生改变   重定向跳转时 地址栏会发生改变
> - 共享request:   转发可以共享request资源    重定向不可以共享
> - 应用场景:   转发只能访问内部资源 和 WEB-INF的资源
>                       重定向可以访问内部和外部资源 但是WEB-INF资源不可以
>
> <hr>
>
> 总结： 一般情况下 查询(使用最多的)场景适合转发(使用最多的)，==增删改查一定不能用转发，因为转发地址栏不改变，刷新网页会重复增删改查操作==
>
> ​             一般情况下 增 删 改适合做重定向 因为地址栏会改变 刷新地址栏没有影响 

>1.如何访问WEB-INF下的资源？
>		请求转发：通过服务器转发 另一个地址
>		相当于访问的是服务器 由它来帮我来访问这个页面 最终给你返回结果
>		结果：地址栏不会改变。
>		request.getRequestDispatcher("../WEB-INF/in.jsp").forward(request, response);
>
>2.为什么转发只能访问内部资源 不能外放资源(项目以外的内容)？
>		因为它是从处理请求所在文件的当前目录开始进行访问。
>
>3.重定向为什么不能访问WEB-INF ？
>
>​		 WEB-INF是Java的WEB应用的安全目录。所谓安全就是客户端无法访问，只有服务端可以访问的目录。因为重定向实际上是客户端再次向服务器发送请求，所以无法访问WEB-INF目录。
>因为它属于两次请求，第一次请求是临时重定向302  第二次请求是get请求 表示最终跳转的地址

### 6.四大作用域 

> 下面四种作用域 都有共同的方法  功能都是一模一样, 唯独他们直接存储范围不同
>
> - page:    在同一个页面有效  类似于java中this 属于当前页面 通过pageContext对象来存储page作用域 
> - request:  类型是HttpServletReqest   表示一次请求之内 有效  
> - session:  类型是HttpSession   表示一次会话有效  属于浏览器和服务器一次通话过程   会包含多次请求和响应   一般情况下关闭浏览器 就认为会话可以关闭  (但是Session没有销毁呢)  或者 会话超时(默认30分钟)的时候 就会销毁session
> - application: 类型是ServletContext  表示应用级别(项目级别)  一个项目只有一个application对象。==servlet要想在不同用户之间共享数据只能通过application作用域==
>
> 应用场景:
>
> - page: 本页面有效,  实现html和js之间的数据传递 可以直接通过EL表达式${} 来获取数据
>
> - request:  一次请求有效 是四大作用域最常用的    会把使用频率非常高 而且经常修改的数据 存储到request  因为只要修改了就是新的请求   请求跨域之前存储数据也会销毁  
>
> - session: 一次会话有效  存储修改频率不高  但是使用频率很高的   比如:登录信息  而且还可以通过session来控制的登录
>
> - application: 一个应用有效，==存储用户和用户之间做数据共享==
>
>   比如:   统计网址访问次数   存储聊天记录   项目前缀  ....

#### 6.1 在jsp中获取四大作用域

> jsp中封装好了这四大作用域对应的对象，直接用即可：
> `pageContext`、`request`、`session`、`application`
> 这些对象都提供了以下方法来操作属性：
>
> - `setAttribute(String name, Object value)`：设置属性及属性值。
> - `getAttribute(String name)`：根据属性名取得属性值。
> - `removeAttribute(String name)`：删除指定名称的属性。
>
> `getParamete()`方法实际上并不属于这四大作用域对象的方法，而是`request`对象独有的方法，用于获取请求参数的值。正确的方法名是`getParameter(String name)`。
>
> ```java
> //page作用域
> pageContext.setAttribute("page","pageValue");
> //request作用域
> request.setAttribute("request","requestValue");
> //session作用域
> session.setAttribute("session","sessionValue");
> //application作用域
> application.setAttribute("application","applicationValue");
> ```

#### 6.2 在servlet中获取四大作用域

> servlet中没有现成的四大作用域对象。
>
> * 要获取request作用域对象，必须要在doGet()或doPost()方法中引入HttpServletRequest参数，它就是request作用域。
> * 要获取session作用域对象，必须通过requset对象，通过getSession()来获取。
>
> ```java
> //获取session作用域
> private void getSession (HttpServletRequest reg,HttpServletResponse){
> 	HttpSession session=reg.getSession(); //通过HttpServletRequest对象来获取session作用域
> 	session.setAttribute(s:"user",u);     //往作用域中存值
>  session.invalidate();                 //销毁作用域(退出登录时会用到)
>  session.removeAttribute(s:"user");    //销毁作用域中的指定键值对(退出登录时会用到)
> }
> //登出要删除session作用域中的所有信息。
> ```
>
> 

#### 6.2 request的常用方法

#### 6.1 session技术的原理 --- 了解

 Session技术是将数据存储在服务端用于管理会话的技术，`服务端会为每个客户端都创建一个会话的内存空间`,而每个session都有一个唯一标识sessionID，目的是为了找到是哪个session对象, 浏览器第一次发送请求时  服务端才会创建session对象  `服务端还会通过响应把sessionID传回 浏览器 , 浏览器会将sessionID存储到Cookie(缓存)中`, 这样浏览器之后发送的每一次请求都可以携带这个sessionID 目的时为了到服务端找到哪个session对象 。

> 总结:==浏览器关闭了 ,只是浏览器中保存sessionId没有了(Cookie清空了)==,无法找到服务器的session，再打开浏览器发送请求就会认为是新的请求所以是新的session。 原来session还在服务器中保存，最后 session除了手动清除 或者 session超时(30分钟)
> 服务器会为某个客户端的每次会话创建一个sessionId，然后保存在客户端自己的浏览器的Cookie中，所以是没有办法通过Cookie来进行不同客户端之间的数据通信的，因为每个sessionId都是客户端私有的。

 ![image-20240730154848628](D:\Desktop\gitee\java-learning\sc240601\Servlet\img\Session原理.png)

#### 6.2 Cookie浏览器缓存

Cookie是`服务端创建`,`存储在客户端` (浏览器) `一小段文本信息` 
Cookie可以实现浏览器和服务器之间的数据传递  
主要用于实现浏览器功能(比如:记住密码  浏览记录 ) 同时还可以实现会话跟踪

实现Cookie步骤:

- 创建Cookie(服务端创建的)

  ```
   Cookie c=new Cookie("key","value");  //只能存字符串
   //辅助功能:可以设置Cookie有效期 默认是会话级别(30分钟)
   c.setMaxAge(秒);
  ```

- 通过`响应response`把Cookie发送给浏览器(保存再浏览器)

  ```
   response.addCookie(c);
  ```

- 通过`请求request`获取所有Cookie对象进行使用

  ```java
   Cookie[] cs=request.getCookies();
   for(Cookie c:cs){
       if(c.getName().equals("key")) c.getValue()
   }
   
   -- 前端如果想获取Cookie推荐使用EL表达式${数据}
   语法:${cookie.key.value}
   比如:${cookie.username.value}
  ```

> 如果浏览器关闭了,是否有方式可以找到之前使用的session?
> 答: 是可以的 只需要之前使用的sessionID保留下来 通过cookie技术发送到浏览器中去就可以获取之前的session。

#### 6.3 如何用Cookie实现免登录？

> ```java
> 后端：
> String pw=req.getParameter("pw");
> if(un.equals("admin")&&pw.equals("123")){
> 	Cookie c=new Cookie("un",un);
> 	c.setMaxAge(300);
> 	resp.addcookie(c);
> 	//登录成功
> 	resp.sendRedirect("XXX.jsp");
> }else{
> 	//登录失败
> 	resp.sendRedirect("login.jsp");
> }
> ```
>
> ```jsp
> 前端：
> <%
> 	Cookiell cs=request.getCookies();
> 	for(Cookie c:cs){ 
> //Cookie不止有一个，每个会话都会有一个Cookie,所以我们要遍历Cookie集合，通过key来找到我们需要的Cookie。
> 		if(c.getName().equals("un")) 
> 			response.sendRedirect("XXX.jsp")
> 	}
> %>
> ```
>
> 进阶：如果我想要记录用户信息怎么办？
>
> ![image-20240808212919590](D:\Desktop\gitee\java-learning\sc240601\Servlet\img\用Cookie记住用户的账号和密码.png)

#### 6.4 session和Cookie区别?

> * 存储位置:session存储在服务端 cookie存储客户端(浏览器)
> * 存储类型:session可以存储任意数据 cookie只能存储字符串类型
> * 长度限制:session理论上没有长度限制 cookie存储数据根据浏览器不同有长度限制
> * 安全性:session相比cookie更安全 因为session是存储在服务器的

### 7.js验证

js验证是通过javascript对用户输入的数据进行校验,目的是为了确保提交的数据需要满足特定规则 ,如果不满足我的规则 则不能提交服务器 如果规则满足才能提交  这样 就起到了 减轻服务器压力的作用  

- 表单form 添加一个属性onsubmit，它是表单提交事件 底层会通过return true和return false 来控制是否提交表单
- 添加一个验证表单元素的函数 如果满足了 return true  否则return false
- 验证表单元素方式:  判断非空  判断长度  判断字母数字下划线 邮箱格式

```
 //实现新增用户功能js验证
 <div id="error" style="color: red"></div>
 <form action="/user?type=add" method="post" onsubmit="return check()">
     <fieldset style="width: 250px;">
         <legend>新增</legend>
         <p>账 号:<input name="phone"  placeholder="请输入手机号/账号"/></p>
         <p>密码1:<input name="pw" placeholder="请输入密码"  type="password"/></p>
         <p>密码2:<input name="pw2" placeholder="请输入密码"  type="password"/></p>
         <p>余 额:<input name="money" placeholder="请输入余额" /></p>
         <p>套 餐:<select name="serviceId">
                     <c:forEach var="s" items="${services}">
                         <option value="${s.id}">${s.name}</option>
                     </c:forEach>
                </select>
         </p>
         <button>新增</button>
     </fieldset>
 </form>
 
 <script>
     //定义函数: 老版的写法
     //目的:确保账号 密码 余额输入的符合规则(你来定的...)
     //满足规则: return true 否则 false
     function check(){
         //1.先获取表单组件对象(文本框对象 .. 密码框对象)
         var phone=document.getElementsByName("phone")[0];
         var pw=document.getElementsByName("pw")[0];
         var pw2=document.getElementsByName("pw2")[0];
         var money=document.getElementsByName("money")[0];
         var error=document.getElementById("error");
         //2.通过组件对象获取里面的值
         //<div name="phone">123</div>
         //<input name="phone" value="456"/>
         //phone.innerHTML;  //123
         //phone.value;     //456
         if(phone.value==""){ //报错
             error.innerHTML="账号不能为空";//提示
             return false;//不能提交
         }
         //验证最好方式:正则表达式 (邮箱格式 身份证号格式  字母数字...)
         //1.定义正则表达式(百度搜)
         var phoneReg=/^[A-Za-z0-9]+$/;
         //2.验证正则表达式
         if(!phoneReg.test(phone.value)){
             error.innerHTML="账号只能由字母和数字构成";
             return false;
         }
         if(pw.value=="" || pw2.value==""){
             error.innerHTML="密码不能为空";
             return false;
         }
         if(pw.value!=pw2.value){
             error.innerHTML="两次密码不一致";
             return false;
         }
         if(money.value==""){
             error.innerHTML="余额不能为空";
             return false;
         }
         var moneyReg=/^[1-9]d*.d*|0.d*[1-9]d*$/;
         if(!moneyReg.test(money.value)){
             error.innerHTML="余额不能是非负数";
             return false;
         }
         return true; //可以提交
     }
     //变量通过匿名函数赋值
     //var check=function(){}
     //vue通过es6语法编写函数(函数式编程 类似于lambda)
     //var check= ()=>{}
 
 </script>
```

### 8.过滤器

> 过滤器可以对应web资源进行拦截只有符合过滤器规则得资源才能到达服务器
> 过滤器是可以设置多个的，也可以同时设置多种规则
>
> 过滤器是Servlet非常重要的一个组件，能够对web资源进行拦截过滤，再拦截的过程中对web资源进行一些处理,目的是让符合要求的数据可以正常使用,否则是无法到达真正的服务器资源 类似于`安检`的过程 
>
> 比如: 过滤器实现编码设置、 登录拦截、敏感词过滤、权限控制等……

#### 8.1过滤器的工作原理

![img](D:\Desktop\gitee\java-learning\sc240601\Servlet\img\过滤器的工作原理.png)

#### 8.2如何实现过滤器?

> * 实现Filter接口。(注意是Servlet下的Filter接口)
>
> * 实现init() destroy() doFilter() ，就要实现这三个方法。
>
> * 配置过滤器（在web.xml中，和配置servlet类似通过<filter>和<filter-mapping>）
>
>   ```xml
>   <!-- 注意filer标签必须在filter-mapping标签的上面-->
>   <filter>
>       <filter-name></filter-name>
>       <filter-class></filter-class>
>   </filter>
>   <!-- 过滤器的执行顺序 根据mapping标签先后来决定的 -->
>   <filter-mapping>
>   	<filter-name></filter-name>
>       <!--配置经过过滤器的所有请求，通常表示一堆值，而不是一个值 -->
>       <!--比如： 写/* 表示所有资源都必须经过过滤器-->
>       <url-pattern></url-pattern>
>   <filter-mapping>
>   ```

#### 8.3 过滤器的工作原理

> * 服务器启动会加载web.xml 然后通过配置好的过滤器全类名 来实现反射来`实例化`过滤器；
> * 然后调用过滤器对象的init()做初始化。
> * 过滤器可以配置多个，会形成一个过滤器栈，遵循先进后出的原则；
>   过滤器的执行顺序是通过web.xml中的filter-mapping中的先后顺序来决定的；
> * 处理请求时，因为每个过滤器必须通过doFilter()进行连接，最后只有经过了每个过滤器才能到达我们最终要访问的资源。
> * 创建和销毁：看web.xml中的filter-mapping标签的顺序来进行创建和销毁，遵循栈的规则，后进先出。

#### 8.4 实例

##### a. 编码过滤器

>```java
>//编码过滤器
>// 1.实现Filter接口。(注意是Servlet下的Filter接口)
>public class EnCodingFilter implements Filter {
>    //2.实现init() destroy() doFilter() ，就要实现这三个方法。
>    //2.1 过滤器初始化
>    public void init(FilterConfig filterConfig) throws ServletException {
>        System.out.println("初始化编码过滤器");
>    }
>
>    //2.2 销毁过滤器
>    public void destroy() {
>        System.out.println("销毁编码过滤器：在服务器关闭时进行销毁");
>    }
>
>    //2.3 过滤器的核心方法：设置过滤规则
>    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
>        System.out.println("进行过滤规则");
>    }
>}
>```
>
>```xml
>  <!-- 配置过滤器：编码过滤器-->
>  <!--  注意顺序，在全局配置<context-param>后面-->
>  <filter>
>    <filter-name>encoding</filter-name>
>    <filter-class>filter.EnCodingFilter</filter-class>
>    <!-- 初始化参数：设置两个，请求一个，响应一个-->
>    <init-param>
>      <param-name>req</param-name>
>      <param-value>utf-8</param-value>
>    </init-param>
>    <init-param>
>      <param-name>resp</param-name>
>      <param-value>text/html;charset=utf-8</param-value>
>    </init-param>
>  </filter>
>  <filter-mapping>
>    <filter-name>encoding</filter-name>
>    <!--因为所有资源都要设置中文所以写/*-->
>    <url-pattern>/*</url-pattern>
>  </filter-mapping>
>```

##### b. 登录过滤器

> 1
>
> ```java
> 
> ```

##### c. 敏感词过滤器

> 1
>
> ```java
> 
> ```

#### 8.5 Servlet和Filter的区别

> Servlet和Filter的主要区别有：
>
> 1. 作用不同：Servlet用来处理和响应请求，Filter用来过滤和筛选请求。
> 2. 生命周期不同：Servlet的实例化和初始化的时机默认是在第一次请求时，而Filter实例化和初始化的时机默认是在服务器启动时。
> 2. 配置方式不同：在web.xml中一个是servlet标签 一个是filter标签。

### 9. 监听器

> 监听器类似于监控 不能控制程序的执行，不像过滤器必须要满足要求才能够到达对应资源。
> 监听器只是用于监听 servlet中某一些组件对象的使用状态的（比如：域对象的创建，域对象的存储）
> 监听器起着类似于日志的功能

#### 9.1 监听器的分类

> * 域对象监听器（page作用域太小了，所有不监听）
>   * ServletRequestListener：监听request作用域的创建和销毁
>   * HttpSessionListener：监听session作用域的创建和销毁
>   * ServletContextListener：监听application作用域的创建和销毁
> * 域对象属性监听器
>   * ServletRequestAttributeListener：监听request作用域中的值，只要里面的值进行了（新增、替换、删除）
>   * HttpSessionAttributeListener:
>   * ServletContextAttributeListener:
> * 绑定监听器（用来做一些自定义功能）

#### 9.2 如何实现监听器

> * 实现对应的接口（看需要监控哪种作用域）
>
> * 实现接口的对应方法（创建和销毁的方法 或 属性新增 删除替换的方法）
>
> * 在web.xml中配置监听器
>
>   ```xml
>   就一个配置：
>   <listener>
>   	<listener-class> ... </listener-class>
>   <listener>
>   ```

### 10. Junit单元测试测试   

> @Test注解：相当于一个main函数。如果同时运行多个则按顺序执行。
>
> @After注解：运行整个测试类时，在测试方法执行后调用。如果同时运行多个则按顺序执行。
>
> @Before注解：运行整个测试类时，在测试方法执行后调用。如果同时运行多个则按顺序执行。

![image-20240803155357001](D:\Desktop\gitee\java-learning\sc240601\Servlet\img\使用Junit要导入junit依赖.png)

```java
// 推荐使用junit来测试，使用简单方便 (要在pox.xml中导入junit依赖）
// 只需要通过几个 简单的注解 就可以让一个方法启动运行
// 它的底层实现也是main()实现的 也就是主函数 可以通过Thread.currentThread().getName()观察
// bug：junit默认不兼容Scanner对象。
```

```java
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//这里之所以没有package 是因为test下的java和resources也是根目录，只不过是测试环境下的
public class MyTest {
    // 推荐使用junit来测试，使用简单方便 (要在pox.xml中导入junit依赖）
    // 只需要通过几个 简单的注解 就可以让一个方法启动运行
    // 它的底层实现也是main()实现的 也就是主函数 可以通过Thread.currentThread().getName()观察
    // bug：junit默认不兼容Scanner对象。
    @Test
    public void aaa() {
        String name = Thread.currentThread().getName();
        System.out.println(name);
        System.out.println("aaaa");
    }

    @Test
    public void bbb() {
        String name = Thread.currentThread().getName();
        System.out.println(name);
        System.out.println("bbbb");
    }

    @Before//@Before注解：在测试方法执行后调用
    public void AAA() {
        String name = Thread.currentThread().getName();
        System.out.println(name);
        System.out.println("在测试方法执行前调用");
    }

    @After//@After注解：在测试方法执行后调用
    public void BBB() {
        String name = Thread.currentThread().getName();
        System.out.println(name);
        System.out.println("在测试方法执行后调用1");
    }
    @After//@After注解：在测试方法执行后调用
    public void BB() {
        String name = Thread.currentThread().getName();
        System.out.println(name);
        System.out.println("在测试方法执行后调用2");
    }

    @Test
    public void work() {
        int i = 1999999999; //19E
        long result=0;
        while(i != 0) {
            result = i%10 + result*10;
            i /= 10;
        }
        System.out.println(result);
        System.out.println((int)result);
        System.out.println(result==(int)result ? result : 0);
    }
}
```

### 11. Servlet实现动态搜索

> 动态sql语句：能根据传入的参数`动态的匹配查询的条件`，如传入name就会根据name进行查询，没传入就进行全查询。

> 注意点：and前面有一个空格，不然拼接后就是直接糊在一起。
>
> ![image-20240814020939267](.\img\实现动态sql语句.png)

> 前端：
>
> ```jsp
>     <!--增加一个动态搜索框-->
>     <form action="/user" method="post">
>         <p>
>             手机号：<input value="${sphone}" placeholder="要查询的号码" size="10px" maxlength="11" name="sphone" type="text">
>             <select name="sid">
>                 <option value="0" selected disabled>请选择套餐</option>
>                 <!--把下拉框改成动态的-->
>                 <c:forEach var="sl" items="${splist}">
>                     <option <c:if test="${sl.id == ssid}"> selected </c:if> value="${sl.id}"> ${sl.name}</option>
>                 </c:forEach>
>                 <option value="0">未知套餐</option>
>             </select>
>             <button>查询</button>
>         </p>
>     </form>
> ```
>
> 控制层：
>
> ```java
>     private void show(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
>         //改成动态搜索，根据用户传来的数据中是否有值来确定
>         String sphone = req.getParameter("sphone");
>         String ssid = req.getParameter("sid");
>         Integer sid = 0;
>         if(ssid != null)
>             sid = Integer.parseInt(ssid);
>         //创建一个搜索条件对象
>         DDUser user = new DDUser(null, sphone, null, 0, sid);
> 
>         //判断是否登录（session中是否有用户信息）：
>         if(req.getSession().getAttribute("user") != null) {
>             // 要把数据发送给前端：
>             // 1. 通过dao层从数据库中获取数据：
>             List<DDUser> list = ud.getUsers(user);
>             // 2. 把数据保存到作用域中
>             req.setAttribute("list",list);
>             req.setAttribute("sphone", sphone);
>             req.setAttribute("ssid", sid);
>         }
>         // 3. 到展示数据的页面
>         // 由于request作用域只在一次请求内有效，所以不能通过重定向来跳转页面
>         // 只能通过转发转发则是相对于根目录开始
>         req.getRequestDispatcher("day2/show.jsp").forward(req,resp);
>     }
> ```
>
> dao层
>
> ```java
> public List<DDUser> getUsers(DDUser user) {
>     //实现查询所有用户功能
>     // 两表关联查询的结果的顺序问题
>     String sql = "select * from dduser u, ddservicepackage s where u.serviceId = s.id ";
>     List<Object> o = new ArrayList<>();
>     if(user.getPhone() != null) {
>         sql += " and u.phone like ? ";
>         //拼接字符串一定要注意空格问题，为了不出错哪怕多打几个空格也没问题。
>         o.add("%" + user.getPhone() + "%"); //为什么不写到上面？ -- 因为预处理时会添加''
>     }
>     if(user.getServiceId() != 0) {
>         sql += " and u.serviceId=? ";
>         o.add(user.getServiceId());
>     }
>     sql += " order by u.id "; //为什么会有顺序问题？导致我要加 order by?
> 
>     Connection conn = DBUtil2.getConn(); //为了关闭连接。
>     ResultSet result = DBUtil2.query(sql, o.toArray());
>     // 解析ResultSet对象，获取所有用户信息：
>     List<DDUser> users = new ArrayList<>();
>     try {
>         while (result.next()) {
>             users.add(createUsers(result));
>         }
>         // 打印提示用于判断是否执行成功。
>         for (DDUser u : users) {
>             System.out.println(u);
>         }
>     } catch (SQLException throwables) {
>         throwables.printStackTrace();
>     }
> 
>     //关闭连接
>     PreparedStatement pstmt = DBUtil2.pstmt;//为了关闭连接
>     DBUtil2.closeConn(result, pstmt, conn); //注意关闭顺序
>     return users;
> }
> ```
