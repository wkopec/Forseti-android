package pl.forseti.android.api;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.forseti.android.api.deserializers.CommentDeserializer;
import pl.forseti.android.api.deserializers.ThumbsDeserializer;
import pl.forseti.android.models.CommentsResponse;
import pl.forseti.android.models.ThumbsResponse;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForsetiApi {

    public static final String API_BASE_URL = "http://77.55.213.42:8080/";

    public static ApiService service(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new AuthorizationInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(getGsonConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(ApiService.class);
    }

    private static GsonConverterFactory getGsonConverterFactory() {
        return GsonConverterFactory.create(new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm")
                .registerTypeAdapter(CommentsResponse.class, new CommentDeserializer())
                .registerTypeAdapter(ThumbsResponse.class, new ThumbsDeserializer())
                .create());
    }

}
