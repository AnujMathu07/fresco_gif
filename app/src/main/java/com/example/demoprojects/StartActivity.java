package com.example.demoprojects;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class StartActivity extends AppCompatActivity {

    private static final long RESIZE_DELAY = 50;
    private SimpleDraweeView sdvImage;
    private ImageView ivImage;
    private Handler mHandler = new Handler();
    private static final int MAX_RESIZE_TIMES = 10;
    private int currentResizeTime = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.start_activity);

        sdvImage = (SimpleDraweeView) findViewById(R.id.sdvImage);

        String gifImageURL = "http://i.imgur.com/MJe4z21.gif";

        Uri uri = Uri.parse(gifImageURL);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        sdvImage.setController(controller);

        findViewById(R.id.btnResize).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentResizeTime = 0;
                resizeImage();
            }
        });

        ivImage = (ImageView) findViewById(R.id.ivImage);
        Glide.with(StartActivity.this).load(gifImageURL).asGif().into(ivImage);

    }

    private void resizeImage() {
        mHandler.postDelayed(resizeRun, RESIZE_DELAY);
    }

    private Runnable resizeRun = new Runnable() {
        @Override
        public void run() {
            int currentW = sdvImage.getWidth();
            int currentH = sdvImage.getHeight();
            int screenWidth = getResources().getDisplayMetrics().widthPixels;
            if(currentW+10 > screenWidth)
            {
                currentW = 200;
                currentH = 200;
            }
            ViewGroup.LayoutParams paramsSDV = sdvImage.getLayoutParams();
            paramsSDV.width = currentW + 10;
            paramsSDV.height = currentH + 5;
            sdvImage.setLayoutParams(paramsSDV);


            ViewGroup.LayoutParams paramsIV = ivImage.getLayoutParams();
            paramsIV.width = currentW + 10;
            paramsIV.height = currentH + 5;
            ivImage.setLayoutParams(paramsIV);
            currentResizeTime++;
            if(currentResizeTime < MAX_RESIZE_TIMES)
                mHandler.postDelayed(resizeRun, RESIZE_DELAY);
        }
    };
}
