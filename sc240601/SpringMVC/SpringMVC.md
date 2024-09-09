## SpringMVC

### 0. 为什么需要使用框架？

> 框架类似于Java代码的模板，它已经完成了一部分（项目中的通用规范、接口、通用功能）那么编程人员只需要根据框架的使用规则来添加需要，就可以快速的写一个符合开发标准的项目，提高效率。

#### 怎么去学框架？

> 去看官网。[Web on Servlet Stack :: Spring Framework](https://docs.spring.io/spring-framework/reference/web.html)
> projects -> Overview -> 网页最下面(Features)

### 1.什么是SpringMVC

> 在JavaEE中能够处理请求的只有 Filter 和 Servlet，Struts2的底层是Filter，SpringMVC的底层是Servlet。

> 是什么？
> 为什么？
> 怎么用？
>
> 谈谈你对SpringMVC的理解：
>
> SpringMVC是`Spring框架的一个子项目`，`底层实现DispatcherServlet ` `可以实现项目中控制层的功能`（替换之前的Servlet) 
> 相比Servlet效率和性能都更高 并且SpringMVC和Spring是无缝连接。

> 对SpringMVC的理解： 
> a.是Spring框架的一个子项目，是专门用来实现Web项目中的控制层的一个框架。（有什么用？）
> b.它用于替换Servlet来实现Web项目中的控制层，因为它相比Servlet更高效。(比servlet的优势？)
>
> * 通过方法形参来自带封装参数、自动类型转换。
> * 还提供了编码过滤器。
>
> c.它的底层是通过DispatcherServlet对象(核心控制器）来实现的，所有请求都要经过DispatcherServlet对象的调度。

### 2. 搭建SpringMVC项目

> a. 创建maven项目
> b. 导入依赖（pom.xml中导入）
> c. 配置SpringMVC的配置文件
> d. 配置web.xml(配置SpringMVC的核心)
> e. 随便写个类 随便写一个方法 通过几个简单的注解 就可以接收请求处理请求。

#### 2.1 SpringMVC的入口

> web.xml下的<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
>
> ```xml
> <!--配置springmvc核心：DispatcherServlet-->
> <servlet>
>     <!--这里的name可以随便写，只要和下面的mapping对应即可-->
>     <servlet-name>springmvc</servlet-name>
>     <!--作用：
>       1.作为springmvc的入口，所有请求都需要经过这个Servlet才能到达Springmvc
>       2.负责读取springmvc的配置文件，默认读取WEB-INF下的文件，而且文件名固定好了：名称-springmvc.xml
>       这里的名称是上面<servlet-name>中的名称
>       通常配置文件 需要单独设置 不使用它的默认方式（使用初始化参数来配置，不配置就读默认的配置文件：名称-springmvc.xml）
>       (除了web.xml会自动加载其他的配置文件都不会自动加载)
>     -->
>     <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
>     <!--初始化参数：配置自定义配置文件 则就不读取默认位置了-->
>     <init-param>
>         <param-name>contextConfigLocation</param-name>
>         <param-value>classpath*:springmvc.xml</param-value> <!--classpath*表示根目录-->
>     </init-param>
>     <!-- 让服务器启动时 就对这个servlet进行实例化，初始化（读取配置文件）-->
>     <load-on-startup>1</load-on-startup>
> </servlet>
> ```
>

### 3. MVC三层架构

> SpringMVC三层架构：
> MVC是一种开发模式,也是软件设计风格 将一个系统分成M模型层 V视图层 C控制层 `能够实现一个系统各个层级的解耦合` 便于后期程序的扩展(不同平台只需要切换V视图层)
>
> ![image-20240801105225746](.\img\MVC的三层架构.png)
>
> 7是controller去哪个页面处理(判断成功去哪个页面，失败去哪个页面)。
>
> 8是返回处理结果，View将处理好的页面返回给controller层，controller层将结果返回给客户端。

#### 3.1 MVC是什么?

> * M ：model模型层：主要用于处理业务逻辑(service)和数据访问(dao)
>    service也叫业务模型 dao也叫数据模型
>   * service：业务逻辑层，一个功能相当于一个业务，专门用于处理事务。
>     因为以后的功能会很复杂 一个业务可能会包括多次数据库操作，而且要保证同时成功和失败，也就是要支持事务，没有service层就比较难做。因为仅仅在单独的dao层中做事务是不够的，后期的一个业务一次性可能要调用多个dao层，要保证他们同时成功和失败，所以单单一个dao层级别的事务是不够的，还需要一个针对所有dao层的事务，所以需要一个service层。
>     同时处理异常也在业务层解决、日志记录一般也会在这里编写。
>     所以以后是service层和dao层进行交互，而不是controller层直接和dao层进行交互了。
>     
>     ```java
>     controller-- 接收下单的信息(商品信息 数量 价格 当前用户...)
>     service--实现一个下单业务功能(包含5次idbc操作 会调用4次dao层)要保证这个4次dao层操作同时成功和失败  同时处理异常也在业务层解决、日志记录一般也会在这里编写。
>     商品dao-- 负责查询商品
>     用户dao-- 负责查询当前登录用户
>     库存dao-- 负责查询库存是否充足  如果充足更新库存(减少库存)
>     订单dao--负责添加订单
>     ```
>     
>   * dao：数据访问层，用于和数据库交互（jdbc mybatis）
> * V：view视图层，主要用于提供可视化操作界面（html、jsp、vue）
> * C：controller控制层，主要用于连接视图层和模型层，用于接收请求和处理请求，控制哪个视图对应哪个模型（servlet、springmvc、springboot)

#### 3.2 为什么要分层？

> 解耦和内聚：
> 	解耦提高了程序的扩展性(模块功能随便增加)和可维护性(模块功能随便换)
> 	内聚提高了可维护性(方便定位和修改错误)

### 4.SpringMVC的输入输出

#### 4.0 总览

##### a. 前后端不分离

>前端代码 和 后端代码 在同一个项目，涉及不到跨域问题。
>
>* 输入：前端如何传递数据给后端，一般是表单提交 和 超链接地址传参。
>  `保证标签的name属性和后端方法中的参数名相同，同value属性里设置值。`
>
>  > 在Spring MVC中，通常不会直接使用HTML元素的`id`属性来与Controller层方法的形参对应。`id`属性在HTML中主要用于标识元素，以便在CSS、JavaScript等中引用，而不是直接用于HTTP请求的参数传递。
>  >
>  > HTTP请求的参数是通过表单字段（如`<input>`, `<select>`, `<textarea>`等）的`name`属性来标识的。当你提交表单时，表单中的数据（即字段的`value`）会作为请求参数（以字段的`name`为键）发送到服务器。
>
>  ```java
>  a. 如果提交的是一个简单的数据（int double String……）
>  	通过springmvc方法：要在方法中添加形参，要求形参名和提交的key(也就是标签的name属性)一致。
>  	比如：
>  		<a href="/del?id=1">删除</a>
>  		@RequestMapping("/del")
>  		public void test(Integer id) {}
>  	好处是：不用手动获取，也不用进行类型转换。
>  
>  b. 如果提交的是多个数据（一个key对应多个value）
>     提交多个值：对于一个key有多个值，可以用数组来接收，用集合有点bug
>     用集合有点bug，为什么？
>      //1.不能用List，因为它是接口，不能被实例化，springmvc不知道用哪个实现类来实例化它
>      //2.要用@RequestParam("key")注解告诉springmvc 集合的变量名对应前端的key.只能通过别名来识别给哪个集合赋值.
>      // 太麻烦了，不推荐使用。用数组
>  	比如：
>          <form action="/inMany" method="post">
>              爱好:
>              <input type="checkbox" value="篮球" name="like"/>篮球
>              <input type="checkbox" value="足球" name="like"/>足球
>              <input type="checkbox" value="乒乓球" name="like"/>乒乓球
>              <input type="checkbox" value="羽毛球" name="ilike"/>羽毛球<br>
>              <button>提交</button>
>          </form>
>          @RequestMapping("/inMany")
>          public void inMany(String[] like)
>  
>  c. 如果提交的是多种数据（有多个key,就是有多个数据类型）
>     在springmvc方法中添加一个包含了所有种类数据的实体类的形参，该形参的形参名可以是任意的就是不能和提交请求中的key相同。（不然报400错误）
>  	比如：    
>  		<a href="/in?name=张三&age=18&score=95.5">新建</a>
>  		@RequestMapping("/inAdd")
>      	public void inMany(Usermvc m, String name)
>  
>  d. 如果提交的数据包含日期
>     如果不做处理直接提交容易报400错误
>     主要原因是：前端传递的是字符串，后端是util.Date类型所以会报错，注意后端的日期类型是util
>     解决方法：在实体类中的util.date类型数据的上面加一个注解：
>  	@DateTimeFormat(pattern = "yyyy-MM-dd") //前后端不分离的springmvc/boot可以用
>      private Date time;
>  	//不同浏览器传入的字符串格式不同有的可能是yyyy/MM/dd，要灵活修改
>  
>  e. 如果提交的数据就是日期类型
>  	形参和key相同，且在形参前加一个@DateTimeFormat(pattern = "yyyy-MM-dd")注解
>  	// 创建一个SimpleDateFormat对象，并指定你想要的日期时间格式
>      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
>      @RequestMapping("/testio4")
>      public void testIo4(@DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday){
>          System.out.println(birthday);
>          // 使用format方法将Date对象转换为指定格式的字符串
>          System.out.println(sdf.format(birthday));
>          System.out.println(birthday.toString());
>      }
>  
>  f. 如果传输文件
>  ```
>
>* 输出：后端返回相应的数据给前端，一般通过作用域来返回。
>
>  ```java
>  a. 存储作用域：形参直接定义类型即可
>  	如果是存储request: 添加HttpServletRequest\Model\Map\ModelMap类型的形参
>  	如果是存储session: 添加HttpSession类型的形参
>  
>  b. 设置跳转方式：给返回的字符串加上前缀即可
>  	如果要设置转发方式： "forword:/day1/show.jsp" 或 不加前缀因为转发是默认的
>      如果要设置重定向方式："redirect:/day1/show.jsp";
>  
>  c. 想使用其他类型的作用域对象存值 request response session……
>      直接在形参定义对应的类型 springmvc帮你自动赋值
>  ```

