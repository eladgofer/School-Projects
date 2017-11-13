package com.example.eladgofer.project;

import android.app.Application;
import android.graphics.Typeface;

/**
 * Created by eladgofer on 21/06/2017.
 */

public class AppManager extends Application {
    public static Typeface nootFont;

    @Override
    public void onCreate() {
        super.onCreate();
        //nootFont =  Typeface.createFromAsset(getAssets(),"fonts/noot-aj.ttf");


    }

}
