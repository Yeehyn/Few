package com.example.few;

public class NewsObject {
    private String newstitle;
    private String newsimageurl;
    private String newsdetail;
    private String newsurl;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNewsUrl() {
        return newsurl;
    }

    public void setNewsUrl(String newsurl) {
        this.newsurl = newsurl;
    }

    public String getNewsTitle() {
        return newstitle;
    }

    public void setNewsTitle(String newstitle) {
        this.newstitle = newstitle;
    }

    public String getNewsImageUrl() {
        return newsimageurl;
    }

    public void setNewsImageUrl(String newsimageurl) {
        this.newsimageurl = newsimageurl;
    }

    public String getNewsDetail() {
        return newsdetail;
    }

    public void setNewsDetail(String newsdetail) {
        this.newsdetail = newsdetail;
    }
}
