## JDBC
### 1. 为什么要学jdbc?
> 学习jdbc就是在学习如何使用程序和数据库进行交互。
> 早期存储数据是通过序列化的方式存储，缺点是不适合做，大量数据存储，所以存储大量数据还是推荐使用数据库技术，所以要学习java和数据库之间的交互，这也是jdbc的作用。而且JDBC是许多框架的基础，学习jdbc也是为了以后更好的学习Mybatis框架。

### 2. 什么是jdbc？
> jdbc:(java database connection) 是Java数据库连接技术的简称，是Java EE的一个核心组件，是一种用于执行sql语句java API,它里面提供了一套完整类和接口，这些接口定义了访问数据库的规则，但是具体的实现哪种数据库，需要根据数据库厂商提供的`驱动包（jar)`来访问。
> <br> 所以我们要学的就是这一系列类和接口。

### 3. JDBC涉及的类和接口 -- 笔试题（选择题）（?）
> * DriverManager类 ：驱动管理类，根据加载好的驱动包(jar)的信息，通过加载器加载好的类来创建对应数据库的连接对象(就是下面的Connection对象)
> * Connection接口  ：数据库连接对象，负责和数据库交互，还负责创建Statement对象
> * Statement接口   ：用于执行sql语句的（增删改查）
> * PreparedStatement ：预编译对象
> * ResultSet接口   ：只有查询语句会返回结果集对象，会保存查询语句的查询结果。（获取结果的方式类似于使用迭代器遍历）
>
> ![img.png](img.png)

### 4.jdbc的使用步骤 -- 面试题
> 1.加载驱动类(`前提：需要先导好驱动包`)<br>
> 2.通过DriverManger管理驱动类来创建数据库连接对象 <br>
> 3.通过连接对象创建Statement对象<br>
> 4.通过Statement对象执行sql语句<br>
> 5.（只有查询才有的）处理结果集对象<br>
> 6.关闭资源（连接对象、statement对象、结果集对象）

