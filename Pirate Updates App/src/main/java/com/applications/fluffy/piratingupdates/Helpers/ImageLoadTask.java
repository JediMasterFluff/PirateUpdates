package com.applications.fluffy.piratingupdates.Helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.applications.fluffy.piratingupdates.Objects.Torrents;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by fluffy on 10/02/17.
 *
 * WORKS
 */

public class ImageLoadTask extends AsyncTask<Void,Void, Bitmap> {

    private String url;
    private Torrents imageView;

    public ImageLoadTask(String url, Torrents imageView) {
        this.url = url;
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        try{
            URL conn = new URL(url);
            HttpURLConnection httpConn = (HttpURLConnection) conn.openConnection();
            httpConn.setDoInput(true);
            httpConn.connect();
            InputStream is = httpConn.getInputStream();
            return BitmapFactory.decodeStream(is);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result){
        super.onPostExecute(result);
        //imageView.setImage(result);
    }
}
