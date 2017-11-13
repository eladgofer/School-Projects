package com.example.eladgofer.project.data;

import com.example.eladgofer.project.IO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * the data from the YnetShop rss site.
 */

public class YnetShopDataSource {
    public static void getYnet(final OnYnetShopArriveListener listener) {
        //1) no networking on UI thread ->
        //lets work on a thread.
        ExecutorService service = Executors.newSingleThreadExecutor();
        //service.execute(Runnable)...
        service.execute(new Runnable() {
            @Override
            public void run() {
                //code that runs in the background..
                //0) try{}catch(Exeption e);
                try {
                    String xml = IO.getWebsite("http://www.ynet.co.il/Integration/StoryRss5363.xml", "Windows-1255");
                    List<YnetShop> data = parse(xml);

                    listener.onYnetShopArrived(data, null);

                } catch (Exception e) {
                    listener.onYnetShopArrived(null, e);
                }


            }
        });
        service.shutdown();
    }

    private static List<YnetShop> parse(String xml) {

        List<YnetShop> data = new ArrayList<>();

        //1)document = jsoup.parse(xml).. give the xml to jsoup.
        Document document = Jsoup.parse(xml);
        //2)get the relevant tag from the rss..
        Elements items = document.getElementsByTag("item");
        //loop through the items and start mining!
        for (Element item : items) {
            String descriptionHtml = item.getElementsByTag("description").first().text();
            String title = item.getElementsByTag("title").first().text().replace("<![CDATA[", "").replace("]]>", "");
            Document descriptionElement = Jsoup.parse(descriptionHtml);
            String link = descriptionElement.getElementsByTag("a").first().attr("href");
            String imgLink = descriptionElement.getElementsByTag("img").first().attr("src");
            String content = descriptionElement.text();


            YnetShop ynetShop = new YnetShop(title, link, imgLink, content);
            data.add(ynetShop);
        }

        return data;
    }

    public interface OnYnetShopArriveListener {
        void onYnetShopArrived(List<YnetShop> data, Exception e);
    }

    public static class YnetShop {
        private String title;
        private String url;
        private String thumbnail;
        private String content;

        //constructor
        public YnetShop(String title, String url, String thumbnail, String content) {
            this.title = title;
            this.url = url;
            this.thumbnail = thumbnail;
            this.content = content;
        }

        //getters
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

        //toString
        @Override
        public String toString() {
            return "YnetShop: " +
                    "title: '" + title + '\'' +
                    ", url: '" + url + '\'' +
                    ", thumbnail: '" + thumbnail + '\'' +
                    ", content: '" + content + '\'';
        }
    }
}
