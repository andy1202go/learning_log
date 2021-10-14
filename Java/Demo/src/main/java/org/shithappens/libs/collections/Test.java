package org.shithappens.libs.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Test {
    static Logger log = Logger.getLogger("Test");


    public static void main(String[] args) {
        List<String> emp = new ArrayList<>();
        emp.forEach(e -> log.info("e"));
    }
}
