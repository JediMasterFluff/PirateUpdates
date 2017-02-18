package com.applications.fluffy.piratingupdates.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.applications.fluffy.piratingupdates.Loaders.XMLParser;
import com.applications.fluffy.piratingupdates.R;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.fluffy.pirateupdate.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void sendTitle(View view){
        // Do something here
        Intent intent = new Intent(this, TorrentActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTitle);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void readRss(View view){

        new XMLParser().execute();
    }


}
