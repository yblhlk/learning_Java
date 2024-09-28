## Mybatis

### 0. 谈谈你对mybatis框架的理解

> 是什么？
> ![image-20240928210103091](D:\Desktop\MyBatis\img\对框架的理解.png)
>
> 为什么？--好处
> 怎么用？

> > 怎么学框架？ -- 看官网[MyBatis 中文网 官网 (p2hp.com)](https://mybatis.p2hp.com/)
>
> > 是用来替代dao层，还是用来替换工具层的JDBC工具类呢？

### 1. 什么是Mybatis框架？

> MyBatis是==基于sql开发的ORM==，是一个持久层框架，其内部封装了jdbc，它能使开发者只关注于sql语句本身不用像jdbc一样反复的编写繁琐的 加载驱动 创建连接  参数处理 结果集处理等操作，mybatis全部都处理好了 其前身是ibatis(底层包名字还是它)

#### 1.1 ORM

> ORM(Object Relation Mapping)：对象关系映射
> 对象--java中的对象
> 关系--指和数据库中的数据之间的关系
> 映射--java中的对象和数据库中的数据之间的映射
>
> ORM就是实现java中的对象和关系型数据库中的数据之间的映射，这样就可以把对于数据库中数据的操作转换为对于java中对象的操作，比如：新增一条数据，就可以通过新增一个对象来实现。

> 如何实现ORM：对象关系映射
>
> 1. 类名 --> 表名对应
> 2. 类中的属性名 --> 表中字段名
>
> 这个过程 通过MyBatis映射文件来实现。（MyBatis-Plus通过注解来实现）

#### 1.2 持久状态 和 临时状态

> 持久状态(保存在磁盘或数据库中) 和 临时状态(保存在内存中，关闭程序就消失)
> 持久层就是为了将数据长久的保存起来，==数据的持久化泛指的就是把数据存储在数据库中==，所以持久化操作就是增删改查操作(CRUD操作：CRUD是四个基本操作的缩写，分别代表Create(创建)、Read(读取)、Update(更新)和Delete(删除)）

### 2.搭建MyBatis环境

> * 创建`maven项目` : MyBatis是基于Maven的项目
> * 在pom.xml中导入相关依赖（mybatis、mybatis-ehcache、ehcache、maven-plugin-api、mybatis-generator-core）
> * 创建表和实体类（注意实体类中`一定要有无参构造和set方法`，mabtis用他们来操作实体类）
> * 创建MyBatis核心配置文件。（加载jdbc参数、创建连接池、事务(有两种)、关联映射文件、设置插件、设置日志）
> * 通过反向生成插件generator来生成实体类、映射文件、mapper接口。
>   * 原来的dao层改成mapper层 为每张表提供一个对应的Mapper接口(不用实现类)
>   * 为每个Mapper接口提供一个映射文件。接口中的每个方法和规定的 操作标签 联系起来（如新增操作要用到insert语句就使用<insert>标签。用标签的id属性来联系。（==在里面写sql语句，sql一定不能加;==)
> * 在核心配置文件中添加映射文件的配置(mappers 下加 mapper)
> * 搭建完毕，可以开始测试MyBatis工作流程。

#### 搭建mybatis环境需要在web.xml中配置吗？

> 搭建MyBatis环境本身并不需要在`web.xml`中进行配置。MyBatis是一个持久层框架(`和前端无关`)，主要负责与数据库的交互，其配置通常是通过MyBatis的配置文件（如`mybatis-config.xml`）来完成的，而不是`web.xml`。
> 搞清楚web.xml是干什么用的。`web.xml`文件是Java Web应用程序中的一个核心配置文件，它主要用于定义和配置Web应用程序中的各种组件和行为。要配置的都是和前端有关的操作。

#### 2.1 相关依赖

> ```xml
>  <properties>
>     <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
>     <!--maven项目的编译版本，就是按哪个版本的Java语法规则来编译代码，在C+A+s中可以看到，推荐用1.8的语法规则-->
>     <maven.compiler.source>1.8</maven.compiler.source>
>     <maven.compiler.target>1.8</maven.compiler.target>
>     <mybatis.version>3.4.5</mybatis.version>
>     <junit.version>4.11</junit.version>
>     <ehcache.version>2.10.4</ehcache.version>
>     <pagehelper.version>4.2.1</pagehelper.version>
>     <mybatis-ehcache.version>1.0.0</mybatis-ehcache.version>
>     <mybatis-generator-core.version>1.3.5</mybatis-generator-core.version>
>     <maven-plugin-api.version>3.5.0</maven-plugin-api.version>
>     <mybatis.spring.version>1.3.1</mybatis.spring.version>
>   </properties>
> 
>   <dependencies>
>     <dependency>
>       <groupId>junit</groupId>
>       <artifactId>junit</artifactId>
>       <version>${junit.version}</version>
>       <scope>test</scope>
>     </dependency>
>     <!--mysql驱动包     换成自己对于版本-->
>     <!--mysql的依赖，推荐：MySQL Connector Java-->
>     <dependency>
>       <groupId>mysql</groupId>
>       <artifactId>mysql-connector-java</artifactId>
>       <version>8.0.17</version>
>     </dependency>
> 
>     <!-- Mybatis -->
>     <dependency>
>       <groupId>org.mybatis</groupId>
>       <artifactId>mybatis</artifactId>
>       <version>${mybatis.version}</version>
>     </dependency>
> 
>     <!--mybatis缓存-->
>     <dependency>
>       <groupId>org.mybatis</groupId>
>       <artifactId>mybatis-ehcache</artifactId>
>       <version>${mybatis-ehcache.version}</version>
>     </dependency>
>     <!-- 缓存 -->
>     <dependency>
>       <groupId>net.sf.ehcache</groupId>
>       <artifactId>ehcache</artifactId>
>       <version>${ehcache.version}</version>
>     </dependency>
> 
>     <!-- mybatis反向建模 -->
>     <dependency>
>       <groupId>org.apache.maven</groupId>
>       <artifactId>maven-plugin-api</artifactId>
>       <version>${maven-plugin-api.version}</version>
>     </dependency>
>     <dependency>
>       <groupId>org.mybatis.generator</groupId>
>       <artifactId>mybatis-generator-core</artifactId>
>       <version>${mybatis-generator-core.version}</version>
>     </dependency>
> 
>   </dependencies>
> 
>   <build>
>     <finalName>mybatis</finalName>
> 	<!--maven的mybatis代码生成插件-->
>     <plugins>
>       <plugin>
>         <groupId>org.mybatis.generator</groupId>
>         <artifactId>mybatis-generator-maven-plugin</artifactId>
>         <version>1.3.5</version>
>         <configuration>
>           <verbose>true</verbose>
>           <overwrite>true</overwrite>
>         </configuration>
>       </plugin>
>     </plugins>
>   </build>
> 
> </project>
> ```

#### 2.2 MyBatis核心配置文件

> MyBatis的核心配置文件关联映射文件有三种方式：
> 具体体现在mappers标签下的mapper标签的三个可选属性：
>
> * class：表示通过注解实现映射文件
> * resource：资源在项目的资源目录中实现的映射文件
> * url：网络请求，访问网络上的映射文件
>
> 我们一般是使用resource属性来关联映射文件，所以要在资源目录下创建一个mapper包，专门用来存放映射文件。

> ````xml
> <?xml version="1.0" encoding="UTF-8" ?>
> <!DOCTYPE configuration
>      PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
>      "http://mybatis.org/dtd/mybatis-3-config.dtd">
> <!--MyBatis的核心配置文件，必须要自己导入上面的约束-->
> <configuration>
>  <!--引入jdbc.properties配置文件，成功后通过EL语法${key}来取文件中的值-->
>  <properties resource="jdbc.properties"/>
>     
>  <!--配置数据库环境-->
>  <!--可以通过environment标签配置多个环境，但哪个环境生效要看主标签中的default属性-->
>  <environments default="mysql">
>      <environment id="mysql">
>          <!--事务管理方式：mybaits主要有两种事务管理策略：
>              a. JDBC(注意是全大写):底层利用jdbc方式做事务，默认是自动提交
>              b. MANAGED：mybatis自身不做事务，让其他容器来实现，如spring容器
>          -->
>          <transactionManager type="JDBC"/>
>          <!--数据源：推荐使用连接池pooled-->
>          <dataSource type="pooled">
>              <property name="driver" value="${driver}"/> <!--还是需要一个properties配置文件-->
>              <property name="url" value="${url}"/>
>              <property name="username" value="${username}"/>
>              <property name="password" value="${password}"/>
>          </dataSource>
>      </environment>
>      <environment id="oracle">
>          <transactionManager type="JDBC"/>
>          <!--数据源：推荐使用连接池pooled-->
>          <dataSource type="pooled">
>           <!--数据源的配置-->
>          </dataSource>
>      </environment>
>  </environments>
> 
>  <!--可选参数：-->
>  <!--数据库环境：-->
>  <!--关联映射文件：-->
>  <mappers>
>      <!--mapper标签有三个可选属性：
>          class：表示通过注解实现映射文件
>          resource：资源在项目的资源目录中实现的映射文件
>          url：网络请求，访问网络上的映射文件
>          -->
>      <mapper resource="mapper/StudentMapper.xml"/>
>      <!--没有将mapper的映射文件整合在一个包下时，每个mapper的映射文件都需要一个配置-->
>  </mappers>
> </configuration>
> ````

#### 2.3 mapper接口和对应的映射文件

>a. mapper接口
>
>```java
>// mapper层就是原来的Dao层
>// 就是原来的StudentDao
>// mapper层每个接口都有一个对应的xml映射文件
>public interface StudentMapper {
>	public int add(Student s);
>	public List<Student> show();
>}
>```
>
>b. 映射文件
>
>> * mapper接口和映射文件如何对应？
>>   通过mapper标签的namespace属性，里面写全类名。
>> * mapper接口中的方法和映射文件如何对应？
>>   通过增删改查标签的id属性。
>>   ![image-20240927121653378](D:\Desktop\MyBatis\img\mapper接口和映射文件.png)
>> * 获取mapper接口中参数传递的值：#{} 或 ${}
>> * 查询语句的结果集如何处理？不用处理通过，resultType或resultMap设置返回值类型，mybatis会自动返回改类型的集合。
>> * 增删改查标签中的sql语句一定不要写;
>
>```xml
><!-- 1.由于IDEA没有集成MyBatis，所以没办法标记MyBatis的配置文件，要我们自己导入xml约束-->
><?xml version="1.0" encoding="UTF-8" ?>
><!DOCTYPE mapper
>PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
>"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
>
><!-- 2.通过namespace属性和mapper层接口一一对应 -->
><mapper namespace="com.sc.mapper.StudentMapper">
>    <!--新增操作就使用insert标签-->
>    <insert id="add"> <!--id属性和mapper层中的方法对应-->
>    	insert into student values(null, #{sname},#{ssex},#{sbirthday},#{classname})
>    	<!--这里的sql语句中一定不能加分号-->
>    	<!--#{}名字和类中的属性名对应，而不是和表中的字段名对应-->
>    </insert>
>    <!--查询操作就使用select标签-->
>    <select id="show" resultType="com.sc.pojo.Student"> <!--resultType属性表示结果的数据类型-->
>    	select * from student
>        <!--这里的sql语句中一定不能加分号-->
>    </select>
></mapper>
>
><!--没有映射文件的标记，所以不会随着项目的加载而加载，使用时我们还要写一个MyBatis的核心配置文件-->
><!--把mybatis的映射文件统一放在resources/mapper目录下-->
>```
>
>==由于每个mapper接口都需要一个对应的实体类，所以可以把映射文件统一放在resources/mapper目录下。==

### 3. MyBatis的工作流程

> mapper接口、映射文件.xml、核心配置文件.xml
>
> * ==加载MyBatis的核心配置文件（会关联很多的映射文件）==
> * 使用SqlSessionFactoryBuilder创建SqlSessionFactory对象，只需要创建一个
> * 通过SqlSessionFactory创建SqlSession(操作mybatis核心对象、做事务也是依赖于这个SqlSession对象来完成的)（一个SqlSession对象处理一个事务）
> * ==通过SqlSession对象动态创建Mapper接口的实现类对象（底层是jdk动态代理）==
> * ==通过mapper对象执行持久化操作(CRUD)==
> * 通过SqlSession对象提交或者回滚事务（==只有真正的提交了事务才会发送sql语句执行，否则不会修改数据库，查询不修改不进行提交没关系，但是修改不提交就没有任何效果。==）
> * 关闭资源SqlSession。(如果配置的是连接池，则是回收资源)

#### 3.0 mybatis什么时候创建mapper接口的实现类？

> MyBatis 在第一次调用 `SqlSession.getMapper()` 方法获取 `mapper` 接口实例时，会动态创建该接口的实现类。这里的关键步骤概括如下：
>
> 1. **定义 Mapper 接口**: 你定义一个接口，里面包含了一些方法，这些方法对应着 SQL 映射。
>
> 2. **配置映射文件**: 你通过 XML 文件或注解方式提供 SQL 语句和接口方法的映射。
>
> 3. **获取 SqlSession**: 在你的应用程序中，你通过 `SqlSessionFactory` 获得 `SqlSession` 的实例。
>
> 4. **调用 getMapper() 方法**: 当你==首次调用== `SqlSession.getMapper(Class<T> type)` 方法时，MyBatis 内部使用 Java 的动态代理机制来创建一个实现了你指定接口的动态代理实例。
>   - **动态代理**: MyBatis 通过 `java.lang.reflect.Proxy` 生成一个动态代理，这个代理会实现你的 mapper 接口。
>    - **方法调用拦截**: 当你通过代理实例调用接口中的方法时，实际上是调用了一个拦截器（`MapperProxy`），这个拦截器会根据方法名去查找对应的 SQL 映射并执行它。
>
> 5. **缓存**: 为了提高性能，MyBatis 会缓存这些代理实例，这意味着动态代理实例只在第一次请求时创建，之后会从缓存中获取。
>
> 因此，可以说 MyBatis 在你第一次请求某个 mapper 接口的代理时创建这个接口的动态代理实例。这种代理实例化的方式允许 MyBatis 在运行时根据接口方法的调用动态地执行 SQL，从而实现了接口的功能而无需实现类。

#### 3.1 实例

> 1
>
> ```java
> public void first() throws IOException {
>        //1.加载核心配置文件 注意Resources对象是ibatis包下的
>        InputStream is = Resources.getResourceAsStream("mybatis.xml");
>        //2.创建session工厂
>        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
>        //3.创建session(不是会话HttpSession，是SqlSession)
>        SqlSession session = sf.openSession();
>        //4.通过session动态创建Mapper接口的实现类
>        StudentMapper mapper = session.getMapper(StudentMapper.class);
>        //5.持久化操作
>        List<Student> list = mapper.show();
>        System.out.println(list);
>        //6.session提交或回滚事务
>        //注意MyBatis不会自动提交事务
>        session.commit(); //一定要提交才会将内存中的数据提交到数据库中，
>        //session.rollback();
>        //7.关闭资源
>        session.close();
> }
> ```
>

#### 3.2 封装MyBatis工具类

> 前4步可以封装在一起，传入一个Mapper接口的全类名来创建Mapper接口实现对象。
>
> 后两步可以封装在一起，用来提交事务和关闭连接。

### 4. MyBatis反向生成工具

> 反向生成工具只要配置好了，就可以根据数据库表，动态生成实体类(pojo)，Mapper
> 接口(mapper),映射文件，并且映射文件和mapper接口还可以帮你生成一些常用增删改查语句，其他需要自定义编写。

#### 4.1 导入maven的mybatis代码生成插件

> ```xml
> 导入maven的mybatis代码生成插件：注意不要放在了<pluginManagement>里面。
>     <plugins>
>       <!--maven的mybatis代码生成插件-->
>       <plugin>
>         <groupId>org.mybatis.generator</groupId>
>         <artifactId>mybatis-generator-maven-plugin</artifactId>
>         <version>1.3.5</version>
>         <configuration>
>           <verbose>true</verbose>
>           <overwrite>true</overwrite>
>         </configuration>
>       </plugin>
>     </plugins>
> ```
>
> <img src="D:\Desktop\MyBatis\img\MyBatis反向生成工具.png" alt="img" style="zoom:50%;" />

#### 4.2 导入反向生成工具的配置文件：generator-config.xml

> ```xml
> <?xml version="1.0" encoding="UTF-8" ?>  
> <!DOCTYPE generatorConfiguration PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN" "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd" >
> <!--导入mybatis的约束和语法标签-->
> <generatorConfiguration>
> 	<!--加载jdbc.properties配置文件-->
> 	<properties resource="jdbc.properties" />
> 	<!--配置驱动jar包的位置-->
> 	<classPathEntry location="${driverClassPath}" /> <!--jdbc的配置文件要加一个driverClassPath-->
> 	<!--
>         context:生成一组对象的环境
>         id:必选，上下文id，用于在生成错误时提示
>         defaultModelType:指定生成对象的样式
>             1，conditional：类似hierarchical；
>             2，flat：所有内容（主键，blob）等全部生成在一个对象中；
>             3，hierarchical：主键生成一个XXKey对象(key class)，Blob等单独生成一个对象，其他简单属性在一个对象中(record class)
>         targetRuntime:
>             1，MyBatis3：默认的值，生成基于MyBatis3.x以上版本的内容，包括XXXBySample；
>             2，MyBatis3Simple：类似MyBatis3，只是不生成XXXBySample；
>         introspectedColumnImpl：类全限定名，用于扩展MBG
>     -->
> 	<context id="context1" targetRuntime="MyBatis3">
> 
> 		<!-- genenat entity时,生成toString -->
> 		<plugin type="org.mybatis.generator.plugins.ToStringPlugin"/>
> 		<!-- generate entity时，生成serialVersionUID -->
> 		<plugin type="org.mybatis.generator.plugins.SerializablePlugin"/>
> 		<!--不生成注释-->
> 		<commentGenerator>
> 			<property name="suppressAllComments" value="true" /> <!--表示不生成注释-->
> 		</commentGenerator>
> 
> 		<!--配置数据库连接信息-->
> 		<jdbcConnection driverClass="${jdbc.driver}"
> 			connectionURL="${jdbc.url}" userId="${jdbc.username}" password="${jdbc.password}" />
> 		<!-- java模型创建器，是必须要的元素
>             负责：1，key类（见context的defaultModelType）；2，java类；3，查询类
>             targetPackage：生成的类要放的包，真实的包受enableSubPackages属性控制；
>             targetProject：目标项目，指定一个存在的目录下，生成的内容会放到指定目录中，如果目录不存在，MBG不会自动建目录
>          -->
> 		<!--表示生成实体类的目录的位置-->
> 		<javaModelGenerator targetPackage="com.sc.pojo"
> 			targetProject="src/main/java">
> 			<!-- 设置是否在getter方法中，对String类型字段调用trim()方法 -->
> 			<property name="trimStrings" value="true" />
> 		</javaModelGenerator>
> 		<!-- 生成SQL map的XML文件生成器，
>                     注意，在Mybatis3之后，我们可以使用mapper.xml文件+Mapper接口（或者不用mapper接口），
>                         或者只使用Mapper接口+Annotation，所以，如果 javaClientGenerator配置中配置了需要生成XML的话，这个元素就必须配置
>                     targetPackage/targetProject:同javaModelGenerator
>                  -->
> 		<!--设置包含了所有实体类映射文件的包的位置-->
> 		<sqlMapGenerator targetPackage="mapper"
> 			targetProject="src/main/resources"></sqlMapGenerator>
> 
> 		<!-- 对于mybatis来说，即生成Mapper接口，注意，如果没有配置该元素，那么默认不会生成Mapper接口
>             targetPackage/targetProject:同javaModelGenerator
>             type：选择怎么生成mapper接口（在MyBatis3/MyBatis3Simple下）：
>                 1，ANNOTATEDMAPPER：会生成使用Mapper接口+Annotation的方式创建（SQL生成在annotation中），不会生成对应的XML；
>                 2，MIXEDMAPPER：使用混合配置，会生成Mapper接口，并适当添加合适的Annotation，但是XML会生成在XML中；
>                 3，XMLMAPPER：会生成Mapper接口，接口完全依赖XML；
>             注意，如果context是MyBatis3Simple：只支持ANNOTATEDMAPPER和XMLMAPPER
>         -->
> 		<!--设置包含了所有mapper接口的包的位置-->
> 		<javaClientGenerator targetPackage="com.sc.mapper"
> 			targetProject="src/main/java" type="XMLMAPPER" />
> 
> 		<!-- 需要逆向 enableCountByExample="false" enableUpdateByExample="false"
> 		  enableDeleteByExample="false" enableSelectByExample="false" selectByExampleQueryId="false"
> 		 -->
> 		<!--设置表名：每一个table表示一张表，会根据这个表生成 实体类、映射文件、mapper接口
> 			存在bug: 已经生成的表就从table标签中删除掉，
> 			不然会重新生成实体类和mapper接口直接覆盖掉，如果自己之前添加了内容，全没了。
> 				实体类: 会还原 自己添加的内容就没了
> 				mapper接口: 会还原 自定义功能没了
> 				映射文件:会追加 映射文件的标签会追加一遍 会报错
> 			如果下面有不存在的表，这个插件就会报错，对项目没有影响，其他表也不会生成。
> 		-->
> 		<table tableName="huser" enableCountByExample="false" enableUpdateByExample="false" enableDeleteByExample="false"
> 			   enableSelectByExample="false" selectByExampleQueryId="false">
> 			<!--<columnOverride column="ID" javaType="java.lang.Integer"></columnOverride>
> 			<columnOverride column="dept" javaType="java.lang.Integer"></columnOverride>-->
> 		</table>
> 		<table tableName="hdept" enableCountByExample="false" enableUpdateByExample="false" enableDeleteByExample="false"
> 			   enableSelectByExample="false" selectByExampleQueryId="false">
> 			<!--<columnOverride column="ID" javaType="java.lang.Integer"></columnOverride>
> 			<columnOverride column="dept" javaType="java.lang.Integer"></columnOverride>-->
> 		</table>
> 		<table tableName="huserinfo" enableCountByExample="false" enableUpdateByExample="false" enableDeleteByExample="false"
> 			   enableSelectByExample="false" selectByExampleQueryId="false">
> 			<!--<columnOverride column="ID" javaType="java.lang.Integer"></columnOverride>
> 			<columnOverride column="dept" javaType="java.lang.Integer"></columnOverride>-->
> 		</table>
> 
> 	</context>
> </generatorConfiguration>  
> ```

##### a. 小心使用table标签

> 在使用反向生成的插件时一定要小心，要确保：
> ==已经生成实体类和mapper和映射文件的表就从table标签中删除掉==，
> 			不然会重新生成实体类和mapper接口直接覆盖掉，如果自己之前添加了内容，全没了。
> 				实体类: 会还原 自己添加的内容就没了
> 				mapper接口: 会还原 自定义功能没了
> 				映射文件:会追加 映射文件的标签会追加一遍 会报错
> 			如果下面有不存在的表，这个插件就会报错，对项目没有影响，其他表也不会生成。

#### 4.3 更新好数据库驱动地址

> 在properties配置文件中通过`driverClassPath`添加数据库驱动包的地址（mysql-connector.jar)
>
> ```properties
> jdbc.driver=com.mysql.cj.jdbc.Driver
> jdbc.url=jdbc:mysql://localhost:3306/sc240601?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&rewriteBatchedStatement=true&serverTimezone=Asia/Shanghai
> jdbc.username=root
> jdbc.password=123456
> #不使用username，而使用jdbc.username的原因是：以后SSM框架username读的是本地计算机的用户名
> #在使用jdbc.properties引入到spring中的时候，用户名不能用username，因为${}会优先去电脑的环境中找用户名，也就是会被自己电脑的用户名覆盖掉
> #反向生成工具的generator使用的，是提供本地数据看驱动包地址
> driverClassPath=D://javajar//mysql//mysql-connector-java//8.0.17//mysql-connector-java-8.0.17.jar
> 
> ```

