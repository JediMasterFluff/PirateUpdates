package com.applications.fluffy.piratingupdates.Activities;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.applications.fluffy.piratingupdates.Loaders.ImageLoadTask;
import com.applications.fluffy.piratingupdates.Objects.Torrents;
import com.applications.fluffy.piratingupdates.R;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

public class TorrentActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private Button btn;
    private ImageView imv;
    private Torrents tor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torrent);

        Intent intent = getIntent();
        tor = (Torrents) intent.getSerializableExtra(MainActivity.EXTRA_MESSAGE);

        android.app.ActionBar bar = this.getActionBar();
        this.setTitle(tor.getTitle());

//        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#A123A1")));

        setContentView(R.layout.activity_torrent);

        imv = (ImageView)findViewById(R.id.torrent_poster);

        fillTextfields(tor);

//        addListenerOnButton();
    }
    private void addListenerOnButton(){
        imv = (ImageView) findViewById(R.id.torrent_poster);

        btn = (Button) findViewById(R.id.torrent_download);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imv.setImageResource(R.drawable.common_plus_signin_btn_icon_light_pressed);
            }
        });

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


