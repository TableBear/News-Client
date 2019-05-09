package com.hzx.news.model.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/5/9 2:08
 * @param:
 * @return:
 * @throws:
 */
public class NewsCommentResponse {

    private String nid;
    private long uid;
    private String comment;
    private Date actionTime;
    private String unick;

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public String getUnick() {
        return unick;
    }

    public void setUnick(String unick) {
        this.unick = unick;
    }

}
