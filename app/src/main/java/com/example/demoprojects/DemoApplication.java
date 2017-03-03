package com.example.demoprojects;

import android.app.Application;
import android.graphics.Typeface;

import com.facebook.drawee.backends.pipeline.Fresco;


public class DemoApplication extends Application {

    public static Typeface TYPEFACE_FONT_OPEN_SANS_LIGHT;
    public static Typeface TYPEFACE_OPEN_SANS_BOLD;
    public static Typeface TYPEFACE_FONT_OPEN_SANS_SEMI_BOLD;
    public static Typeface TYPEFACE_FONT_OPEN_SANS;
    public static Typeface TYPEFACE_FONT_LEAGUE_GOTHIC;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
