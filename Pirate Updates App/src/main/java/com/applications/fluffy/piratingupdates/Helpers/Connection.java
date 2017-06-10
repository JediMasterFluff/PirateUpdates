package com.applications.fluffy.piratingupdates.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by fluffy on 10/06/17.
 */

public class Connection {

    private static SharedPreferences preferences;
    private static final Connection INSTANCE = new Connection();
    private Socket socket;
    private static Context context;

    public static Connection getInstance() {
        return INSTANCE;
    }

    private Connection() {
        String host;
        int port;
        context.getApplicationContext();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        host = preferences.getString("serverAddress", "");
        port = preferences.getInt("port", 0);

        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Unable to connect to server", Toast.LENGTH_LONG).show();
        }
    }


    public void sendMessage(JSONObject json) {
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            pw.write(json.toString());

            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
