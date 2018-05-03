package pl.forseti.android.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiService {

    @POST("/signup")
    Call<Void> signup(@Body LoginRequest loginRequest);

//    @PUT("/api/driver/transactions/{type}")
//    Call<Void> getTransactions(@Header("X-access-token") String token, @Path(value = "type", encoded = true) String type, @Query("date") String formattedDate, @Query("page") int page);

}
