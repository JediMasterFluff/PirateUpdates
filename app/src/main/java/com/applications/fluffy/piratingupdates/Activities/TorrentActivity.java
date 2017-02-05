package com.applications.fluffy.piratingupdates.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.applications.fluffy.piratingupdates.R;
import com.google.android.gms.common.api.GoogleApiClient;

public class TorrentActivity extends Activity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private Button btn;
    private ImageView imv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torrent);

        /*
        Intent intent = getIntent();
        String title = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        this.setTitle(title);
        */


        setContentView(R.layout.activity_torrent);

        addListenerOnButton();
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

}
