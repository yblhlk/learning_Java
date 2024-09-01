## Spring

### 1. 什么是spring?

> spring是一个轻量级的`控制反转(IOC)` 和 `面向切面编程(AOP)`的容器框架，主要是用来管理对象的生命周期的，这样spring就可以更加方便的控制对象资源的消耗，它集合了JavaEE全功能的解决方案。

#### 怎么去学框架？

> 去看官网。[Web on Servlet Stack :: Spring Framework](https://docs.spring.io/spring-framework/reference/web.html)
> projects -> Overview -> 网页最下面(Features)
>
> ![image-20240813174640132](.\Img\SpringMVC官网.png)

#### 1.0 架构体系：

> ![image-20240813173253932](.\Img\Spring架构体系.jpg)

#### 1.1 spring框架体系介绍

> * `core container`: 核心容器，主要通过BeanFactory管理对象的生命周期来控制哪些对象是单例模式，哪些对象是多例模式，他就是IOC
> * `Spring AOP`：面向切面编程，底层实现是jdk动态代理(默认)
> * `Data Access`：数据库访问模块，Spring支持对于持久层框架的整合
>   Spring可以整合MyBatis、MyBatis Plus、Hibernate、jdbc ...
> * `web`：控制层的功能，和前端进行交互的，Spring支持对于控制层框架的整合（比如：Spring可以整合Servlet、struts2、SpringMVC ...)
> * `Test`：测试模块，表示Spring支持整合测试框架(junit)

#### 1.2 spring依赖

> ```xml
> <!--spring依赖-->
> <dependency>
>     <groupId>org.springframework</groupId>
>     <artifactId>spring-context</artifactId>
>     <version>5.0.3.RELEASE</version>
> </dependency>
> <!--aop依赖-->
> <dependency>
>     <groupId>org.springframework</groupId>
>     <artifactId>spring-aspects</artifactId>
>     <version>5.0.3.RELEASE</version>
> </dependency>
> ```

### 2. IOC

> IOC就是控制反转，就是将创建对象控制权，交给Spring容器去管理，这样Spring就可以集中管理这些对象的生命周期，哪些只需要创建一个(单例) 哪些对象需要创建多个，这样就可以节省整个项目的资源消耗
> 对象控制器原来是自己，后面交给Spring容器，控制器由主动变成了被动就是控制反转。
>
> ==IOC控制反转是如何实现的：是通过DI实现==
>
> ```
> User u = new User(); -- 对象控制权是自己
> User u2 = Spring容器创建 -- 对象控制器是Spring容器
> 控制器有主动变成被动就是控制反转
> ```

#### 2.1 DI

> DI(Dependency Injection)就是依赖注入。（对象中属性的控制权）
> 就是它允许类的依赖关系（即依赖的属性或对象）不是由类本身在内部创建和管理的，而是由外部（如Spring容器）在运行时动态地注入到类中。这种方式极大地提高了代码的模块化和可测试性，同时也降低了组件之间的耦合度。
> 注入的过程中Spring推荐使用接口来当作依赖属性（因为接口可以提供很多种实现类 这样Spring可以更加方便切面实现类，而不修改代码，这样就降低各层之间的依赖关系 实现了解耦合。

#### 2.2 DI依赖注入方式

> 构造方法比set()先调用，所以一定是先执行构造方法注入。
>
> * set注入:
>   在类中给依赖属性添加set方法，
>   并且在spring配置文件的beans标签下添加bean标签来表示对象(用class属性来定位)，
>   在bean标签里面添加==property标签== 用于给属性赋值(用name属性定位，使用value属性赋值)，
>   在底层bean是通过无参构造方法来创建对象，创建完对象后才通过set方法给依赖属性赋值。
>   property不考虑注入的属性的顺序和个数，==所以set注入适合注入复杂依赖关系，更直观。==
>
> * 构造方法注入：
>   在类中添加有参构造方法，
>   并且在spring配置文件的beans标签下添加bean标签来表示对象(用class属性来定位)
>   在bean标签里面添加==construtor-arg标签==来配置有参构造的参数 (靠顺序定位，使用value属性赋值)，
>   在底层bean是调用有参构造，在对象创建的时候 来完成所有属性的注入 
>   如果有一个属性出现问题 就会导致创建对象失败，
>   并且可能会产生循环依赖的问题，==它不适合复杂关系注入==
>
> * 注解注入:借助于IOC注解 和 DI依赖注入注解 来完成属性的注入
>   * IOC注解：@Controller @Service @Repository @Component
>
>     ```java
>     这些注解用于将类标记为Spring容器管理的组件，从而实现了控制反转（IoC）。
>
>     @Controller：用于标注控制层组件，主要处理HTTP请求。
>     @Service：用于标注服务层组件，实现业务逻辑。
>     @Repository：用于标注数据访问层组件，即DAO组件，主要进行数据库操作。
>     @Component：这是一个泛化的概念，当不好归类组件到上述三个层时，可以使用@Component。
>     ```
>
>   * DI依赖注入注解：@Autowired(spring推荐使用)  @Resource(java自带)
>
>   * 最后spring的配置文件中：==一定要配置扫描包==

