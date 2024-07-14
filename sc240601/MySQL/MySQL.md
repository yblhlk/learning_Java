## 1. 什么是数据库？

> 从宏观上来讲，数据库分为三个部分：
>
> * 客户端：一个与用户交互，接收用户指令的程序。
> * 服务端：一个用来管理数据库文件的程序，它会响应客户端的指令，对相应的数据库文件进行操作。
> * 数据库文件： 保存数据的数据库文件或文件的集合。
>
> 总结一下：从宏观上来讲，数据库就是管理数据库文件的程序 + 保存数据的数据库文件的集合。
>
> 从狭义上来讲，保存数据的数据库文件或文件的集合也能被叫做数据库。
>
> <hr>
>
> ![image-20240708141257555](D:\Pictures\MySQL\image-20240708141257555.png)

## 2.数据库分类

![image-20240708141352338](D:\Pictures\MySQL\image-20240708141352338.png)

### 2.1 关系型数据库

![image-20240708141552569](D:\Pictures\MySQL\image-20240708141552569.png)

> ==感悟==：
>
> 对象中的引用类型成员，无法通过一格来描述，可以再开一张表，让字段和表中一行联系起来即可：
>
> ![image-20240708142053106](D:\Pictures\MySQL\image-20240708142053106.png)

<img src="D:\AppData\Typora\typora-user-images\image-20240708142451355.png" alt="image-20240708142451355" style="zoom:50%;" />

### 2.2 非关系型数据库

![image-20240708142805546](D:\Pictures\MySQL\image-20240708142805546.png)

<img src="D:\Pictures\MySQL\image-20240708143018558.png" alt="image-20240708143018558" style="zoom:50%;" />

![image-20240708143155550](D:\Pictures\MySQL\image-20240708143155550.png)

## 3. 安装MySQL

![image-20240708144252154](D:\Pictures\MySQL\image-20240708144252154.png)

![image-20240708144235889](D:\Pictures\MySQL\image-20240708144235889.png)

![image-20240708145503967](D:\Pictures\MySQL\image-20240708145503967.png)

![image-20240708145713649](D:\Pictures\MySQL\image-20240708145713649.png)

![image-20240708144614293](D:\Pictures\MySQL\image-20240708144451726.png)

> 拓展：
>
> 环境变量中的__系统变量__和__用户变量__：
>
> 系统变量是这台电脑上的，用户变量是属于这个Windows账户的。
>
> ![image-20240708145243077](D:\Pictures\MySQL\image-20240708145243077.png)

> 拓展：
>
> 以管理员权限打开命令提示符窗口
>
> ![image-20240708150230383](D:\Pictures\MySQL\image-20240708150230383.png)
>
> 
>
> ![image-20240708150346432](D:\Pictures\MySQL\image-20240708150346432.png)

![image-20240708154332360](D:\Pictures\MySQL\image-20240708154332360.png)

> 在Navicat中新建数据库时，一定要更改编码，不然查询的时候会乱码（mysql8 选择 utf8-mb4)
>
> ![image-20240708154546535](D:\Pictures\MySQL\image-20240708154546535.png)
>
> ![image-20240708175851361](D:\Pictures\MySQL\image-20240708175851361.png)



![image-20240708155848804](D:\Pictures\MySQL\image-20240708155848804.png)



## 4. MySQL

![image-20240708160321901](D:\Pictures\MySQL\image-20240708160321901.png)

## 5. SQL语句的分类

* DDL ：数据定义语言，用于创建和删除数据库中的对象（创建数据库，创建表，创建视图，创建索引，创建触发器，创建函数……），不支持回滚
  如：create(创建) drop(删除) alter(修改) truncate(截断表)
* DML ：数据操作语言，用于操作表中的数据。
  如：insert(插入) delete(删除) updata(修改)
* DQL ：数据查询语言，用于查询表中的数据
  如：select -- 最常用，最难用
* DCL ：数据控制语言，用来控制数据库组件的属性，权限等
  如：grant(授权) revoke(撤销权限)
* TCL ：事务控制语言，用来提交或回滚事务
  如：commit(提交) rollback(回滚)

![image-20240708161551432](D:\Pictures\MySQL\image-20240708161551432.png)

![image-20240708161946313](D:\Pictures\MySQL\image-20240708161946313.png)

![image-20240708162246920](D:\Pictures\MySQL\image-20240708162246920.png)