#### 4.4 在maven插件处使用反向生成工具

> <img src="D:\Desktop\MyBatis\img\MyBatis反向生成工具.png" alt="img" style="zoom:50%;" />

### 5.映射文件的介绍

> 每一个mapper接口都有一个对应的映射文件，接口中的每个方法在映射文件中都有一个增删改查标签来处理，通过标签的id和方法对应来进行映射。
>
> ```xml
> <?xml version="1.0" encoding="UTF-8"?>
> <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
> <!--mapper标签：根节点，使用namespace属性来配置全类名 用于和Mapper接口一一对应-->
> <mapper namespace="com.sc.mapper.HuserMapper">
>       <!--resultMap标签：用于实现ORM，也可以用于实现复杂的关联查询
>         id属性：resultMap的唯一标识 resultMap可以配置多个，用id来标识区分
>         type属性：表示当前这个表对应的是哪个实体类
> 
>         子标签id：表示主键列，mybatis推荐每个表推荐主键列
>         子标签result：表示其他列标签
>             column属性：表示查询语句的查询字段，设置了的查询字段才能使用，否则查不出来
>             property属性：表示类中的属性名
>             jdbcType：表示表中字段的类型，可以省略
>         -->
>       <resultMap id="BaseResultMap" type="com.sc.pojo.Huser">
>            <id column="id" jdbcType="INTEGER" property="id" />
>            <result column="username" jdbcType="VARCHAR" property="username" />
>            <result column="password" jdbcType="VARCHAR" property="password" />
>            <result column="createtime" jdbcType="DATE" property="createtime" />
>            <result column="did" jdbcType="INTEGER" property="did" />
>       </resultMap>
>       <!--sql标签：用于定义sql语句可以重用的片段，也可以配置多个
>               下面的所以操作标签都可以通过include标签来引用
>   -->
>       <sql id="Base_Column_List">
>            id, username, password, createtime, did
>       </sql>
>       <sql id="myBaseSql">
>            id, username, password
>       </sql>
>       <!--增删改查操作标签 都必须通过id和mapper接口中的方法对应，必须对应，不对应就会报错-->
>       <!--select标签：
>       parameterType="java.lang.Integer" 表示mapper接口方法的参数类型，可以省略
>       resultType：表示自动映射、只要查询字段名或别名和类中属性一样，就可以自动映射 就可以自动给属性赋值，反之如果不同就是null
>       resultMap：自定义表中属性和类中成员的映射关系，这样查询字段和属性是否一样都可以查询出来，它还可以复杂关联查询，select标签必须显示指定resultType和resultMap中的一个不然会报错。
> 
>       #{id} : 通过预编译 占位符 可以防止sql注入
>       ${id} : 非预编译 字符串拼接  有注入风险
>   -->
>       <select id="selectByPrimaryKey" parameterType="java.lang.Integer" resultMap="BaseResultMap">
>            select 
>            <include refid="Base_Column_List" />
>            from huser
>            where id = #{id}
>       </select>
> 
>       <delete id="deleteByPrimaryKey">
>            delete from huser where id = #{id}
>       </delete>
>       <!--全列新增：所有字段都添加数据-->
>       <insert id="insert" parameterType="com.sc.pojo.Huser">
>            insert into huser values (#{id}, #{username}, #{password}, #{createtime}, #{did})
>       </insert>
>       <!--动态新增：根据传入的参数，如果指定列 有值 就插入，否则不插入
>       最终拼成 inser into 表(字段1，字段2) values (值1，值2)-->
>       <insert id="insertSelective" parameterType="com.sc.pojo.Huser">
>            insert into huser
>            <trim prefix="(" suffix=")" suffixOverrides=","> <!--去除后缀的,-->
>                <if test="id != null">
>                    id,
>                </if>
>                <if test="username != null">
>                    username,
>                </if>
>                <if test="password != null">
>                    password,
>                </if>
>                <if test="createtime != null">
>                    createtime,
>                </if>
>                <if test="did != null">
>                    did,
>                </if>
>            </trim>
>            <trim prefix="values (" suffix=")" suffixOverrides=",">
>                <if test="id != null">
>                    #{id,jdbcType=INTEGER},
>                </if>
>                <if test="username != null">
>                    #{username,jdbcType=VARCHAR},
>                </if>
>                <if test="password != null">
>                    #{password,jdbcType=VARCHAR},
>                </if>
>                <if test="createtime != null">
>                    #{createtime,jdbcType=DATE},
>                </if>
>                <if test="did != null">
>                    #{did,jdbcType=INTEGER},
>                </if>
>            </trim>
>       </insert>
>       <!--动态更新：根据参数的属性 是否有值 来动态更新对应的属性
>       update 表名 set 字段=?, 字段2=? where id = ?
>   -->
>       <update id="updateByPrimaryKeySelective" parameterType="com.sc.pojo.Huser">
>            update huser
>            <set> <!--会自动生成最后一个,-->
>                <if test="username != null">
>                    username = #{username},
>                </if>
>                <if test="password != null">
>                    password = #{password},
>                </if>
>                <if test="createtime != null">
>                    createtime = #{createtime},
>                </if>
>                <if test="did != null">
>                    did = #{did},
>                </if>
>            </set>
>            where id = #{id}
>       </update>
> 
>       <!--全列更新-->
>       <update id="updateByPrimaryKey" parameterType="com.sc.pojo.Huser">
>            update huser set username = #{username},
>            password = #{password},
>            createtime = #{createtime},
>            did = #{did}
>            where id = #{id}
>       </update>
> </mapper>
> ```

