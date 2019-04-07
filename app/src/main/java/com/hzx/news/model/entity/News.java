package com.hzx.news.model.entity;

public class News {
    private String nid;
    private long articleType;
    private String abstractTitle;
    private String title;
    private String articleUrl;
    private java.sql.Timestamp crawlTime;
    private String keywords;
    private String cate;
    private java.sql.Timestamp publishTime;
    private String author;
    private String coverImageUrl;
    private long hasVideo;
    private long hasImage;
    private long favoriteCount;
    private long commentsCount;
    private String content;


    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }


    public long getArticleType() {
        return articleType;
    }

    public void setArticleType(long articleType) {
        this.articleType = articleType;
    }


    public String getAbstractTitle() {
        return abstractTitle;
    }

    public void setAbstractTitle(String abstractTitle) {
        this.abstractTitle = abstractTitle;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }


    public java.sql.Timestamp getCrawlTime() {
        return crawlTime;
    }

    public void setCrawlTime(java.sql.Timestamp crawlTime) {
        this.crawlTime = crawlTime;
    }


    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }


    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }


    public java.sql.Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(java.sql.Timestamp publishTime) {
        this.publishTime = publishTime;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }


    public long getHasVideo() {
        return hasVideo;
    }

    public void setHasVideo(long hasVideo) {
        this.hasVideo = hasVideo;
    }


    public long getHasImage() {
        return hasImage;
    }

    public void setHasImage(long hasImage) {
        this.hasImage = hasImage;
    }


    public long getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(long favoriteCount) {
        this.favoriteCount = favoriteCount;
    }


    public long getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(long commentsCount) {
        this.commentsCount = commentsCount;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
