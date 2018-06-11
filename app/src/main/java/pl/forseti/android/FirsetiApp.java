package pl.forseti.android;

import android.app.Application;
import android.content.Context;

public class FirsetiApp extends Application {
    private static FirsetiApp instance;

    public static FirsetiApp getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
