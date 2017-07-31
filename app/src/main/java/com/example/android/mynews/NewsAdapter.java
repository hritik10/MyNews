package com.example.android.mynews;

/**
 * Created by Hritik on 10-05-2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NewsAdapter extends ArrayAdapter {
    public NewsAdapter(Context context, List<News> news) {
        super(context, 0, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView = convertView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_item, parent, false);
        }

        News current_News = (News) getItem(position);
        TextView date_view = (TextView) listView.findViewById(R.id.news_date);
        String date = current_News.getDate();
        date_view.setText(date);
        TextView title_view = (TextView) listView.findViewById(R.id.headline);
        String title = current_News.getTitle();
        title_view.setText(title);
        return listView;
    }
}