### 6. MyBatis中Mapper接口参数传递

> 映射文件中的sql语句如何获取Mapper接口中的参数保存的值？
>
> * 传递一个参数：#{随便写}，只有一个参数的时候可以随便写，因为就一个参数怎么写都是它自己。
>
>   ```java
>   //1.传递一个参数
>   List<Huser> selectByUsername(String username);
>   ```
>
>   ```xml
>     <select id="selectByUsername" resultType="com.sc.pojo.Huser">
>       select
>       <include refid="Base_Column_List"/>
>       from huser
>       where username=#{可以随便写} <!--因为接口中的对应方法只有一个形参，所以可以随便写-->
>     </select>
>   ```
>
> * 传递对象参数：#{成员属性名} 直接获取对象中的成员属性值
>
>   ```java
>   //3.传递对象参数：#{成员属性名} 直接获取对象中的成员属性值，但是加了别名就不能直接用成员属性名直接取出来了要用#{对象别名.成员属性名}
>   int addUser3(Huser u);
>   ```
>
>   ```xml
>     <insert id="addUser3">
>       insert into huser
>       values(null,#{username},#{password},null,#{did})<!--但是加了别名就不能成员属性名直接取出来了-->
>     </insert>
>   ```
>
> * 传递多个参数：==必须要添加注解@Param("别名")，映射文件只识别别名==
>
>   ```java
>    //2.传递多个参数(必须要在参数前加@Param("别名")注解来给参数取别名)
>    int addUser1(@Param("a") String username, @Param("b") String password);
>    int addUser2(@Param("un") String username,
>                 @Param("user") Huser u,
>                 @Param("date") Date time);
>   ```
>
>   ```xml
>      <insert id="addUser1">
>        insert into huser
>        values(null,#{a},#{b},null,null)
>      </insert>
>      <insert id="addUser2">
>        insert into huser
>        values(null,#{un},#{user.password},#{date},#{user.did})
>      </insert>
>   ```
>
> * 传递集合或数组：批量删除和批量新增……
>
>   a. 传递数组
>
>   ```java
>   //a. 传递数组模拟批量删除
>   int batchDel(Integer[] ids);
>   ```
>
>   ```xml
>     <!--批量删除是用in-->
>     <!--foreach属性介绍：
>         collection: 指定遍历的数组或集合 一般写 list,array或多个参数时自定义
>         item: 临时变量，就是每次遍历出来的数据
>         separator: 指定遍历每个元素后添加的间隔符
>         open:  指定遍历开始时要在开头加上的内容，一般是"("
>         close: 指定遍历结束时要在结尾加上的内容，一般是")"
>     -->
>     <delete id="batchDel">
>       delete from huser
>       where id in
>       <foreach collection="array" item="id"
>                separator="," open="(" close=")">
>           #{id}
>       </foreach>
>     </delete>
>   ```
>
>   b. 传递集合
>
>   ```java
>   //b. 传递集合模拟批量新增
>   int batchAdd(List<Huser> users);
>   ```
>
>   ```xml
>     <insert id="batchAdd">
>       insert into huser values
>       <foreach collection="list" item="u" separator=",">
>         (null,#{u.username},#{u.password},#{u.createtime},#{u.did})
>       </foreach>
>     </insert>
>   ```
>
>   ```java
>       //调用mapper接口的实现类进行测试
>   	@Test
>       public void testBatchAdd(){
>           HuserMapper mapper = MyBatisUtil.getMapper(HuserMapper.class);
>           List<Huser> list = new ArrayList<>();
>           list.add(new Huser(null,"aaa","123",new Date(), 1));
>           list.add(new Huser(null,"bbb","123",new Date(), 2));
>           list.add(new Huser(null,"ccc","123",new Date(), 3));
>           mapper.batchAdd(list);
>           MyBatisUtil.close();
>       }
>   
>       @Test
>       public void testBatchDel(){
>           HuserMapper mapper = MyBatisUtil.getMapper(HuserMapper.class);
>           Integer[] ids = {13,14,15};
>           mapper.batchDel(ids);
>           MyBatisUtil.close();
>       }
>   ```
>
> * 传递Map集合：类似于对象参数使用#{key}获取value，一般一组不相关的数据不适合封装到一个对象中，可以封装到Map集合中去。
>
>   ```java
>   //5.传递Map集合：
>   List<Huser> selectByMap(Map m);
>   ```
>
>   ```xml
>     <select id="selectByMap" resultType="com.sc.pojo.Huser">
>       select <include refid="Base_Column_List"/>
>       from huser
>       where password=${pw}
>       limit ${begin},${length}
>     </select>
>   ```

