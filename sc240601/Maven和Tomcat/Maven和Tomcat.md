## 一、Maven

### 1.什么是Maven

> maven是用来构建和部署项目、管理依赖的工具，它基于项目对象模型(Project Object Model简称pom)，是为了管理整个项目生命周期的 （项目创建---项目使用---项目打包(前三步maven负责 )--->web服务器发布(tomcat负责)）

> 每个maven项目都有一个pom.xml文件，这是用来管理整个maven项目的资源文件，如项目要导入的依赖和maven的插件，是maven项目最重要的文件之一。
>
> ==你觉得maven有什么作用?==
>
> 主要有两个: 
>
> * 管理项目生命周期
>
> * 帮助管理依赖
>
>   创建maven不需要自己手动下载依赖
>   可以配置本地仓库 重复利用jar包
>
>   (网址不好找 版本不好找 下载衣赖如果还关联其他依赖也需要去下载)

> ==C + A + s ：开启IDEAmaven管理界面==
>
> ==S+C+A+s：开启IDEA项目管理界面==

### 2.maven安装和配置

#### 2.0 下载maven

> ```java
> maven不同版本的网址:
> https://archive.apache.org/dist/maven/maven-3
> 选择版本->选择binaries/目录->选择tar.gz结尾的是Linux版本的maven，zip结尾的才是Windows版本的maven.
> ```
>
> ![image-20240803114814083](.\img\下载maven.png)
>
> ==maven4需要高版本的jdk，所以jdkb8一般使用maven3.5==

#### 2.1 maven安装

> 只需要`解压` 一般都免安装的 

#### 2.2 maven配置

> - 配置maven环境变量
>
>   - 在Windows搜索栏搜索环境变量
>   - 在系统变量里添加`maven_home` 和`m2_home`变量，变量的内容:maven解压地址（bin目录的上一级）
>     ![image-20240803115509483](.\img\配置maven的环境变量.png)
>   - 在系统办理中的path变量中追加两个值: `%maven_home%\bin`   和  `%m2_home%\bin`
>   - 测试: cmd输入：`mvn  -version` 测试maven版本
>     ![image-20240803120330492](.\img\cmd中查看maven版本.png)
>
> - 配置maven配置文件：`conf目录 --> settings.xml`(要配置私服的maven可以加一个settings.xml.bak2)
>
>   - 配置本地仓库: 在本地磁盘备份一次 第一次下载对应依赖(jar包)从远程仓库下载到本地仓库  目的是为了第二次使用相同依赖 不用再次下载可以重复利用
>
>     ```xml
>     <!--配置本地仓库位置56行左右-->
>     <localRepository>D:\mavenRepository</localRepository>
>     ```
>
>   - 配置远程仓库: 默认访问国外服务器  由于需要翻墙 网络会有影响
>
>     一般中大型公司都会从远程仓库下载所有依赖 当成远程仓库的镜像
>
>     最后只需要访问公司内部仓库镜像  这样网络不会出现太大的问题
>
>     推荐: 阿里云远程仓库地址
>
>     ```xml
>     <!--166行左右-->
>     <mirrors>
>         <mirror>
>           <id>alimaven</id>
>           <name>aliyun maven</name>
>          <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
>           <mirrorOf>central</mirrorOf>        
>         </mirror>
>       </mirrors>
>     ```
>
> 

### 3.idea集成maven

> 左上角的`File`选项 --> `settings`  -->  搜索maven
>
> ![image-20240803123401139](D:\Desktop\gitee\java-learning\sc240601\Maven和Tomcat\img\在IDEA中集成maven.png)
>
> ==注:通过idea如果打开了新的项目窗口，这些配置会还原，所以上面的步骤要重新做一遍==

### 4.创建maven项目

