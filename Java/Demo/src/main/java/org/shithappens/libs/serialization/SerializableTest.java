package org.shithappens.libs.serialization;

import org.shithappens.libs.entities.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试
 *
 * https://mp.weixin.qq.com/s/ptPmYbBiQSdA9CJMYE5WKg
 * @author 80338398
 * @date 2022/08/08
 */
public class SerializableTest {


    public static void main(String[] args) throws Exception {
        Hi hi = new Hi("dsf", 123);
        List<Hi> list = new ArrayList<>();
        list.add(hi);
        Object obj = list;

        List<Hi> listAfter = (List<Hi>) list;

        User user = new User();
        user.setName("tyshawn");
        user.setAge(18);
        user.setSex("woman");
        System.out.println("序列化前的结果: " + user);

        serialize(user);
        user.setSignature("sign test");

        User dUser = deserialize();
        System.out.println("反序列化后的结果: "+ dUser);
    }


    private static class Hi {
        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Hi(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }

    /**
     * 序列化和反序列化
     *
     *
     * 序列化：把对象转换为字节序列的过程称为对象的序列化.
     *
     * 反序列化：把字节序列恢复为对象的过程称为对象的反序列化.
     */

    /**
     * 什么时候需要用到序列化和反序列化呢?
     *  * 只要我们对内存中的对象进行持久化或网络传输, 这个时候都需要序列化和反序列化.
     */

    /**
     * 实现序列化和反序列化为什么要实现Serializable接口?
     * * 在Java中实现了Serializable接口后, JVM会在底层帮我们实现序列化和反序列化, 如果我们不实现Serializable接口, 那自己去写一套序列化和反序列化代码也行,
     */

    /**
     * 实现Serializable接口就算了, 为什么还要显示指定serialVersionUID的值?
     *  在实际开发中, 不显示指定serialVersionUID的情况会导致什么问题? 如果我们的类写完后不再修改, 那当然不会有问题, 但这在实际开发中是不可能的, 我们的类会不断迭代, 一旦类被修改了, 那旧对象反序列化就会报错. 所以在实际开发中, 我们都会显示指定一个serialVersionUID, 值是多少无所谓, 只要不变就行.
     */
    private static void serialize(User user) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("D:\\111.txt")));
        oos.writeObject(user);
        oos.close();
    }

    private static User deserialize() throws Exception{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("D:\\111.txt")));
        return (User) ois.readObject();
    }

    /**
     * Java序列化的其他特性
     * 先说结论, 被transient关键字修饰的属性不会被序列化, static属性也不会被序列化.
     *
     * static属性为什么不会被序列化?
     * 因为序列化是针对对象而言的, 而static属性优先于对象存在, 随着类的加载而加载, 所以不会被序列化.
     */

}