```java
// jdbc的使用实例：
package Class;

import java.sql.*;

// 测试的第一个JDBC
public class FirstJDBC {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//0. 导入驱动包
// a. 在src目录同级处创建一个lib目录
// b. 把jar包拷贝到该目录中去
// c. 右击包，选择 "Add as Library……"选项 加载包
// d. 能展开包就表示导入成功

        //1. 加载驱动类（前提是导入驱动包）
        // 通过反射来获取驱动类的类对象，目的是让类加载器加载驱动类。
        // 不同数据库的驱动包(jar)的全类名：
        //    mysql5 驱动包：com.mysql.jdbc.Driver
        //    mysql8 驱动包：com.mysql.cj.jdbc.Driver
        //    Oracle ： oracle.jdbc.driver.OracleDriver
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2. DriverManager驱动管理
        // a. 导入java.sql下的Connection接口
        // b. 创建Connection对象：Connection conn = DriverManager.getConnection(请求地址，账号，密码);
        //     参数说明：
        //         *请求地址(url)：统一资源定位符，俗称网址（协议://ip地址:端口号/项目资源?可选参数）
        //                                      jdbc:mysql://localhost:3306/数据库名?key=value&key2=value2
        //                                      数据库协议://本机ip:mysql端口号/数据库名
        //                      可选参数：可以让操作数据库时，数据库不会出现不一致（比如：乱码和时区问题)
        //                      常用的可选参数：1. useUnicode：表示unicode编码方式
        //                                   2. characterEncoding：修改字符集编码（让中文不乱码）
        //                                   3. autoReconnect: 是否自动连接
        //                                   4. rewriteBatchedStatement：是否开启批处理（一口气处理很多条语句）
        //                                   5. serverTimezone：设置时区 不是必选的 看数据库时间和系统时间是否有差异（报时区错误必须加）
        //                                   6. useSSL：是否使用SSL协议 一般mysql5.7需要配置这个 （报ssl错误必须加）
        //         *账号：数据库账号
        //         *密码：数据库账号对应密码
        String url = "jdbc:mysql://localhost:3306/sc240601?" +
                "useUnicode=true&" + //开启unicode编码方式
                "characterEncoding=utf-8&" +
                "autoReconnect=true&" +
                "rewriteBatchedStatement=true&";
        // +"useSSL=false"; //报Establishing SSL connection错误要加该选项
        // +"serverTimezone=Asia/Shanghai" //报serverTimezone错误要加该选项
        String username = "root";
        String password = "123456";
        Connection conn = DriverManager.getConnection(url, username, password); //要处理SQL异常
        System.out.println("连接成功：" + conn);

        // 3.通过连接对象创建Statement对象 （注意是java.sql包下的）
        Statement stmt = conn.createStatement();

        // 4.通过Statement执行sql语句
        //   * stmt.execute(sql语句);
        //       可以用于增删改查四种语句，但是返回值是一个boolean类型
        //       无法查看查询的数据 不推荐这种方式
        //   * stmt.executeUpdate(sql语句);
        //       适用于增删改三种情况，返回值是一个int，表示受影响的行数（等于0表示失败）
        //       增删改时推荐使用
        //   * stmt.executeQuery(sql语句);
        //       只适用于查询语句，返回值是一个ResultSet对象
        //       查询数据时推荐使用
        //   * stmt.executeBatch(); //要结合添加批处理addBatch(sql)来使用
        //       在执行批量操作的时候使用，如批量新增100条数据，
        //       要结合添加批处理 addBatch() 来使用
        //       添加完成后使用stmt.executeBatch();来执行批中所有语句。
        String sql = "select * from dept";
        // 如果sql语句里有参数（条件） 还需要传递条件
        ResultSet rs = stmt.executeQuery(sql);

        //5. 只有查询才需要处理结果集（目的是为了取出查询的数据）
        //   rs.next() ：含义表示每次获取第一行数据，然后删除第一行（初值指向的是第一行之前）
        //   rs.getXXX() : 获取每行数据的每个字段，根据字段类型调用一下方法
        //      rs.getInt() \ rs.getString() \ rs.getDate() \ rs.getDouble()
        //      rs.get类型(数值) : 根据查询结果的第几个字段来获取，不推荐使用，字段在第几个位置不确定，不适合复杂查询。
        //      rs.get类型(字符串) : 根据字段的每次来获取字段的值。
        while (rs.next()) {
            Integer deptno = rs.getInt(1);
            Integer deptno2 = rs.getInt("deptno");
            String dname = rs.getString("dname");
            String loc = rs.getString("loc");
            // 后续…… 要看需要，一般是打印 或者封装对象 集合。
            System.out.println(deptno + " " + dname + " " + loc);
            // 封装对象要先有实体类，即和数据库表中字段一样的属性
            //    实体类：就是用来描述表中的数据。
            // 然后用集合来保存所有对象。
        }

        //6.释放资源 （有查询要释放3个对象，否则释放两个）
        //  调用对象的close方法
        //  释放资源要注意顺序，按创建顺序的倒序来关闭
        rs.close();
        stmt.close();
        conn.close();
    }
}
```
```java
// 测试批处理
public class TestBatch {
    public static void addBatch() throws ClassNotFoundException, SQLException { //批量新增100条
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
        Statement stmt = conn.createStatement();
        List<MyUser> list = getUsers();//调用一个方法 返回对象集合100个
        for (MyUser u : list) {
            String sql = "insert into myUser values (null,'" + u.getName() + "','" + sdf.format(u.getTime()) + "'," + u.getCard() + "," + u.getSex() + ")";
            //添加sql语句到批处理
            stmt.addBatch(sql);
        }
        //执行批处理(一口气执行里面保存的所有sql语句)
        stmt.executeBatch();
        stmt.close();
        conn.close();
    }
}
        // 使用addBatch()来添加数据
        // 添加完成后使用stmt.executeBatch();来执行批中所有语句。
```
> 关于jdbc的查询：<br>
> 要对ResultSet对象的结果集进行解析。
> 通过next() 来确认和获取下一行数据。
> 使用getXXX(下标/字段名)，来获取每行中每个数据。下标是从1开始算的，即第一列数据的下标为1。
> 关于jdbc的批处理：<br>
> 使用addBatch()将sql语句添加到批处理。
> 使用executeBatch()将批中的语句一次性执行。<br>
> 观察：
> 1W条以下sql语句使用批处理和不用批处理的效率差不多，可能使用批处理性能还差点，因为批处理还要添加sql语句到批中，
> 1W条以上sql语句使用批处理就会开启优化，速度快了1倍以上。<br>
> ![img_1.png](img_1.png)