##### b. 前后端分离

>前端是一个独立的项目(VSCode)后端也是独立的项目(IDEA)两个项目是相互独立的，独立部署，两者没有影响。功能的实现只需要通过前端项目发送请求到另一个后端项目接收处理，涉及到跨域问题。
>
>* 输入： 前端项目提交数据如何给后端接收
>
>   * 前端：发送异步请求（同步请求不好返回），同时要把提交的数据转换为json格式提交过来。
>
>
>   ```js
>   axios.post('后端url', json数据).then(()=>{})//这个then()就是回调函数，里面是匿名函数
>   ```
>
>   * 后端：在springmvc方法的`形参`前面添加`@RequestBody`目的是用于接收前端提交的json转换后的后端对象。
>
>
>   ```java
>   @RequestMapping("/地址")
>   public XXX get(@RequestBody User u){
>   }
>   ```
>
>* 输出：后端项目返回数据给前端去使用
>
>   * 后端：`方法`上加`@ResponseBody`目的是为了表示返回值不是地址而是给前端项目响应的结果也会自动转换为json。
>
>
>   ```java
>   @RequestMapping("/地址")
>    @ResponseBody
>    public List<User> get(@RequestBody User u){
>    	return 集合;//后端会自动把返回值转换成json格式的字符串给前端
>    }
>   ```
>
>   * 前端：发送异步请求后，会有一个回调函数(指请求发送成功的函数)，回调函数的参数就是后端返回的json数据。
>
>
>   ```js
>    axios.post('后端url', json数据).then((abc)=>{
>   	//abc就是后端返回集合的json数据
>   })
>   ```
>
>
>

### 5. 前后端不分离的SpringMVC

#### 5.1 请求处理方法`获取前端输入`

> 前后端不分离项目，前端给后端传值。

##### a. 获取单个数据

> 要求在SpringMVC方法中加参数，参数名要和前端提交的key对应。
>
> ```jsp
> 前端代码：
> <%@ page contentType="text/html;charset=UTF-8" language="java" %>
> <html>
>     <head>
>          <title>登录</title>
>     </head>
>     <body>
>         <%--1.测试提交简单的数据--%>
>         <form action="/in" method="post">
>              姓名：<input type="text" name="name"/><br>
>              年龄：<input name="age"/><br>
>              分数：<input name="score"/><br>
>              <button>提交</button>
>         </form>
>         <a href="/in?name=张三&age=18&score=95.5">连接</a>
>     </body>
> </html>
> ```
>
> ```java
> 后端代码：
> @Controller //标识当前类为控制层，让扫描包扫描到时，SpringMVC创建这个类的对象（创建控制层对象）
> // 所以扫描包的地址配置很重要，扫描包的地址要配置到整个controller层
> public class InOutController {
>      //法一：通过HttpServletRequest对象来获取，可以，但太麻烦不推荐
>     //    @RequestMapping("/in")//该注解表示处理哪个请求
>     //    public void inSimple(HttpServletRequest req) {
>     //        String name = req.getParameter("id");//获取的是String类型数据，还要进行类型转换。
>     //
>     //    }
> 
>      // 法二：在SpringMVC方法中加参数，参数名要和前端提交的key对应
>      // 好处不需要通过对象获取，且不需要类型转换了
>      @RequestMapping("/in")//该注解表示处理哪个请求
>      public void inSimple(String name, int age, double score) {
>            System.out.println("进入in页面");
>            System.out.println(name + " " + age + " " + score);
>      }
> }
> ```
>
> ![image-20240801120630126](.\img\通过springmvc方法获取前端数据.png)

##### b. 获取多个数据(一个key有多个value)

> 要求在SpringMVC方法中加参数`数组`，参数名要和前端提交的key对应。
>
> ```jsp
> 前端：
> <%--2.测试提交多个数据的情况，一个key有多个值--%>
> <form action="/inMany" method="post">
>      批量删除:
>      <input type="checkbox" value="1" name="ids"/>1
>      <input type="checkbox" value="2" name="ids"/>2
>      <input type="checkbox" value="3" name="ids"/>3
>      <input type="checkbox" value="4" name="ids"/>4<br>
>      爱好:
>      <input type="checkbox" value="篮球" name="like"/>篮球
>      <input type="checkbox" value="足球" name="like"/>足球
>      <input type="checkbox" value="乒乓球" name="like"/>乒乓球
>      <input type="checkbox" value="羽毛球" name="ilike"/>羽毛球<br>
>      <button>提交</button>
> </form>
> ```
>
> ```java
> 后端：
> //2. 提交多个值：对于一个key有多个值，可以用数组来接收，用集合有点bug
>  // 法一：使用数组
>  @RequestMapping("/inMany")
>  public void inMany(Integer[] ids, String[] like){
>      System.out.println(Arrays.toString(ids));
>      System.out.println(Arrays.toString(like));
>  }
>  // 法二：用集合，有点bug：
>  //1.不能用List，因为它是接口，不能被实例化，springmvc不知道用哪个实现类来实例化它
>  //2.要用@RequestParam("key")注解告诉springmvc集合的变量名对应前端的key.只能通过别名来识别给哪个集合赋值.
>  // 太麻烦了，不推荐使用。用数组
>  @RequestMapping("/inMany")
>  public void inMany(@RequestParam("ids") ArrayList<Integer> ids, ArrayList<String> like){
>      System.out.println(ids);
>      System.out.println(like);
>  }
> ```

##### c.获取多种数据

> 在springmvc方法中添加一个包含了前端提交所有数据种类属性的实体类的形参，==该形参的形参名可以是任意的就是不能和提交请求中的key相同。==
>
> ```jsp
> 前端：
> <%--3.测试提交多种数据 模拟新增或注册时--%>
> <form action="/inAdd" method="post">
>  姓名：<input name="name"><br>
>  存款：<input name="money"><br> <!--基本类型接收null会报400错误-->
>  <button>提交</button>
>  </form>
> ```
> 
>```Java
> 后端：
> //3.提交多种数据，即有多个key，模拟新增或注册时
> //自定义类型的形参名可以是任意的就是不能和提交请求中的key相同
>  @RequestMapping("/inAdd")
>  public void inMany(Usermvc m, String name){
>    System.out.println(m);
>      System.out.println(name);
>    }
>  ```

##### d.日期类型数据

> 前端传来的日期是字符串类型，后端的是Util.Date日期类型，直接传就会报400错误
> 如果是单个日期数据（即前端只提交了一个日期数据）可以干脆用String来接收，然后再转换。
> 也可以保证形参和key相同，且在形参前加一个@DateTimeFormat(pattern = "yyyy-MM-dd")注解
>
> ```html
> 前端：
>     <form action="/testio4" method="post">
>         <p>生日<input name="birthday" type="date"></p>
>         <button>提交4</button>
>     </form>
> ```
>
> ```java
> 后端： （还要格式转换十分麻烦）
>  //创建一个SimpleDateFormat对象，并指定你想要的日期时间格式
>     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
>     @RequestMapping("/testio4")
>     public void testIo4(@DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday){
>         System.out.println(birthday);
>         // 使用format方法将Date对象转换为指定格式的字符串
>         System.out.println(sdf.format(birthday));
>         System.out.println(birthday.toString());
>     }
>     
> ```
>
> 如果是和多个数据一起提交，用一个实体类来接收，就在实体类的日期字段前加一个@DateTimeFormat(pattern = "yyyy-MM-dd")注解，必须紧跟着写。
>
> ```java
> 实体类：
> public class Usermvc {
>     private Integer id;
>     private String name;
>     private double money;
>     @DateTimeFormat(pattern = "yyyy-MM-dd") //前后端不分离的springmvc和springboot可以用
>     private Date time;
>     private String head;
> }
> ```
>
> ```jsp
> 前端：
> <%--4.测试提交日期类型数据--%>
> <form action="/inAdd" method="post">
> 姓名：<input name="name"><br>
> 存款：<input name="money"><br> <!--基本类型接收null会报400错误-->
> 生日：<input type="date" name="time"><br> <!--前端传递的是字符串，后端是util.Date类型所以会报错-->
> <button>提交</button>
> </form>
> ```
>
> ```java
> 后端：
> //3.提交多种数据，即有多个key，模拟新增或注册时
> //自定义类型的形参名可以是任意的就是不能和提交请求中的key相同
> @RequestMapping("/inAdd")
> public void inMany(Usermvc m, String name){
> System.out.println(m);
> System.out.println(name);
> }
> ```

#### 4.2 请求处理方法`存储数据到作用域`

> 前后端不分离项目，后端给前端传值。

>  a. 存储作用域：形参直接定义类型即可
>  	如果是存储request: 添加HttpServletRequest\Model\Map\ModelMap类型的形参
>  	如果是存储session: 添加HttpSession类型的形参
>     如果是存储application: 添加HttpSession类型的形参，调用getServletContext()
>
>  b. 想使用其他类型的作用域对象存值 request response session……
>      直接在形参定义对应的类型 springmvc帮你自动赋值
>
> ```java
> public String testForwardAndRedirect(Map map, Model model, ModelMap modelMap, HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
>     //以下四种方式都是将数据存储在request作用域
>     map.put("map", "mapValue");
>     model.addAttribute("model", "modelValue");
>     modelMap.addAttribute("modelMap", "modelMapValue");
>     req.setAttribute("request", "requestValue");
>     // 上面四种方式都是将数据存储在request作用域，可以通过导入其他作用域参数的方法将数据存储在其他作用域。
>     session.setAttribute("session", "sessionValue");
>     session.getServletContext().setAttribute("application", "applicationValue");
> 
>     //设置跳转方式：
>     req.getRequestDispatcher("/Test/show.jsp").forward(req,resp);
>     return "";
> }
> ```
>
> 

