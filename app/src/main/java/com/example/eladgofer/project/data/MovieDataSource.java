package com.example.eladgofer.project.data;

import android.util.Log;

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
 * Created by eladgofer on 09/08/2017.
 */

public class MovieDataSource {

    public static void getMovie(final OnMoviesArriveListener listener) {
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
                    String xml = IO.getWebsite("https://www.fandango.com/rss/top10boxoffice.rss", "Windows-1255");
                    List<Movie> data = parse(xml);

                    listener.onMovieArrived(data, null);

                } catch (Exception e) {
                    listener.onMovieArrived(null, e);
                }


            }
        });
        service.shutdown();
    }

    private static List<Movie> parse(String xml) {
        List<Movie> data = new ArrayList<>();

        Document document = Jsoup.parse(xml, "", Parser.xmlParser());
        Elements items = document.getElementsByTag("item");
        for (Element item : items) {
            String title = item.getElementsByTag("title").first().text().replace("<![CDATA[", "").replace("]]>", "");

            Elements descriptionHtml = item.getElementsByTag("description");
            String lll = descriptionHtml.attr("src");

            for (Element element : descriptionHtml) {
                Log.d("ggg", element.text());
                int index = element.text().indexOf("href=");
                index += 6;
                int endIndex = element.text().indexOf("src=");
                int indexSrc = element.text().indexOf("src=");
                indexSrc += 5;
                int endIndexSrc = element.text().indexOf("alt=");
                String myStr = element.text().substring(index, endIndex - 7);
                String myStrSrc = element.text().substring(indexSrc, endIndexSrc - 2);

                Movie movie = new Movie(title, myStrSrc, myStr);
                data.add(movie);
            }


        }

        return data;
    }

    public interface OnMoviesArriveListener {
        void onMovieArrived(List<Movie> data, Exception e);
    }

    public static class Movie {
        private String title;
        private String thumbNail;
        private String url;
        /*private String content;
        private String airDate;*/


        public Movie(String title, String thumbNail, String url) {
            this.title = title;
            this.thumbNail = thumbNail;
            this.url = url;
            /*this.content = content;
            this.airDate = airDate;*/

        }

        public String getTitle() {
            return title;
        }


        public String getThumbNail() {
            return thumbNail;
        }

        public String getUrl() {
            return url;
        }

        @Override
        public String toString() {
            return "Movie{" +
                    "title='" + title + '\'' +
                    ", thumbNail='" + thumbNail + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }
}