> 创建maven项目：
>
> 1. Ctrl+shift+alt+s 打开项目管理界面，选择Modules，选择"+"
> 2. 选择Maven , Create from archetype处打上√ 开启模板，选择web模板：maven-archetype-webapp
> 3. 为项目命名
> 4. 别急着next，选择命名界面中的Artifact Coordinates
>    GroupId 组id：控制某公司某部门的项目名（为了区分同名项目，且附带公司和部门信息）[写倒序]
>    注意每一段都不能以数字开头
>    ArtifactId：项目名
>    Version: 版本号
> 5. 一路ok，等待jar包下载，注意这个过程中任何的网络波动都会导致maven项目创建失败
>    成功后会在控制台显示BUILD SUCCESS
>
> * ctrl+shift+alt+s：打开项目管理界面 -> Modules -> "+" -> new Module
>   ![image-20240803124024721](D:\Desktop\gitee\java-learning\sc240601\Maven和Tomcat\img\创建maven项目1.png)
>
> * Maven -> 勾选Create from archetype(选择模板) -> 选择maven-archetype-webapp(web项目模板)
>   ![image-20240803124145072](D:\Desktop\gitee\java-learning\sc240601\Maven和Tomcat\img\创建maven项目2.png)
>
> * 设置项目信息（组id、项目名、版本号……）
>
>   ![image-20240803124552663](D:\Desktop\gitee\java-learning\sc240601\Maven和Tomcat\img\创建maven项目3.png)
>
> * 检测没有问题就点击Finish完成maven项目构建。（完成构建后要下载必要依赖和maven插件，要保证网络良好，不然下载失败要重新创建）
>   ![image-20240803125018507](D:\Desktop\gitee\java-learning\sc240601\Maven和Tomcat\img\创建maven项目4.png)

#### 4.1 IDEA中管理maven项目

> ==C + A + s ：开启IDEAmaven管理界面==
>
> ==S+C+A+s：开启IDEA项目管理界面==

### 5.maven项目目录结构

> <img src="D:\AppData\Typora\typora-user-images\image-20240803125408291.png" alt="image-20240803125408291" style="zoom:50%;" />
>
> - main : 项目核心代码
>   - java:  相当于之前的src目录`.java代码文件必须放在它下面才能成功运行`  ---重点 
>   - resources:  `存储项目配置文件`  如果放在其他目录不识别  ---重点
>   - webapps:  一般前后不分离项目才需要使用的包 `前端目录(页面 css  js img)`
>     - WEB-INF: 这个包里面的内容不能对外共享 (不能通过地址直接访问),只能通过服务器转发访问
>       - web.xml : 是web项目配置文件  默认随着tomcat服务器启动自己加载
>     - index.jsp :  是web项目的默认首页   如果只访问当前项目时 默认进入的页面 
>
> ​    ==注: 哪个是根目录,java和resources和webapps都是根目录,原因在于项目随着服务编译发布他们会变成一个目录==
>
> - test: 项目测试代码       --- 了解
>
>   - java  : 测试相关java源代码   junit测试
>   - resources : 存放测试相关的配置文件
> - pom.xml:  整个maven项目唯一配置文件 用于管理整个项目生命周期 --- 最重要

#### 5.1 标记maven目录

>建好后必须告诉IDEA这些目录的作用，所以要标记他们
>选中目录，右击，选择Mark Directory，选择对应的标记：
>	main-java           标记为  Sources Root
>	main-resources  标记为  Resources Root
>	test-java              标记为  Test Sources Root
>	test-resources     标记为  Test Resources Root
><img src="D:\AppData\Typora\typora-user-images\image-20240803130149167.png" alt="image-20240803130149167" style="zoom: 50%;" />

#### 5.2 标记maven项目的web目录

