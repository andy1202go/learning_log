package org.shithappens.libs.collections;

import com.alibaba.fastjson.JSON;
import org.shithappens.libs.entities.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public class Test {
    static Logger log = Logger.getLogger("SerializableTest");


    public static void main(String[] args) {
        List<String> emp = new ArrayList<>();
        emp.forEach(e -> log.info("e"));
        List<Integer> emp2 = new ArrayList<>();
        emp2.add(1);
        emp2.add(2);

        emp2.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        log.info(JSON.toJSONString(emp2));

        emp2.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        log.info(JSON.toJSONString(emp2));
        listSortTest();

    }

    private static void listSortTest() {
        /**
         *         sort test
         */

        log.info("List sort SerializableTest begin!!!");

        List<User> users = new ArrayList<User>() {{
            add(new User("a", 1, 1));
            add(new User("ab", 10, 10));
            add(new User("abc", 0, null));
        }};

        log.info("before sort list:"+ JSON.toJSONString(users));

        users.sort(Comparator.comparingInt(User::getAge));
        log.info("after sort [Comparator.comparingInt] list:"+JSON.toJSONString(users));

//        users.sort(Comparator.comparingInt(User::getBigAge));
//        log.info("after sort [Comparator.comparingInt Integer null] list:"+JSON.toJSONString(users));

        users.add(new User());
        users.sort(Comparator.comparingInt(User::getAge));
        log.info("after sort [Comparator.comparingInt int init] list:"+JSON.toJSONString(users));

        users.sort((a,b)->b.getAge()-a.getAge());
        log.info("after sort [Comparator.comparingInt desc] list:"+JSON.toJSONString(users));

//        users.add(null);
//        users.sort(Comparator.comparingInt(User::getAge));
//        log.info("after sort [Comparator.comparingInt int null object] list:"+JSON.toJSONString(users));
    }
}