### 5.sql注入 -- 高频面试题
#### 5.1 是什么？
> sql注入：用户传入非法参数，而且服务器未进行预防(如预处理)，导致服务器被欺骗，让用户进行了未授权的操作。
> 其主要原因是服务端未预处理而采用拼接字符串的方式执行sql语句，比如：
> `delete from 表 where name= "随便写" or 1=1` 这里 or 1=1 就是非法参数 结果name条件可能不成立，但是1=1永远成立 最后会造成全表数据删除。
#### 5.2 解决方案
> 使用预编译对象PreparedStatement来解决sql注入问题，它会编译sql语句，先让sql语句结果固定，
> PreparedStatement对象已经在预处理时获取了sql语句，所以不需要在execute()时重新传入一遍，千万别搞混了。
> 而且写sql语句时无需用''将字符串字段的指扩起来。
> sql语句参数通过`?`作为占位符，同时还可以实现一次编译多次运行，执行效率会更高一些。
> 一定要注意PreparedStatement对象的setXXX()是以1开始算？的下标的。
#### 5.3 PreparedStatement 和 Statement 的区别。
> * Statement：是通过字符串拼接的方式处理参数，所以存在sql注入的隐患，非常不安全， 不推荐使用。
>   mybatis中${}的底层是Statement。
> * PreparedStatement：是Statement的子类，是预编译对象，先编译sql语句，可多次运行，效率会高于Statement 是采用`?`作为占位符形式来处理参数，可以`防止sql注入`比较安全 推荐使用 也是
    mybatis框架底层#{}实现方式

```java
// 测试sql注入
public class TestSQLInjection {
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url="jdbc:mysql://localhost:3306/sc240601?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&rewriteBatchedStatement=true";
    private static String username="root";
    private static String password="123456";
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String name = "100 or 1=1";
        String name1 = "100' or '1=1";
        test1(name);
    }
    // 存在sql注入隐患的方法
    public static void test1(String name) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
        Statement stmt = conn.createStatement();
        String sql = "delete from myuser where name=" + name;
        int n = stmt.executeUpdate(sql);
        System.out.println("成功删除：" + n + "条数据");
    }

    // 解决sql注入隐患的方法
    public static void test2(String name) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
        //Statement stmt = conn.createStatement();

        //创建预编译对象 先编译sql 后运行 同时 参数通过？来占位
        String sql = "delete from myuser where name=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        // 执行语句之前 处理一下语句中的？(就是给？赋值)
        //pstmt.set类型（整数：表示第几个？(从1开始), 数据：给？赋值
        pstmt.setString(1,name);
        // 执行，返回受影响的行数
        int line =pstmt.executeUpdate();
        System.out.println("受影响的行数:"+ line);
        pstmt.close();
        conn.close();
    }
}
```
#### 5.4 mybatis中#{}和${}的区别
> mybatis的底层是jdbc，${}的底层是Statement，也存在sql注入的隐患

### 6. 封装jdbc工具类
#### properties文件
> 
#### 实例
```java

```

