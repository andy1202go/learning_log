package org.shithappens.libs.string;

import org.shithappens.libs.TestingSet;

import java.util.logging.Logger;

/**
 * java api测试
 *
 * @author liangbo
 * @date 2021/09/21
 */
public class JavaApiTest {

    static Logger log = Logger.getLogger("ApiTest");

    public static void testIndexOf() {
        log.info("indexof yes " + TestingSet.LONG_STR.indexOf(TestingSet.INDEX_OF_YES_STR));
        log.info("lastindexof yes " + TestingSet.LONG_STR.lastIndexOf(TestingSet.INDEX_OF_YES_STR));
        log.info("indexof no " + TestingSet.LONG_STR.indexOf(TestingSet.INDEX_OF_NO_STR));
        log.info("lastindexof no " + TestingSet.LONG_STR.lastIndexOf(TestingSet.INDEX_OF_NO_STR));

        log.info("targetCount is 0. Get result as " + TestingSet.LONG_STR.indexOf("", 10));
//        log.info("target is null. Get result as " + TestingSet.LONG_STR.indexOf(null, 10));

    }


    public static void main(String[] args) {
        testIndexOf();
    }
}