### 5.1 DDL

#### a. create命令创建表：

```sql
-- 语法规则：[]表示可有可无
-- if not exists : 判断表是否存在。
-- `` : 防止和关键字冲突。
-- [desc 表名] ：查看表的详细信息。
-- [show create table 表名 [\G]] ：查看创建表的语句。
create table [if not exists] `表名` (
 字段名1  数据类型  [约束] [索引] [注释],
 字段名2  数据类型  [约束] [索引] [注释],
 ……
 字段名n  数据类型  [约束] [索引] [注释] --最后一句不用加","
)[表类型][表字符集][注释];
```

> 注：创建表的关键字是table， 字段和字段之间通过","间隔，结束语句通过";"

> 保存查询：
>
> 1. Ctrl + q 打开一个新的查询窗口
> 2. Ctrl + s 保存查询
>    ![image-20240708163625640](D:\Pictures\MySQL\image-20240708163625640.png)
> 3. 在数据库的查询中找到，没有就右击，然后选择刷新
>
> ![image-20240708164401754](D:\Pictures\MySQL\image-20240708164401754.png)

> Navicat 更改字体大小和颜色：
>
> 工具 -> 选项 -> 字体/颜色
>
> ![image-20240708164108746](D:\Pictures\MySQL\image-20240708164108746.png)
>
> ==重启后生效。==

> ![image-20240708164618170](D:\Pictures\MySQL\image-20240708164618170.png)
>
> ![image-20240708164714803](D:\Pictures\MySQL\image-20240708164714803.png)



> 如何查看表注释？
>
> ![image-20240708171023688](D:\Pictures\MySQL\image-20240708171023688.png)

#### b. alter修改表

```sql
-- 修改表名
alter table oldname rename newname;
-- 添加字段
alter table 表名 add 字段名 类型;
-- 修改字段
alter table 表名 change 原字段名 新字段名 类型;
-- 删除字段
alter table 表名 drop 字段名;
```

![image-20240708171544604](D:\Pictures\MySQL\image-20240708171544604.png)

#### c. drop命令删除表

```sql
drop table 表名;
```

### 5.2 DML

#### a. insert命令新增数据

```sql
-- 语法1：选择字段插入（字段可乱序，但必须和后面的值一一对应）
insert into 表名 (字段名2, 字段名5，字段1) values (值2,值5,值1);

-- 语法2：全字段插入（表里有几个字段，要写几个数据，且要一一对应）
insert into 表名 values (值1,值2,值3……);

-- 语法3：批量插入（value后面接多组具体值）
insert into 表名 (字段名2, 字段名5，字段1) values (值2,值5,值1),(值22,值55,值11),……;
insert into 表名 values (值1,值2,值3……),(值11,值22,值33……),……;

-- 语法4：借助select特点实现批量插入 (以下是批量新增三条数据的例子)
insert into 表名 select 值1,值2,值3……,值n union
				select 值1,值2,值3……,值n union
				select 值1,值2,值3……,值n ;
```

> `dual`：mysql和Oracle 中的虚表，mysql 可略写
>
> ![image-20240708174407038](D:\Pictures\MySQL\image-20240708174407038.png)

#### b. update语句

```sql
-- 语法：(注意update后面没有from)
update 表名 set 字段=值1,字段2=值2,…… where 条件;
注：where后面的条件 必须加，否则全表更新
条件目的：计算限制数据集 比如：> < = 来进行控制。
```

#### c. delete语句

```sql
-- 语法（注意from不要写成form表单）
delete from 表名 where 条件
注：where后面一定要添加条件 否则全表删除
```

## 6. MySQL的数据类型（面试题）

* 数值类型

|     类型      |  大小  |      介绍      |
| :-----------: | :----: | :------------: |
|   smallint    | 2字节  |     小整型     |
|      int      | 4字节  |      整型      |
|    bigint     | 8字节  |     大整型     |
|     float     | 4字节  |     单精度     |
|    double     | 8字节  |     双精度     |
| decimal(参数) | 31字节 | 精确数值浮点型 |

> * double：是一种双精度浮点型，可以存储大约15位有效数字。占8个字节，到达一定精度后会进行四舍五入。
> * decimal：是精确数值类型，不允许误差，可以精确保存小数位，不能进行四舍五入。可以存储大约65位有效数字。
>
> ==注：浮点型的使用都是类似的，float(6,2)4个整数，2个小数，deouble(6,2)，decimal(6,2)也一样。==