##### 实例

> ```xml
> <beans xmlns="http://www.springframework.org/schema/beans"
>        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
>     <!--IOC对象控制权给Spring和DI通过Spring给依赖属性赋值-->
>     <!--每一个bean就是一个java可重用组件（java对象）-->
>     <!--scope属性用来指定是单例还是多例模式创建对象-->
>     <bean scope="singleton" id="u" class="com.sc.pojo.User">
>         <!--（property不考虑注入的属性的顺序和个数）-->
>         <property name="id" value="1"/>
>         <property name="name" value="zhangsan"/>
>         <property name="moeny" value="100"/>
>         <property name="time">
>             <!--相当于调用sdf对象的parse方法-->
>             <bean factory-bean="sdf" factory-method="parse">
>                 <constructor-arg value="2020-10-1"/>
>             </bean>
>         </property>
>     </bean><!--这里就相当于new了一个User对象，只不过Spring可以管理这个类是单例还是多例-->
> 
>     <!--创建一个日期类对象-->
>     <bean id="sdf" class="java.text.SimpleDateFormat">
>         <constructor-arg value="yyyy-MM-dd"/>
>     </bean>
> 
> 
>     <bean id="uc" class="com.sc.controller.UserController">
>         <!--value是给字符串和基本类型赋值，ref是给对象类型成员赋值-->
>         <property name="us" ref="us"/>
>     </bean>
> 
>     <bean id="us" class="com.sc.service.Impl.UserServiceImpl">
>         <!--注意接口不能被实例化-->
>         <property name="ud" ref="ud"/>
>     </bean>
>     <bean id="ud" class="com.sc.dao.Impl.UserDaoImpl">
>     </bean>
> 
>     <!--一个对象可以有多个bean-->
>     <!--测试构造注入，使用类提供的有参构造方法来注入-->
>     <bean id="u2" class="com.sc.pojo.User">
>         <!-- 使用有参构造来进行注入：属性个数顺序根据构造方法来定义-->
>         <constructor-arg value="1"/>
>         <constructor-arg value="李四"/>
>         <constructor-arg value="1.0"/>
>         <constructor-arg>
>             <bean factory-bean="sdf" factory-method="parse">
>                 <constructor-arg value="2020-08-01"/>
>             </bean>
>         </constructor-arg>
>     </bean>
> <!--    <bean></bean>-->
> <!--    <bean></bean> 不能有空的会报错-->
> <!--    <bean></bean>-->
> <!--    <bean></bean>-->
> </beans>
> 
> <!--
>     像这样面向接口编程的好处是：
>     只要在这个配置文件中修改bean引用的其他bean就完成了修改对象中的引用类型属性。
> -->
> ```
>
> 

#### 2.3 循环依赖问题  待测试？

> 循环依赖就是两个类相互依赖，如A类依赖于B类，B类也依赖于A类（即A类中有个属性是B……）
> 如果采用构造方法注入，会在对象实例化时借助于有参构造来完成依赖关系，但是A要等里面的属性B创建好，而B又要得到A创建好，这就发生了循环依赖。
> (如果两个bean相互依赖，那么每个bean的构造器都需要另一个bean的实例作为参数，这就形成了一个死锁)
>
> ```xml
> <!--假设A和B都提供有参构造-->
> <bean id="a" class="A">
> 	<construtor-arg ref="b"/> <!--会调用有参构造方法-->
> </bean>
> <bean id="b" class="B">
> 	<construtor-arg ref="a"/>
> </bean>
> ```
>
> 解决方案：
>
> * 可以重新设计：不设计成相互依赖的清空。
> * 如果不能重新设计，可以使用set注入或者注解注入 原因他们可以不会在对象创建时，而是先利用无参构造把对象创建好，再完成依赖属性的注入。
> * 也可以添加一个@Lazy延迟加载注解。可以对依赖注入添加该注解，这样这个依赖属性不会立马注入，只会注入一个代理对象，只有当首次使用时，才会完成对象实例化过程，才进行注入。

