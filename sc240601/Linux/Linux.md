## Linux

### 0. 使用VirtualBox连接虚拟机

> VirtualBox 类似于jdbc是用来连接虚拟机的。
>
> ova文件只能在VirtualBox中使用。

> * 点击工具->全局设定：在全局设定里设置虚拟机的存放位置，要求不能放在VirtualBox的文件夹中
>   ![image-20240904103430347](D:\Desktop\gitee\java-learning\sc240601\Linux\img\VirtualBox连接虚拟机1.png)
>
>   ![image-20240904100227461](D:\Desktop\gitee\java-learning\sc240601\Linux\img\VirtualBox连接虚拟机2.png)
>
> * 点击工具->导入：导入虚拟机镜像文件
>   ![image-20240904103516681](D:\Desktop\gitee\java-learning\sc240601\Linux\img\VirtualBox连接虚拟机3.png)
>
>   ![image-20240904100245799](D:\Desktop\gitee\java-learning\sc240601\Linux\img\VirtualBox连接虚拟机4.png)
>
> * 下一步，名称可以随便改，点击完成。
>
> * 打开虚拟机设置，配置网络
>   ![image-20240904103634329](D:\Desktop\gitee\java-learning\sc240601\Linux\img\VirtualBox连接虚拟机5.png)
>
>   ![image-20240904100700771](D:\Desktop\gitee\java-learning\sc240601\Linux\img\VirtualBox连接虚拟机6.png)
>
> * 右击桌面右下角网络图标，打开网络设置，查看两个网线和无线网卡的名称。![image-20240904101612188](D:\Desktop\gitee\java-learning\sc240601\Linux\img\VirtualBox连接虚拟机7.png)
>
> * 在VirtualBox中为虚拟机桥接网络，选择网络，哪个有网络选哪个，名称要对应![image-20240904101503731](D:\Desktop\gitee\java-learning\sc240601\Linux\img\VirtualBox连接虚拟机8.png)
>
> * 启动虚拟机
>   ![image-20240904103712738](D:\Desktop\gitee\java-learning\sc240601\Linux\img\VirtualBox连接虚拟机9.png)
>
> * 选第一个（我也不知道为什么）
>   ![image-20240904103940838](D:\Desktop\gitee\java-learning\sc240601\Linux\img\VirtualBox连接虚拟机10.png)
>
> * 输入账号密码（初始账号：root，初始密码：123456）
>   ![image-20240904104209498](D:\Desktop\gitee\java-learning\sc240601\Linux\img\VirtualBox连接虚拟机11.png)
>
> * 登录成功
>   ![image-20240904104515050](D:\Desktop\gitee\java-learning\sc240601\Linux\img\VirtualBox连接虚拟机12.png)
>
> * 修改窗口大小
>   ![image-20240904110940440](D:\Desktop\gitee\java-learning\sc240601\Linux\img\VirtualBox连接虚拟机13.png)
>
> * 使用cd命令进入到/etc/sysconfig/network-scripts/目录下，输入ls查看目录，我们要修改ifcfg-XXX文件
>   ![image-20240904111137462](D:\Desktop\gitee\java-learning\sc240601\Linux\img\VirtualBox连接虚拟机14.png)
>
> * 在windows中打开cmd，输入ipconfig查看当前网络下的本机ip和网关
>   找到正在联网的网卡查看它的ip和网关
>
>   ![image-20240904112058184](D:\Desktop\gitee\java-learning\sc240601\Linux\img\VirtualBox连接虚拟机15.png)
>
> * 修改Linux的ip地址
>   进入ifcfg-XXX文件进行修改
>
>   ![image-20240904113910173](D:\Desktop\gitee\java-learning\sc240601\Linux\img\VirtualBox连接虚拟机16.png)
>
> * 重启Linux网络service network restart
>   ![image-20240904114256629](D:\Desktop\gitee\java-learning\sc240601\Linux\img\VirtualBox连接虚拟机17.png)
>
> * 通过Xshell连接Linux![image-20240904114710569](D:\Desktop\gitee\java-learning\sc240601\Linux\img\VirtualBox连接虚拟机18.png)
>   ![image-20240904114859869](D:\Desktop\gitee\java-learning\sc240601\Linux\img\VirtualBox连接虚拟机19.png)
>
> * Xshell 中 Alt+回车 切换全屏
>
> * 使用Xftp快速传输文件
>   ![image-20240904164435356](D:\Desktop\gitee\java-learning\sc240601\Linux\img\VirtualBox连接虚拟机20.png)
>
> * 直接双击文件，就可以互相传输。

