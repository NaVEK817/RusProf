package com.example.rusprof;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class HasConnection {
    public static boolean hasConnection(final Context context) {// создаём метод для проверки соединения
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNW = cm.getActiveNetworkInfo();
        if (activeNW != null && activeNW.isConnected()) {
            return true;
        }
        return false;
    }
}