#### 2.4日期格式异常问题

> 对象中有时间类属性时要进行处理：
>
> ```xml
>     <!--创建一个日期类对象-->
>     <bean id="sdf" class="java.text.SimpleDateFormat">
>         <constructor-arg value="yyyy-MM-dd"/>
>     </bean>
>     
> <bean scope="singleton" id="u" class="pojo.User">
>         <!--（property不考虑注入的属性的顺序和个数）-->
>         <property name="id" value="1"/>
>         <property name="name" value="zhangsan"/>
>         <property name="moeny" value="100"/>
>         <property name="time">
>             <!--相当于调用sdf对象的parse方法-->
>             <bean factory-bean="sdf" factory-method="parse">
>                 <constructor-arg value="2020-10-1"/>
>             </bean>
>         </property>
>     </bean><!--这里就相当于new了一个User对象，只不过Spring可以管理这个类是单例还是多例-->
> 	
>     <!--一个对象可以有多个bean-->
>     <!--测试构造注入，使用类提供的有参构造方法来注入-->
>     <bean id="u2" class="pojo.User">
>         <!-- 使用有参构造来进行注入：属性个数顺序根据构造方法来定义-->
>         <constructor-arg value="1"/>
>         <constructor-arg value="李四"/>
>         <constructor-arg value="1.0"/>
>         <constructor-arg>
>             <bean factory-bean="sdf" factory-method="parse">
>                 <constructor-arg value="2020-08-01"/>
>             </bean>
>         </constructor-arg>
>     </bean>
> 
> ```
>
> 

#### 2.4 spring管理的bean对象的生命周期

> spring提供了多种作用域（Scope）来控制bean的创建和销毁过程，这些作用域直接影响了bean的生命周期。
>
> spring默认管理的bean都是单例模式,除了默认的还提供很多种生命周期
>
> * 单例: 默认的，读取spring配置文件创建（一般是服务器启动时读取配置文件）就会加载所有的bean标签，也会扫描IOC注解 创建bean对象。
>   在服务器关闭的时候就可以正常消毁。
> * 多实例(多例)：这个类Spring使用一次创建一个新的，每次使用结束后，这个bean就会自动回收。
> * 请求request：这个类在Spring每次发送请求，就会创建一个新的对象，请求结束了会自动回收。
> * 会话session：这个类spring每次会话时会创建一个新的对象，这个类在Spring每次会话中有效，当会话超时或者强制销毁时，对象才会回收。
> * 全局session：global session 就是application，属于应用级别的，生命周期和项目的生命周期一致。

##### 2.4.1 Spring如何设置不同的生命周期

> * 可以通过Spring配置文件，修改bean标签，添加scope属性，来指定对象作用范围
>   * 注：如果要修改request session 和全局session 需要添加一个配置 否则失效 
>     <aop:scoped-proxy/ >
>   
>     ```xml
>     <bean id="user" class="com.sc.pojo.User" scope="prototype">
>         <aop:scoped-proxy/>
>     </bean>
>     ```
>   
>     
>   
> * 也可以通过@Scope注解 来设置对象作用范围。
>
> ![image-20240815112225662](D:\Desktop\gitee\java-learning\sc240601\Spring\Img\scope属性.png)

### 3. AOP

#### 3.1 是什么?

> AOP 是面向切面编程，主要`用于将项目中的通用功能`（事务、异常、日志、权限、资源回收）和 `主要的业务功能进行分离`，分离完成后Spring就可以把这些通用功能做成一个切面，指定的方法只要经过这个切面，就会自动添加通用功能，对于开发者而言，只需要关注于业务功能(好处就是通用功能和业务功能进行了解耦)，底层实现 是jdk动态代理实现的，可以不改变源程序基础上做增强处理(切面 也就是通用功能)。
>
> 好处：
>
> * 可以实现 通用功能 和 业务功能 的解耦。
> * 可以很方便添加额外的功能，而且不影响源程序的运行，提高扩展性。

#### 3.2 AOP中的几个重要概念

