package org.shithappens.libs;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试组
 *
 * @author liangbo
 * @date 2021/09/21
 */
public class TestingSet {
    public static final String LONG_STR = "123456abcdhelloshithappens";
    public static final String INDEX_OF_YES_STR = LONG_STR.substring(4, 10);
    public static final String INDEX_OF_NO_STR = "fuck";

    public static final String FILE_NAME = "test.file.mp4";
    public static final String FILE_NAME2 = "testfilemp4";

    public static final List<String> NORMAL_LIST = new ArrayList<String>() {{
        add("123b");
        add("abc");
        add("long sentence........................");
        add("@@@");
        add("\"well\"");
    }};

    public static final long EXPIRE_LONG = 120L;

}
