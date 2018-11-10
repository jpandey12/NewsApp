package com.example.rkjc.news_app_2;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private NewsRecyclerViewAdapter nrvAdapter;
    private ArrayList<NewsItem> newsItems = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.news_recyclerview);

        nrvAdapter = new NewsRecyclerViewAdapter(this, newsItems);
        rv.setAdapter(nrvAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        NewsQueryTask task = new NewsQueryTask();
        task.execute();
    }

    public void populateRecyclerView(String jstring) {
        Log.d("mycode", jstring);
        newsItems = JsonUtils.parseNews(jstring);
        nrvAdapter.nItems.addAll(newsItems);
        nrvAdapter.notifyDataSetChanged();
    }


    class NewsQueryTask extends AsyncTask<String, Void, String> {
        private final String TAG = "MainActivity Async";
        private String jstring;

        @Override
        protected String doInBackground(String... params) {
            Log.d(TAG, "Async task do in background");
            String jstring = null;
            try {
                jstring = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildURL());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jstring;
        }

        @Override
        protected void onPostExecute(String newsResult) {
            populateRecyclerView(newsResult);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int newsclicked=item.getItemId();
        if(newsclicked==R.id.action_search){
            nrvAdapter = new NewsRecyclerViewAdapter(this, newsItems);
            rv.setAdapter(nrvAdapter);
            rv.setLayoutManager(new LinearLayoutManager(this));
            NewsQueryTask task = new NewsQueryTask();
            task.execute();
        }

        return super.onOptionsItemSelected(item);
    }



}