#### 4.3 请求处理方法`设置跳转方式`

>设置跳转方式：给返回的字符串加上前缀即可
> 	如果要设置转发方式： "forword:/day1/show.jsp" 或 不加前缀因为转发是默认的
>    如果要设置重定向方式："redirect:/day1/show.jsp";
>
>```java
>public String testForwardAndRedirect( HttpServletRequest req, HttpServletResponse resp) {
>    //设置跳转方式(选下面的一种，不能同时选两个)：
>    //req.getRequestDispatcher("/Test/show.jsp").forward(req,resp);
>    //resp.sendRedirect("/Test/show.jsp");
>    //return "forward:/Test/show.jsp";
>    return "redirect:/Test/show.jsp";
>}
>```
>
>

#### 4.4 SpringMVC的底层实现

> springmvc的底层实现 默认是返回ModelAndView对象
>
> * 通过mv对象的addObject()来设置模型，就是存储的数据(默认存储在request作用域)
> * 通过mv对象的setViewName()来设置视图，就是最终要跳转的页面(默认是转发方式forward:)
>   如果要使用重定向(redirect)，可以在视图名称前添加"redirect:"前缀
>
> ```java
> //springmvc的底层实现 默认是返回ModelAndView对象
> //模型和视图对象：
> // 模型就是要存储的数据(默认存储在request作用域)，通过mv对象的addObject()来设置
> // 视图就是最终要跳转的页面(默认是转发方式) 通过mv对象的setViewName()来设置
> @RequestMapping("/testmvc")
> public ModelAndView testmvc() {
>     ModelAndView mv = new ModelAndView();
>     //存储数据(默认存储在request作用域中）
>     mv.addObject("user", new Usermvc("wang", 12));
>     //通过视图设置要转发的页面
>     mv.setViewName("/Test/TestMVC.jsp");
>     //默认情况下，Spring MVC使用转发（forward）而不是重定向（redirect）来将请求发送到视图。
>     // 如果你需要使用重定向，可以在视图名称前添加"redirect:"前缀，例如mv.setViewName("redirect:/someOtherUrl")。
>     // 但是要注意重定向会发送两次请求，所以将数据存储在Request作用域中可能不够。
>     return mv;
> }
> ```

#### 4.5 解决中文乱码问题

> 解决的是前后端不分离的项目中，前端给后端发送数据时的乱码问题。
> 后端给前端传数据，只要前端页面编码方式配置好，就不会出现编码问题。

> springmvc已经提供了编码过滤器，但是要我们自己在`web.xml`中去配置`CharacterEncodingFilter`
> Character -- 计算机领域中表示字符的意思  Encoding -- 编码方式   `Filter -- 过滤器`(注意它是一个过滤器)
> ==注意一定要提供初始化参数encoding==
>
> ```xml
> <filter>
>   <filter-name>encondingFilter</filter-name><!--过滤器的实现类如下-->
>   <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
>   <!--想做编码格式：就要提供初始化参数-->
>   <init-param>
>         <param-name>encoding</param-name> <!--点进CharacterEncodingFilter对象看它的参数-->
>         <param-value>utf-8</param-value>
>   </init-param>
> </filter>
> <filter-mapping>
>   <filter-name>encondingFilter</filter-name>
>   <url-pattern>/*</url-pattern> <!--对所有请求过滤编码-->
> </filter-mapping>
> ```

#### 4.6 如何解决请求重名问题

> ```
> <%--不支持重名请求，哪我们如何防止重名呢？
>  --参考Windows系统，不同包下的文件不算重名
>  --我们给请求加上一个"请求前缀"
>  --%>
> ```
>
> 在控制层的`实现类的前面`加一个`@RequestMapping("/XXX")`来表示请求前缀 是为了防止不同模块可能存在重名请求
>
> ```java
> @Controller
> @RequestMapping("/usermvc")
> public class UsermvcController {
>      @RequestMapping("/add")
>      public void add(){
> 		...
>      }
> }
> ```

### 5. 文件上传和下载

#### 5.1 文件上传

##### a.前提

> * 前端
>
>   ```jsp
>   a. 请求方式只能是post方式。给from表单设置method="post"属性
>   b. 数据的传递方式不能是字符串提交，需要设置附件提交。
>   给from表单设置enctype="multpart/form-data"属性
>   另外一个enctype="application/x-www-form-urlencoded" 表示以字符串的方式提交数据是默认的
>   c. 在接收文件的标签中添加一个属性：type="file" 
>   
>   <fieldset>
>       <legend>上传文件</legend>
>       <!--1.必须使用post方式才能上传文件-->
>       <!--2.enctype="multipart/form-data" 表示以附件方式提交数据-->
>       <!--enctype="application/x-www-form-urlencoded" 表示以字符串的方式提交数据是默认的-->
>       <!--3.input输入框，设置类型为文件类型：type="file"-->
>       <form action="/test/testUploadFile" method="post" enctype="multipart/form-data">
>           上传头像：<input type="file" name="">
>           <button>上传</button>
>       </form>
>   </fieldset>
>   ```
>
> * 后端
>
>   * springmvc配置文件 在配置文件中加上上传组件(允许接收文件)
>   
>     ```xml
>     <!--3.配置上传组件-->
>     <!-- id必须是multipartResolver -->
>     <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
>         <!--设置上传文件得保证id必须是multipartResolver，class必须是CommonsMultipartResolver-->
>         <!--设置上传文件的编码方式-->
>         <property name="defaultEncoding" value="utf-8"/>
>         <!--设置上传文件的大小上限 单位：字节 10M=1024*1024*10-->
>         <property name="maxUploadSize" value="10240000"/>
>     </bean>
>     ```
>   
>     > 为什么上传组件的bean标签的id必须固定？
>     > --  因为它被框架中别的bean引用了，而且引用的名称固定了
>   
>   * 处理请求的类的形参
>   
>     ```java
>     //a. 方法形参要设置为MultipartFile类型表示文件类型
>     //b. 形参名 也要和表单提交的key一致
>     //	 bug: key的名字不能和对象中的属性名一样 否则400
>     @RequestMapping("/testUploadFile") 
>     //u中有head属性，所有MultipartFile对象的名字不能是head
>     public String add(Usermvc u, MultipartFile myHead){
>     	return "跳转的地址";
>     }
>     ```

##### b. 上传头像后立即在前端显示

> 这个功能在前端实现：
>
> * 给上传文件的input表单添加一个域发生改变数据onchange(只要里面改变了就会触发)
>
>   ```jsp
>   <!--onchange：域发生改变数据：里面的内容只要改变了就会触发-->
>   <p>头像：<input onchange="showimg(this)" type="file" name=""></p>
>   <p><img id="img" src="img/1.PNG"
>           style="width:50px; height: 50px; border-radius: 50px;"></p>
>       
>   <script>
>       function showImg(o){
>           //1. 获取上传的文件的对象
>           //var imgFile = document.getElementById("a").files[0];
>           var imgFile = o.files[0];//通过this直接把当前input对象传入进来
>           //2. 通过上传的文件对象生成一个虚拟地址
>           var src = window.URL.createObjectURL(imgFile);
>           //3. 将img标签的src属性 设置为这个地址，从而在前端展示上传的图片
>           document.getElementById("img").setAttribute("src", src);
>       }
>   </script>
>   ```
>
>   
>
> * 在`springmvc配置文件`中放行静态资源（因为springmvc默认会拦截静态资源(img, css, js)）
>
>   ```xml
>   <!--4.放行静态资源-->
>   <!--springmvc会默认拦截静态资源(img css js) 需要让springmvc放行静态资源-->
>   <mvc:default-servlet-handler/>
>   ```

##### c. 完整案例

> 前端：
>
> ```jsp
> <form action="/usermvc/add" method="post"
>  enctype="multipart/form-data"> <!--enctype属性的application/x-www-form-urlencoded表示按字符串类型提交-->
>  <p>姓名：<input name="name"></p>
>  <p>存款：<input name="money"></p>
>  <p>生日：<input type="date" name="time"></p>
>  <%--onchange:域发生改变事件：里面内容只要改变了就会触发--%>
>  <p>头像：<input onchange="showImg(this)" type="file" name="myHead"></p>
>  <p><img id="img" src="/img/default1.jpg" style="width: 50px; height: 50px; border-radius: 50px"/></p>
>  <button>新增</button>
> </form>
> </body>
> </html>
> <script>
>  //实现上传头像立即展示
>  function showImg(o) {
>      //1.获取type=file组件对象中的文件(可能有多个文件，所以是数组保存，用下标获取）
>      var imgFile = o.files[0]
>      //2.通过文件对象 创建一个地址（是临时创建出来的虚拟地址）
>      var src = window.URL.createObjectURL(imgFile);
>      //3.img标签sec属性，设置这个地址
>      document.getElementById("img").setAttribute("src",src);
>  }
> </script>
> ```
>
> 控制层代码：
>
> ```java
> @RequestMapping("/add")
> public String add(HttpServletRequest req, Usermvc u, MultipartFile myHead) throws IOException {
>      u.setHead(UpDownUtil.upload(req, myHead));
> 
>      //service层来实现u.add()方法
>      int i = us.add(u);
>      if(i>0) {
>          //适合重定向
>          return "redirect:/usermvc/show"; //"进入成功show页面"
>      }
>      return "redirect:/day1/add.jsp"; //"进入失败回到新增页面"，写转发方式有bug
>  }
> ```
>
> 上传文件工具类：
>
> ```java
>  //上传通用方法：获取文件路径(只适用于SSM项目)
>  public static String upload(HttpServletRequest req, MultipartFile myHead) {
>      //SSM项目上传文件通用方法：
>      //问题1：文件保存地址？
>      // --存储在服务器中特定的目录下（后期可以存储在云服务器下）
>      // 如在服务器中定义一个目录：upload 专门保存上传的文件。
>      // 获取服务器真实路径： HttpServletRequest对象的getServletContext()的getRealPath()方法
>      String path=req.getServletContext().getRealPath("/upload/");
>      File file=new File(path);
>      if(!file.exists()) file.mkdirs();
>      //问题2：文件名（不同用户上传了同名文件怎么办？拿上传名称来保存会发生替换）
>      // 保证文件名是随机的，后缀名是固定不变的
>      // 文件名（随机数 + 时间戳 UUID) + 后缀名
>      // a.获取提交的文件名
>      String oldName = myHead.getOriginalFilename();//获取文件名
>      // b.获取后缀名，使用lastIndexOf获取最后一个"."的下标，通过substring进行截取
>      String suffix= oldName.substring(oldName.lastIndexOf("."));
>      // c.保存文件需要随机 UUID：32位永不重复的字符串
>      String name = UUID.randomUUID().toString();
>      String newName = name+suffix;
>      //开始上传：
>      try {
>          myHead.transferTo(new File(path + newName));
>      } catch (IOException e) {
>          e.printStackTrace();
>      }
> 
>      return newName;
>  }
> //要将图片保存在服务器中，如果存储绝对路径(D:/xxx)服务器反而找不到
> ```