#### 6.1 Mapper接口如何传递多个参数给映射文件？

> * 添加@Param注解来给参数起别名，映射文件中使用#{别名}来获取
> * 添加对象参数，把一组`有关联`的数据封装成一个对象，映射文件直接用#{属性名}来获取
> * 添加map集合，把一组`不相关`的数据添加到map集合中，映射文件使用#{key}获取value



### 7. 映射文件中resultType 和 resultMap的区别？

#### 7.0 是什么？

> resultType 和 resultMap 是映射文件中`select标签`的必选属性(必须写其中的一个或两个一起写，都生效）。
>
> ==`resultType`和`resultMap`都是用于将数据库`查询结果`映射到Java对象的。==
>
> 在MyBatis的映射文件中，select标签如果不指定`resultType`和`resultMap`，**映射文件将不会默认使用任何一种映射方式**而且会报错。这是因为`resultType`和`resultMap`是MyBatis用于将数据库查询结果映射到Java对象的关键属性，它们都需要在映射语句（`<select>`）中显式指定。

#### 7.1 区别

> `resultTyper`：自动映射，当`查询的字段`和实体类中的属性名一样时才会自动映射或自动赋值，如果不一样属性或查询结果就是null
>
> * 解决方案：
>   * 给sql语句自动添加和属性名相同的别名，表中字段的别名和属性一致，也可以映射。
>   * 可以使用resultMap 它可以自定义映射关系

> `resultMap`: 属于可以根据用户需要自定义查询字段和属性的关系 所以无论名称是否一致都可以映射，还可以实现复杂多表关联査询。
>
> * 开发阶段: 推荐使用resultMap，因为==resultMap可以处理实体类和表中字段名不一样的情况。==

#### 7.2 实例

> 数据库字段：sanme、ssex、sbirthday、class
> 实体类属性：sanme、ssex、sbirthday、classname(表的字段名是java中的关键字只能换别的名称)
> 映射文件：
>
> ```xml
> <mapper namespace="com.sc.mapper.StudentMapper">
>     <!--先要定义一个resultMap(结果集合映射关系)：来对应mapper方法的形参和表中的字段-->
>     <!--resultMap标签：用于实现ORM，也可以哟关于实现复杂的关联查询
>         	id属性  ：resultMap的唯一标识 resultMap可以配置多个，用id来标识区分
>         	type属性：表示当前这个表对应的是哪个类
> 
>         子标签id    ：表示主键列，mybatis推荐每个表推荐主键列。
>         子标签result：表示其他列标签
>              column属性  ：表示查询语句的查询字段，设置了的查询字段才能使用，否则查不出来
>              property属性：表示类中的属性名
>              jdbcType属性：表示表中字段的类型，可以省略
>         -->
>     <resultMap id="myMap" type="com.sc.pojo.Student">
>         <id column="sname" property="sname"/>
>         <result column="ssex" property="ssex"/>
>         <result column="sbirthday" property="sbirthday"/>
>         <result column="class" property="classname"/>
>     </resultMap>
> 
>     <select id="show" resultMap="myMap" resultType="com.sc.pojo.Student"> 
>         <!--两个属性可以一起写,都生效-->
>         <!--resultMap 通过id来生效，
> 			resultType 通过实体类的全类名来生效 -->
>         select * from student <!--这里的sql语句中一定不能加分号-->
>     </select>
> </mapper>
> 
> ```

### 8.#{} 和 ${} 的区别

> * ${} 底层是Statement实现 采用字符串替换的方式 处理参数 存在sql注入的隐患。==它通常用于处理表名、字段名、表中关键字等不需要预编译的内容==
> * #{} 底层Preparedstatement实现 采用预编译的方式 先编译sql语句 一次编译可以多次执行 参数是通过?做占位符 可以防止sql注入 执行效率会稍微高一些。

### 9.==MyBatis的动态sql语句==是如何实现的

> 动态sql语句：能根据传入的参数`动态的匹配查询的条件`，如传入name就会根据name进行查询，没传入就进行全查询。

> mybatis动态sql语句通过类似于c:forEach 和c:if标签 根据mapper接口传递的参数不同 会动态生成不同的sql语句 面试官的问题就是问你 有哪些常用标签可以实现这种动态sql语句。

#### 9.1 映射文件中动态sql语句常用标签

> 动态sql语句标签是`增删改查标签`的子标签。
>
> * if: 可以根据属性是否满足条件 来拼接sql语句
>
>   ```xml
>   User对象 有一个un属性
>   <if test = "un!=null"> <!--判断属性是否为null-->
>   	#{un} <!--取值-->
>   </if>
>   ```
>
> * set: 只能用于`更新语句` ；用于自动添加set关键字， 能动态去除`最后一个","`(已经实验只能去除末尾的","所以","不能写在条件前面)
>
>   ```xml
>   <!--动态更新-->
>   <update id="updateSelective1">
>       update student
>       <set> <!--动态添加set关键字 还能去除末尾的","-->
>           <if test="sname != null">sname=#{sname},</if>
>           <if test="ssex != null">ssex=#{ssex},</if>
>           <if test="sbirthday != null">sbirthday=#{sbirthday},</if>
>           <if test="classname != null">class=#{classname},</if>
>       </set>
>       where sno=#{sno}
>   </update>
>   ```
>
> * where: 一般用于 删除 更新 查询 用于在查询语句中添加where关键字和查询条件，实现where 1=1，同时where关键字还能去除前面的and。（已经实验只能去除第一个and，所以and只能写在前面）
>
>   ```xml
>   <sql id="myBase">
>       sno,sname,ssex,sbirthday,class classname
>   </sql>
>   <!-- 实现动态搜索（模糊查询）-->
>   <select id="querySelective1" resultMap="myMap">
>       select <include refid="myBase"></include>
>       from student
>       <where> <!-- 能够实现jdbc类似于where 1=1 帮我们添加where 还可以动态去除add-->
>           <if test="sname != null">
>               <!-- 可以对实体类的sname属性值 做二次处理生成新的变量-->
>               <bind name="newName" value="'%'+sname +'%'"/>
>               and sname like #{newName}
>           </if>
>           <if test="ssex != null">
>               and ssex = #{ssex}
>           </if>
>           <if test="sbirthday != null">
>               and sbirthday = #{sbirthday}
>           </if>
>           <if test="classname != null">
>               and class = #{classname}
>           </if>
>       </where>
>   </select>
>   ```
>
> * trim：适用于任何sql语句功能 非常强大 内部已经结合了set和where的功能 
>   也可以去除多余","和"and"（已实验，prefixOverrides属性只能删除第一个多余的"," 和 "and"，suffixOverrides只能删除最后一个多余的"," 和 "and"）
>   trim适合处理需要拼接带前缀后缀的情况。（如加一个括号前后缀）
>
>   ```xml
>   trim可以用来替代：set和where标签
>   <select id="querySelective2" resultMap="myMap">
>       select <include refid="myBase"></include>
>       from student
>       <trim prefix="where" prefixOverrides="and"> <!--加一个where前缀，不加后缀-->
>           <if test="sname != null">
>               <!-- 可以对实体类的sname属性值 做二次处理生成新的变量-->
>               <bind name="newName" value="'%'+sname +'%'"/>
>               and sname like #{newName}
>           </if>
>           <if test="ssex != null">
>               and ssex = #{ssex}
>           </if>
>           <if test="sbirthday != null">
>               and sbirthday = #{sbirthday}
>           </if>
>           <if test="classname != null">
>               and class = #{classname}
>           </if>
>       </trim>
>   </select>
>   ```
>
> * forEach: 用于遍历参数 是集合或者数组得
>
>   ```xml
>   <!--批量删除是用in-->
>   <!--foreach属性介绍：
>         collection: 指定遍历的数组或集合 一般写 list,array或多个参数时自定义
>         item: 临时变量，就是每次遍历出来的数据
>         separator: 指定遍历每个元素后添加的间隔符
>         open:  指定遍历开始时要在开头加上的内容，一般是"("
>         close: 指定遍历结束时要在结尾加上的内容，一般是")"
>     -->
>   <delete id="batchDel">
>       delete from huser
>       where id in
>       <foreach collection="array" item="id"
>                separator="," open="(" close=")">
>           #{id}
>       </foreach>
>   </delete>
>   ```
>
> * bind: 用于对传递的参数 做二次处理的 (拼接%) 通常用于模糊查询
>
>   ```xml
>       <!-- 实现动态搜索（模糊查询）-->
>       <select id="querySelective1" resultMap="myMap">
>           select <include refid="myBase"></include>
>           from student
>           <where> <!-- 能够实现jdbc类似于where 1=1 帮我们添加where 还可以动态去除add-->
>               <if test="sname != null">
>                   <!-- 可以对实体类的sname属性值 做二次处理生成新的变量-->
>                   <bind name="newName" value="'%'+sname +'%'"/>
>                   and sname like #{newName}
>               </if>
>           </where>
>       </select>
>   ```
>   
> * `choose`: 多分枝的if-else要搭配`when`一起使用
>
>   ```xml
>   //使用choose做时间范围查询
>   <choose>
>       <when test="beginTime != null and endTime != null">
>           and time between #{beginTime} and #{endTime}
>       </when>
>       <!--&gt; 表示 大于是转义字符-->
>       <!--&ge; 表示 大于等于是转义字符，但是在Mybatis中不能用-->
>       <when test="beginTime != null">
>           and time &gt;= #{beginTime}
>       </when>
>       <when test="endTime != null">
>           and time &lt;= #{endTime}
>       </when>
>   </choose>
>   ```
>
>   

> MyBatis 的 `<trim>` 元素是一个非常有用的功能，它主要用于动态地生成 SQL 语句的一部分，特别是用于处理前缀、后缀以及多余的逗号或分隔符。使用 `<trim>` 元素，你可以很容易地定制你的 SQL 语句，使其更加灵活和动态。
>
> `<trim>` 元素有几个重要的属性：
>
> - `prefix`：如果 `trim` 标签内包含内容，则会给内容加上前缀。
> - `suffix`：如果 `trim` 标签内包含内容，则会给内容加上后缀。
> - `prefixOverrides`：用于去除内容前面的特定字符，例如去除多余的逗号。
> - `suffixOverrides`：用于去除内容后面的特定字符。
>
> 一个常见的使用场景是在插入语句中动态地包含列名和值。比如，你可能只想更新某些字段，而不是表中的所有字段。使用 `<trim>`，你可以很容易地构建这样的 SQL 语句，同时避免语法错误，如多余的逗号。
>
> 示例：
>
> ```xml
> <insert id="insertSelective" parameterType="com.example.User">
>   INSERT INTO user
>   <trim prefix="(" suffix=")" suffixOverrides=",">
>     <if test="id != null">
>       id,
>     </if>
>     <if test="name != null">
>       name,
>     </if>
>     <if test="email != null">
>       email,
>     </if>
>   </trim>
>   <trim prefix="VALUES (" suffix=")" suffixOverrides=",">
>     <if test="id != null">
>       #{id,jdbcType=BIGINT},
>     </if>
>     <if test="name != null">
>       #{name,jdbcType=VARCHAR},
>     </if>
>     <if test="email != null">
>       #{email,jdbcType=VARCHAR},
>     </if>
>   </trim>
> </insert>
> ```
>
> 在这个例子中，`<trim>` 元素被用来动态地生成列名和对应的值的列表，同时确保不会有多余的逗号。`prefix` 和 `suffix` 属性分别用于添加括号，而 `suffixOverrides` 属性用于去除最后一个逗号，从而避免 SQL 语法错误。

### ==10. 关联映射==

#### 10.1 为什么需要关联映射？

>如果mybatis查询的数据涉及到了多张表，就比较难控制。

#### 10.2 有哪些关联映射？

> * 一对一关联：用户表和用户详情表是一对一的。班主任表和班级表。
> * 一对多关联：班级表和学生表就是一对多的。（因为每个班级可以包含多个学生。）
> * 多对一关联：用户表和部门表就是多对一的。（因为多个用户都可以加入一个部门。）
> * 多对多关联：学生表和老师表就是多对多的、用户和权限。（因为一个学生可以有很多老师，一个老师可以有很多学生。）

#### 10.3 实现关联映射的前提

> * `表`和表的关系要维护好
>   * 1:1：一个表的主键 和 另一个表的主键是一一对应的（让两个id相等）
>   * 1:n：两个表 必须存在 主外键关联（如果数据存储合理也可以不建）
>   * n:1：两个表 必须存在 主外键关联（如果数据存储合理也可以不建）
>   * n:n：多建立一个关系表(`多对多的情况必须要建立关系表`) 两个表之间 是通过关系表来维护的 关系表保存两个表的外键。
>   
> * `类`和类的关系要维护好
>   * 1:1 : 用户类添加一个关联属性：用户信息对象
>   * 1:n : 部门类 添加属性：用户集合
>   * n:1 : 用户类添加一个关联属性：部门对象。==（x:1 都是给实体类加一个关联属性`对象`）==
>   * n:n : 老师类 添加属性：学生集合。==（x:n 都是给实体类加一个关联属性`集合`)==
>   
> * 映射文件：mybatis如何实现关联映射
>   实现关联映射有两种方式：
>
>   * 第一种：可以通过多表连接，`一次性查询`完全部数据，就可以给多个类保存。
>
>     ```xml
>     <!--描述对象-->
>     <association property="关联属性名" javaType="关联属性类型">
>         <id column="?" property="?"/>
>         <result column="?" property= "?"/>
>         …… 
>         <!--如果是1:1:1，即三表一对一关联，就可以在这里继续嵌套</association>-->
>     </association>
>                                                                         
>     <!-- 描述集合 -->
>     <collection property="关联的属性名" ofType="集合泛型">
>         <id column="?" property="?"/>
>         <result column="?" property= "?"/>
>         ……
>     </collection>
>     			                                                                     
>     ```
>
>     
>
>   * 第二种：可以分`两次查询`，调用其他mapper接口写好的方法来给关联属性赋值。
>
>     ```xml
>     <!--描述对象-->
>     <association property="关联属性名" 
>                  column="其他mapper接口需要的参数" 
>                  select="其他mapper接口的全类名+方法名"/>
>                                                                         
>     <!-- 描述集合 -->
>     <collection  property="关联属性名" 
>                  ofType="集合泛型"
>                  column="其他mapper接口需要的参数" 
>                  select="其他mapper接口的全类名+方法名"/>
>     ```