> * Aspect：切面，一组通知的集合，指定AOP中定义通用功能的类。
>
> * Advice：通知，指定切面作用在 业务功能的时机和位置（就是切面中的一个功能，无数个功能合在一起就是一个切面）
>
> * JoinPoint：连接点，业务功能(方法)可以插入切面的点，说白了就是要经过切面的具体方法。
>   每个`JoinPoint`都对应着一个具体的执行点，比如一个方法的调用。当程序执行到这个点时，AOP框架可以根据配置决定是否要在这个点上应用切面逻辑。
>
> * PointCut：切入点，AOP的精髓，就是连接点的集合，目的就是指定哪些方法需要被我切入。
>   Spring是通过表达式语言和通配符的方式来共同描述 多种不同的方法
>   比如：让所有业务层方法全部经我的切面
>
>   ```
>   	  任意返回值                  (任意参数)
>   execution(* com.sc.service.impl.*.*(..))
>   			   包名.包名.包名.所有类.所有方法
>   void test(参数){
>             
>   }
>   ```

> ### AOP的核心概念
> 1. **切面（Aspect）**：切面是AOP的核心模块，它定义了横切关注点的具体行为。切面通常由多个“通知”（Advice）和“切入点”（Pointcut）组成。
>    
> 2. **通知（Advice）**：通知是切面中实际执行横切关注点行为的代码。通知可以在方法执行的不同阶段被执行，例如方法执行前、执行后或抛出异常时。
>
> 3. **切入点（Pointcut）**：切入点定义了哪些方法或类的哪些地方需要被切面“织入”（weave），也就是说，切入点决定了通知何时以及在什么条件下执行。
>
> 4. **连接点（Joinpoint）**：连接点是程序执行过程中一个可以插入切面的点，通常是方法调用或异常抛出。
>
> 5. **织入（Weaving）**：织入是将切面应用到目标对象上的过程。织入可以在编译时、类加载时或运行时进行。
>
> ### AOP的好处
> 1. **代码复用**：通过将横切关注点分离到独立的切面中，AOP减少了代码的重复。例如，一个通用的日志记录切面可以应用到多个模块的不同方法上，而无需在每个方法中都手动添加日志代码。
>
> 2. **提高模块化**：AOP将横切关注点从业务逻辑中分离出来，增强了代码的模块化。这使得代码更加清晰易读，业务逻辑更加专注，横切关注点的变更也不会影响核心业务逻辑。
>
> 3. **简化维护**：由于横切关注点集中在切面中，维护和更新这些功能变得更加简单。例如，如果需要修改安全策略，只需更新安全切面而无需修改每个需要安全检查的方法。
>
> 4. **灵活性和可扩展性**：AOP允许动态地应用和修改切面，增加了程序的灵活性。例如，开发者可以根据不同的配置条件在运行时选择性地应用某些切面。
>
> 5. **解耦合**：AOP通过切面实现关注点的解耦，使得核心业务逻辑与横切关注点独立，降低了代码的耦合度。这有助于更清晰地理解和管理代码。
>
> ### AOP的典型应用场景
> - **日志记录**：自动记录方法的进入、退出、参数和返回值。
> - **事务管理**：自动管理事务的开启、提交和回滚。
> - **安全检查**：在方法执行前进行权限验证。
> - **异常处理**：统一处理异常并记录错误日志。
>
> 总体来说，AOP通过分离关注点，简化了代码结构，增强了代码的可维护性和灵活性，是一种强大的编程工具。

#### 3.3 AOP的通知类型

> * 前置通知：aop:before标签，表示在目标方法执行之前调用。
> * 后置通知：aop:after-returning标签，表示在目标方法执行之后并正常返回才调用。
> * 最后通知：aop:after标签，表示在目标方法执行之后调用，类似于finally(必走)
> * 异常通知：aop:after-throwing标签，表示在目标方法执行时发生异常时调用
> * 环绕通知：aop:around标签，表示在目标方法执行前后都会调用（包含了前面4种通知的集合）
>
> ```Java
> try{
> 	//这个位置(时机)进行前置通知
> 	通过aop动态调用业务层所有方法
> 	//这个位置(时机)进行后置通知
> } catch(Exception e) {
> 	//这个位置(时机)进行异常通知
> } finally {
> 	//这个位置(时机)进行最后通知
> }
> //这上面这个整体就是环绕通知。
> ```

##### 实例1（不通过注解）