* 字符串类型

> ==注：mysql没有字符，只有字符串类型==

|     类型      |                     大小                     |    介绍    |
| :-----------: | :------------------------------------------: | :--------: |
|     char      |                 255个`字符`                  | 定长字符串 |
|    varchar    |   65535字节<br>(能存几个字符要看编码方法)    | 变长字符串 |
| text/longtext |           65535`字符`/longtext更长           | 长文本类型 |
|     blob      | 2^16-1个字节<br>（存放二进制文件，如图片等） | 二进制类型 |

> MySQL中char类型和varchar类型的区别：
>
> char一旦创建就不能改变其大小。
>
> varchar会根据内容来决定占用的空间，存储的内容变大，空间也变大。
>
> ![image-20240709155007187](D:\Pictures\MySQL中char类型和varchar类型的区别.png)

* 日期类型

|   类型    |        格式         |    介绍    |
| :-------: | :-----------------: | :--------: |
|   date    |     YYYY-MM-DD      |   日期值   |
|   time    |      HH:mm:ss       |   时间值   |
|   year    |        YYYY         |   年份值   |
| timestamp | YYYY-MM-DD HH:mm:ss |   时间戳   |
| datetime  | YYYY-MM-DD HH:mm:ss | 日期时间值 |

> timestamp和datetime的区别：
>
> * 容量不同：timestamp占4个字节 datetime在mysql5.6后占5个字节之前是8个字节？
> * 存储不同：timestamp存储的值转换成UTC的时间再进行存入datetime不会转换，给什么存什么
> * 存储null：timestamp在mysql8之前，如果存储null自动转换为now()，datetime不会，给什么存什么。
> * 存储now()：timestamp可能与当前时间不一致，因为他会把当前时间转化为UTC来存储；但datetime不会转换，和当前时间是一致的。

## 7. DQL

### 1. 基础查询语句

```sql
-- 基本语法：[]可有可无，必须按下面的顺序来加，如group by必须在where后面。
select 查询的字段，字段2，…… from 表名或者视图名
[where 分组前的条件]
[group by 分组的字段]
[having 分组后的条件]
[order by 排序的字段 desc/asc]
[limit [[起始偏移量,]函数] //从3下标开始查5条数据
```

> 别名：进行复杂查询时，可能会涉及到多张表重名的情况，很可能出现 字段 和 表 重复的问题
> -- 可以给这些重名的内容 添加一个临时的名称，可以减少代码复杂度
> -- 语法：字段名 as 别名 或 表名 as 别名
> -- 注：as可以省略 别名是可以不加引号的

#### a. where子句 (精确查询)

对查询结果做第一次筛选

```sql
-- 1. 查询范围
select * from 表 where 字段>60;

-- 2. MySQL中的逻辑运算符是and or not
select * from 表 where 字段>60 and 字段<80;

-- between 是闭区间，包括左右边界
select * from 表 where 字段 60 between 80;

-- 判断是否为null
select * from 表 where 字段 is null;

-- 筛选不等于 (!= 或 <>)
select * from 表 where 字段!=100;
select * from 表 where 字段<>100;

-- 筛选等于
-- 注意：MySQL中的等于是单个=。
select * from 表 where 字段=10;

-- 筛选等于多个值(=)
-- 简化版：in()函数等价于或者的意思
select * from 表 where 字段 in (10,20,50);
-- 等价于
select * from 表 where 字段=10 or 字段=20 or 字段=50);
```

#### b. like子句 （模糊查询）

> 如果对查询条件不明确，只知道部分内容，Mysql中可以使用模糊查询方式来实现它。

> 常见的模糊查询:
>
> ![image-20240709174744425](D:\Pictures\常见的模糊查询.png)
>
> * ___ : 长度为3的内容。

#### c. rlike（正则表达式查询）

在MySQL中，`RLIKE` 是一个操作符，用于在字符串搜索中执行正则表达式匹配。它与 `REGEXP` 操作符是同义词，即两者在功能上完全相同，只是名称不同。使用 `RLIKE` 或 `REGEXP`，你可以在查询中搜索与指定正则表达式模式匹配的字符串。

基本语法

```sql
SELECT column_name(s)
FROM table_name
WHERE column_name RLIKE pattern;
```

或者

```sql
SELECT column_name(s)
FROM table_name
WHERE column_name REGEXP pattern;
```

