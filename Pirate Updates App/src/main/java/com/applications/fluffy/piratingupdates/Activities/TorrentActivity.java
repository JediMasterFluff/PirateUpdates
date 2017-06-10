package com.applications.fluffy.piratingupdates.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.applications.fluffy.piratingupdates.Helpers.Connection;
import com.applications.fluffy.piratingupdates.Helpers.TorrentRVAdapter;
import com.applications.fluffy.piratingupdates.Objects.Torrents;
import com.applications.fluffy.piratingupdates.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class TorrentActivity extends Activity {

    private ImageView imv;
    private Torrents tor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torrent);
        //showDialog();

        Intent intent = getIntent();
        tor = intent.getParcelableExtra(TorrentRVAdapter.EXTRA_MESSAGE);

        this.setTitle(tor.getTitle());

        setContentView(R.layout.activity_torrent);

        imv = (ImageView)findViewById(R.id.torrent_poster);
        try {
            imv.setImageBitmap(tor.readBitmap());
        } catch (IOException e) {
            e.printStackTrace();
        }

        fillTextfields(tor);
    }

    private void fillTextfields(Torrents obj) {
        TextView genre, imdb, runtime, size, desc, pubdate;
        genre = (TextView) findViewById(R.id.torrent_details_genre);
        imdb = (TextView) findViewById(R.id.torrent_details_rating);
        runtime = (TextView) findViewById(R.id.torrent_details_runtime);
        size = (TextView) findViewById(R.id.torrent_details_size);
        desc = (TextView) findViewById(R.id.torrent_desc);
        pubdate = (TextView) findViewById(R.id.torrent_details_pubDate);

        genre.setText(obj.getGenre());
        imdb.setText(obj.getImdbRating());
        runtime.setText(obj.getRuntime());
        size.setText(obj.getSize());
        desc.setText(obj.getDescription());
        pubdate.setText(obj.getPubDate());
    }

    public void onClick(View view) {
        System.out.println("CLicked Download");

        Connection conn = Connection.getInstance();

        JSONObject jObj = new JSONObject();

        try {
            jObj.put("Link", tor.getTorrentDownloadLink());

            conn.sendMessage(jObj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}


