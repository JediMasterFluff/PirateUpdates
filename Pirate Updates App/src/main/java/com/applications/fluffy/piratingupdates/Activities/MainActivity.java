package com.applications.fluffy.piratingupdates.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.applications.fluffy.piratingupdates.Helpers.TorrentRVAdapter;
import com.applications.fluffy.piratingupdates.Objects.Torrents;
import com.applications.fluffy.piratingupdates.R;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private Bitmap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getActionBar();

        actionBar.setTitle("");

        Intent intent = getIntent();
        Bundle b = intent.getBundleExtra(SplashScreen.MESSAGE);
        ArrayList<Torrents> torrentsList = b.getParcelableArrayList(SplashScreen.MESSAGE);

        RecyclerView rv = (RecyclerView) findViewById(R.id.activity_main);

        LinearLayoutManager llm = new LinearLayoutManager(getBaseContext());
        rv.setLayoutManager(llm);

        TorrentRVAdapter adapter = new TorrentRVAdapter(torrentsList);
        rv.setAdapter(adapter);

    }
}