在这里，`column_name` 是你要搜索的列名，`table_name` 是表名，而 `pattern` 是你要匹配的正则表达式模式。

示例

假设我们有一个名为 `employees` 的表，其中有一个名为 `email` 的列，我们想要找出所有以 "john" 开头，后跟任意字符（包括0个字符），最终以 "@example.com" 结尾的电子邮件地址。

```sql
SELECT email
FROM employees
WHERE email RLIKE '^john.*@example.com$';
```

这个查询会匹配所有形如 "john@example.com"、"john123@example.com" 等的电子邮件地址，但不会匹配 "johnny@example.com" 或 "john@example.net"，因为正则表达式模式要求以 "john" 开头并以 "@example.com" 结尾。

注意事项

- 正则表达式区分大小写，除非你使用了不区分大小写的模式（例如，在MySQL中，这通常意味着使用 `REGEXP_LIKE()` 函数与适当的模式标志，但请注意，`REGEXP_LIKE()` 是MySQL 8.0中引入的，并且不是所有MySQL版本都支持）。
- 正则表达式可以非常复杂，允许你执行精确的文本匹配，包括字符类、量词、边界匹配等。
- ==使用正则表达式可能会比使用简单的字符串函数（如 `LIKE`）更慢，因为正则表达式需要更复杂的处理。因此，在性能敏感的查询中，应谨慎使用正则表达式。==
- MySQL的正则表达式语法与Perl兼容，但并非所有Perl正则表达式特性都在MySQL中受支持。请查阅MySQL的官方文档以获取有关支持的特性和语法的详细信息。

#### d. union子句

mysql中union用于`链接多个select语句的结果集`，最终合并为一个结果集。特点：如果这多个select语句的结果中有重复的数据，`会自动去重`。
并且使用union还可以实现数据库列转行。

```sql
-- 语法1：(会自动去重)
select 字段1，字段2 from 表
union
select 字段1，字段2 from 表

-- 语法2：(如果不想去重，使用union all)
select 字段1，字段2 from 表
union all
select 字段1，字段2 from 表
```

> 注：使用union合并时，查询结果中列的个数必须相同，和字段的类型没有关系。 

#### e. order by子句

数据库本身的顺序是无序的。

![image-20240710114902124](D:\Pictures\MySQL\image-20240710114902124.png)

``` sql
-- 语法：
order by 字段 排序规则;
-- 排序规则：ASC升序（默认值）,DESC（降序）

-- 语法2：如果一个查询要按照两个字段来排序可以通过“,”来分隔
order by 字段2 排序规则, 字段2 排序规则;
```

#### f. limit 子句

limit是mysql中用于通过下标（索引值）来限定查询条数的一种方式，它后期也是实现数据分页的非常重要的途径。

```sql
-- 语法：
limit [起始索引值,]查询的条数;

-- 例如：查询一张表 每页5条数据，查询第三页的所有数据
limit 10,5; //10表示从下标为10的第11条数据开始查询，查询5条
-- 比如：查询一张表的前10条数据
limit 0,10;
-- 等价于
limit 10;
```

## 8. MySQL约束

约束：是用于限定表中的数据，凡是不符合约束要求的数据，是无法插入到表中的，增加的表的准确性。

约束可以在建表时添加。也可以通过修改表来添加。

* 非空约束：`not null`   强制要求字段不能为空。

* 默认约束：`default`    保证字段一定有值，如果没有赋值，会被设置为默认值。（赋值为null也表示其有值，不会被设为默认值。）（全字段插入没有办法走默认值

* 唯一约束：`unique`   保证字段中的值是唯一的，但是值可为null。并且唯一约束 还会自动添加`唯一索引`

* 检查性约束：`check` 是用于给字段的数据做一些检查，看是否符合要求(如大于某个值)，但mysql8以后才会生效，mysql8之前语法能通过，但是没有效果。

* 主键约束：`primary key(pk)` 主键约束自带 唯一 和非空两种功能，`每张表只能有一个主键`，并且主键约束会自动添加一个`主键索引`

* 外键约束：`foreign key(fk)` 定义在具有父子关系的子表中，子表中的外键必须对应父表中的主键，父表中没有的值子表中的外键字段不能写。
  (==前提：添加外键时，一定是先创建父表再创建子表，父表不存在，子表会创建失败。==)
  （在删除时也不能先删除父表，再删除子表）

  （外键列可以为null，要不就必须是父类主键已有数据）

  （外键和主键的数据类型必须相同）

  如何添加外键约束：foreign key(子表字段) references 父表名(父表主键)  （父表必须存在，且必须是主键）
  