### 1.Linux安装

> - 安装Vbox 运行不同系统的镜像文件
>   - Vbox 6 ==> win 10
>   - Vbox7 ==> win 11
> - 导入虚拟机镜像 *.ova文件
> - 设置网络 选择好对应的网卡
>   - 使用无线网卡的  选择 Wireless...
>   - 使用有线网卡的  选择 GbE Family Controller...
> - 启动linux镜像    root  123456(密码是隐式的)
> - 修改IP地址: 因为vbox操作linux很难操作 所以安装Xshell去链接 vbox启动的虚拟机 去操作linux   需要让window的ip 和 linux 的ip 再同一个网络

### 2.修改Linux的IP地址

> - 进入Linux网卡目录
>
>   ```
>   cd /etc/sysconfig/network-scirpts
>   ```
>
> - 找到网卡文件 文件名一般叫做 `ifcfg-xxx`
>
> - 查看Windows的ip地址 （`ipcofig`）
>
>   ```
>   在cmd中通过ipcofig来查看Windows的ip地址，注意是看有网络的那个。
>
>   IPv4地址(不能重复): 192.168.26.82
>   子网掩码(控制网段): 255.255.0.0
>   默认网关(是否访问外网): 192.168.1.26
>   ```
>
> - 修改linux的ip地址(即修改ifcfg-xxx文件)
>
>   ```java
>   Ip地址(和Windows的Ip在同一个网段，但但一定要配置不同的ip): 192.168.?.?
>   子网掩码(必须和window一致):  255.255.0.0
>   网关(如果需要连接外网 只需要window一致): 192.168.1.26
>   ```
>   
>   ![image-20240904113910173](D:\Desktop\gitee\java-learning\sc240601\Linux\img\VirtualBox连接虚拟机16.png)
>
>   比如: 有的同学设置的手机流量 家里的无限
>   ip地址: 192.168.0.?
>   子网掩码: 255.255.255.0
>
>   ```
> 
>   ![image-20240906144958281](D:\AppData\Typora\typora-user-images\image-20240906144958281.png)
> 
>   - vi ifcfg-eXXX  : 进入或者创建 或者 编辑命令
>   - 输入 i 进入插入模式可以修改里面的内容了
>   - 修改完后  输入esc 退出插入模式
> 
>     - :q 退出    :w 保存    :wq 保存并退出   :x 保存并退出     :q!  强制退出
> 
> - 通过命令 重启网络 配置才会生效
> 
>   ```shell
>   service network restart
>   ```
>
> - 重启Linux网络service network restart
>   ![image-20240904114256629](D:\Desktop\gitee\java-learning\sc240601\Linux\img\VirtualBox连接虚拟机17.png)
> - 在Windows的cmd里通过`ping Linux的ip地址`来检查是否修改成功
>   ![image-20240906151545635](D:\Desktop\gitee\java-learning\sc240601\Linux\img\检查Linux的IP地址是否修改成功.png)
>


### 3.什么是Linux

> linux是一种操作系统， 内核是由芬兰程序员林纳斯-托瓦兹 由于个人兴趣爱好  编写发布到网上  是一套免费  基于unix系统研发的，支持多用户、多任务、多CPU、安全性特别高、稳定性特别好的一种服务器操作系统。

#### 3.1 unix和linux 

> unix是美国贝尔公司研发的立足于服务器操作系统，是一款商业软件, 需要收费的。
>
> Linux就是一种类似于Unix系统，所以开发思想都是借鉴于Unix，兼容Unix中的开源软件、操作命令，使用方式基本一模一样。

#### 3.2 Linux优势

> - 开源  免费的
>
> - 模块化程度高:  内核设计非常精巧 主要分以下几大模块:
>   可以根据用户的需要  内核中 自由组合搭配  使用linux系统更加小巧
>   - 进程调度
>   - 内存管理
>   - 进程间的通信 
>   - 虚拟化文件
>   - 网络接口
>
> - 广泛的硬件支持:  支持多种微处理器  目前已经成功移植数十种主流硬件平台  几乎可以运行所有处理器上
>
> - 安全性和稳定性: linux集成unix稳定性和安全性  linux连续运行一年不宕机是比较普遍的   特别适合做服务器 
>
> - 低配置要求:  linux对硬件支持很低的，512M就可以正常运行，而且linux还可以选择是否安装图形化界面   但是一般公司不会安装这个  所以这样更加降低了linux对硬件的支持。

### 4. Linux目录结构

```
/:  最顶层目录 根目录
bin: 用户二进制文件  命令文件(比如: cd ls ...可以生效)
boot: 引导程序文件 加载系统使用
dev:  设备文件  比如: 终端设备 USB  移动端 ...
mnt:  用于挂载文件系统的地方  比如:需要通过命令挂载类似于优盘这种资源..
lib:  系统库文件(环境 依赖)
home: 存储普通用户的个人文件夹
root: linux超级管理员root目录
proc: 进程目录(类似于window中的任务管理器....)

