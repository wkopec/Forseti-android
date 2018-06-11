package pl.forseti.android.api;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import pl.forseti.android.FirsetiApp;

import static pl.forseti.android.utils.Constants.AUTHORIZATION;
import static pl.forseti.android.utils.Constants.PREFS;

public class AuthorizationInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain
                .request()
                .newBuilder()
                .header("Authorization", getAuthorizationString())
                .build());
    }

    private String getAuthorizationString() {
        SharedPreferences sharedPreferences = FirsetiApp.getContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        String authorizationToken = sharedPreferences.getString(AUTHORIZATION, "");
        return authorizationToken;
    }

}