#### 5.2 文件下载

##### a. 完整案例

> 前端代码：
>
> ```jsp
> <td>
> 	<%--使用${empty u.head?'default.jpg':u.head}来判断文件是否为空--%>
> 	<a href="/usermvc/download?fileName=${empty u.head?'default.jpg':u.head}">下载</a>
> </td>
> ```
>
> 控制层代码：
>
> ```java
> //下载文件
> @RequestMapping("/download")
> //不需要跳转返回值就设置为0
> public ResponseEntity<byte[]> download(String fileName, HttpServletRequest req) throws IOException {
>      //处理一下路径 或者 文件名
>      return UpDownUtil.download(req,fileName);
>  }
> ```
>
> 下载文件工具类：）
>
> > 有两个关键点：|
> >
> > * 获取并修改请求的头部信息`HttpHeaders`（设置文档类型，指定附件形式下载）
> > * 指定返回值`ResponseEntity<bytep[]>`(保存了下载文件的字节数组，请求头部信息，响应状态)
>
> ```java
> //下载文件通用方法
> @RequestMapping("/download")
> public ResponseEntity<byte[]> load(String fileName, HttpServletRequest req) {
>     //1.获取请求头部信息
>     HttpHeaders headers = new HttpHeaders();
>     byte[] bs = new byte[0];
>     	//1.1 设置文档类型 默认是text/html(跳转到页面) 设置成流，这样服务器就能读取到文件了
>     headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
>     try {
>     //2. 设置文件名的编码方式，如果文件名是中文也可以识别（注意URLEncoder是.net包下的类）
>         fileName= URLEncoder.encode(fileName, "utf-8");
>         //1.2 指定附件下载形式
>         //参数1：附件，表示支持下载功能；参数2：指定下载后的文件名
>         headers.setContentDispositionFormData("attachment", fileName);
>     //3.指定返回值 ResponseEntity<byte[]>
>         //参数1：下载的文件的字节数组
>         //参数2：请求头部信息
>         //参数3：响应实体类的状态（新建状态）HttpStatus.CREATED
>         String path = req.getServletContext().getRealPath("/upload/") + fileName;
>         File file = new File(path);
>         bs = FileUtils.readFileToByteArray(file);
>     } catch (IOException e) {
>         e.printStackTrace();
>     }
>     ResponseEntity<byte[]> re = new ResponseEntity<>(bs,headers, HttpStatus.CREATED);
> 
>     return re;
>     // 下载的图片打不开的原因有：
>     // 1.图片路径出错，可能是文件名错误、文件名前少了一道/，一定要和target目录下的图片路径一致。
>     // 2.前端传输文件名时传错了。
> }
> ```

#### 5.3 文件上传下载工具类

```java
package com.sc.util;

import com.sc.pojo.Usermvc;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

// 文件上传和下载的工具类：只适用于SSM项目
public class UpAndDown_Load_File {
    //上传通用方法：获取文件路径(只适用于SSM项目)
    public static String upLoadFile(HttpServletRequest req, Usermvc user, MultipartFile userFile) {
        //文件上传功能（后端）
        //1. 获取服务器路径 + /upload/
        String path = req.getServletContext().getRealPath("/upload/");
        //2. 创建保存上传图片的目录
        File upFileDir = new File(path);
        if(!upFileDir.exists())
            upFileDir.mkdirs();
        //3. 获取上传的文件的名字
        String oldName = userFile.getOriginalFilename();
        //4. 获取文件的后缀
        if(oldName.lastIndexOf(".") == -1)
            return null;
        String suffix = oldName.substring(oldName.lastIndexOf("."));
        //5. 创建一个永不重复的新文件名（使用UUID）
        String newName = UUID.randomUUID().toString() + suffix;
        //6. 创建接收上传文件的文件对象
        File f = new File(path + newName);
        //7. 接收上传的文件
        try {
            userFile.transferTo(f); //封装为工具类，只能通过抛异常来处理
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(newName);
        return newName;
    }
    
    //下载文件通用方法(只适用于SSM项目)
    public static ResponseEntity<byte[]> downLoadFile(String fileName, HttpServletRequest req) {
        //1.获取请求头部信息
        HttpHeaders headers = new HttpHeaders();
        byte[] bs = new byte[0];
        //1.1 设置文档类型 默认是text/html(跳转到页面) 设置成流
        //    这样服务器就能读取到文件了
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            //2. 设置文件名的编码方式，如果文件名是中文也可以识别（注意URLEncoder是.net包下的类）
            fileName= URLEncoder.encode(fileName, "utf-8");
            //1.2 指定附件下载形式
            //参数1：附件，表示支持下载功能；参数2：指定下载后的文件名
            headers.setContentDispositionFormData("attachment", fileName);
            //3.指定返回值 ResponseEntity<byte[]>
            //参数1：下载的文件的字节数组
            //参数2：请求头部信息
            //参数3：响应实体类的状态（新建状态）HttpStatus.CREATED
            String path = req.getServletContext().getRealPath("/upload/") + fileName;
            File file = new File(path);
            bs = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ResponseEntity<byte[]> re = new ResponseEntity<>(bs,headers, HttpStatus.CREATED);

        return re;
        // 下载的图片打不开的原因有：
        // 1.图片路径出错，可能是文件名错误、文件名前少了一道/，一定要和target目录下的图片路径一致。
        // 2.前端传输文件名时传错了。
    }
}
```

### 6. 分页

> 分页是一种将所有数据分段展示给用户的技术，用户看到的部署所有的数据，而是其中的一部分，用户可以通过指定的页码数切换可见内容，类似于阅读书籍。

#### 6.1 实现分页的步骤

> a. 编写分页的sql语句使用limit(起始下标从0开始，查询条数)
>
> ```sql
> select * from 表 where 条件 limit(起始下标从0开始，查询条数)
> ```
>
> b. 指定好分页的页码数 和 每页条数
>
> ```sql
> 当前页：3 每页5条数据
> 第一页： select * from 表 limit 0,5
> 第二页： select * from 表 limit 5,5
> 第三页： select * from 表 limit 10,5
> ...
> 第n页： select * from 表 limit (n-1)*5,5
> 
> 所以当前页：
> select * from 表 limit (当前页-1)*每页条数, 每页条数;
> ```
>
> c. 封装分页信息（可以封装为工具类或分页插件 pageHelper(MyBatis框架的分页插件)）
>
> ```java
> 需要的信息：
> 当前页数
> 每页条数
> 总条数
> 总页数
> 每页数据的集合
> ...
> ```
>
> d. 把封装好的分页信息 传递给前端

#### 6.2 分页实例

> 封装分页信息类：
>
> ```java
> public class PageInfo <E>{
>     private int pageNum;  //当前页数
>     private int pageSize; //每页条数
>     private int totalCount; //总条数  -- 数据库查询
>     private int totalPage;  //总页数  -- 计算：总条数/每页条数后向上取整
>     private List<E> list;   //每页的数据集合 -- 数据库查询 limit分页
> 
>     public PageInfo() {
>     }
> 
>     public PageInfo(int pageNum, int pageSize, int totalCount, List<E> list) {
>         this.pageNum = pageNum;
>         this.pageSize = pageSize;
>         this.totalCount = totalCount;
>         this.list = list;
>         //分页工具类的向上取整： int强转  Math.ceil()   *(1.0)转为double
>         this.totalPage = (int)Math.ceil(totalCount*(1.0)/pageSize);
>     }
>     get、set方法略...
> }
> ```
>
> Service层：
>
> ```java
> public PageInfo<Usermvc> show(Integer pageNum, Integer pageSize) {
>     //获取总页数
>     int totalCount = dao.count();
>     //获取每页数据集合
>     List<Usermvc> list = dao.show(pageNum, pageSize);
>     PageInfo<Usermvc> pageInfo = new PageInfo<>(pageNum, pageSize, totalCount, list);
>     return pageInfo;
> }
> ```
>
> dao层：
>
> ```java
> @Override
> public List<Usermvc> show(Integer pageNum, Integer pageSize) {
>     Connection conn = DBUtil2.getConn();
>     String sql = "select * from usermvc limit ?, ?";
>     ResultSet result = DBUtil2.query(sql, (pageNum-1)*pageSize, pageSize);
>     List<Usermvc> list = new ArrayList<>();
>     // 解析结果集为list集合
>     resultList(result, list);
>     DBUtil2.closeConn(result, DBUtil2.pstmt, conn);
>     return list;
> }
> 
> @Override
> public int count() {
>     Connection conn = DBUtil2.getConn();
>     String sql = "select count(1) from usermvc";
>     ResultSet result = DBUtil2.query(sql);
>     try {
>         if(result.next())
>             return result.getInt("count(1)");
>     } catch (SQLException throwables) {
>         throwables.printStackTrace();
>     }
>     DBUtil2.closeConn(result, DBUtil2.pstmt, conn);
>     return 0;
> }
> ```
>
> 前端，分页显示器
> ![image-20240908005049021](.\img\分页显示器.png)