> 如何将普通项目设置为Web项目(给项目配置webapp目录）？
>
> 1. 给项目添加一个webapp目录
> 2. S + C + A + s 打开项目设置界面 选中项目 -> 右击选择"+ Add" -> 选择Web （给项目添加Web模块）
> 3. 在Deployment Descriptors 和 Web Resource Directories 栏目中选择"+"把刚刚的web目录添加进去
>    （配置Web资源目录和部署描述符）
>
> ![image-20240728164335517](.\img\将普通项目设置为Web项目.png)
>
> 4. 点击右下角的Apply完成设置（应用设置）

#### 5.3 maven项目中的根目录

> maven项目中谁是根目录？
> 		main下的java、resources、webapps编译后会生成一个webapps\WEB-INF\lib目录，这个lib就是根目录，所以他们三个都是根目录。

### 6.pom.xml解析

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- xml的版本信息，一般放在第一行，控制版本和编码-->

<!--xml的根节点：xml的根节点只有一个：即最外层标签只能有一个
    xml早期：可扩展的标记语言，可扩展指的是标签是可以自定义的，所以默认每个标签没有任何意义。
    前期xml应用场景主要用于做数据传递，还可以实现数据库存储（表当成父级标签，字段当成子级标签）
    后期出现了json更适合做数据传递（因为json就是一个字符串，不用像xml要解析一个一个标签）
    后期出现数据库更适合做数据存储（安全，易操作）
    所以现在xml一般被当成配置文件，
    但是xml的自定义标签是没有任何含义的，
    所以每个框架或技术给xml设置了一套标准，给标签赋予了含义，限制了根节点，限制了子节点能放在哪个父节点下，
    这个规则就叫约束，约束主要分两种：
      DTD约束：初级约束，规范没怎么细致
      Schema约束：高级约束，规范更细致，现在框架主要使用的约束
    html：超文本标记语言
-->
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
<!--  组id：公司域名倒叙-->
  <groupId>com.sc.sc240601</groupId>
<!--  项目名-->
  <artifactId>servlet</artifactId>
<!--  版本号：随着项目更新迭代 1.0 1.1 1.12 2.0-->
  <version>1.0-SNAPSHOT</version>
    <!--  打包方式：
            jar: 将项目所有内容打包成一个xxx.jar （一般javaSE项目和springboot项目会使用）
            war: 将项目所有内容打包成一个xxx.war （一般普通的web项目（servlet，SSM）会使用）
            pom: 不是打包的意思，这表示一个聚合项目，一般来说父项目才需要配置成pom，是不会编译的，是让子项目来继承环境，这样子项目就无需再次导入重复的依赖。
    -->
  <packaging>pom</packaging>
<!--IDEA右侧栏点开的Maven界面的名字-->
  <name>servlet Maven Webapp</name>
  <!-- FIXME change it to the project's website -->
<!--  测试网站-->
  <url>http://www.example.com</url>

<!--  设置通用属性：一般会自带编码方式 和 maven编译版本 推荐1.8
      还可以在这里设置项目依赖的通用版本号
        设置方式：
          写一个任意的标签（成对出现）
        使用方式：
          在定义标签的下面，可以使用${标签名} 来获取标签里的内容。
        好处：
          代码重用，方便统一修改（提高可维护性）
-->
  <properties>
    <!--设置编码方式-->
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <!--maven编译版本 推荐1.8-->
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>

<!--  设置依赖：最核心的
      每个<dependency>都表示一个依赖，有几个<dependency>就添加了几个依赖（表示添加了几个jar包）
      依赖如果添加成功，在IDEA右侧的Maven界面的Dependencies栏中可以找到
      要添加jar可以到 网上maven仓库：https://mvnrepository.com/中去找他的<dependency>，赋值到下面，然后在maven界面中刷新
-->
  <dependencies>

    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <!-- score标签：用于指定jar包作用范围
            test：指定jar包测试范围有效 编译和打包时不会使用该依赖
            provided: 框架已经提供时不会使用该依赖 比如jsp和servlet 服务器自带，就不会重复使用
            compile(编译): 默认方式，编译范围有效，打包的时候会使用该依赖
            runtime: 在运行时才会需要的依赖 编译时候不需要使用该依赖
      -->
      <scope>test</scope>
    </dependency>

    <!-- jsp依赖-->
    <dependency>
      <groupId>javax.servlet.jsp</groupId>
      <artifactId>javax.servlet.jsp-api</artifactId>
      <version>2.3.3</version>
      <scope>provided</scope>
    </dependency>

    <!-- servlet依赖 -->
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>4.0.1</version>
      <scope>provided</scope>
    </dependency>


    <!--mysql的依赖，推荐：MySQL Connector Java-->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.17</version>
    </dependency>

    <!--jstl依赖-->
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
    </dependency>

    <!-- spring-webmvc的依赖：
         是springmvc的核心依赖
     -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>5.0.3.RELEASE</version>
    </dependency>

    <!-- com.alibaba/druid的依赖：
          德鲁伊，是阿里巴巴提供的一个依赖，是一个连接池。
     -->
    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid</artifactId>
      <version>1.1.20</version>
    </dependency>

    <!-- mybatis的依赖 -->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.4.5</version>
    </dependency>

    <!--com.github.pagehelper/pagehelper的依赖：
        分页用的一个插件
     -->
    <dependency>
      <groupId>com.github.pagehelper</groupId>
      <artifactId>pagehelper</artifactId>
      <version>4.2.1</version>
    </dependency>

    <!-- slf4j-log4j12的依赖：
          日志插件
     -->
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-log4j12</artifactId>
      <version>1.7.25</version>
      <scope>test</scope>
    </dependency>

  </dependencies>

  <!--构建标签：-->
  <build>
    <!--项目编译后的名字-->
    <finalName>servlet</finalName>
    <!--配置maven插件-->
    <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
      <plugins>
        <plugin>
          <artifactId>maven-clean-plugin</artifactId>
          <version>3.1.0</version>
        </plugin>
        <!-- see http://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_war_packaging -->
        <plugin>
          <artifactId>maven-resources-plugin</artifactId>
          <version>3.0.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.8.0</version>
        </plugin>
        <plugin>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>2.22.1</version>
        </plugin>
        <plugin>
          <artifactId>maven-war-plugin</artifactId>
          <version>3.2.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-install-plugin</artifactId>
          <version>2.5.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-deploy-plugin</artifactId>
          <version>2.8.2</version>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>

```

#### 6.1 score标签：用于指定jar包作用范围

>score标签：用于指定jar包作用范围
>            test：指定jar包测试范围有效 编译和打包时不会使用该依赖
>            provided：以提供时不会使用该依赖 比如jsp和servlet 服务器自带，不需要使用
>            compile：`默认方式`，编译范围有效，打包的时候会使用该依赖
>            runtime：在运行时才会需要的依赖 编译时候
>
>S+C+A+s打开项目结构管理：在Dependencies界面中可以看到导入的各依赖和其作用范围：
>![image-20240803131322047](D:\Desktop\gitee\java-learning\sc240601\Maven和Tomcat\img\查看项目导入的依赖和其作用范围.png)

#### 6.2 为什么我下一个依赖会附带这么多其他依赖？

> 因为我们下载的只是一个子项目，所以我们还需要把它的父项目下载下来，父项目中的其他依赖就是和它有关联的依赖。
> ![image-20240803131904238](D:\Desktop\gitee\java-learning\sc240601\Maven和Tomcat\img\导入依赖时还会一起导入相关依赖.png)

#### 6.3如何在仓库中找到一个依赖？
> 通过依赖的组id也就是<groupID>标签每一级就是一个文件夹，一直按这个找到和项目名相同的jar包就是导入的依赖了。

### 7.maven项目导入依赖出现错误如何解决

> - file->settiings->搜索maven  查看maven配置是否正确(版本，配置文件，本地仓库)
> - 查看pom.xml 里面的标签是否写对了  <!--   //  /*   (语法，标签位置  注释)
> - 查看本地仓库 指定jar包是否下载 成功 （可能由于网络延迟 或者波动 导致下载依赖没有下载完 下载一半 ） 如果没有成功直接删除这个jar包 重新下载

### 8.maven常用命令

> <img src="D:\AppData\Typora\typora-user-images\image-20240803132030299.png" alt="image-20240803132030299" style="zoom:50%;" />
>
> maven界面--->Lifecycle--->命令-->双击运行
>
> - clean: 清理项目,会将项目编译后的内容删除(target目录)，maven有可能会有缓存  如果编译了 可能不会每次都重新编译   会导致代码更新运行后还是更新之前效果。不使用maven的clean命令，直接删除targey目录的效果是一样的。(千万不要把jsp文件写到target中，clean后都没了，三次啊)
>
> - validate: 验证项目是否正确,如果错误 后期编译 后者打包都会出现问题
>
> - compile: 编译项目  生成一个target目录 里面存放项目中所有编译后的内容
>
>   java resources webapps这三个包的内容会同时放在target目录里面
>
> - test: 运行测试代码
>
> - package: 将项目进行打包（pom.xml  packaging标签配置决定） 会在target目录下生成一个XXX.war
>   （扩：main下面的java和resources目录下的文件编译后都直接放在target\classes下）
>
>   默认先编译 后打包
>
> - install:  安装命令，将网上下载好的jar 安装到本地仓库去
>
> - ....

## 二、Tomcat

### 1.什么是Tomcat

> tomcat是一个开源的 轻量级的 web服务器,目前在中小型企业被广泛使用  tomcat默认支持1000个左右的并发量，后期可以去搭建tomcat服务器集群目前是java开发的首选     常用web服务器:  tomcat     apache   weblogic .

### 2. Tomcat目录结构

> tomcat安装 :  只需要解压即可  不用配置环境变量   
>
> - bin:  存放一些可执行文件   *.bat文件 和 *.sh文件比较重要。 
>
>   *.bat文件 ：给Windows系统使用的\.sh文件 ： 给Linux 和 Unix系统使用的 （所以tamcat支持Windows和Linux系统）其中开启和关闭tomcat的文件又是最重要的：
>
>   * 开启服务器：startup.bat\startup.sh(可以点开startup.bat文件看看会不会闪退，会闪退就表示java的环境变量没有配置好。)
>   * 关闭服务器：shutdown.bat\shutdown.sh
>     
>
> - `conf`:  存放tomcat配置文件目录 .properties 和*.xml文件非常重要。
>   其中server.xml文件 和web.xml文件比较重要。
>
>   * server.xml文件 : 修改编码方式 和 tamcat服务器的端口号（默认为8080)
>
>     ```xml
>     如何修改端口号？
>     用记事本打开server.xml文件，找到根节点中的<Connector port=……>标签(70行左右)，注意是没有注释的那个标签。
>     <Connector port="8080" protocol="HTTP/1.1"
>                    connectionTimeout="20000"
>                    redirectPort="8443" />
>     ```
>
>   * web.xml文件: 修改tomcat全局配置 比如：修改会话的默认时间(600行左右)，默认为30
>     ![image-20240803133700257](D:\Desktop\gitee\java-learning\sc240601\Maven和Tomcat\img\修改session会话时间.png)
>
> - `webapps`:  最重要的目录  存储部署的项目   war包存放的位置     tomcat启动后 自动解压war包  自动部署项目  
>
> - lib:  存放tomcat自己运行需要依赖的环境  
>
> - `logs`:  日志文件的包   记录服务器运行的过程  方便后期维护 或者调错 
>
> - temp:   存放临时文件
>
> - work:  存放项目中jsp编译后的内容(servlet) 和 一些缓存内容

### 3.tomcat项目部署过程

> - war包:  将idea打好的war包(通过maven的packing选项打包)放入到`tomcat/webapps目录`下，使用`startup.bat`启动tomcat服务器就会自动部署。最后如果服务器启动没有报错   就可以通过网址(url)来测试：
>   `http://localhost:tomcat的端口号/项目名[/index.jsp]`
>   ![image-20240803144652483](D:\Desktop\gitee\java-learning\sc240601\Maven和Tomcat\img\在Tomcat中部署war包项目.png)
> - jar包:   部署方式和上面一样，然后linux系统中或者windows中使用cmd 输入命令`java -jar xxx.jar`来启动如果服务器启动没有报错   就可以通过网址(url)来测试：`http://localhost:tomcat的端口号/项目名[/index.jsp]`

#### 3.1 部署在Tomcat服务器的项目如何省略网址

> * 省略项目名：删除webapps目录下的 项目目录和ROOT目录删除，然后把项目的war包重命名为ROOT.war就可以省略项目名。
>
> * 省略端口号：把服务器端口号改写成80，那么写网址的时候端口号也可以省略。
>
> * 省略协议头：浏览器会自动添加http协议所以 http:// 也可以省略。
>
>   完成以上步骤，通过网址访问项目的时候就可以只写一个localhost。
>
>   这样设置省略的好处在于：后期如果申请了域名，我们直接用域名来代替IP地址直接访问部署在服务器上的项目，也就是为什么百度等网站使用域名www.baidu.com就能访问百度搜索项目的原因。

### 4.idea如何集成tomcat

#### 4.1 IDEA中的Tomcat是本地Tomcat的镜像副本

> `IDEA中的Tomcat是本地Tomcat的镜像副本`，对其的设置进行修改并不会影响本地的Tomcat。在C盘下的用户数据文件夹的JetBrains公司文件夹下的IDEA文件夹的tomcat文件下的XXXXX加密文件夹下的server.xml中（C:\Users\wang'ya'lin\AppData\Local\JetBrains\IntelliJIdea2020.3\tomcat\XXXXXXX\server.xml）就可以看到这镜像副本的配置文件，就可以看到其修改的设置。

#### 4.2 IDEA集成Tomcat服务器

> 右上角点击TomcatXX -> 选择Edit Configurations -> 选择Tomcat Server -> 选择"+"添加Tomcat服务器 ->
> 选择Tomcat Server（注意是Tomcat Server不是TomEE Server） -> 选择Local(Local是本地  Remote是云服务器)  -> 选择Server页面：
>
> 1. Configure... 找到服务器文件位置
> 2. Name 表示服务器名，可修改
> 2. URL 表示启动服务器后默认访问的页面
> 4. HTTP port 表示IDEA自带的Tomcat临时服务器的端口号 和 Cof目录Server.xml中配置的端口号没关系。
> C:\Users\wang'ya'lin\AppData\Local\JetBrains\IntelliJIdea2020.3\tomcat下的server.xml中就可以看到这个HTTP port设置的端口号
> 5. VM options: 表示虚拟机参数，"-Dfile.encoding=UTF-8"可设置虚拟机编码方式
>
> <img src=".\img\IDEA集成Tomcat.png" alt="image-20240728163000296" style="zoom:50%;" />
>
> 还要在Deployment界面中
> 1. 选择 "+" 选择 Artifact 添加项目（将一个前端项目部署到Tomcat服务器，必须是包含了前端的项目），选择 exploded后缀的选项(==exploded后缀表示已经展开的项目==，不像war后缀项目一样表示项目要解压后才能部署，所以它：
>
>    - 部署速度更快，因为不需要解压WAR文件。
>    - 能进行热部署，即在不重启服务器的情况下更新应用程序。)
>
>    ![image-20240731212429411](.\img\exploded表示已展开的项目.png)
>
> 2. 在Application context中 设置项目前缀（设置为"/" 在网页访问项目时就不需要带上一串前缀了）
>
> 完成后返回Server页面
> 6. On 'Update' action 和 On frame deactivation 表示前端代码修改时是否要通过重启服务器来更新
>     两个都设置为update classes and resources 就是前端热部署即不重启服务器，直接刷新网页就可以完成更新。

#### 4.3 如何将普通项目设置为Web项目(给项目配置webapp目录）？

> 1. 给项目添加一个webapp目录
> 2. S + C + A + s 打开项目设置界面 选中项目 -> 右击选择"+ Add" -> 选择Web （给项目添加Web模块）
> 3. 在Deployment Descriptors 和 Web Resource Directories 栏目中选择"+"把刚刚的web目录添加进去
> （配置Web资源目录和部署描述符）
>
> ![image-20240728164335517](.\img\将普通项目设置为Web项目.png)
>
> 4. 点击右下角的Apply完成设置（应用设置）

#### 4.2 IDEA乱码问题

##### a.  解决控制台打印信息乱码问题

> 法一：
> Setting -> Editor -> General -> Console -> Default Encoding 改成和系统一样的编码方式
>
> 法二：
>
> 或是修改idea.exe.vmoptions文件（在IDEA的bin目录下） 加一句：-Dfile.encoding=UTF-8(有弊端)

##### b. 解决前端页面中文乱码问题

> 在前端jsp页面的开头加一句：<%@page contentType="text/html; charset=utf-8" %>
>
> 因为不设置的话，jsp页面的默认编码方式为 IOS--8859-1(欧洲编码方式)

##### c. Tomcat服务器乱码问题

> IDEA控制台: Tomcat localhost Log  和 IDEA: Tomcat catalina Log乱码问题：
> 打开本地tomcat包的conf\loggin.properties文件：
>
> ==IDEA: Tomcat localhost Log乱码改这里==
>
> 1catalina.org.apache.juli.AsyncFileHandler.level = FINE
> 1catalina.org.apache.juli.AsyncFileHandler.directory = ${catalina.base}/logs
> 1catalina.org.apache.juli.AsyncFileHandler.prefix = catalina.
> 1catalina.org.apache.juli.AsyncFileHandler.encoding = GBK
>
> ==IDEA: Tomcat catalina Log乱码改这里==
>
> 2localhost.org.apache.juli.AsyncFileHandler.level = FINE
> 2localhost.org.apache.juli.AsyncFileHandler.directory = ${catalina.base}/logs
> 2localhost.org.apache.juli.AsyncFileHandler.prefix = localhost.
> 2localhost.org.apache.juli.AsyncFileHandler.encoding = GBK
>
> 3manager.org.apache.juli.AsyncFileHandler.level = FINE
> 3manager.org.apache.juli.AsyncFileHandler.directory = ${catalina.base}/logs
> 3manager.org.apache.juli.AsyncFileHandler.prefix = manager.
> 3manager.org.apache.juli.AsyncFileHandler.encoding = GBK
>
> 4host-manager.org.apache.juli.AsyncFileHandler.level = FINE
> 4host-manager.org.apache.juli.AsyncFileHandler.directory = ${catalina.base}/logs
> 4host-manager.org.apache.juli.AsyncFileHandler.prefix = host-manager.
> 4host-manager.org.apache.juli.AsyncFileHandler.encoding = UTF-8
>
> ==IDEA: 服务器乱码改这里==
>
> java.util.logging.ConsoleHandler.level = FINE
> java.util.logging.ConsoleHandler.formatter = org.apache.juli.OneLineFormatter
> java.util.logging.ConsoleHandler.encoding = UTF-8

##### d.IDEA无法输出中文标点符号的原因
> 因为在idea.exe.vmoptions文件中加了-Dfile.encoding=UTF-8，就无法在IDEA输入中文标点