> 通知类(切面) 写通知方法
>
> ```java
> // AOP实现日志功能：让所有业务层方法执行的时候 可以添加日志
> // 日志切面
> public class MyLog {
>     public void aa() {
>         System.out.println("我是前置通知");
>     }
>     public void bb(Object result) {
>         System.out.println("我是后置通知，参数表示目标方法的返回值");
>     }
>     public void cc(Exception e) {
>         System.out.println("我是异常通知，参数表示目标方法发生的异常");
>     }
>     public void dd() {
>         System.out.println("我是最后通知");
>     }
>     // 配置环绕通知方法
>     //必须有一个返回值(不能为void)
>     public Object ee(ProceedingJoinPoint jp) {
>         Object result = null;
>         try {
>             System.out.println("环绕的前置通知");
>             result = jp.proceed(); //等价于jdk动态代理invoke()
>             System.out.println("环绕的后置通知");
>         } catch (Throwable throwable) { //要求抛Throwable，Exception还不够大
>             throwable.printStackTrace();
>             System.out.println("环绕的异常通知");
>         } finally {
>             System.out.println("环绕的最后通知");
>         }
>         return result;
>     }
> }
> ```
>
> spring配置文件：
>
> ```xml
> <!--aop配置-->
> <aop:config>
>     <!--1. 配置切入点，目的是告诉Spring哪些方法要经过切面-->
>     <!--注意不能包含接口，不然接口也会创建对象，一注入就出错-->
>     <aop:pointcut id="pc" expression="execution(* com.sc.service.Impl.*.*(..))"/>
>     <!--2. 配置切面：告诉spring哪个bean是负责实现切面-->
>     <!--可以配置多个切面-->
>     <aop:aspect id="log" ref="myLog">
>         <!--告诉Spring切面的哪个方法是做什么通知的-->
>         <aop:before method="aa" pointcut-ref="pc"/>
>         <!--要添加属性returning 来表示目标方法的返回值，填入切面方法的参数-->
>         <aop:after-returning method="bb" returning="result" pointcut-ref="pc"/>
>         <!--要添加属性throwing 来表示目标方法发生的异常的类型，填入切面方法的参数-->
>         <aop:after-throwing method="cc" throwing="e" pointcut-ref="pc"/>
>         <aop:after method="dd" pointcut-ref="pc"/>
>         <aop:around method="ee" pointcut-ref="pc"/>
>     </aop:aspect>
> </aop:config>
> <bean id="myLog" class="com.sc.aop.MyLog"></bean>
> ```

##### 实例2：全靠注解

> 通知类（切面）写通知方法
>
> ```java
> package com.sc.aop;
> 
> //通过注解配置AOP日志
> //创建MyLoqBean对象
> //创建切面 ref引入上面的对象
> //切面里面配置很多标签 实现不同通知 关联切入点
> //配置切入点··
> 
> import org.aspectj.lang.annotation.*;
> import org.springframework.stereotype.Component;
> @Component //通过IOC扫描 等价于编写了bean标签
> @Aspect    //标注我是切面 等价于配置了Aop:aspect标签
> public class MyLog2 {
>     //配置切入点的注解：等价于 aop:pointcut标签
>     @Pointcut("execution(* com.sc.service.Impl.*.*(..))")
>     public void pc() { }
> 
>     @Before("pc()") //注意不要导成了junit的Before注解
>     public void before(){
>         System.out.println("前置通知");
>     }
> 
>     @AfterReturning(pointcut = "pc()", returning = "result")
>     public void afterReturning(Object result) {
>         System.out.println("后置通知");
>     }
> 
>     @After("pc()")
>     public void after(){
>         System.out.println("最后通知");
>     }
> 
>     @AfterThrowing(pointcut = "pc()", throwing = "e")
>     public void afterThrowing(Exception e) {
>         System.out.println("异常通知");
>     }
> 
>     @Around("pc()")
>     public Object around(ProceedingJoinPoint jp) {
>         Object result = null;
>         // 获取目标方法的名称
>         String methodName = jp.getSignature().getName();
>         try {
>             Object[] args = jp.getArgs();
>             System.out.println("\033[33m" + new Date() + "【前置通知】：" + methodName
>                     + "开始调用，参数" + Arrays.toString(args) + "\033[0m");
>             result = jp.proceed();
>             System.out.println("\033[33m" + new Date() + "【前置通知】：" + methodName
>                     + "运行结束，返回值" + result + "\033[0m");
>         } catch (Throwable e) {
>             System.out.println("\033[31m" + new Date() + "【异常通知】：" + methodName
>                     + "运行时发生异常：" + e + "\033[0m");
>         } finally {
>             System.out.println("\033[35m" + new Date() + "【最后通知】：" + methodName
>                     + "运行结束\033[0m");
>         }
>         return result;
>     }
> }
> ```
>
> spring配置文件
>
> ```xml
>     <!--开启AOP注解：让@Aspect @Point... 等注解生效-->
>     <aop:aspectj-autoproxy/>
> ```

