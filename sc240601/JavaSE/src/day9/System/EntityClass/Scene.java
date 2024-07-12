package day9.System.EntityClass;

import java.io.Serializable;
import java.util.Objects;

// 模拟使用场景类
public class Scene implements Serializable {
    String type; // 被使用的元素（通话时间、短信数量、流量）
    int data; // 使用的数量
    String description; //对使用场景的描述
    double price; // 如果对应元素消耗完时，本次使用需要的花费

    public Scene(String type, int data, String description, double price) {
        this.type = type;
        this.data = data;
        this.description = description;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "场景描述{" +
                "消耗内容：'" + type + '\'' +
                ", 消耗数量：" + data +
                ", 场景描述：'" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scene scene = (Scene) o;
        return data == scene.data && Double.compare(scene.price, price) == 0 && Objects.equals(type, scene.type) && Objects.equals(description, scene.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, data, description, price);
    }
}