> 实现关联映射(关联查询)的步骤：
>
> 1. 改变实体类，给实体类加上关联属性 和 它的getset方法
> 2. 在mapper接口中创建对应方法
> 3. 在映射文件中配置对应的关系映射标签<resultMap>
> 4. 在关系映射标签<resultMap>中添加子标签<association>:描述对象，<collection>:描述集合，来引入要关联查询的对象。
> 5. 在select标签中通过resultMap属性引入resultMap标签。（只能用reultMap属性）

### 11. Mybatis缓存

#### 11.1 为什么需要缓存？

> ==提高`查询`效率==缓存是用于查询的。
>
> 缓存类似于系统中的内存，访问数据库mysql类似于访问本地磁盘,缓存访问速度一定是高于访问mysql的速度，mybatis提供一种缓存机制,当第一次查询数据时，不会先查询数据库 而是先查看缓存是否存在 如果有直接返回 这样就不走数据库了，如果缓存中没有 才会发送sql语句查询数据库 并且还会再缓存中保存一份 为了方便下一次使用。（存在bug：如果缓存后修改了数据呢？）
>
> ==注：如果其他人修改了数据，mybatis会清空缓存，防止脏读==

#### 11.2 mybatis提供的两种缓存机制

> * 一级缓存：是SqlSession范围，`默认开启，无需多余配置`，可以在同一个SqlSession中生效，==对数据做增删改会自动清空缓存，防止脏读。==
> * 二级缓存：==是Mapper接口级别的缓存==，它可以在不同的SqlSession中共享，`它是默认关闭，需要手动配置`，==只有一级缓存没了才会使用二级缓存，即一级缓存被清空了，才会从二级缓存中找数据。==
>
> 开启二级缓存后，一二级缓存中都会保存一份缓存。

