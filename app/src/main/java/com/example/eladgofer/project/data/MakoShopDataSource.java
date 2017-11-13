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
 * class in charge of getting the data from mako shopping rss.
 */

public class MakoShopDataSource {

    public static void getMako(final OnMakoShopArrivedListener listener) {
        //working on a secondary thread to get the data from mako:
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String xml = IO.getWebsite("http://rcs.mako.co.il/rss/news-money.xml");
                    List<MakoShop> data = parse(xml);
                    listener.onMakoShopArrived(data, null);

                } catch (Exception e) {

                    listener.onMakoShopArrived(null, e);
                }
            }
        });
        service.shutdown();
    }

    public static List<MakoShop> parse(String xml) {
        List<MakoShop> data = new ArrayList<>();

        //1)document = jsoup.parse(xml).. give the xml to jsoup.
        Document document = Jsoup.parse(xml, "", Parser.xmlParser());
        //2) now we need to get the relevant tag from the rss.
        Elements items = document.getElementsByTag("item");
        //3)  loop through the item and start mining.
        for (Element item : items) {
            String title = item.getElementsByTag("title").first().text();
            String descriptionHtml = item.getElementsByTag("description").first().text();
            String url = item.getElementsByTag("link").first().text();
            Document descriptionElement = Jsoup.parse(descriptionHtml);

            String imgLink = descriptionElement.getElementsByTag("img").first().attr("src");
            String content = descriptionElement.text();


            MakoShop mako = new MakoShop(title, imgLink, content, url);
            data.add(mako);
        }
        return data;
    }

    public interface OnMakoShopArrivedListener {
        void onMakoShopArrived(List<MakoShop> data, Exception e);
    }

    public static class MakoShop {
        private String title;
        private String description;
        private String image;
        private String content;
        private String url;

        public MakoShop(String title, String image, String content, String url) {
            this.title = title;
            this.description = description;
            this.image = image;
            this.content = content;
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getImage() {
            return image;
        }

        public String getContent() {
            return content;
        }

        public String getUrl() {
            return url;
        }

        @Override
        public String toString() {
            return "Mako{" +
                    "title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", image='" + image + '\'' +
                    ", content='" + content + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }
}