重点目录:
usr: 用户目录 存放用户级的文件  用户安装的软件一般都会保存在这里
etc: 配置文件目录 所有的配置文件都保存在这里 比如:配置ip  环境变量 配置防火墙  配置定时任务...
```



### 5.linux常用命令--- 重点

熟练记忆操作linux系统的不同命令   面试官的考点

#### 5.1 通用命令   

```
shutdown -h now: 立即关闭系统
reboot : 重启
logout : 注销
clear  : 清空界面
ifconfig: linux查看ip地址详细信息
ip  addr: linux查看ip地址基本信息
pwd    : 查看当前包 工作空间 

cd     :  切换目录  类似于window中cmd使用方式
cd .   : 表示返回到当前目录
cd ..  : 表示返回上一级目录
cd -   : 表示返回上次的目录 类似于浏览器的后退


ls     : 查看目录中文件
ls -l  : 以长格式的形式显示目录中的文件(类似于window详细信息)
ll     : 类似于 ls -l 都是可以查看详情的


mkdir   : 创建目录 如果父级目录不存在 则创建失败
mkdir -p: 如果父级目录不存在 可以逐级创建
rmdir   : 删除空目录   
rm      : 删除文件
rm -rfv : 递归删除 r表示递归  f强制  v展示
vi      : 可以查看 可以编辑  如果查看的文件不存在 帮你创建文件
touch   : 创建文件
cp      : 复制文件  不能复制整个目录
cp -r   : 递归复制整个目录
mv      : 可以重命名(修改新文件名和原文件名在同一个目录)
          mv  原文件  新文件
          也可以剪切(修改新文件名和原文件名不在同一个目录)
          mv  原文件  a/b/c/新文件
```



#### 5.2 查看命令

```
vi        :  可以有查看的功能  需要结合:q 退出
cat       :  查看文件  可以查看文件的全部内容  不适合读取数据很大的文件
tail -数字 :  查看文件 根据数字定义 查看文件后几行  一般查看tomcat日志文件可以比较适合 来查看服务器是否有异常 或者是否启动成功
head -数字 :  查看文件 根据数字定义 查看文件前几行   查看配置文件 查看约束  比较适合
more -数字 :  查看文件 可以动态分页显示数据  再结合ctrl+b/f 切换上下一页  比较适合读取数据量比较大的文件
             回车逐行读，空格下一页，h提示，q退出
```

#### 5.3 打包压缩命令

```java
压缩命令:
tar [操作类型][操作选项] f 压缩包名字  需要压缩的文件(通常是通配符)

解压命令:  在当前目录解压
tar [操作类型][操作选项] f 解压的压缩包名
解压命令:  想指定位置解压
tar [操作类型][操作选项] f 解压的压缩包名 -C  解压的位置
-- 加-C来指定解压位置

操作类型:很重要 是必选的
-cf  : 建立压缩文件  -cvf:建立并查看压缩文件
-xf  : 解压文件
-tf  : 查看压缩包中文件
-rf  : 向压缩包追加文件
-uf  : 更新压缩包中的某个文件
-f 选项后面紧跟着的就是归档文件的路径和名称。