#### 3.4 spring如何实现aop
> * 通过配置文件编写
>
>   * 随便写个类(表示切面) 随便写几个方法(表示通知)
>   * 通过spring配置文件告诉它 哪个切面 哪个方法是通过
>
> * 通过aop注解来完成
>
>   * 通过@Component注解，让Spring扫描创建bean对象
>
>   * 通过@Aspect注解标注类是切面类
>
>   * 通过@PointCut注解配置切入点
>
>   * 最后@Before @After @AfterReturning @AfterThrowing @Around
>
>   * Spring配置文件 重要开启AOP注解即可
>
>     ```xml
>         <!--开启AOP注解：让@Aspect @Point... 等注解生效-->
>         <aop:aspectj-autoproxy/>
>     ```

#### 3.5 Spring如何实现事务功能

> 声明式事务：通过配置文件，配置事务严格的策略(安全性高)，事务的传播特性
>
> * 加载jdbc配置文件
> * 创建数据库连接池（德鲁伊连接池）
> * 创建事务管理类（类似于Spring写好的环绕通知）
> * 配置事务管理策略（控制事务的传播特性，控制哪些方法加事务，哪些只读事务）
> * 配置AOP，在里面配置切入点，关联前面的事务策略。
>
> <hr>
>
> 注解式事务：通过配置文件，配置完成后只需要通过@Transactional注解就可以做事务（Springboot）
>
> * 加载jdbc配置文件
> * 创建数据库连接池（德鲁伊连接池）
> * 创建事务管理类（类似于Spring写好的环绕通知）
> * 开启事务注解（注解只是用来标记，没有其他作用，所以一定要开启注解）
>   使用时，什么方法需要做事务，添加@Transactional注解，也可以写在类上表示这个类的所有方法都需要做事务。
>
> <hr>
>
> ```xml
> <!--阿里的德鲁伊连接池-->
> <dependency>
>     <groupId>com.alibaba</groupId>
>     <artifactId>druid</artifactId>
>     <version>1.1.2</version>
> </dependency>
> <!-- Spring整合ORM框架依赖 -->
> <dependency>
>     <groupId>org.springframework</groupId>
>     <artifactId>spring-orm</artifactId>
>     <version>5.0.3.RELEASE</version>
> </dependency>
> ```
>
> 

### 4.Spring常用注解

> * IOC扫描注解：一般是使用在类上面，Spring配置文件必须提供扫描包
>   Spring只要扫描到类上有这类的注解，他就会自动创建该类的对象（等价于Spring帮你自动编写了bean标签）
>   下面四个注解底层实现都是一样的，底层都是Component，也就是说Component都是它们的父类注解，而且还会提供`默认bean的id：类名首字母小写`，如果想修改bean的id:@IOC扫描注解("id")
>   ==不能写在接口里面写==
>   ==注解只是用来标记的，不起任何作用，是告诉配置文件，让配置文件来创建的。==
>   ==配置好注解后，可以不提供set方法也能创建对象==
>   * @Controller ：标记控制层，因为只有控制层才能接收请求。
>   * @Service ：标记业务层，因为只有业务层才能正常完成事务。
>   * @Repository ：标记持久层/dao层/mapper层，这个注解后期可以不写，因为spring后期可以整合MyBatis的mapper接口(创建它的实现类)不是靠扫描创建出来的。
>   * @Component ：标注其他层的注解，比如：过滤器、拦截器、创建、工具类。
> * DI依赖注入注解：一般写在成员变量上的注解，用于通过springIOC容器中的bean对象给该成员变量赋值 前提是：ioc容器必须有这个bean对象。
>   * @Autowired：是spring提供的注解，自动根据Spring IOC容器中有的 bean对象类型去匹配(通过IOC扫描创建的bean对象也在里面) 再根据依赖属性名和容器中的bean的id匹配
>     如果匹配上了就自动注入(赋值)，如果匹配不上Spring则会抛出没有bean的异常。
>     如果匹配上了，但是不止一个 Spring也会抛出异常，因为Spring不知道注入哪个类型，
>     解决方案：通过@Qualifier("bean的id") 用来指定使用哪个bean来注入
>   * @Resource：是java自带的注解，先匹配容器中bean的类型，匹配上了自动赋值，否则报错，如果匹配上了，但是不止一个，还可以根据bean的id去匹配关联属性名，也会自动赋值。
>   * @Resource 和 @Autowired：
> * AOP注解：
> * MVC注解：
>   * @RequestMapping @GetMapping @PostMapping
>     @DateTimeFormat @JsonFormat 
>     @RequestBody @ResponseBody
>     @RequestParam
> * 其他注解：
>   * @Scope：指定bean对象的作用域。

