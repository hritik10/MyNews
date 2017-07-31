package com.example.android.mynews;

/**
 * Created by Hritik on 10-05-2017.
 */

public class News {

    private String mTitle;
    private String mDate;
    private String mURL;

    public News(String Date, String Title, String url) {
        mDate = Date;
        mTitle = Title;
        mURL = url;
    }


    public String getTitle() {
        return mTitle;
    }

    public String getDate() {
        return mDate;
    }

    public String getURL() {
        return mURL;
    }
}
