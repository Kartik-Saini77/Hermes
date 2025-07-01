package com.hermes.Models;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Content {
    @BsonProperty("title")
    private String title;
    @BsonProperty("textContent")
    private String textContent;
    @BsonProperty("mediaContent")
    private String mediaContent;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getMediaContent() {
        return mediaContent;
    }

    public void setMediaContent(String mediaContent) {
        this.mediaContent = mediaContent;
    }
}