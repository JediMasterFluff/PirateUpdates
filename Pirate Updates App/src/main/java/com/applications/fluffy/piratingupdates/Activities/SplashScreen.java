package com.applications.fluffy.piratingupdates.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.applications.fluffy.piratingupdates.R;


public class SplashScreen extends Activity implements Runnable {
    Thread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        mThread = new Thread(this);

        mThread.start();
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(2000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            startActivity(new Intent(this, MainActivity.class));

            finish();
        }
    }

}