> 父表和子表
>
> 子表中的数据是从父表中来的。
>
> ![image-20240710151309253](D:\Pictures\MySQL\父表和子表.png)

> 设置自增：`auto_increment`
>
> -- 4. 主键自增
> -- 主键设置了自增，就可以设置主键为null,表示走自增的意思。
> -- 只有数值类型(包括double)才能设置自增，自增只能设置在索引中
> -- 自增按最后一行数据来自增
>
> create table city (
> 	id int `auto_increment` comment "城市id",
> 	name varchar(100) comment "城市名",
> 	primary key(id)
> );

```sql
-- 1. 在创建表时添加约束
-- 要添加多个约束时，直接使用","进行分隔
create table person (
id int primary key comment "人类编号", -- 主键
name varchar(20) not null comment "姓名",
age int default 18 comment "年龄",
sex char(1) check(sex="男" or sex="女") comment "性别",
phone varchar(11) not null, unique comment "手机号",
city_id int reference city(id) comment "城市id" -- 外键(O)
foreign key(city_id) references city(id);-- 外键
) comment "测试约束";

create table city (
id int primary key comment "城市id",
name varchar(100) comment "城市名"
);

-- 2. 通过修改表来添加约束
alter table 表名 add primary key(字段名); -- 添加主键约束
alter table 表名 add unique(字段名) -- 添加唯一约束
-- 注意：是用modify来添加非空约束，还要带上字段的类型
alter table 表名 modify 字段 类型 not null;
alter table 表名 add check(sex in('男','女')); -- 添加检查约束
-- 注意：是用alter来添加非空约束
alter table 表名 alter column 字段 set default 默认值; 
alter table 表名 add foreign key(city_id) references city(id); -- 添加外键约束。
```

## 9. 数据库范式 -- 面试题

> 范式有6层，一层比一层严格，层数越高数据安全性越高，但效率会越低。
>
> 结合安全性和效率，所以一般只考虑前3个范式。
>
> ![image-20240710162635227](D:\Pictures\MySQL\数据库范式.png)

* 第一范式（1NF）：要求设计数据库时字段具有原子性，也就是说不能将类似于对象，集合，数组这类不符合原子性的数据当成字段。

* 第二范式（2NF）：前提是必须先满足1NF，又要求其数据必须依赖于主键，本质上就是为了让我们强制添加主键，因为主键是唯一非空，这样可以防止数据的冗余。

* 第三范式（3NF）：前提是满足2NF，又要求不能有其他表中的非主键列，不然也会造成数据冗余（因为我们可以通过其他表的主键列来找到其他列，这样就没必要添加主键列以外的列来作为外键）强调了主外键的关联。

  > 但是第三范式在实际开发过程中是可以违反的，为了提高效率。不然每次都要通过外键列来查找其他列，效率太低。（因为很多时候都可能要用到两张表中的数据，如果遵守3NF，每次都要查询两张表，效率肯定没有查询一张表的效率高，所以违反3NF在表中添加一个冗余字段，这样只需要查询一张表，效率一定更高。这就是一种空间换时间的方式。

* BC范式（BCNF）：BC范式就是要求一张表中的其他非主键属性必须`直接`依赖于整个主键，不能存在任何间接依赖。

## 11. MySQL函数 -- 重点

> 类型于java中的方法，可以传参，`一定有返回值`

主要分三大类：

* 聚合函数：对多条数据，最终只会返回一个值，比如：最大值、最小值、平均值、总值
* 单行函数：对于多条数据，最终每行数据都会返回一个结果，比如：拼接字符串
* 自定义函数：

### 1. 聚合函数

* avg()
* count() : 返回数据的函数 
  count(distinct 字段)：`distinct`去重
* max()
* min()
* sum() ：返回指定字段的总和

前提：聚合函数一般可以写在select后面，同时还可以写在having后面。

### 2. 单行函数

* 数字函数：主要用于处理数字（整数和浮点数），用来处理聚合函数的结果

  ```sql
  abs(字段) ： 返回字段数据的绝对值
  ceil(字段)：返回字段向上取整的值
  floor(字段)：返回字段向下取整的值
  rand(字段)：返回0-1的随机数
  `round(字段，数值)：设置保留几位小数`
  ……
  ```
  