### 5. Spring加载配置文件的方式

> * 添加依赖mvc依赖(包含Spring的监听器)
>
>   ```xml
>   <!-- spring版本一定要和springmvc的版本是一致的 否则出现error-->
>   <!--springmvc核心依赖 -->
>   <dependency>
>       <groupId>org.springframework</groupId>
>       <artifactId>spring-webmvc</artifactId>
>       <version>5.0.3.RELEASE</version>
>   </dependency>
>   ```
>
> * 添加全局配置：配置Spring配置文件地址（web.xml中）
>
> * 配置Spring提供的监听：加载Spring配置文件（web.xml中）

### 6. Spring事务的传播特性

> Spring一共提供了七种事务传播特性，是为了解决事务和事务之间嵌套的问题。通过它可以控制哪些方法支持事务，哪些方法不支持，哪些支持嵌套事务（复杂业务中很可能会出现，业务层调用其他业务层的情况，这种时候就会出现一共事务嵌套了到了另一共事务中了。）
>
> * required：默认值，必须有一个事务，如果事务不存在，则开启一个新的事务。
> * required_new：属于新的事务 必须运行在自己新建的事务中。
> * supports：支持事务(支持不代表必须要有)，不强制要求有事务，但是有事务一定会支持。
> * not_supports：不支持事务，如果有事务也不会运行。
> * never：永不支持事务。如果有事务，不但不运行，还会抛出异常。
> * mandatory：必须要有事务，没有事务则抛出异常。
> * nested：嵌套事务，可以支持多个事务之间嵌套在一起，但是一旦里面的子事务失败，外层事务也会失败都要回滚。

### 7.整合SSM(Spring\Springmvc\Mybatis)
> 整合SM(Springboot+Mybatis)

> * 导入相关依赖（有很多，不仅仅是三个框架的依赖，还有用于整合的依赖，Spring版本不能不一样）
> * 创建对应的配置文件：Spring、SpringMVC、MyBatis、反向生成插件
> * 配置web.xml
>   * 在全局配置(配置Spring配置文件的地址)
>   * 配置编码过滤器（为了前端提交数据到后端可以识别中文）
>   * Spring的监听器（用来读取Spring的配置文件）
>   * 配置核心控制器（是为了读取SpringMVC的配置文件，也是SpringMVC的入口）
>   * 按需求配置其他配置：日志...
> * 配置MyBatis配置文件
>   * 加载jdbc 、基础设置(日志)、环境(事务和连接池)、插件、映射文件关联配置
>   * 只有基础设置是Spring无法整合的，要自己配置，其他都可以靠Spring来整合，不是必要的。
> * 配置SpringMVC：Spring和SpringMVC是无缝连接的所以它的配置和之前是一样的。
>   * IOC扫描Controller层
>   * 开启注解驱动
>   * 放行静态资源
>   * 视图解析器
>   * 上传组件对象
>   * 拦截器
>   * 静态资源路径映射
> * 配置Spring配置文件
>   * IOC扫描(一般是扫描service层，`注意控制层在SpringMVC中已经扫描过了`，
>     如果添加了过滤器，aop也需要扫描；如果添加了工具类和注解，aop也可以扫描；)
>   * Spring如何做事务
>     * 加载jdbc
>     * 创建数据源连接池
>     * 创建事务管理类
>     * 开启注解事务（或配置声明式事务策略）
>   * Spring整合MyBatis
>     * 整合SqlSessionFactory(要关联数据源，关联MyBatis配置文件，关联映射文件，配置分页插件)
>     * 整合MyBatis中的Mapper接口：会在Spring IOC容器中创建Mapper接口的实现类，如果需要使用，只需要添加@Autowired注解即可。

