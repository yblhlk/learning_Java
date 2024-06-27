## 深拷贝
![img_1.png](img_1.png)
![img_2.png](img_2.png)

> Java中如何实现深克隆？
> 让对象的引用类型成员也继承Cloneable接口，并重写clone()方法
 
> 小细节：
> 更改引用类型成员的指向，拷贝对象不会跟着改变。