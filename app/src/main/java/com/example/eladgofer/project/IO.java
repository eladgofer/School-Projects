package com.example.eladgofer.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by eladgofer on 18/06/2017.
 */

public class IO {

    public static String getWebsite(String url, String charset) throws IOException {
        //1) URL url = new URL("..")
        URL address = new URL(url);
        //2) con = url.openConnection();
        URLConnection con = address.openConnection();
        //3) in = con.getInputStream();//binary input stream
        InputStream in = con.getInputStream();
        //4) String xml = IO.getString(in); //parse the binary as string.
        return IO.getString(in, charset);
    }

    public static String getWebsite(String adress) throws IOException {
        return getWebsite(adress, "utf-8");
    }

    public static String getString(InputStream in) throws IOException {

        return getString(in, "utf-8");
    }

    public static String getString(InputStream in, String charset) throws IOException {
        StringBuilder builder = new StringBuilder();

        String line = null;//Empty line for the loop.

        BufferedReader reader = new BufferedReader(new InputStreamReader(in, charset));
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } finally {
            reader.close();
        }
        return builder.toString();
    }
}
