package com.applications.fluffy.piratingupdates.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.applications.fluffy.piratingupdates.Helpers.TorrentRVAdapter;
import com.applications.fluffy.piratingupdates.Objects.Torrents;
import com.applications.fluffy.piratingupdates.R;

import java.io.IOException;

public class TorrentActivity extends AppCompatActivity {

    private ImageView imv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torrent);

        Intent intent = getIntent();
        Torrents tor = intent.getParcelableExtra(TorrentRVAdapter.EXTRA_MESSAGE);

        android.app.ActionBar bar = this.getActionBar();
        this.setTitle(tor.getTitle());

//        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#A123A1")));

        setContentView(R.layout.activity_torrent);

        imv = (ImageView)findViewById(R.id.torrent_poster);
        try {
            imv.setImageBitmap(tor.readBitmap());
        } catch (IOException e) {
            e.printStackTrace();
        }

        fillTextfields(tor);

//        addListenerOnButton();
    }

    private void fillTextfields(Torrents obj){
        TextView genre, imdb, runtime, size, desc, pubdate;
        genre = (TextView) findViewById(R.id.genre);
        imdb = (TextView) findViewById(R.id.imdbRating);
        runtime = (TextView)findViewById(R.id.runtime);
        size = (TextView) findViewById(R.id.size);
        desc = (TextView) findViewById(R.id.torrent_desc);
        pubdate = (TextView)findViewById(R.id.pubdate);

        genre.setText(obj.getGenre());
        imdb.setText(obj.getImdbRating());
        runtime.setText(obj.getRuntime());
        size.setText(obj.getSize());
        desc.setText(obj.getDescription());
       // pubdate.setText(obj.getPubDate());
    }

}