### 问题记录

> 配置文件中不能有空的bean标签，会报错
>
> 实体类必须有set方法，且set方法必须是public。
>
> 接口不能被bean标签实例化。
>
> Spring如何排错：
> ![image-20240814143107109](D:\Desktop\gitee\java-learning\sc240601\Spring\Img\Spring如何排错.png)
>
> 日期转换错误：
> ![image-20240814144619542](D:\Desktop\gitee\java-learning\sc240601\Spring\Img\日期转换错误.png)
>
> 一个类有多个bean，就不能只传入一个类对象来创建，会报不知道调用哪个bean来创建的错误
> ![image-20240814145954794](D:\Desktop\gitee\java-learning\sc240601\Spring\Img\类有多个bean就不能只传入一个类对象来创建.png)
>
> @Autowired或@Resource自动注入时有多个Bean对象
> ![image-20240814162207814](D:\Desktop\gitee\java-learning\sc240601\Spring\Img\@Autowired自动注入时有多个Bean对.png)
>
> Springmvc的配置文件注意是导入后缀是mvc的
> ![image-20240814171938895](D:\AppData\Typora\typora-user-images\image-20240814171938895.png)
>
> Springmvc的配置文件太容易配置错误了，会报500错误，注意是很长的一段。
>
> ```xml
> <?xml version="1.0" encoding="UTF-8"?>
> <beans xmlns="http://www.springframework.org/schema/beans"
> xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
> xmlns:context="http://www.springframework.org/schema/context"
> xmlns:mvc="http://www.springframework.org/schema/mvc"
> xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd">
> 
> <context:component-scan base-package="com.sc.controller"/>
> <mvc:annotation-driven/>
> <mvc:default-servlet-handler/>
> </beans>
> ```
>
> 扫描不到bean对象：
> 原因是配置文件配置扫描包时没有把目录加入到扫描包的扫描范围里面。
>
> ![image-20240815115040098](D:\Desktop\gitee\java-learning\sc240601\Spring\Img\扫描不到bean对象.png)
>
> <hr>
>
> 配置spring配置文件前一定要在pom.xml导入spring的依赖，不然弹不出提示，而且配对了也会报找不到错误。而且一定要注意不要使用cache为后缀的。
>
> <hr>
>
> 配置德鲁伊连接池时一定要注意驱动包的注入配置：
> 如果我们是通过properties文件来导入，是字符串就一定不能用driver，因为它需要注入一个Driver类。
>
> ![image-20240816122426973](D:\Desktop\gitee\java-learning\sc240601\Spring\Img\配置德鲁伊连接池时一定要注意驱动包的注入配置.png)
>
> ![image-20240816123123475](D:\Desktop\gitee\java-learning\sc240601\Spring\Img\德鲁伊连接池报错.png)
>
> <hr>
>
> webapps\TCP报错通常是web.xml文件没有配置好
> ![image-20240816125402213](D:\Desktop\gitee\java-learning\sc240601\Spring\Img\web.xml文件的错误.png)
>
> <hr>
>
> 找不到图片css等静态资源文件
>
> 就是静态资源路径映射哪里写错了
>
> ![image-20240816142243197](D:\Desktop\gitee\java-learning\sc240601\Spring\Img\找不到图片css等静态资源文件.png)
>
> <hr>
>
> 前后端不分离项目的前端页面乱码问题：
>
> 一定是jsp页面忘记在`第一行`加 ==< %@page contentType="text/html; charset=utf-8" % >==
>
> <hr>
> 空指针异常可能还有什么？
> -- 还有可能是你乱加打印提示。
> 别乱加打印提示，不然会有空指针异常。（没查询到数据）
>
> <hr>
> ![image-20240819101232329](D:\Desktop\gitee\java-learning\sc240601\Spring\Img\IDEA清空缓存.png)
>
> <hr>
>
> ![image-20240821115051556](D:\Desktop\gitee\java-learning\sc240601\Spring\Img\80端口被占用导致服务器无法启动.md)
>
> <hr>
>
> 如果页面一片空白很可能是你的拦截器没有放行资源：
> ![image-20240822004325487](D:\Desktop\gitee\java-learning\sc240601\Spring\Img\网页页面空白的原因.png)
>
> 
>
> 