#### 11.3 配置一二级缓存

> * 关闭一级缓存
>   ==在核心配置文件中使用`setting标签`关闭一级缓存==
>
>   ```xml
>   <!--在mybatis核心配置文件中-->
>   <!--开启全局缓存-->
>   <setting>
>       <!--默认为true，关闭后一二级缓存都会失效-->
>   	<setting name="cacheEnabled" value="false"></setting>
>   </setting>
>   ```
>
>   
>
> * 在对应mapper接口的映射文件中开启二级缓存 
>   ==在mapper接口对应的映射文件中使用`cache标签`开启二级缓存==
>
> ```xml
> <cache flushInterval="600000" readOnly="true" eviction="LRU" size="512"/>
> ```

#### 11.4 实例

##### a. 测试一级缓存

```java
public class TestMybatis {
    @Test
    public void testOneCache(){
        HuserMapper mapper = MyBatisUtil.getMapper(HuserMapper.class);
        // 第一次查询 先访问mysql 存储到缓存中 发送一次sql语句
        Huser u = mapper.selectByPrimaryKey(18);
        System.out.println(u);

        // 第二次查询 缓存一级有了 不走mysql
        // 开启日志后从控制台何以观察到没有缓存。
        // 没有配置过，所以缓存是默认开启的，不用配置。
        u = mapper.selectByPrimaryKey(18);
        System.out.println(u);

        //如果修改了数据 为了防止脏读 会清空缓存
        u.setPassword("11111");
        mapper.updateByPrimaryKey(u);

        //再次查询发现缓存没了，只能再次查询mysql, 发送一条sql语句
        // 开启日志后，从控制台可以看到查询了两次。
        u = mapper.selectByPrimaryKey(18);
        System.out.println(u);
        MyBatisUtil.close(); // 已经提交关闭了，是新的mapper

        //重新获取SqlSession
        mapper = MyBatisUtil.getMapper(HuserMapper.class);
        // 它是新的sqlSession得到的结果，不能和之前的sqlSession的缓存
        //使用重新查询mysql 会发送一次sql语句
        u = mapper.selectByPrimaryKey(18);
        System.out.println(u);
    }
```

##### b. 测试二级缓存

```java
@Test
    public void testTwoCache(){
        //开了二级缓存 可以在不同的SqlSession共享（因为是基于mapper的，所以mapper是不能更换的）
        // 只有一级缓存没了 才会使用二级
        HuserMapper mapper = MyBatisUtil.getMapper(HuserMapper.class);
        //第一次查 会查mysql 默认存一级缓存，二级缓存只要开启也会存储
        Huser u = mapper.selectByPrimaryKey(18);
        System.out.println(u);
        //一级缓存 存在 不走MySql
        u = mapper.selectByPrimaryKey(18);
        System.out.println(u);
        MyBatisUtil.close(); // 一级缓存基于SqlSession被回收了， 没了

        mapper = MyBatisUtil.getMapper(HuserMapper.class);
        //这个时候一级没了，才开始走二级缓存 这个时候也不走mysql
        u = mapper.selectByPrimaryKey(18);
        System.out.println(u);

        //2. 修改数据后，一级和二级都会清空缓存
        u.setPassword("33333");
        mapper.updateByPrimaryKey(u);
        MyBatisUtil.close(); //再关闭一次 为了测试二级缓存是否会清空

        mapper=MyBatisUtil.getMapper(HuserMapper.class);
        // 由于前面更新了 二级缓存也清空了
        // 再查相同的数据 也只能访问mysql
        u = mapper.selectByPrimaryKey(5);
        MyBatisUtil.close();
    }
}
```