* 字符串函数：类似于java中的String中的方法

  ```sql
  char_length(字段)：返回的是该字段的字符长度。（一个中文算一个字符）
  	比如：select char_length("中文123"); -- 5
  length(字段)：返回该字段的字节长度。
  	比如：select length("中文123"); --  9(UTF-8)
  concat(s1, s2, s3, ……)：将所有字符串拼接在一起。
  upper(字符串)：将字符串变为大写
  lower(字符串)：将字符串变为小写
  substring(字符串,下标,长度)：截取字符串，下标从1开始算，长度不写就截取到末尾
  left(s,n)：返回字符串s的前n个字符，用来获取前缀
  right(s,n)：返回字符串s的后n个字符，用来获取后缀
  
  replace(s, 原内容, 新内容)：替换字符串内容
  instr(字符串, "内容")：返回字符串指定内容下标
  reverse(zfc)：反转字符串
  ……
  ```

* 日期函数：

  ```sql
  now() : 当前时间，显示年月日时分秒
  sysdate(): 当前时间，和now()一样
  curdate(): 当前时间，只有年月日
  	要时分秒用now(),不要时分秒用curdate()
  year()\month()\day()\hour()\…… 获取日期数据的年月日时分秒
  adddate(日期，天数) ：给日期添加几天
  last_day(日期)：求当前日期当月的最后一天的日期数据
  datediff(日期1，日期2)：求日期1-日期2的天数差
  from_days(天数)：可以将天数转为日期类型。
  				只支持365以上的天数转换，不满365就为0000-00-00
  				每四年会减一天闰年
  str_to_date(字符串，日期格式)：将字符串变成日期，但是mysql会自动将字符串转变为日期
  date_format(日期，日期格式)：将日期按照指定格式转换为字符串
  	注：mysql的日期格式比较特殊和java不一样（oracle和java一样）
  	注意一下的大小写必须区分
  	%W 表示星期名字 %D 表示月份英文前缀 %A返回星期的缩写
  	%Y 表示四位年 %y 二位年 %m 月份 %d 表示日
  	%H 表示24进制小时 %h 表示12进制小时 %i 分钟 %s表示秒
  ```

  > %Y 年, 数字, 4 位
  > %y 年, 数字, 2 位
  >
  > %M 英文月(June，October……)
  > %m 数字月(01……12)
  > %c  数字月，无前缀0(1……12)
  > %b 缩写月(Jan……Dec)
  >
  > %H 24小时(00……23)
  > %h 12小时(01……12)
  > %k 24小时(0……23)
  > %I 12小时(01……12)
  > %l 12小时(1……12)
  >
  > %i 分钟, 数字(00……59)
  >
  > %S 秒(00……59)
  > %s 秒(00……59)
  >
  > %r 时间,12 小时(hh:mm:ss [AP]M)
  > %T 时间,24 小时(hh:mm:ss)
  >
  > %D 日期(1st, 2nd, 3rd, 等等。）
  > %d 数字日期(00……31)
  > %e 数字日期, 无前缀0(0……31)
  >
  > %W 星期名字(Sunday……Saturday)
  > %a 缩写的星期名字(Sun……Sat)
  >
  > %j 一年中的天数(001……366)%p AM或PM
  > %w 一个星期中的天数(0=Sunday ……6=Saturday ）
  > %U 一年的第几个星期(0……52), 这里星期天是星期的第一天
  > %u  一年的星期(0……52), 这里星期一是星期的第一天
  >

* 条件判断函数：

  ```sql
  if(表达式,值1,值2)：如果表达式为真返回值1，否则值2。
  	比如：select if(1>0, "正确", "错误");
  	
  ifnull(字段,结果)：如果字段为null，就返回结果，如果不为null,就返回原来的字段。（MySQL独有）
  
  case函数：最复杂，模拟Java中的if和switch功能，能用来实现列转行。
  	-- 语法1：注意以end结束，类似于java多重if
  	case
  		when 条件1 then 结果1
  		when 条件2 then 结果2
  		……
  		else 其他结果
  	end
  	-- 语法2：类似于java中的switch，不存在穿透。
  	case 字段
  		when 值1 then 结果1
  		when 值2 then 结果2
  		……
  		else 其他结果
  	end
  	
  --案例：
  select name, if(sex = 1, "男","女") 姓别,
  case status
  	when 1 then "会员"
  	when 2 then "超级会员"
  	when 3 then "至尊会员"
  	else "冻结"
  end as 状态,
  ifnull(card, "游客") as 卡号
  from members;
  ```

* 其他函数：系统信息函数，加密函数。

  ```sql
  version()：返回mysql的版本。
  	select version();
  	
  database()：返回正在使用的数据库名称。
  	select database();
  	
  md5()：对字段进行加密，加密3次以上，网站就要花钱破解了。
  	select md5("王亚林");
  ```

## 12. 分组查询

> 分组：group by：将查询的数据按照某个条件进行分组，这样就会将原来只有一个数据集变成很多组的数据集。（如果分组后，使用聚合函数后，每组都会返回一个结果。所以一般在select和having中写聚合函数）

```sql
分组语法：[]可有可无
select [聚合函数,] 分组字段 from 表
[where 分组前的条件]
[group by 分组字段]
[having 分组后的条件] -- 筛选的是每组的数据

-- 比如：统计公司中每个部门的平均工资和总和 要求显示部门平均工资大于2000
select deptno, avg(sal) 平均工资, sum(sal) 工资总和
from emp group by deptno
having 平均工资 > 2000;
```

> ==注：如果添加了分组，查询的字段只能是分组的字段或者是聚合函数，否则：在mysql默认显示第一行数据，在Oracle直接报错。==

## 13.连接查询 -- 面试题

如果当要查询的数据分散在不同的表中，这样如果只查询一张表就无法满足我的需求了，就需要实现多张表之间进行连接后查询，这种情况就叫做连接查询，连接查询主要分两种：

* 内连接：只会把满足匹配条件的数据查询出来，不匹配的数据查询不出来，匹配条件也叫关联条件。如果有一边为null就不会显示。
* 外连接：
  * 左外连接：会将左边的表当成主表，右边的表当成副表，主表的数据会全部展示，副表只会展示满足关联条件的数据，如果出现不满足的，显示null。
  * 右外连接：类似于左外连接，只不过是以右边的表作为主表。
  * 全外连接：==mysql不支持==，类似于左外连接，只不过左右两张表都是主表，没有副标，两张表的数据都会全部展示。

### 1. 内连接

```sql
-- 关键字：inner join on
-- 语法1：完整写法
select * from 表1 inner join 表2 on 关联条件
				  inner join 表3 on 关联条件
-- 关联条件：就是两张表可以共同描述同一个内容的字段 名称可以不同 但是含义一般是一样的。
-- 比如：员工表和部门表的关联 
-- 内连接：如果员工没有部门编号就查不出来了，反之部门里面没有员工也查不出来。
select * from emp inner join dept
on emp.deptno=dept.deptno

-- 语法2：简化语法
select * from 表1,表2,表3
where 关联条件(表1.X = 表2.X and 表2.X = 表3.X)
```

### 2. 左外连接

```sql
-- 关键字：left join on
--         左表（主）  基准   右表
select * from 表1 left join 表2 on 关联条件
-- 主表的数据会全部展示，副表只会展示满足关联条件的数据
```

### 3. 右外连接

```sql
-- 关键字：right join on
--            左表    基准   右表(主)
select * from 表1 right join 表2 on 关联条件
-- 主表的数据会全部展示，副表只会展示满足关联条件的数据

-- 例子： 查询所有员工及其部门信息，包括那些还不属于任何部门的员工。
-- 外连接，可以为null的为主表
select e.* from emp e left join dept d
on d.deptno=e.deptno;

select e.* from dept d right join emp e
on d.deptno=e.deptno;
```

### 4. 全外连接

```sql
-- 关键字：full join on
--         左表（主）  基准   右表(主)
select * from 表1 full join 表2 on 关联条件
-- 主表的数据会全部展示，副表只会展示满足关联条件的数据
```

### 5. 自连接

> 自连接比较特殊，不是一种连接类型，而是一种连接的使用方式，本质上讲就是自己连接自己，把一张表当成两张表来处理。一般来说有一些非常抽象的题目通过自连接非常好解决。

```sql
-- 查询同一个学生，课程3-105 比 课程6-166成绩高的学生
select * 
from t_score s1, t_score s2
where s1.sno=s2.sno and s1.cno="3-105" and s2.cno="6-166" and s1.degree>s2.degree;
```

