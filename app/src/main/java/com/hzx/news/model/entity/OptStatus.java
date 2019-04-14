package com.hzx.news.model.entity;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/4/14 20:38
 * @param:
 * @return:
 * @throws:
 */
public class OptStatus {
    private boolean isLike;
    private boolean isCollect;

    public boolean getIsLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public boolean getIsCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }
}
