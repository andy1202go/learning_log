package org.shithappens.libs.thirdparty.craw.jsoup;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
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
    private static String URL = "https://www.flipkart.com/oppo-reno6-pro-5g-stellar-black-256-gb/product-reviews/itm105fdede3d67b?pid=MOBG4GGZF8YEQQGK&lid=LSTMOBG4GGZF8YEQQGKOKSCLW&marketplace=FLIPKART&page=3";

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
        JSONObject jsonObject = JSONObject.parseObject(json);

        Object o = JSONPath.eval(jsonObject,"$.pageDataV4.page.data.10002");



        //String a = (String) JSONPath.read(json,"$..author[0]");
        //JSONPath.extract(json,"");
        //
        //JSONObject jsonObject1 = (JSONObject) JSONPath.eval(jsonObject,"$.pageDataV4.page.data");
        //List<Object> ds = (List<Object>) JSONPath.eval(jsonObject,"$.pageDataV4.page.data[viewType=\"REVIEWS_DEFAULT\"]");
        String title = doc.title();


    }

}
