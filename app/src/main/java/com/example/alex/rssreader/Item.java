package com.example.alex.rssreader;

/**
 * Created by Alex on 8.10.2015.
 */
public class Item {

    private String title;
    private String description;
    private String link;

    public Item(){
        this.title = null;
        this.description = null;
        this.link = null;

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
