package org.shithappens.libs.collections;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public class Test {
    static Logger log = Logger.getLogger("Test");


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
        log.info( JSON.toJSONString(emp2));

        emp2.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2- o1;
            }
        });
        log.info( JSON.toJSONString(emp2));

    }
}
