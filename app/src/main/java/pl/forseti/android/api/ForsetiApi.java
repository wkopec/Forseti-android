package pl.forseti.android.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForsetiApi {

    public static final String API_BASE_URL = "77.55.213.42/";

    public static ApiService service(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }

}
