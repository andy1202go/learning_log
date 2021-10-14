package org.shithappens.libs.serialization;

import java.util.ArrayList;
import java.util.List;

public class Test {


    public static void main(String[] args) {
        Hi hi = new Hi("dsf", 123);
        List<Hi> list = new ArrayList<>();
        list.add(hi);
        Object obj = list;

        List<Hi> listAfter = (List<Hi>) list;


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
}
