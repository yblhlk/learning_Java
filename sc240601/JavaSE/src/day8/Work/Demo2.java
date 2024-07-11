package day8.Work;

// 工厂模式
// 属于设计模式中的创建型模式，其实就是将创建对象的过程封装起来。
// 作用是用于将对象的创建和使用进行分离，这样就可以实现解耦合
// 同时工厂模式就可以统一的管理对象的创建方式 和 创建的个数（能控制实现单例模式）就可以降低程序资源消耗

import day5.Class.Animal;

// 工厂模式分类
// 1. 简单工厂模式：包含一个工厂类，该类提供一个方法，根据方法参数来创建不同类型的对象。
// 2. 工厂方法模式：
// 3. 抽象工厂模式：
// 4. 静态工厂模式：
public abstract class Demo2 {
    public abstract void test();
}

class D1 extends Demo2{
    public void test() {
        System.out.println("测试工厂模式1");
    }
}

class D2 extends Demo2{
    public void test() {
        System.out.println("测试工厂模式2");
    }
}
// 1. 简单工厂模式的实现： 注意工厂模式是用来创建其他类的对象的
class SimpleFactory {
    // 要根据
    public static Demo2 getBean(String type) {

        return null;
    }

    // 应用场景：
    //当对象创建的逻辑并不复杂时，而且对象不太会发送变化时，
}

// 2. 工厂方法模式：需要定义一个创建对象的接口，但是
// 就是把简单工厂的工作交给自己的子类来完成。
interface MethodFactory {
    Demo2 getBean();
}
class FactoryA implements MethodFactory {
    public  D1 getBean() {
        return new D1();
    }
}

class FactoryB implements MethodFactory {
    public  D2 getBean() {
        return new D2();
    }
}

//工厂方法模式的应用场景：当一个类无法预知要创建的对象时，比如一个框架 定义一组接口具体的实现是由于框架使用者提供 一般是通过子类或子类插件 来实现具体对象的创建逻辑
// 这种设计模式在框架中使用饭菜广泛
// 比如：java中SSM框架中spring框架和Mybatis都使用了工厂方法模式……
// Spring中BeanFactory接口 Mybatis中SqlSessionFactory


// 抽象工厂模式：它提供了一个接口，用来创建一系列相关对象 而不需要向上面一样指定他们的具体实现
interface AbstractFactory {
    ProductA getBeanA();
    ProductB getBeanB();
}
// 具体工厂1 ： 按照第一种逻辑实现(活动时间）
class Factory1 implements AbstractFactory {
    @Override
    public ProductA getBeanA() {
        return new ProductAImpl2();
    }

    @Override
    public ProductB getBeanB() {
        return new ProductBImpl2();
    }
}
// 产品1的接口
interface ProductA {
    void productA();
}
// 具体产品的实现：
class ProductAImpl implements ProductA{
    public void productA() {
        System.out.println("开始删除产品A1");
    }
}
class ProductAImpl2 implements ProductA{
    public void productA() {
        System.out.println("开始删除产品A2");
    }
}

// 产品2的接口
interface ProductB {
    void productB();
}
// 具体产品的实现：
class ProductBImpl implements ProductB{
    public void productB() {
        System.out.println("开始删除产品B1");
    }
}
class ProductBImpl2 implements ProductB{
    public void productB() {
        System.out.println("开始删除产品B2");
    }
}