操作选项:可选的
-z  : 压缩包类型是gzip格式的 一般要添加这个
-j  : 压缩包类型是bz2格式的 一般要添加这个
linux默认格式: tar格式
-v  : 显示压缩或者解压的过程

```

![image-20240909195137771](D:\Desktop\gitee\java-learning\sc240601\Linux\img\打包命令tar的进阶使用查看压缩包内容tf追加内容rf更新内容uf.png)

#### 5.4 用户创建和授权命令

```java
useradd :用于添加用户
可选项:
- u : 指定uid   不指定的话 当前id最大值+1
- g : 指定组id  不指定的话 和uid是一致的

passwd: 给用户设置密码
比如: passwd 用户名 回车 弹出密码让你输入(隐式)

userdel: 删除用户
注: 只有超级管理员root可以这么操作


chmod  : 用户改变文件或者目录 使用权限   只有管理员root或者该文件的创建者可以使用 
在linux中权限只分三种:
r: 读取 对应数值4
w: 写入 对应数值2
x: 执行 对应数值1

添加权限语法: u创建者 g所属组 o其他用户
	chmod u=rwx,g=rx,o=rw  文件或者目录
添加权限语法2: 可以通过数值来表示不同的权限
	chmod 731 文件或者目录 (创建者都有 所属组有写入和执行  其他只有执行)

