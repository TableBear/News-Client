package com.hzx.news;

public class Cates {
    private String cateName;
    private int cid;

    public Cates(String cateName, int cid) {
        this.cateName = cateName;
        this.cid = cid;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
