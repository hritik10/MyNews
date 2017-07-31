package com.example.android.mynews;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {


    private static String news_URL = "https://content.guardianapis.com/search?q=&section=sport&from-date=2017-01-01&page-size=20&api-key=test";
    private TextView Empty;
    private static final int newsloader = 1;
    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView newsList = (ListView) findViewById(R.id.list);
        Empty = (TextView) findViewById(R.id.empty_view);
        newsList.setEmptyView(Empty);
        adapter = new NewsAdapter(this, new ArrayList<News>());
        newsList.setAdapter(adapter);
        android.app.LoaderManager loader = null;
        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                News current_news = (News) adapter.getItem(position);
                Uri news_uri = Uri.parse(current_news.getURL());
                Intent internetpage = new Intent(Intent.ACTION_VIEW, news_uri);
                startActivity(internetpage);
            }
        });
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            loader = getLoaderManager();
            loader.initLoader(newsloader, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.indicator);
            loadingIndicator.setVisibility(View.GONE);
            Empty.setText("please Check Your Internet Connection ");
        }
    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this, news_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> News) {
        View loadingIndicator = findViewById(R.id.indicator);
        loadingIndicator.setVisibility(View.GONE);
        Empty.setText("");
        adapter.clear();
        if (News != null && !News.isEmpty()) {
            adapter.addAll(News);
        } else {
            Empty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        adapter.clear();
    }
}