### 12. Mybatis的悲观锁和乐观锁

#### 12.1 什么是乐观锁和悲观锁

> * 乐观锁：给表添加一个字段（版本号(int)或时间戳(timestap)）两种实现方式都差不多，就是类型不同。原理就是每次更新数据时，先查询一遍版本号，等用户使用完数据了，提交事务之前，再匹配一下版本号。
>   如果前后结果一致，说明其他人没有修改过，说明其他人没有修改过，就可以提交事务，然后更新版本号。
>   如果两次匹配结果不一致，说明其他人修改过，则不能提交事务。
> * 悲观锁：表的悲观锁是由数据库实现出来的，不是java程序。原理就是在查询时，添加for update 来锁住表中的数据，这样其他用户就无法操作这些加锁的数据。只有提交了数据才会释放锁，这样其他用户可以操作这些数据，类似于synchronized同步锁。
>
> ==乐观锁效率高，悲观锁更安全。==

#### 12.2 实现乐观锁 的 更新操作

> 测试 表+版本字段 实现的乐观锁
>
> ```sql
> 键表时要加上一个版本字段
> -- 测试锁
> create table testLock(
>     id int primary key  auto_increment,
>     name varchar(100),
>     version int -- 添加一个锁字段来实现乐观锁
> )
> ```
>
> ```Java
> 在mapper接口中写一个乐观锁 的 更新操作
> 	// 实现乐观锁 的 更新操作
>     int updateLock(Testlock testlock);
> ```
>
> ```xml
> 在映射文件中要手动编写版本的比较和变更
> 	<!--springboot框架由一个专门的配置类，自动处理版本号的问题-->
>     <!--如果只有mybatis 只能手动编写版本的比较和变更-->
>     <update id="updateLock">
>         update testlock
>         set name=#{name},version=version+1
>         where id=#{id} and version=#{version}
>     </update>
> ```
>
> ```xml
> 在mybatis核心文件添加，关联映射文件
> <mapper resource="mapper/TestlockMapper.xml"/>
> ```
>
> ```java
> 测试类测试：
> 	@Test
>     public void testHappyLock1() throws InterruptedException {
>         TestlockMapper mapper = MyBatisUtil.getMapper(TestlockMapper.class);
>         //先查询一遍数据 记录版本号
>         Testlock lock = mapper.selectByPrimaryKey(1);
>         Thread.sleep(10000);
>         //更新的时候 要先查询版本号是否一致 如果一致才能提交事务
>         lock.setName("zhangsan1111111");
>         mapper.updateLock(lock);
>         MyBatisUtil.close();
>     }
>     @Test
>     public void testHappyLock2() throws InterruptedException {
>         TestlockMapper mapper = MyBatisUtil.getMapper(TestlockMapper.class);
>         //先查询一遍数据 记录版本号
>         Testlock lock = mapper.selectByPrimaryKey(2);
>         //更新的时候 要先查询版本号是否一致 如果一致才能提交事务
>         lock.setName("zhangsan22222222");
>         mapper.updateLock(lock);
>         MyBatisUtil.close();
>     }
> ```

#### 12.3 实现悲观锁 的 查询操作

> 查询时加上for update实现行级的悲观锁
>
> ```
> 在mapper接口中实现悲观锁 的 查询操作
>     Testlock selectForUpdate(Integer id);
> ```
>
> ```xml
> 在映射文件中：
> 查询时加上for update：
>     <select id="selectForUpdate" resultMap="BaseResultMap">
>         select <include refid="Base_Column_List"/>
>         from testlock
>         where id=#{id} for update
>     </select>
> <!--FOR UPDATE：这是SQL语句的一部分，用于在SELECT语句中指定数据库应该对选定的行加上排他锁（exclusive lock）。排他锁意味着在当前事务完成之前，其他事务不能对这些行进行更新或删除操作。这对于确保数据的一致性和防止并发更新冲突至关重要。-->
> ```
>
> ```java
> 测试悲观锁:
>     @Test
>     public void testNotHappyLock1() throws InterruptedException {
>         TestlockMapper mapper = MyBatisUtil.getMapper(TestlockMapper.class);
>         //先查询一遍数据 是为了给数据加锁
>         Testlock lock = mapper.selectForUpdate(1);
>         Thread.sleep(10000);
>         lock.setName("111111");
>         mapper.updateLock(lock); // 虽然该方法底层有id条件和 version条件
>         MyBatisUtil.close();//提交事务是否了数据的锁，其他用户才能操作
>     }
>     @Test
>     public void testNotHappyLock2() throws InterruptedException {
>         TestlockMapper mapper = MyBatisUtil.getMapper(TestlockMapper.class);
>         //先查询一遍数据 是为了给数据加锁
>         Testlock lock = mapper.selectForUpdate(1);
>         Thread.sleep(3000);
>         lock.setName("222222");
>         mapper.updateLock(lock); // 虽然该方法底层有id条件和 version条件
>         MyBatisUtil.close();//提交事务是否了数据的锁，其他用户才能操作
>     }
> ```

### 13. Mapper接口的工作原理

> 1.mapper接口的原理？
> 2.mapper接口能重载嘛?
> 3.mybatis主要使用了几种拦截器?

#### 13.0 什么是mapper接口

> Mapper接口就是原来的dao接口，是根据映射文件中的namespace属性 对应哪个mapper接口 Mappe接口的方法对应的是映射文件中标签id。

#### 13.1 工作原理

> 先根据Mapper接口全类名+方法名作为key值，用于快速定位是哪个映射文件中的哪个标签，
> 底层通过jdk动态代理方式，运行时会给每一个Mapper接口创建一个Proxy(代理对象)，
> 会拦截Mapper接口的方法，拦截之后帮你动态调用对应映射文件的对应标签，就可以执行sql语句，
> 最终执行结束后，运行结果也会通过拦截器，按照你方法提供的返回值进行封装。

#### 13.2 提供的几种拦截器

>  ==拦截器的实现原理都是AOP面向切面编程==
>
>  * `3` 执行器拦截器：拦截sql语句，拦截下来后由它来执行sql语句，还可以做事务，用来实现mybatis缓存机制
>
>  * `1` 参数拦截器：拦截传给mapper接口的实参，目的是用来做参数的转换。(转化为表能接受的类型)
>                                但此时还没有替换sql中的？
>  * `4` 结果集拦截器：用来拦截查询结果 处理结果封装到返回值中。
>  * `2`sql语句构建拦截器：用来修改sql语句的，把sql语句中的#{}替换为?占位符，${}则是不进行预处理直接替换。

> ### MyBatis中的拦截器类型及功能
>
> 1. **SQL语句构建拦截器**（或称为**SQL解析/修改拦截器**）：
>    - 主要用于在SQL语句执行前对其进行解析和修改。
>    - 如您所述，它可以将`#{}`占位符替换为JDBC的`?`占位符，并将`${}`占位符的内容直接拼接到SQL语句中。
>    - 注意：直接替换`${}`占位符时要特别注意SQL注入的风险。
>
> 2. **参数拦截器**（或称为**参数处理器**）：
>    - 拦截传递给Mapper接口方法的参数。
>    - 可以用于参数的预处理、转换或验证。
>    - 但通常不会在这个阶段替换SQL中的占位符，因为占位符的替换通常是在SQL语句准备阶段完成的。
>
> 3. **执行器拦截器**（或称为**执行器插件**）：
>    - 拦截SQL语句的执行过程。
>    - 可以用于实现事务管理、缓存机制、性能监控等功能。
>    - 实际上，MyBatis的`Executor`接口就是执行器，而拦截器可以拦截其方法（如`update`、`query`等）来影响SQL的执行。
>
> 4. **结果集拦截器**（或称为**结果处理器**）：
>    - 拦截查询结果的处理过程。
>    - 用于将数据库返回的结果集转换为Java对象，并封装到Mapper接口的返回值中。
>    - 可以对结果进行进一步的加工或转换。
>
> ### 注意
>
> - MyBatis的拦截器机制非常强大，但也需要谨慎使用，以避免对性能造成不必要的影响。
> - 在实现拦截器时，要确保对MyBatis的内部机制和生命周期有深入的理解。
> - 对于SQL注入的风险，要特别注意`${}`占位符的使用，尽量避免或严格验证其替换内容的安全性。
> - MyBatis的官方文档和社区是学习和解决问题的宝贵资源。

#### 13.3 mapper接口的工作原理 它是否可以重载 ?