### 7. 批量新增与删除

> * 如何和全选框绑定？
> * 如何通过js提交表单？
> * 如何确定要提交的id值？
>
> ```js
> <button type="button" onclick="deleteAll()">批量删除</button>
> <input onclick="selectAll(this)" type="checkbox"/>
> <td>
>     <input name="ck" value="${l.id}" type="checkbox"/>
> </td>
> 
> function selectAll(all) {
>     let cks = document.getElementsByName("ck");
>     for(let i = 0; i < cks.length; ++i) {
>         cks[i].checked = all.checked;
>     }
> }
> function deleteAll() {
>     // document.表单的name属性.action : 更改网址
>     // document.表单的name属性.submit() : 提交表单
>     document.myForm.action="/usermvc/deleteAll";
>     document.myForm.submit();
> }
> ```
>
> ```java
> @RequestMapping("/deleteAll")
> public String deleteAll(Integer[] ck){
>     if(ck != null) {
>         service.deleteAll(ck);//减少jdbc连接次数提高性能。所以不反复调用service.delete()
>     }
>     return "/usermvc/show";
> }
> ```

### 8. 静态资源的路径映射

> 我们一般都是把服务器资源(img css js)放在WEB-INF中，这样更安全，但是WEB-INF不能对外访问(就是`WEB-INF目录外的文件无法直接访问WEB-INF中的内容`)，所以使用静态资源路径映射，让服务器来转发访问静态资源。

