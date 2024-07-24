## 在IDEA中连接数据库

0. 右上角有一个Database
   ![image-20240718163839292](D:\Pictures\MySQL\在IDEA中连接数据库\image-20240718163839292.png)

1. 选择加号 -> 选择Data Source -> 选择要连![image-20240718163641776](D:\Pictures\MySQL\在IDEA中连接数据库\image-20240718163641776.png)

2. 点击Driver:MySQL 选择 Go to Driver
   ![image-20240718163907189](D:\Pictures\MySQL\在IDEA中连接数据库\image-20240718163907189.png)

3.  点击 + 导入驱动包， 在class中选择和包对应的全类名
   ![image-20240718163922748](D:\Pictures\MySQL\在IDEA中连接数据库\image-20240718163922748.png)
   ![image-20240718163947763](D:\Pictures\MySQL\在IDEA中连接数据库\image-20240718163947763.png)

4. 在左上角回到刚才连接MySQL的页面，填写账号，密码，URL
   ![image-20240718164018303](D:\Pictures\MySQL\在IDEA中连接数据库\image-20240718164018303.png)

5. 选择Test Connection测试连接, 出现√表示连接成功

   ![image-20240718164548223](D:\Pictures\MySQL\在IDEA中连接数据库\image-20240718164548223.png)

6. 选择ok完成连接

7. 新建查询页面

   有三种方法：

   * 快捷键Ctrl + Shift + q
   * 选择Query Console选项
   * 选择QL图标
   * 本质上都是选择Query Console选项
     ![image-20240718164632497](D:\Pictures\MySQL\在IDEA中连接数据库\image-20240718164632497.png)

8. 查找查询页面
   选择QL图标，在QL图标中能找到之前所有的查询页面
   ![image-20240718174402563](D:\Pictures\MySQL\在IDEA中连接数据库\image-20240718174402563.png)
   ==或是在Database Consoles目录下可管理所有查询页面（上图的Jump to Query Console Files选项可直接跳转）==
   ![image-20240718174557775](D:\Pictures\MySQL\在IDEA中连接数据库\image-20240718174557775.png)