```

#### 5.5 防火墙命令  

```
语法: systemctl  关键字  firewalld
systemctl restart firewalld : 开启防火墙
systemctl stop firewalld : 关闭防火墙
systemctl enable firewalld : 启用防火墙
systemctl disable firewalld : 禁用防火墙
systemctl status firewalld : 查看防火墙状态
```



### 6.通过linux部署tomcat服务器但数据库在本地

> 注意下面的案例是==项目部署在Linux上，但数据库在本地(Windows)中==，所以jdbc要改为WindowsIP地址
> 如果数据库也在Linux，jdbc就使用localhost就可以了。

#### 6.1 前后端不分离的SSM项目

> * 部署项目满足几个前提
>
>   ```
>   1.本地的数据库 需要开启ip访问(远程访问)
>   	因为数据库在本地，所以要在数据库中创建一个支持远程访问的用户：用户名随意@% 【注意主机一定要是%】
>   	然后测试远程连接用户是否可用
>             
>   2.SSM.war放入linux   访问windows本机的数据库
>   	修改jdbc的配置文件的url：url=Windows的IP地址:3306/数据库名...
>   	还要修改jdbc的user用户：uesr=设置的远程连接用户
>             
>   3.linux  关闭防火墙 或 开放访问端口
>   ```
>   
>   ![image-20240909203119875](D:\Desktop\gitee\java-learning\sc240601\Linux\img\Mysql创建远程连接用户.png)
>   ![image-20240909211253997](D:\Desktop\gitee\java-learning\sc240601\Linux\img\测试远程连接用户是否可用.png)
>   ![image-20240910101458238](D:\Desktop\gitee\java-learning\sc240601\Linux\img\打包前要修改jdbc配置.png)
>
> - 在Windows(本地)的IDEA中利用maven的Packege插件进行打包
>   <img src="D:\Desktop\gitee\java-learning\sc240601\Linux\img\使用maven的package插件进行打包.png" alt="image-20240909213710075" style="zoom:35%;" />
>
>   ==打好的包在target目录下==
>   ![image-20240909213829995](D:\Desktop\gitee\java-learning\sc240601\Linux\img\打好的包在target目录下.png)
>   pom的编译版本和maven设置的版本不一致打包的时候会出现错误：
>   
>   ![image-20240910100829231](D:\Desktop\gitee\java-learning\sc240601\Linux\img\pom的编译版本和maven设置的版本不一致打包的时候会出现错误.png)
>   
> - 在Linux的tomcat上部署war包：
>
>   - 安装Tomcat：通过linux的tar命令 解压Tomcat的压缩包（去官网下载Linux版本）
>   - 利用Xftp工具 传输war包 到linux 中tomcat目录(webapps)
>     - 如果想添加项目前缀  war包的文件名 改成项目前缀
>     - 如果没有添加项目前缀 先删除ROOT包 再war包名字改成大写ROOT.war
>       ==不改名字就要加上项目前缀，war包的名字就是项目前缀==<img src="D:\AppData\Typora\typora-user-images\image-20240909214109083.png" alt="image-20240909214109083" style="zoom: 50%;" />
>   - 修改端口号  利用tomcat目录(server.xml)
>     - tomcat-->conf-->server.xml-->Connect标签   port属性
>       <img src="D:\AppData\Typora\typora-user-images\image-20240909214718034.png" alt="image-20240909214718034" style="zoom: 33%;" />
>   - 启动tomcat：
>     通过bin目录 `startup.sh` 启动   
>     通过bin目录`shutdown.sh` 关闭   是linux使用的
>   - 查看日志 观察服务器启动是否成功:  `tail -10 ./log/catalina.2024-09-05.log`![image-20240910152838290](D:\Desktop\gitee\java-learning\sc240601\Linux\img\查看tomcat是否启动成功.png)
>     查看进程 观察服务器是否启动成功： `ps -ef | grep tomcat`
>   - 测试:
>     在网站上输入：LinuxIP地址:在server.xml中设置的端口号/请求
>
> - 关闭服务器:
>
>   - ./shutdown.sh  关闭服务器  只能再tomcat中使用
>
>   - ps -ef | grep 程序    :  根据 程序名去搜索进程  目的是为了找到进程id
>
>     kill -9 进程id       : 强制杀死指定进程   可以关闭任何程序

#### 6.2 前后端分离项目

> * 前后端分离项目部署在Linux上的注意前提:
>
>   ```java
>   1.sm.jar放入linux   访问windows本机的数据库
>   	修改jdbc的配置文件的url：url=Windows的IP地址:3306/数据库名...
>   	还要修改jdbc的user用户：uesr=设置的远程连接用户
>       还要修改springboot的核心配置文件的连接池的url：url=Windows的IP地址:3306/数据库名...
>   
>   2.数据库 需要开启ip访问(远程访问)
>   
>   3.linux  防火墙关闭或开放访问端口
>   
>   4.前端项目 跨域地址  http://linux的ip地址:9999 
>   ```
>
> * 注释pom.xml中的<skip>
>     
> * 修改jdbc的配置文件和springboot的核心配置文件
>     修改jdbc和连接池的数据库url
>
> * 配置端口号（在springboot的核心配置文件中配置server.port=8888）
>
> * 通过mavan 将不报错的项目打成jar包（==先clean后package，不clean存在target目录就有可能打包失败==）
>
>   ```xml
>   <configuration>                		
>       <mainClass>com.sc.sm.SmApplication</mainClass>
>   	<!--这个必须要注释 如果不注释  通过linxu启动jar包项目 找不到主类-->
>   	<!--<skip>true</skip>-->
>   </configuration>
>   ```
>
>   <img src="D:\Desktop\gitee\java-learning\sc240601\Linux\img\打jar包.png" alt="image-20240909220535577" style="zoom:50%;" />
>
> * 部署jar包
>
>   * 通过Xftp工具传输jar包到linux (是可以随便放的  内置tomcat服务器)
>
>   * 通过命令 启动java包项目 `java -jar jar包名 &`
>
>     ```
>     java -jar xxx.jar    (ctrl+c结束)
>     java -jar xxx.jar &  (ctrl+c进行后台运行)
>         
>     -- 如果端口号被占用  可以不用修改jar包 也可以动态修改端口号
>     java -jar -Dserver.port=XXXX  xxx.jar 
>     ```
>
>     ![image-20240910203441653](D:\Desktop\gitee\java-learning\sc240601\Linux\img\查看jar包是否运行起来.png)
>
> * 前端
>   ![image-20240910202445005](D:\Desktop\gitee\java-learning\sc240601\Linux\img\修改vue访问后端服务器.png)



### 7. Linux防火墙

> ​	防火墙是系统的安全机制，项目上线运行时 一定会开防火墙，但是开启防火墙后，其他计算机就无法正常访问linux中的某个程序了，`所以需要针对某些特定程序开放指定的端口`，再开防火墙。
> ![image-20240910211743815](D:\Desktop\gitee\java-learning\sc240601\Linux\img\查看防火墙状态.png)

#### 7.1 防火墙命令

> 语法: systemctl  关键字  firewalld
>
> ```properties
> 语法: systemctl  关键字  firewalld
> systemctl restart firewalld : 开启防火墙
> systemctl stop firewalld : 关闭防火墙
> systemctl enable firewalld : 启用防火墙
> systemctl disable firewalld : 禁用防火墙
> systemctl status firewalld : 查看防火墙状态
> ```

#### 7.2 配置防火墙开启端口

> - 找到linux防火墙配置文件
>
>   ```
>   vi /etc/firewalld/zones/public.xml
>   ```
>
> - 配置防火墙开放端口
>   ==注意protocol不要写错了，port="端口号"后面没有;==
>
>   ```xml
>   <!--开发一个端口  rule标签可以配置多个-->
>   <rule family="ipv4">
>           <port protocol="tcp" port="9999"/>
>           <accept/>
>   </rule>
>   ```
>
>   ![image-20240906142047722](D:\AppData\Typora\typora-user-images\image-20240906142047722.png)
>
> - 重启防火墙 配置才会生效
>
>   ```
>   systemctl restart firewalld
>   ```



### 8.Linux配置java环境变量

> - 下载jdk   可以再官网下载(*.tar.gz)  也可以通过命令下载
>
> - 通过Xftp 传输到 linux中  如果是命令下载 就不用传递了
>
> - 安装jdk(解压即可)
>
> - 配置java环境变量 `vi /etc/profile`
>   ![image-20240911093249738](D:\Desktop\gitee\java-learning\sc240601\Linux\img\Linux配置java环境变量.png)
>
>   ```
>   vi /etc/profile  打开编辑环境变量文件
>   -- 添加如下配置:
>   -- 1.添加两个变量导出 JAVA_HOME 和 CLASSPATH
>   export JAVA_HOME=linux解压JDK目录
>   export CLASSPATH=百度搜直接粘贴根window几乎一样 
>          但是linux间隔符是冒号   
>          .:%JAVA_HOME%/lib/dt.jar:%JAVA_HOME%/lib/tools.jar
>                 
>   -- 2.追加PATH变量 添加一个配置 导出  $PATH 和 $JAVA_HOME 调用定义好的变量  间隔符是冒号
>   export PATH=$PATH : $JAVA_HOME/bin
>         
>   -- 3.通过命令source /etc/profile  刷新配置(否则不生效)
>         
>   -- 4.测试 java -version   javac -version
>   ```
>
> 

### 9.Linux安装mysql

#### 9.1 先保证linux是联网状态 

> 首先 需要网卡设置成桥接模式，`Linux ip地址中的网关,改成和windows的网关一致` ，这样配置好后 windows 有网的 Linux也可以联网。（etc/sysconfig/network-scripts/ifcfg-XXX)
> ![image-20240911094522791](D:\Desktop\gitee\java-learning\sc240601\Linux\img\Linux桥接网络需要网关一致.png)
>
> ![image-20240911094839901](D:\Desktop\gitee\java-learning\sc240601\Linux\img\检查Linux是否有网络.png)
>
> ```
> windows: 
> IPv4 地址 . . . . . . . . . . . . : 192.168.26.235
> 子网掩码  . . . . . . . . . . . . : 255.255.0.0
> 默认网关. . . . . . . . . . . . . : 192.168.1.26
> 
> linux:
> IPADDR=192.168.100.250
> NETMASK=255.255.0.0
> GATEWAY=192.168.1.26
> 
> 测试:是否联网  
> ping www.baidu.com
> ```
>

#### 9.2 先去查看是否安装过mysql  然后卸载

> - 查看版本 
>
>   ```
>   -- mariadb是linux版本安装mysql的分支
>   rpm -qa | grep mariadb
>   
>   mariadb-libs-5.5.56-2.el7.x86_64
>   ```
>
> - 卸载
>
>   ```
>   rpm -e --nodeps mariadb-libs-5.5.56-2.el7.x86_64
>   ```
>
> - 再检查版本
>
>   ```
>   rpm -qa | grep mariadb
>   ```
>

#### 9.3 通过命令安装mysql

- 下载并安装： yum 命令(需要联网)

  ```
  yum install https://dev.mysql.com/get/mysql57-community-release-el7-9.noarch.rpm
  
  -- 如果出现Is this ok [y/d/N]: Y  输入Y即可
  
  ```

- 安装mysql服务  

  ```
  yum install -y mysql-server --nogpgcheck
  这个--nogpgcheck表示：跳过GPG检查
  ```

- 命令启动mysql服务

  ```
  systemctl  start  mysqld.service
  ```

- 查看mysql初始密码是什么   root@localhost:  初始密码

  ```
  cat /var/log/mysqld.log | grep localhost
  
  -- x>dW96k#/hzd
  ```

- 使用这个密码登录mysql服务   默认身份localhost

  ```
  mysql -uroot -p  回车 让你输入密码
  
  如果想访问远程的mysql数据库
  mysql -h ip地址 -uroot -p  回车 让你输入密码
  ```

  

- 进入服务后 修改root账号密码   默认规则 要求特别严格必须有大写 小写 还有特殊字符 而且还有长度限制

  ```
  alter user 'root'@'localhost' identified by 'Tao123456.';
  ```

- 如果想设置简单的密码  修改mysql  的密码策略

  ```mysql
  -- 查看mysql密码策略
  SHOW variables LIKE 'validate_password%';
  
  --结果:
  +--------------------------------------+--------+
  | Variable_name                        | Value  |
  +--------------------------------------+--------+
  | validate_password_check_user_name    | OFF    |
  | validate_password_dictionary_file    |        |
  | validate_password_length             | 8      |
  | validate_password_mixed_case_count   | 1      |
  | validate_password_number_count       | 1      |
  | validate_password_policy             | MEDIUM |
  | validate_password_special_char_count | 1      |
  +--------------------------------------+--------+
  validate_password_policy: 修改密码策略
  -- 0 或者 Low    只会验证长度
  -- 1 或者 MEDIUM 验证长度 数字 大小写 特殊字符
  -- 2 或者 STRONG 验证长度 数字 大小写 特殊字符、字典
  set global validate_password_policy=0;
  set global validate_password_length=1;
  ```

  

- 开启mysql远程访问（支持通过ip地址的访问 不仅仅是localhost)

  ```sql
  -- @% 就表示远程访问
  create user root@'%' identified by 'root';
  -- 修改远程账号的密码
  alter user 'root'@'%' identified by '123456';
  ```

- 设置远程访问用户的权限

  ```
  -- 授权远程访问
  -- 语法规则：
  -- DCL  grant revoke
  -- grant 权限名 on 数据库.表 to 用户@'主机'
  -- 比如给root远程用户，授权sc数据库的所有表查询和插入权限：
  grant select,insert on sc.* to root@'%';
  -- 比如给root远程用户，授权所有数据库的所有表的所有操作权限;
  grant all privileges on *.* to root@'%';
  
  --刷新权限 或 重启mysql让上面的修改生效
  flush privileges;
  ```
  
- 开放防火墙端口号3306（etc/firewalld/zones/public.xml)
  ![image-20240911102558709](D:\AppData\Typora\typora-user-images\image-20240911102558709.png)

### 10. MySQL的备份和还原和定期备份

> * 手动备份：
>
>   ```sql
>   mysqldump -u账号 -p密码 --default-character-set=utf8 -B
>   -- 加上default-character-set选项就能识别中文了 注意是utf8 不是-8
>   -- B后面添加要备份的数据库，如果有很多数据库需要备份，则可以写多个 
>   -- 如果所有数据库都要备份-B后面加上选项 --all -databases
>   
>   --按日期生成文件名：
>   mysql> /usr/local/sql/test.$(date +%F).sql
>   --注意date后面有一个空格，不加空格不识别
>   ```
>
> * 还原数据库：前提是需要先登录mysql，在mysql中进行还原。
>
>   ```
>   source /usr/local/sql/xxx.sql
>   ```
>
> * 定期备份(自动备份)：cenos7系统 是通过cron来实现定期备份的任务，`cron` 是 Linux 和 Unix 系统中用于安排周期性执行任务的工具。主要负责定时任务的调度，可以根据用户需要来定义数据间隔，它便会自动执行任务。
>
>   ```sql
>   -- 1.先配置 cron job 配置文件
>   vi /etc/crontab
>   分钟 时 天 月 星期几  用户    执行什么命令
>    *  *  *  *  *   user-name command to be executed
>   --比如:11:58 执行mysq1备份任
>    58 11 *  *  *     root 备份任务
>   --比如:每周一到周五 凌晨零点 开始执行备份任务
>    0  0  *  * 1-5    root 备份命令
>   --比如:每分钟执行一次数据库备份任务
>    */1 * *  *  *    root mysqldump -uroot -proot -B sc240601>/usr/local/sql/cron.$(date +\%Y-\%m-\%d-\%H:\%M:\%S).sql
>       
>   -- 2.重启cron服务
>   systemctl restart crond
>   ```
>

> `cron` 是 Linux 和 Unix 系统中用于安排周期性执行任务的工具。它允许用户安排在特定时间执行脚本或命令，无论是每天、每周还是每月等。`cron` 使用一个名为 `crontab`（cron table）的表来管理任务计划。每个用户可以有自己的 `crontab` 文件，而系统管理员可以管理一个用于系统任务的特别 `crontab`。
>
> ### 基本组成部分
>
> `cron` 系统基于两个主要部分组成：
>
> 1. **crontab 文件**：包含一系列的任务和时间安排。每个任务都在一行上指定，格式包括六个字段：分钟（0-59）、小时（0-23）、日（1-31）、月（1-12）、星期几（0-7，其中0和7都代表星期日），以及要执行的命令。
> 2. **cron 守护进程**：后台运行的服务，检查 `crontab` 文件，并在设定的时间执行指定的任务。
>
> ### crontab 语法
>
> 典型的 `crontab` 文件条目如下所示：
>
> ```
> # 分钟 小时 日 月 星期 命令  
>    30  4   *  *  *  /usr/bin/find / -name "core" -type f -print | xargs /bin/rm -f
> ```
>
> 此条目表示在每天凌晨 4:30 执行一个命令，该命令查找系统上名为 "core" 的文件并删除它们。
>
> - `*` 字符代表所有可能的值，所以如果所有的时间字段都是 `*`，那么命令会在每分钟执行。
> - 使用逗号 `,` 可以指定多个值，例如 `1,15` 在分钟字段中表示在第 1 和第 15 分钟时执行。
> - 使用短横线 `-` 可以指定一个范围，例如 `1-5` 在小时字段表示从 1 点到 5 点之间的每个小时执行。
>
> ### 如何使用 cron
>
> 1. **查看当前用户的 crontab**：
>
>    ```bash
>    bash复制代码
>    
>    crontab -l
>    ```
>
> 2. **编辑当前用户的 crontab**：
>
>    ```bash
>    bash复制代码
>    
>    crontab -e
>    ```
>
>    这将打开一个文本编辑器，允许你添加、修改或删除任务。
>
> 3. **删除当前用户的 crontab**：
>
>    ```bash
>    bash复制代码
>    
>    crontab -r
>    ```
>
> 4. **以特定用户执行或编辑 crontab**（需要管理员权限）：
>
>    ```bash
>    bash复制代码
>          
>    crontab -u <用户名> -e
>    ```
>
> ### 注意事项
>
> - 执行环境：`cron` 任务运行在一个简化的环境中，可能不包含用户的完整环境变量。因此，在 `cron` 任务中运行的脚本可能需要设置环境变量或使用绝对路径来执行命令。
> - 日志记录：默认情况下，`cron` 的输出（包括标准输出和错误输出）会通过电子邮件发送给任务的所有者。如果系统没有配置邮件服务，可能需要重定向输出到文件中。
> - 编辑工具：通过设置环境变量 `EDITOR`，可以指定 `crontab -e` 命令使用的默认编辑器，例如 `export EDITOR=vim`。
>
> `cron` 是系统管理员和用户自动化重复性任务的强大工具，了解其工作方式和如何正确配置它可以极大地提高系统的效率和可靠性。

 



## 问题记录

1. pom的编译版本和maven设置的版本不一致打包的时候会出现错误：

>![image-20240910100829231](D:\Desktop\gitee\java-learning\sc240601\Linux\img\pom的编译版本和maven设置的版本不一致打包的时候会出现错误.png)