> SpringMVC提供了路径映射的功能，如果项目静态资源放入web-inf里面（不能对外访问的）或者静态资源路径很长 都可以借助于SpringMVC来重写地址 SpringMVC会帮你转发到真实地址。
>
> 注意两点：
>
> * mapping属性的值的末尾一定要加一个==/**==
> * location属性的值的末尾一定要加一个==/==
>
> ![image-20240908172253972](.\img\静态资源路径映射的注意事项.png)

```
<mvc:resources mapping="/映射地址前缀/**" location="真实地址"/>
<mvc:resources mapping="/abc/**" location="/WEB-INF/js/"/>

前端：
<script src="/abc/my.js"> </script>
```

### 9. 视图解析器

> 从这里开始我们不是考虑怎么去功能了，而是要开始考虑整个项目安全性等方面了。

#### 9.1 什么是视图解析器

> 是SpringMVC重要的组件，也是SpringMVC工作流程的必加项，目的是用于解析视图，如果把页面放入了web-inf里面很多地址中都有重叠的部分，这样页面越多，代码冗余(地址前缀和后缀)越多。

```java
return "/WEB-INF/jsp/user/show.jsp"
return "/WEB-INF/jsp/study/show.jsp"
在这里/WEB-INF/jsp/ 和 .jsp 出现了冗余
```

> 视图解析器就可以统一处理上面这种代码冗余(地址前缀和后缀)：
> springmvc视图解析器：`为请求处理函数的返回值添加统一的前缀和后缀`
> ==InternalResourceViewResolver : 内部资源视图解析器==

```xml
<!--6.springmvc视图解析器：添加统一的前缀和后缀-->
<!--bug: 这样设置所有的跳转地址 都会添加前缀和后缀
        解决方案：如果一些请求不想加前后缀怎么办？
        return "forward:/地址" 或 "redirect:/地址" - 返回值加了前缀就不会走视图解析器
    -->
<!--以下的class属性：只能识别jsp视图 如果换成其他视图 要换全类名 -->
<!--id="viewResolver" 就是 视图解析器的意思-->
<!--InternalResourceViewResolver : 内部资源视图解析器-->
<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <!--设置前缀-->
    <property name="prefix" value="/WEB-INF/jsp"/>
    <!--设置后缀-->
    <property name="suffix" value=".jsp"/>
</bean>
```

> 其他不想走视图解析器的请求怎么办？
> -- 返回值加上一个前缀：forward 或 redirect

### 10. SpringMVC的工作流程

> 这相当于在讲整个`控制层`的工作流程。
> SpringMVC的入口：web.xml下的<servlet>标签

#### 8.0 工作流程中的几个重要组件

> * `DispatherServlet`：核心控制器
>   最核心的组件，所有请求都必须经过它才能到达SpringMVC 其他核心组件，也必须经过它才能进行正常的交互过程。
> * `HandlerMapping`：请求映射器
>   保存@RequestMapping...配置过的请求地址，目的就是用于和用户发送的请求进行匹配，匹配不到则返回404匹配到了就可以正常执行。
> * `HandlerAdapter`：代理对象
>   谁来调用控制层对象处理请求的对应方法？就是HandlerAdapter用jdk动态代理来实现的。
>   @RequestMapping...对应的哪个Controller中的哪个Method方法。
>   (请求处理对象是扫描包扫描到后创建的)
> * `Controller`：控制层
>   用来编写控制层代码@Controller @RequestMapping。
> * `ViewResolver`：视图解析器
>   用于在前后端不分离的项目中解析ModelAndView对象 是为了解析出哪个Model(作用域和数据)对应哪个View(视图)。

#### 8.1 前后端分离的SpringMVC的工作流程

> ![image-20240806142853722](.\img\前后端分离的SpringMVC的工作流程.png)

#### 8.2 前后端不分离的SpringMVC的工作流程

> ![image-20240806143433854](.\img\前后端不分离的SpringMVC的工作流程.png)

> 上图中controller的返回值中什么是Model什么是View?
> ![image-20240806143211445](.\img\model和view.png)
>
> 上图中的mv用来保存数据的作用域就是model，要跳转的网页就是view。

#### ==8.3 工作流程总结==

##### a. 前后端不分离

> 1.请求先到核心控制器(DispatherServlet)。
>
> 2.核心控制器查询请求映射器(HandlerMapping)中是否存在对应请求的处理方法，如果不存在直接返回浏览器404，如果存在就返回对应的Controller对象。(在springmvc.xml中配置了扫描包，服务器启动时开启扫描，扫描到了就会创建对象，所以对象是在？？时创建)
>
> 3.核心控制器根据请求，通过jdk动态代理对象(HandlerAdapter)动态调用Controller对象的对应Method的方法。
>
> 4.执行控制层的方法进行请求处理，最后执行结束返回ModelAndView给核心控制器。
>
> 5.核心控制器把ModelAndView调用视图解析器(ViewResolver)对其进行解析 会解析成Model对象(保存作用域和其中的数据)和View对象(保存跳转的地址) 返回给核心控制器。
>
> 6.核心控制器返回view视图给前端。

##### b. 前后端分离

> 1.请求先到核心控制器(DispatherServlet)。
>
> 2.核心控制器查询请求映射器(HandlerMapping)中是否存在对应请求的处理方法，如果不存在直接返回浏览器404，如果存在就返回对应的Controller对象。(在springmvc.xml中配置了扫描包，服务器启动时开启扫描，扫描到了就会创建对象，所以对象是在？？时创建)
>
> 3.核心控制器根据请求，让HandlerAdapter动态调用(通过jdk动态代理)Controller对象的对应Method的方法。（什么时候创建Controller层的对象？）
>
> 4.执行控制层的方法进行请求处理，最后执行结束返回json格式数据给核心控制器。
>
> 5.核心控制器直接将json数据返回给前端浏览器。

### 9. SpringMVC拦截器

> springmvc拦截器 依赖于springmvc框架 ，类似于Servlet中的过滤器。
> 底层实现通过反射实例化对象，功能实现是通过动态代理，属于面向切面编程(AOP)重要的应用 
> ==拦截器主要用于拦截进入控制层的请求==，并且在一个请求生命周期内拦截器可以拦截多次 ….
>
> * 应用场景：通过拦截器实现登录拦截、权限控制
> * ==再次强调拦截器只拦截进入控制层的请求==

#### 9.1 拦截器的实现方式

> 1. 实现一个HandlerInterceptor接口。
> 2. 可以选择性的重写接口的三个默认方法，分别对应不同的执行时机：
> 3. 在SpringMVC配置文件中配置拦截器组件。（之前的过滤器把要过滤的放在一个包下一起过滤，无法具体控制拦截哪个请求，==但拦截器可以通过配置来拦截哪些请求，哪些放行==）

> 实现权限控制需要的表：有5张 多对多和多对多所以要用一张表来维护关系
> 用户表             角色表          权限表
> 用户角色关系表       角色和权限关系表

```java
package com.sc.sc240601.interceptor;

import com.sc.sc240601.pojo.Usermvc;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    //1. 控制层方法执行之前调用进行拦截
    //   return true;表示可以访问控制层方法
    //   return false;表示无法访问控制层方法
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Usermvc u = (Usermvc) request.getSession().getAttribute("user");
        if(u == null) {
            response.sendRedirect("/index.jsp");
            return false;
        }
        return true;
        //拦截器是依赖于框架的所以要在框架的配置文件中配置，也就是springmvc.xml
    }
    //Ctrl + o : IDEA快捷方法重写
    //C + A + s: 打开IDEA的Maven管理

    //2. 在控制层方法调用之后执行 在视图解析器之前调用
    //   一般通过它 对请求作用域的数据 进行二次请求
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    //3. 在控制层方法执行结束并且返回了 视图解析器也执行完毕了 才调用
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

```

```xml
    <!--7.配置拦截器-->
    <!--  拦截器是依赖于框架的所以要在框架的配置文件中配置，也就是springmvc.xml-->
    <mvc:interceptors>
        <!--一个拦截器-->
        <mvc:interceptor>
            <!--配置哪些请求需要拦截-->
            <mvc:mapping path="/**"/> <!--表示请求的子请求也拦截-->
            <!--配置哪些请求不需要拦截，可以配置多个-->
            <mvc:exclude-mapping path="/user/login"/>
            <bean class="com.sc.sc240601.interceptor.LoginInterceptor"> </bean> <!--找到拦截器对象，其中id属性可以省略-->
        </mvc:interceptor>
    </mvc:interceptors>
```

#### 9.2 拦截器(Interceptor)和过滤器(Filter)的区别

* Filter：过滤器依赖于Servlet，==过滤器几乎可以拦截所有资源，一个过滤器在一个请求的生命周期中只会拦截一次==。过滤器无法控制拦截规则。
* Interceptor：拦截器依赖于框架，==拦截器只会拦截进入控制层的请求(什么请求会进入控制层？看web.xml中的配置)，拦截器在一个请求中可以设置拦截多次（可以设置三次拦截时机：执行请求处理方法前，执行请求处理方法后，所有方法结束后。）==拦截器可以控制拦截规则。（拦截谁，放行谁）

> 过滤器在拦截器前执行。
>
> 1.过滤器和拦截器触发时机不一样
> 	过滤器是在请求进入容器后，但请求进入servlet之前进行预处理的。请求结束返回也是，是在servlet处理完后，返回给前端之前。
> 总结:过滤器包裹住servlet，servlet包裹住拦截器

### ==10. Ajax==

> ajax：Asynchronous(异步的) JavaScript(js) and xml，即异步的js和xml，是一种无需刷新整个浏览器情况下就可以和服务器进行异步通信，就可以提升整个浏览器页面的响应速度，也叫局部刷新技术。
>
> ==注：ajax必须依赖于js来发送异步请求==
> 这是因为AJAX的核心技术——XMLHttpRequest对象——是通过JavaScript来创建和管理的。没有JavaScript，就无法在浏览器中执行AJAX请求，因为XMLHttpRequest对象是JavaScript的一个内置对象，专门用于与服务器进行异步通信。

#### 10.1 ajax异步请求的优势

> * 提高页面性能：因为ajax可以并行加载数据或者样式，从而提高页面的加载速度和性能。
> * 减少带宽的消耗：由于只更新局部内容，不用更新整个浏览器，从而减少带宽消耗。
> * 提高用户体验：使用ajax发送请求时不刷新整个页面，只是局部刷新，所以不会影响该用户的在该页面的其他操作。（如看视频时点赞，不会刷新页面导致停止视频播放）
> * 实时内容更新：ajax可以更加方便的使用js和服务器进行数据交互，就可以动态进行内容加载。就是有数据更新了，展示数据的部分立马实时刷新。（比如：实时搜索，验证用户名是否可用）
> * 也是前后端分离项目的基础：前端项目和后端项目进行交互，都是发送异步请求(如ajax)然后通过json进行交互。

#### 10.2 异步请求的缺点

> 缺点：
>
> * ==兼容性问题==：浏览器不同，对于ajax处理可能是不同的（这也是前端的一大难点，不同浏览器兼容性不同，前端还要专门兼容性这一课程。）而且一些手机设备也不能很好的支持ajax，都需要进行兼容性处理
> * 调试困难。
> * 依赖于json，但json能`直接`传递的数据类型有限。（能够传递几乎所有类型的数据，但可能需要进行各种转换）
> * 回调函数：前端发送完请求后必须处理回调函数，才能得到响应结果。
> * ==前进和后退==：ajax不适合做前进和后退 用户是无法回到前一个页面的效果（只能回退到当时刚进入该页面的效果）

#### 10.2 ajax异步请求的实现方式

##### a.使用`原生的ajax`来实现

>步骤特别繁琐，不推荐使用。
>
>```java
>1. 创建ajax对象：XMLHttpRequest
>2. 为ajax对象绑定监听(就知道什么时候成功，什么时候失败 就相当于回调函数)
>3. 为ajax对象绑定地址
>4. 发送异步请求
>5. 接收响应数据（如果成功了会执行成功的回调函数，否则执行失败的）
>```
>
>前端页面：
>
>```js
><p>姓名：<input name="name" onblur="ajax1(this)"> <!--onblur失去焦点事件-->
><script>
>   //原生js实现ajax:实现验证可用
>   function ajax1(input) {
>       var msg = document.getElementById("msg");
>       //发送异步请求:
>       //1.创建对象
>       var ajax = new XMLHttpRequest();
>       //2.绑定监听（定义成功和失败的回调函数）
>       ajax.onreadystatechange = function () {
>           //ajax.status表示请求的状态码
>           //ajax.readyState表示监听的连接状态
>           //连接状态：0未连接 1打开连接 2发送请求 3交互 4完成交互，可以接收响应。
>           if (ajax.readyState == 4 && ajax.status == 200) {
>               //表示异步请求已经成功执行完毕，就可以执行成功的回调函数
>               //5. 接收响应的数据
>               // ajax.responseText这个对象表示后端返回的数据
>               var value = ajax.responseText; //后端返回的数据
>               msg.innerHTML = value;
>           }
>
>           //除了成功的 其他判断都是失败的 if(){失败的回调函数}
>       };
>       //3.绑定提交地址
>       //参数1：请求方式get或post
>       //参数2：请求地址url
>       //参数3：表示是否是异步请求，是boolean类型true表示异步
>       ajax.open("get", "/usermvc/checkName?name="+input.value, true);
>
>       //4.发送请求
>       ajax.send();
>   }
></script>
>```
>
>后端：
>
>```java
>异步请求不能跳转，跳转就是整个页面进行刷新，就是同步请求了，所以在后端要加一个@ResponseBody注解，表示
>返回值不是页面跳转而是前端的响应结果。
>//ajax验证姓名是否可用
>// SpringMVC如何处理异步请求？
>// 1.不能跳转
>// 2.方法的返回值就是给前端的响应结果 使用要添加注解@ResponseBody
>// 如果是String返回值需要修改响应格式否则中文乱码,其他类型都不用处理
>// 处理方式：在@RequestMapping注解中添加参数：produces = "text/html;charset=utf-8"
>@RequestMapping(value = "/checkName", produces = "text/html;charset=utf-8")
>@ResponseBody //表示方法的返回值就是给前端的响应结果，而不是页面跳转
>public String checkName(String name) {
>	if(us.checkName(name)){ //调用service层来进行验证
>		return "姓名可以使用";
>	}
>	return "姓名已经重复";
>}
>```

##### b. 使用`jQuery`封装好的方法发送异步请求

> 数据交互格式默认不是json格式，不适合做前后端分离的项目。
>
> ```java
> 1. 先导入jQuery (或是将jQuery下载到本地)
> <script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
> 
> 2. 通过$.方法()来方式异步请求 $就是jQuery的简称
> $.ajax() $.post() $.get()是常用的用来发送异步请求的方式
> 中小型项目推荐使用$.post()
> ```
>
> 前端：
>
> ```js
> <p>姓名：<input name="name" onblur="ajax2(this)"> <!--onblur失去焦点事件-->
> <!--在js中使用jQuery前要先导入jquery-->
> <script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
> <script>
>     //2.通过jQuery来发送异步请求
>     function ajax2(input) {
>         //参数1：请求地址
>         //参数2：可以不写
>         //参数3：回调函数,就是请求发送成功的回调函数
>         $.post("/usermvc/checkName?name="+input.value, function(res){
>             //jQuery访问前端标签的方式和js不同
>             $("#msg").html(res); //jQuery访问前端标签的方式
>             //document.getElementById("msg").innerHTML; //js访问前端标签的方式
>         })
>     }
> </script>
> ```
>
> 后端：略和前面一样。

##### c. 通过`axios`发送异步请求

> Axios的全称是**Ajax – I/O – System**，它是一个基于Promise的HTTP客户端，用于浏览器和Node.js环境。Axios是一个简单而强大的工具，它提供了易于使用的API，可以方便地发送HTTP请求和处理响应数据。
>
> ### Axios的特点和优势包括：
>
> 1. **基于Promise**：Axios返回的是一个Promise对象，这意味着你可以使用`.then()`和`.catch()`链式调用，或者通过async/await来处理异步请求，使得代码更加简洁和易于维护。
>
> 2. **请求和响应拦截**：Axios允许你在请求或响应被then或catch处理之前拦截它们，这可以在请求或响应数据被处理之前进行一些统一的操作，如设置请求头、转换响应数据等。
>
> 3. **自动转换JSON数据**：Axios会自动将请求数据转换为JSON格式，并尝试将响应数据自动转换为JSON对象。这意味着你可以直接发送JavaScript对象作为请求体，并直接以JavaScript对象的形式接收响应数据。
>
> 4. **客户端支持防御XSRF**：Axios在浏览器中自动发送一个XSRF令牌作为请求头，这有助于防止跨站请求伪造（XSRF）攻击。
>
> 5. **取消请求**：Axios提供了取消请求的功能，你可以通过传递一个取消令牌（cancel token）到请求配置中，然后在需要时取消请求。
>
> 6. **多种请求方式**：Axios支持多种HTTP请求方式，包括GET、POST、PUT、DELETE等，你可以根据需要选择合适的请求方式。
>
> 7. **支持浏览器和Node.js**：Axios是一个通用的HTTP客户端，它不仅可以在浏览器中使用，还可以在Node.js环境中使用，这使得它成为前端和后端开发者都喜欢的工具。
>
> 8. **易于配置**：Axios允许你通过创建一个实例来配置全局的默认值，如基础URL、请求超时时间等，这使得在多个请求中使用相同的配置变得非常简单。
>
> ### Axios的使用场景包括：
>
> * 在Web应用中与后端API进行通信。
> * 在Node.js应用中与其他HTTP服务进行交互。
> * 在需要发送HTTP请求和处理响应数据的任何JavaScript环境中使用。
>
> 总的来说，Axios是一个功能强大、易于使用的HTTP客户端，它已经成为许多前端和后端开发者在发送HTTP请求时的首选工具。

> axios对jQuery进行了封装，是目前最主流的方式，非常适合前后端分离的项目，默认交互格式就是json 还可以和vue框架很好的兼容。
>
> ```js
> 1. 导入axios环境 网络导入 本地导入 vue导入<!--在js中使用axios前要先导入axios-->
> <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
> 
> 2. 通过axios.post() axios.get()发送异步请求
> axios.post(url,data).then(匿名函数) //这里的匿名函数就是回调函数
> axios.post(url,data).then(function(res){
> 	res.data//后端返回的结果
> })
> 简化写法：
> axios.post(url,data).then(匿名函数) //这里的匿名函数就是回调函数,
> axios.post(url,data).then(res => {
> 	res.data//后端返回的结果
> })
> ```
>
> 前端：
>
> ```js
> <p>姓名：<input name="name" onblur="ajax3(this)"> <!--onblur失去焦点事件-->
> <!--在js中使用axios前要先导入axios-->
> <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
> <script>
> //3.通过axios来发送异步请求
>     function ajax3(input) {
>         //参数1：请求地址
>         //参数2：可写可不写,表示提交给后端的数据
>         axios.post("/usermvc/checkName?name="+input.value).then(res => {
>             //res.data 表示后端返回的结果对象
>             $("#msg").html(res.data);
>         })
>     }
> </script>
> ```
>
> 后端：略，和前面一样。

#### 10.3 同步和异步请求

##### a. 同步和异步请求的区别

> 同步请求: 
>
> * ==通过正常表单 或者 超连接发送的请求，一般都是同步请求==
> * ==发送请求后 需要等待 服务器响应==，这个时期页面会被阻塞 只能响应了之后 才能执行后续代码(说白了就是单线程程序一行一行执行代码)
>   * 应用场景: 适用于需要立即响应结果，需求后续的功能需要依赖这个结果才能执行的 比如:登录成功后 才能访问后续页面。
>
>
> 异步请求: 
>
> * ==通过vue或者手写jQuery和axios 一般可以发送异步请求，==
> * ==发送请求后 不需要等待服务器响应 类似于有一个子线程专门负责== 异步请求执行，所以服务器没有响应的话依然可以执行后续的代码 (说白了 程序是由主线程执行 异步请求由一个子线程执行 两者不影响 )
>   * 应用场景: 适用于多个并发请求 或者不确定响应时间场景下使用 比如:订单支付 不确定用户何时付款 适合异步的。

##### b. 同步请求和异步请求的区别测试代码

>设置同步和异步可以看到执行顺序上的明显区别：因为异步请求无需等待回调函数的返回结果，所以主函数中的警告框先被调用。而同步请求情况下回调函数中的警告框先被调用。
>
>```js
>    //原生js实现ajax:实现验证可用
>    function ajax1(input) {
>        var msg = document.getElementById("msg");
>        //发送异步请求:
>        //1.创建对象
>        var ajax = new XMLHttpRequest();
>        //2.绑定监听（定义成功和失败的回调函数）
>        ajax.onreadystatechange = function () {
>            //ajax.status表示请求的状态码
>            //ajax.readyState表示监听的连接状态
>            //连接状态：0未连接 1打开连接 2发送请求 3交互 4完成交互，可以接收响应。
>            if (ajax.readyState == 4 && ajax.status == 200) {
>                //表示异步请求已经成功执行完毕，就可以执行成功的回调函数
>                //5. 接收响应的数据
>                // ajax.responseText这个对象表示后端返回的数据
>                var value = ajax.responseText; //后端返回的数据
>                msg.innerHTML = value;
>                alert(value); // alert：警惕的; 警报; 戒备
>            }
>
>            //除了成功的 其他判断都是失败的 if(){失败的回调函数}
>        };
>        //3.绑定提交地址
>        //参数1：请求方式get或post
>        //参数2：请求地址url
>        //参数3：表示是否是异步请求，是boolean类型true表示异步
>        ajax.open("get", "/usermvc/checkName?name="+input.value, true);
>
>        //4.发送请求
>        ajax.send(); //回调函数，子线程
>
>        alert(111111); //主线程,设置同步和异步可以看到执行顺序上的明显区别：因为异步请求无需等待回调函数的返回结果
>    }
>```

### ==11.Json==

> 为什么需要Json? -- 因为异步请求要传递多个数据时，光靠字符串拼接存在缺陷(长度限制，麻烦)，有了Json就可以传递任意数据。
> Json取代了xml，因为性能方面，之前是靠xml来传递多个数据的。

> Json(JavaScript Object Notation)是一种轻量级的数据交互格式，==其本质就是一个字符串==，但是它可以描述任意数据类型，具有良好的拓展性 体积小、传输效率很高 、易于解析。目前适用于各种大中小型前后端分离项目。
> ==前后端分离的系统就是基于json来进行前后端数据交互的。==

#### 11.0 json的优缺点

##### a. 优点

> * 简单易于解析:json数据是可以直接转换成js对象方便前端使用，而且一些成熟框架vue 也可以帮你自动转换。
> * 传输效率高: 属于轻量级的，占用资源小，比较轻便（本质上就是字符串）。
> * 广泛类型支持：通过转换后几乎支持所有的数据结构。

##### b. 缺点

> * 不支持注释：json不支持数据里面添加注释。
> * 换行也不友好：换行的时候 也不能灵活的进行。

#### 11.1 json语法

> Json是基于`键值对`的形式组装数据的：
>
> * key和value 之间通过`:`隔开，多组key和value通过`,`隔开
>
> * key可以任意编写类似于对象中的属性名，Vue中双引号可加可不加
>
>   ```json
>   key:"value",
>   "key2":"value2"
>   ```
>
> * value类似于对象中的属性值，可以存放任意数据
>
>   * value存储整型：key:100
>   * value存储字符串：key:"str"
>   * value存储布尔值：key:true
>   * value存储对象：key:{k:v,k2:v2}
>   * value存储集合或数组：key:[{},{},{}]
>   * value存储空值：key:null

#### 11.2 实际案例

>在js或vue中通过json来表示一个用户对象（id,name,sex）
>
>```js
>var user={
>        id:1,
>        name:"wangwu",
>    sex:"男"
>}
>```
>
>使用json
>
>```js
>通过对象.key来使用json对象
>user.id 
>user.name 
>user.sex 
>```

> 在js或vue中通过json来表示一个班级对象（id, classname, useres(用户集合), group(小组数组)）
>
> ```js
> var class = {
> 	id:100,
> 	classname: "sc240601",
> 	users:[
>         {id:1,name:"zhangsan",sex:"男"},
>         {id:2,name:"lisi",sex:"女"},
>         {id:3,name:"wangwu",sex:"男"} //vue中这里的,加不加都行。
> 	]
> 	group:["group1","group2","group3"]
> }
> ```

> 在js或vue中通过json来表示一个员工对象（id, name, info(部门信息对象)）
>
> ```js
> var emp = {
> 	id:1,
> 	name:"lisi",
> 	info:{id:"100",name:"人事部"}
> }
> ```

#### 11.3 使用json进行前后端数据交互

##### a. 实例

> 前端：
>
> ```js
> <!--必须用type属性将button设置为普通按钮，点击不会提交表单-->
> <button type="button" onclick="ajaxAdd()">异步新增</button>
> 
> <!--在js中使用axios前要先导入axios-->
> <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
> <script>
> 	//前后端分离的模式实现异步新增 vue基本是这样子写的
>     function ajaxAdd() {
>         //$("标签名[name='属性名']")是jQuery的标签属性选择器，axios封装了jQuery所以可以使用。
>         // 它通过指定标签(可省略，不写就是对所有标签进行选择)的指定属性的指定属性值进行选择
>         var name = $("input[name='name']").val();
>         var money = $("input[name='money']").val();
>         var time = $("[name='time']").val(); //标签可以省略
>         alert(name + money + time);
>         // 组装数据
>         var user = {
>             //$("标签名[name='属性名']")是jQuery的标签属性选择器，axios封装了jQuery所以可以使用。
>             // 它通过指定标签(可省略，不写就是对所有标签进行选择)的指定属性的指定属性值进行选择
>             name: name,
>             money: money,
>             time: time//标签可以省略
>         };
>         axios.post('/usermvc/ajaxAdd',user).then(res=>{
>             //res.data 后端返回的几个 //$("#msg")是jQuery的语法，但axios封装了jQuery所以可以使用。
>             console.log(res.data); //在浏览器控制台打印
>             if(res.data.code==1){
>                 alert(res.data.msg);
>                 //vue路由跳转
>             }else{
>                 alert(res.data.msg);
>             }
>         });
>     }
> </script>
> ```
>
> 后端：
>
> ```java
>     @RequestMapping("/ajaxAdd")
> 	//1.前端传递json格式的数据就不能加produces = "text/html;charset=utf-8"参数不然会报406错误
>     	// 因为produces指定了服务器支持的数据类型（如上面指定了支持text/html类型的数据）
>     @ResponseBody
> 	//2.该注解表示方法的返回值就是给前端的响应结果，而不是页面跳转
>     	// 而且返回值如果是对象，它会自动转换为json字符串
>     	//前后端分离项目中，处理任何请求都不能随便返回值
>     	// -- 要统一返回一个对象目的是通过这个对象可以表示任意请求的处理结果
>     	// -- 这个对象要包含三个内容：结果、错误信息、数据
> 	//3.异步新增：前端提交的json数据转换成后端的对象要通过@RequestBody注解来实现
>     public Result ajaxAdd(@RequestBody Usermvc u) throws IOException {
>         int i = us.add(u);
>         // 4.异步新增该返回什么？ --一个自定义的结果对象
>         if (i > 0) {
>             return new Result(1, "新增成功", null);
>         }
>         return new Result(0, "新增失败", null);
>     }
> ```
>
> 总结：
>
> 1.==前后端要用json格式的数据进行交互，后端必须加`@ResponseBody`和`@RequestBody`注解==。
>
> * @ResponseBody注解：表示方法的返回值就是给前端的响应结果，而不是页面跳转， 而且返回值如果是对象，它会自动转换为json字符串。
> * @RequestBody注解：前端提交的json数据转换成后端的对象要通过@RequestBody注解来实现。
>
> 2.@RequestMapping注解不能指定数据类型为application/json以外的数据类型。
>
> * @RequestMapping注解：前端传递json格式的数据就不能加produces = "text/html;charset=utf-8"参数不然会报406错误，因为produces指定了服务器支持的数据类型（如上面指定了支持text/html类型的数据）
>
> 3.==返回结果强烈建议统一的格式==用一个自定义的结果对象：
>
> * 前后端不分离项目 用一个自定义对象表示所有请求的返回结果
>   code -- 响应结果的状态码，1成功，0失败
>   msg -- 表示返回给前端的 信息
>   data:Objet -- 表示返回给前端的 数据，没有就为null

> 后端给前端的自定义结果对象：
>
> ```java
> // 前后端不分离项目 所有请求的返回结果
> // code -- 响应结果的状态码，1成功，0失败
> // msg -- 表示返回给前端的 信息
> // data:Objet -- 表示返回给前端的 数据，没有就为null
> public class Result {
>     private int code; //表示响应结果的状态码，1成功，0失败
>     private String msg; //表示返回给前端的 信息
>     private Object data; //表示返回给前端的 数据
> 
>     public Result() {
>     }
> 
>     public Result(int code, String msg, Object data) {
>         this.code = code;
>         this.msg = msg;
>         this.data = data;
>     }
> 	// get\set方法
> }
> ```

### 12.前后端分离项目的流程

> 前后端分离的项目，无法通过把数据保存到作用域中来进行前后端数据的交互，因为即使是最大的作用域application也要求在同一个服务器中才能传递数据。而前后端分离项目中前端和后端分别部署在不同的服务器上。所以前后端分离的项目只能？

> * 前端：vue
>   * 所有功能通过axios发送异步请求。
>   * 数据传递自动组装成json格式传递给后端。
>   * 异步请求发送成功，等待后端响应，回调函数获取响应数据，展示到前端。
> * 后端：SpringMVC实现控制层
>   * 方法参数上如果接受json数据，添加@RequestBody注解（自动将json数据转化为对象）。
>   * 方法上添加@ResponseBody注解表示通过json返回给前端。
>   * 返回值通过一个统一的Result格式返回。这样前端可以更加方便解析。
> * 优化：
>   * 如果所有请求处理函数都是处理异步请求需要添加@ResponseBody(表示返回值不是页面跳转，而是请求的处理结果)，就可以把类上的注解@Controller修该为@RestController等价于(@Controller+@ResponseBody)下面所有的方法默认都会添加@ResponseBody表示方法的返回值是请求的处理结果。

> 假设要实现一个用户新增功能：如何实现前后端分离的流程？
>
> * 首先用户点击用户新增,用户可以输入必要的数据(账号 密码 性别 日期 ...类似)
>   这些数据会转换成json格式 用户单击提交 发送异步请求 这些json数据也会传递过去
> * 后端: @RestCcontroller 标注控制层方法参数上添加注解RequestBody
>   可以接收前端提交的json数据   后面 进行 ==业务层(在这里讲一些花里胡哨的的）==  mapper层 交互
>   在用户表 实现一个新增操作    新增结果会随着业务层 返回给控制层
> * 控制层会返回一个统一的格式 比如:result对象 转换成json返回给前端
> * 前端发送的异步 会有一个回调函数，可以接收后端返回的json数据
>   最后前端就可以解析这个json 控制结果成功和失败

#### 12.1 跨域问题

##### a.是什么？

> 跨域：前端是一个项目，后端也是一个项目，不同项目之间想去访问必须写完整的url(协议://ip地址:端口号/项目），这三者如果有一个不同都会产生跨域问题。
> 但是前后端分离后url就是不一样的，所以一定会产生跨域问题。

##### b.解决方案

> * 后端：注解 或 配置类 ...
> * 前端：通过代理...
>
> 有一方做了就可以解决跨域问题，但是前后端分离时，我们只做一方，不确定对方做不做，所以默认是两边都要做的。

### 13.SpringMVC的常用注解？（Spring的常用注解）

> SpringMVC和Spring框架中的常用注解非常丰富，这些注解极大地简化了Web应用程序的开发和配置。以下是对SpringMVC和Spring中常用注解的归纳：
>
> ### SpringMVC常用注解
>
> 1. **@Controller**：用于标识一个类为Spring MVC的控制器组件。控制器类通常处理HTTP请求并返回响应结果。
>
> 2. **@RequestMapping**：用于映射HTTP请求到具体的处理函数。它可以定义在类级别或方法级别，用于指定请求的URL路径。
>    此外，Spring MVC还提供了更细分的映射注解，如@GetMapping、@PostMapping、@PutMapping、@DeleteMapping、@PatchMapping等，分别用于处理不同类型的HTTP请求。
>
> 3. **@ResponseBody**：用于将控制器的返回值作为HTTP响应体返回给客户端。通常与@RequestMapping注解一起使用，以返回JSON或XML等格式的数据。
>
> 4. **@RequestBody**：允许HTTP请求的参数在请求体中，而不是在URL中。它可以将请求体中的JSON或XML数据绑定到方法的参数上。
>
> 5. **@PathVariable**：用于将URL模板变量绑定到控制器方法的参数上。例如，在URL `/user/{id}`中，`{id}`就是一个路径变量，可以通过@PathVariable注解将其值传递给方法的参数。
>
> 6. **@RequestParam**：用于将HTTP请求参数的值绑定到控制器方法的参数上。当请求参数在URL的查询字符串中时，可以使用此注解。
>
> 7. **@RestController**：是@Controller和@ResponseBody的组合注解，用于标识一个类为RESTful风格的控制器。使用@RestController注解的类中的所有方法都会自动应用@ResponseBody注解。
>
> 8. **@CrossOrigin**：用于处理跨域资源共享（CORS）问题。可以注解在类或方法上，以允许来自不同源的HTTP请求访问资源。
>
> 9. **@ExceptionHandler**：用于处理控制器中抛出的异常。可以将异常处理方法与特定的异常类型关联起来，以便在发生异常时执行相应的处理逻辑。
>
> 10. **@ControllerAdvice**：用于定义全局的异常处理逻辑或数据绑定逻辑等。它作用于类级别，可以对多个控制器进行统一处理。
>
> ### Spring常用注解
>
> 1. **@Component**：一个通用的组件注解，用于标识一个类为Spring的组件。@Controller、@Service、@Repository都可以视为@Component的特例。
>
> 2. **@Service**：用于标识一个类为服务层组件。服务层通常包含应用程序的业务逻辑。
>
> 3. **@Repository**：用于标识一个类为数据访问层组件，通常用于DAO类。它提供了额外的功能，如异常转换，将原生访问层异常转换为Spring的数据访问异常。
>
> 4. **@Autowired**：用于自动注入依赖。它可以注解在字段、构造器、Setter方法或配置方法上，Spring容器会自动查找匹配的Bean并注入到注解所在的位置。
>
> 5. **@Configuration**：用于标识一个类为配置类，它允许通过Java代码而不是XML文件来定义Bean。通常与@Bean注解一起使用。
>
> 6. **@Bean**：用于在配置类中声明一个Bean。当Spring容器启动时，会调用被@Bean注解的方法，并将方法的返回值注册为一个Bean。
>
> 7. **@Value**：用于注入配置文件中的值或表达式的结果。它可以注解在字段、构造器或方法上。
>
> 8. **@Scope**：用于指定Bean的作用域，如singleton（单例）、prototype（原型）等。
>
> 9. **@PostConstruct**和**@PreDestroy**：分别用于指定Bean的初始化方法和销毁方法。这些方法会在Bean的生命周期中的相应阶段被自动调用。
>
> 以上只是SpringMVC和Spring中常用注解的一部分，实际上Spring框架提供了非常丰富的注解，以满足不同场景下的需求。开发者可以根据具体的应用场景和需求选择合适的注解进行使用。

### 14.SpringMVC常见面试题

> 1. 谈谈你对SpringMVC的理解？
>
> 2. MVC三层架构？（为什么要分层和分层的好处）
>
> 3. SpringMVC的工作流程？
>
> 4. 拦截器(Interceptor)和过滤器(Filter)的区别？（什么时候用Interceptor？什么时候用Filter?）
>
> 5. SpringMVC的常用注解？（Spring的常用注解）
>
>    ```java
>    @Controller : 标注Controller类，让扫描包能扫描到
>    @RequestMapping\@GetMapping\@PostMapping：GetMapping只能处理get请求
>    @ResponseBody：表示方法的返回值就是给前端的响应结果，而不是页面跳转，
>    @RequestBody 前端提交json数据，后端转换成对象接收
>    @RestController = @Controller+@ResponseBody
>    @RequestParam(当形参名和前端的key不一样时可以使用)[@RequestParam("name") String a]
>   
>    @DateTimeFormat ：前端提交的日期数据是字符串类型，后端接收的则是日期格式，用它来进行格式转换。
>    @SQLFormat
>    ```
>    
>6. 什么是ajax 应用场景？
> 
>7. 同步和异步请求的区别 应用场景？
> 
>8. json是什么？笔试题可能需要按题目来写json格式的数据。

### 15. 问题记录

> 1.如果不在web.xml中配置springmvc的核心配置文件会出现Ctrl + 右击无法找到请求的问题。
