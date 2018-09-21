package com.greenravolution.gravoapp.contents;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.webkit.WebView;

import com.greenravolution.gravoapp.R;

public class VideoPage extends AppCompatActivity {
    Toolbar toolbar;
//    VideoView video;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_page);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        video = findViewById(R.id.video);
        webView = findViewById(R.id.webview);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = (size.x)-200;
        int height = (size.x)-400;
//        String path = "<iframe width=\""+String.valueOf(width)+"\" height=\""+String.valueOf(height)+"\" src=\"https://www.youtube-nocookie.com/embed/ksbl_Yues4A\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>";
        String path = "<iframe width=\""+String.valueOf(width)+"\" height=\""+String.valueOf(height)+"\" src=\"https://www.youtube-nocookie.com/embed/ksbl_Yues4A?rel=0&amp;controls=0&amp;showinfo=0\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>";
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadData(path, "text/html", "UTF-8");
//        video.setVideoURI(uri);
//        video.start();
    }


}
