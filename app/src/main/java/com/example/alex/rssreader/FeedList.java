package com.example.alex.rssreader;

import android.app.Activity;
import android.app.ListActivity;

/**
 * Created by Alex on 29.9.2015.
 */
public class FeedList{

    //Instance variables

    private String url;
    private String headline;
    private String description;
    private int iconID;

//CONSTRUCTOR
    public FeedList(String url, String headline,String description, int iconID){
        super();
        this.url = url;
        this.headline = headline;
        this.description = description;
        this.iconID = iconID;

    }
//GETTERS
    public String getUrl() {
        return url;
    }
    public String getHeadline() {
        return headline;
    }
    public String getDescription() {
        return description;
    }

    public int getIconID() {
        return iconID;
    }

}