> ### Mapper接口的工作原理
>
> MyBatis中的`mapper`接口是一种用于定义数据库操作的接口，它通常由MyBatis框架在运行时自动生成实现类。这个接口的每个方法对应一个SQL操作（通过映射文件来一一对应），通过接口方法调用，可以自动地将SQL语句与方法绑定，实现对数据库的CRUD（创建、读取、更新、删除）操作。
>
> #### 工作原理
>
> 1. **接口定义**：开发者定义一个接口（通常称为`mapper`接口），并在接口方法上声明与数据库交互的操作。例如，查询、插入、更新、删除等操作。
>
> 2. **XML映射文件**：通常，这个接口会有一个对应的XML文件，里面定义了SQL语句。每个SQL语句通过`<select>`, `<insert>`, `<update>`, `<delete>`等标签与接口中的方法名绑定。
>
> 3. **动态代理**：在运行时，MyBatis通过Java的动态代理机制，为`mapper`接口生成一个代理对象。当调用接口的方法时，代理对象会拦截方法调用，并根据方法名称找到对应的SQL语句执行。
>
> 4. **SQL执行与结果映射**：MyBatis代理对象执行SQL操作，并将查询结果映射为Java对象或返回值类型，然后返回给调用者。
>
> #### 使用示例
>
> 假设有一个`UserMapper`接口：
>
> ```java
> public interface UserMapper {
>     User selectUserById(int id);
>     List<User> selectAllUsers();
> }
> ```
>
> 对应的XML映射文件（`UserMapper.xml`）可能如下：
>
> ```xml
> <mapper namespace="com.example.mapper.UserMapper">
>     <select id="selectUserById" resultType="User">
>         SELECT * FROM users WHERE id = #{id}
>     </select>
>     
>     <select id="selectAllUsers" resultType="User">
>         SELECT * FROM users
>     </select>
> </mapper>
> ```
>
> 在这个例子中，当调用`userMapper.selectUserById(1)`时，MyBatis会找到对应的SQL语句`SELECT * FROM users WHERE id = #{id}`，执行并返回结果。
>
> ### Mapper接口是否可以重载？
>
> **理论上是可以的**，但需要谨慎使用。因为映射文件的增删改查标签可以通过id(方法名)和parameterType(参数类型)来识别和绑定mapper接口中的方法。因此，在`mapper`接口中使用重载方法时，必须确保每个重载方法有独特的签名，并且这些方法在XML映射文件中有相应的、唯一的SQL语句绑定。
>
> #### 使用示例
>
> 例如，可以定义以下重载方法：
>
> ```java
> public interface UserMapper {
>     User selectUserById(int id);
>     User selectUserById(String id);
> }
> ```
>
> 在XML映射文件中，确保为每个方法绑定不同的SQL语句：
>
> ```xml
> <select id="selectUserById" parameterType="int" resultType="User">
>     SELECT * FROM users WHERE id = #{id}
> </select>
> 
> <select id="selectUserById" parameterType="String" resultType="User">
>     SELECT * FROM users WHERE id_string = #{id}
> </select>
> ```
>
> #### 注意事项
>
> - **方法签名必须唯一**：MyBatis通过方法签名来匹配SQL语句，如果方法的签名（包括方法名、参数类型、参数数量等）不唯一，可能会导致SQL语句绑定错误。
> - **XML配置的准确性**：在XML映射文件中，每个方法的SQL配置要严格对应方法的签名。如果签名不匹配，会导致SQL执行错误或者映射失败。
> - **可读性和维护性**：虽然重载在某些情况下有助于代码的简洁，但在`mapper`接口中滥用重载可能导致混淆。因此，在设计接口时，应该权衡使用重载的必要性。
>
> ### 小结
>
> - MyBatis的`mapper`接口通过动态代理机制在运行时生成实现类，实现与数据库的交互。
> - 这个接口中的方法可以被重载，但需要确保每个重载方法有唯一的签名，并在XML配置中正确映射对应的SQL语句。虽然技术上可行，但在实践中应谨慎使用，以避免潜在的维护问题。

### 14. 通过MyBatis分页插件实现分页

#### 14.1 分页步骤

> 1.在pom.xml中导入分页插件的依赖
>
> ```xml
> <!--mybatis分页插件-->
>  <properties>
>     <pagehelper.version>4.2.1</pagehelper.version>
>   </properties>
>   
> <dependencies>
>     <dependency>
>         <groupId>com.github.pagehelper</groupId>
>         <artifactId>pagehelper</artifactId>
>         <version>${pagehelper.version}</version>
>     </dependency>
> </dependencies>
> ```
>
> 2.mybatis核心配置文件配置这个插件(繁琐)-->spring会帮你整合
>
> ```xml
>     <!--3.配置分页插件-->
>     <plugins>
>         <!--一个分页插件-->
>         <plugin interceptor="com.github.pagehelper.PageHelper">
>             <property name="offsetAsPageNum" value="true" />
>             <property name="rowBoundsWithCount" value="true" />
>             <!--pageSize=0时，是否查询出全部结果，默认为false-->
>             <property name="pageSizeZero" value="true" />
>             <!--分页合理化参数，默认文false；pageNum<=0，查询第一页；pageNum>总页数，查询最后一页-->
>             <property name="reasonable" value="true" />
>             <property name="params"
>                       value="pageNum=pageHelperStart;pageSize=pageHelperRows;" />
>             <property name="supportMethodsArguments" value="false" />
>             <property name="returnPageInfo" value="none" />
>         </plugin>
>     </plugins>
> ```
>
> 3.service层 使用PageHelper封装好分页信息(Pagelnfo类型)
> 4.通过controller层 把Pagelnfo信息返回给前端
>
> * 前后端不分离：通过作用域传递。
> * 前后端分离：通过统一格式Reult对象转换为json传递。

#### 14.2 PageInfo属性介绍

> 1
>
> ```java
> public class PageInfo<T> implements Serializable {
>  private static final long serialVersionUID = 1L;
>  private int pageNum;
>  private int pageSize;
>  private int size;
>  private String orderBy;
>  private int startRow;
>  private int endRow;
>  private long total;
>  private int pages;
>  private List<T> list;
>  private int prePage;
>  private int nextPage;
>  private boolean isFirstPage;
>  private boolean isLastPage;
>  private boolean hasPreviousPage;
>  private boolean hasNextPage;
>  private int navigatePages;
>  private int[] navigatepageNums;
>  private int navigateFirstPage;
>  private int navigateLastPage;
> }
> 
> public class PageInfo<T> implements Serializable {
>     private int pageNum; //当前页数
>     private int pageSize; //每页条数
>     private int size;  //当前页的数量
>     private String orderBy;
>     private int startRow; //当前页 第一行行号
>     private int endRow; //当前页 最后一行行号
>     private long total; //总条数
>     private int pages;  //总页数
>     private List<T> list; //每页的信息 
>     private int prePage; //上一页
>     private int nextPage; //下一页
>     private boolean isFirstPage; //是否是第一页
>     private boolean isLastPage;  //是否是最后一页
>     private boolean hasPreviousPage; //是否有上一页
>     private boolean hasNextPage; //是否有下一页
>     private int navigatePages;  //导航页码数
>     private int[] navigatepageNums; //所有导航页码数
>     private int navigateFirstPage;//导航页码数首页
>     private int navigateLastPage; //导航页码数尾页
> }
> ```
>
> 

> 问题记录：
>
> 1. 千万别使用user表，Mysql中自带了一个user表，mybatis的反向生成插件会将它们弄混。
> 2. 首先先clean一下清空缓存。
> 3. 前端的c:forEach 标签的两个属性中如果每有值，就会报500错误。
>    ![image-20240813182146983](D:\Desktop\MyBatis\img\前端的cforEach标签的两个属性中如果每有值就会报500错误.png)
> 4. mybatis的核心配置文件中如果没有配置映射文件前端就没有数据展示且报500错误，如果配置错了会报500错误。
>    ![image-20240813181925587](D:\Desktop\MyBatis\img\mybatis的核心配置文件中如果没有配置映射文件前端就没有数据展示且报500错误.png)![image-20240813181728400](D:\Desktop\MyBatis\img\mybatis的核心配置文件如果配置错了会报500错误.png)
> 5. 实体类中的属性和前端取出来的属性不一致时会报500错误。
>    ![image-20240813181225147](D:\Desktop\MyBatis\img\实体类中的属性和前端取出来的属性不一致时会报500错误.png)

### 15.mybatis总结 常见面试题

>1.谈谈你对mybatis理解?
>
>2.mybatis工作流程?
>
>3.resultMap和resultType区别?
>
>4.#{} 和 ${} 区别?
>
>5.mybatis动态sql语句?
>
>6.mybatis关联映射如何实现? 
>
>7.Mybatis缓存机制?
>
>8.乐观锁和悲观锁?
>
>9.Mapper接口工作原理?   拦截器?  是否能重载?
>
>10.Mybatis中的mapper接口 如何传递多个参数?
>
>...

### 16.建立MyBatis配置文件的模板

> ![image-20240816110509224](D:\Desktop\MyBatis\img\建立MyBatis配置文件的模板.png)
