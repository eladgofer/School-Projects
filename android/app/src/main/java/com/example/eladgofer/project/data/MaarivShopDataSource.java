package com.example.eladgofer.project.data;

import com.example.eladgofer.project.IO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * the data from Maariv rss site.
 */

public class MaarivShopDataSource {

    public static void getMaarivShop(final OnMaarivShopArrivedListener listener) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String xml = IO.getWebsite("http://www.maariv.co.il/Rss/RssFeedsZarchanot?BeforeArticleID=0");
                    List<MaarivShop> data = parse(xml);
                    listener.onMaarivShopArrived(data, null);
                } catch (Exception e) {
                    listener.onMaarivShopArrived(null, e);
                }
            }
        });
        service.shutdown();
    }

    public static List<MaarivShop> parse(String xml) {
        List<MaarivShop> data = new ArrayList<>();

        Document document = Jsoup.parse(xml, "", Parser.xmlParser());

        Elements items = document.getElementsByTag("item");

        for (Element item : items) {
            String descriptionHtml = item.getElementsByTag("description").first().text();
            String title = item.getElementsByTag("title").first().text();
            String url = item.getElementsByTag("link").first().text();

            Document descriptionElement = Jsoup.parse(descriptionHtml);
            String thumbnail = descriptionElement.getElementsByTag("img").first().attr("src");
            String content = descriptionElement.text();
            MaarivShop maariv = new MaarivShop(title, url, thumbnail, content);
            data.add(maariv);
        }
        return data;
    }


    public interface OnMaarivShopArrivedListener {
        void onMaarivShopArrived(List<MaarivShop> data, Exception e);
    }

    public static class MaarivShop {
        private String title;
        private String url;
        private String thumbnail;
        private String content;

        public MaarivShop(String title, String url, String thumbnail, String content) {
            this.title = title;
            this.url = url;
            this.thumbnail = thumbnail;
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public String getUrl() {
            return url;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public String getContent() {
            return content;
        }

        @Override
        public String toString() {
            return "Maariv----: " +
                    "title----: " + title + '\'' +
                    ", url----: " + url + '\'' +
                    ", thumbnail---: " + thumbnail + '\'' +
                    ", content----: " + content;
        }
    }
}
