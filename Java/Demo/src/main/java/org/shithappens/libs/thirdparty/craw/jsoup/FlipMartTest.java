package org.shithappens.libs.thirdparty.craw.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * 翻转集市测试
 *
 * @author 80338398
 * @date 2021/10/14
 */
public class FlipMartTest {
    private static String URL = "https://www.flipkart.com/oppo-reno6-pro-5g-stellar-black-256-gb/product-reviews/itm105fdede3d67b?pid=MOBG4GGZF8YEQQGK&lid=LSTMOBG4GGZF8YEQQGKOKSCLW&marketplace=FLIPKART&page=2";

    private static String dd = "window.__INITIAL_STATE__ = ";

    public static void main(String[] args) {
        //https://www.cnblogs.com/youyoui/p/11065923.html
        Document doc = null;
        try {
            //Flipmart
            doc = Jsoup.connect(URL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element el = doc.getElementById("is_script");
        DataNode elChild = (DataNode) el.childNode(0);
        String data = elChild.getWholeData();
        String json = data.substring(dd.length(), data.length() - 1);
        

        String title = doc.title();


    }
}
