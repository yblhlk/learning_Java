## 使用VirtualBox连接虚拟机

* 点击工具->全局设定：在全局设定里设置虚拟机的存放位置，要求不能放在VirtualBox的文件夹中
  ![image-20240904103430347](D:\AppData\Typora\typora-user-images\image-20240904103430347.png)

  ![image-20240904100227461](D:\AppData\Typora\typora-user-images\image-20240904100227461.png)

* 点击工具->导入：导入虚拟机镜像文件
  ![image-20240904103516681](D:\AppData\Typora\typora-user-images\image-20240904103516681.png)

  ![image-20240904100245799](D:\AppData\Typora\typora-user-images\image-20240904100245799.png)

* 下一步，名称可以随便改，点击完成。

* 打开虚拟机设置，配置网络
  ![image-20240904103634329](D:\AppData\Typora\typora-user-images\image-20240904103634329.png)

  ![image-20240904100700771](D:\AppData\Typora\typora-user-images\image-20240904100700771.png)

* 右击桌面右下角网络图标，打开网络设置，查看两个网线和无线网卡的名称。![image-20240904101612188](D:\AppData\Typora\typora-user-images\image-20240904101612188.png)

* 在VirtualBox中为虚拟机桥接网络，选择网络，哪个有网络选哪个，名称要对应![image-20240904101503731](D:\AppData\Typora\typora-user-images\image-20240904101503731.png)

* 启动虚拟机
  ![image-20240904103712738](D:\AppData\Typora\typora-user-images\image-20240904103712738.png)

* 选第一个（我也不知道为什么）
  ![image-20240904103940838](D:\AppData\Typora\typora-user-images\image-20240904103940838.png)

* 输入账号密码（初始账号：root，初始密码：123456）
  ![image-20240904104209498](D:\AppData\Typora\typora-user-images\image-20240904104209498.png)

* 登录成功
  ![image-20240904104515050](D:\AppData\Typora\typora-user-images\image-20240904104515050.png)

* 修改窗口大小
  ![image-20240904110940440](D:\AppData\Typora\typora-user-images\image-20240904110940440.png)

* 使用cd命令进入到/etc/sysconfig/network-scripts/目录下，输入ls查看目录，我们要修改ifcfg-XXX文件
  ![image-20240904111137462](D:\AppData\Typora\typora-user-images\image-20240904111137462.png)

* 在windows中打开cmd，输入ipconfig查看当前网络下的本机ip和网关
  找到正在联网的网卡查看它的ip和网关

  ![image-20240904112058184](D:\AppData\Typora\typora-user-images\image-20240904112058184.png)

* 修改Linux的ip地址
  进入ifcfg-XXX文件进行修改

  ![image-20240904113910173](D:\AppData\Typora\typora-user-images\image-20240904113910173.png)

* 重启Linux网络service network restart
  ![image-20240904114256629](D:\AppData\Typora\typora-user-images\image-20240904114256629.png)

* 通过Xshell连接Linux![image-20240904114710569](D:\AppData\Typora\typora-user-images\image-20240904114710569.png)
  ![image-20240904114859869](D:\AppData\Typora\typora-user-images\image-20240904114859869.png)