### 7. JDBC如何做事务 -- 面试题
> jdbc 做事务类似于mysql数据库，都是自动提交事务，如果想自己实现事务，
> 需要设置手动提交事务（setAutoCommit(false)）通过Connection对象调用commit()和rollback()
> 还要保证用这几个方法的是同一个conn对象
> * a.关闭自动提交事务conn.setAutoCommit(false);
> * b. 提交事务 conn.commit();
> * c. 关闭事务 conn.rollback();
```java
// 支持事务的JDBCUtil
package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class JDBCUtil2 { //扩展 支持事务的jdbc
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    static{
        // 创建Properties对象
        InputStream is = JDBCUtil2.class.getClassLoader().getResourceAsStream("config/jdbc.properties");
        Properties p = new Properties();

        try {
            // 通过is对象 将数据封装到properties中
            p.load(is);
            driver = p.getProperty("driver");
            url = p.getProperty("url");
            username = p.getProperty("username");
            password = p.getProperty("password");
            System.out.println(p.entrySet());
            // 加载驱动类：最先执行，只赋值一次 所以在静态代码块中赋值
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // 创建连接的通用方法：一个用户一个线程，只有一个连接，除非连接被回收了
    // 定义一个本地线程 只存储连接对象 它独享该对象，其他线程只能访问自己的本地线程。
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();
    public static Connection getConn() {
        Connection conn = tl.get(); //通过本地线程获取
        try {
            //第一次使用，本地线程里没有连接要新建，新建后要存储到本地线程
            if(conn==null) {
                conn = DriverManager.getConnection(url, username, password);
                //新建后要存储到本地线程
                tl.set(conn);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    // 关闭连接的通用方法
    // 可变长参数 + AutoCloseable接口（不能是Object，它没有close()）
    // 要关闭的数量不确定（select要多关一个ResultSet
    // bug:关闭顺序要注意，只能通过传参顺序来控制关闭顺序。
    // 关闭的时候 本地线程也要清空
    public static void close(AutoCloseable ... able) {
        tl.set(null); // 清空本地线程
        for (AutoCloseable a : able) {
            // 防止空指针异常要先判断a是否为null
            if(a != null) {
                try {
                    a.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    // 增删改的通用方法
    // 可变长参数的长度是不确定的，所以一般写到最后面。
    // bug : sql中的?的个数是不确定的，但是又一定要和可变长参数o的个数和顺序一致。
    // 解决办法：不知道类型就使用setObjet()来替换。但用户多传了参数就没办法了。
    public static int update(String sql, Object ... o){
        int result = 0;

        try {
            pstmt = conn.prepareStatement(sql);
            if(o != null) { //判断是否有参数
                for (int i = 0; i < o.length; i++) {
                    // 不知道类型就使用setObjet()来替换。
                    pstmt.setObject(i+1, o[i]);
                }
            }
            result = pstmt.executeUpdate();
            System.out.println("受影响的行数：" + result);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // close(pstmt, conn); //事务结束后才能关闭连接
        return result;
    }
    // 查询的半通用方法。
    // 存在两个问题：
    // 1.返回的是ResultSet 需要用户自己封装数据
    // 2.ResultSet不能在方法中关闭，因为用户还有处理结果集，处理完成后才能关闭。
    public static Connection conn = null;
    public static PreparedStatement pstmt= null;
    public static ResultSet select(String sql, Object ... o){

        ResultSet rs = null;

        try {
            conn = getConn();
            pstmt = conn.prepareStatement(sql);
            if(o != null) { //判断是否有参数
                for (int i = 0; i < o.length; i++) {
                    // 不知道类型就使用setObjet()来替换。
                    pstmt.setObject(i+1, o[i]);
                }
            }
            rs = pstmt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // 无法处理不能关闭。
        //close(rs, pstmt, conn);
        return rs;
    }
}
```

### 8. ThreadLocal -- 面试题
#### 8.1 什么是ThreadLocal?
> ThreadLocal 是 java中一个特殊的类，用于在多线程中去维护线程的变量。
> 一般情况下，如果多线程共享同一个变量，会引起并发问题。但是ThreadLocal为每一个线程提供了一个独立的变量副本。每个线程都可以独立修改这个副本，所以这里面保存的数据不会产生并发问题。
> 适合在多线程的情况下共享变量，共享资源时使用，
> 比如：存储数据库的连接对象（因为每个用户只需要一个连接，而且用户和用户之间的连接不能相互影响）、
> session会话管理技术等场景。
#### 8.2 常用方法：
> * set() : 设置当前线程变量的副本 【set(null); // 清空本地线程】
> * get() : 获取当前线程变量的副本
> * remove() : 清除当前线程的变量副本
```java
    // 测试本地线程：ThreadLocal
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();
    public static Connection getConn() {
        Connection conn = tl.get(); //通过本地线程获取
        try {
            //第一次使用，本地线程里没有连接要新建，新建后要存储到本地线程
            if(conn==null) {
                conn = DriverManager.getConnection(url, username, password);
                //新建后要存储到本地线程
                tl.set(conn);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }
```