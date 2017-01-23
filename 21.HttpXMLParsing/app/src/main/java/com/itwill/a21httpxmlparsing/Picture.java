package com.itwill.a21httpxmlparsing;

import android.graphics.Bitmap;

/**
 * Created by stu on 2017-01-18.
 */

public class Picture {
    private String title;
    private String url;
    private Bitmap bitImg;

    public Picture() {
    }

    public Picture(String title, String url, Bitmap bitImg) {
        this.title = title;
        this.url = url;
        this.bitImg = bitImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getBitImg() {
        return bitImg;
    }

    public void setBitImg(Bitmap bitImg) {
        this.bitImg = bitImg;
    }
}
