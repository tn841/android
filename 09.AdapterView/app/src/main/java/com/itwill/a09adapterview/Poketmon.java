package com.itwill.a09adapterview;

/**
 * Created by stu on 2017-01-13.
 */

public class Poketmon {
    private String poketName;
    private int img_id;

    public Poketmon(){

    }

    public Poketmon(String poketName, int img_id) {
        this.poketName = poketName;
        this.img_id = img_id;
    }

    public String getPoketName() {
        return poketName;
    }

    public void setPoketName(String poketName) {
        this.poketName = poketName;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }
}
