package pl.forseti.android.api;

import pl.forseti.android.models.LoginRequest;
import pl.forseti.android.models.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/signup/")
    Call<Void> signup(@Body LoginRequest loginRequest);

    @POST("/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

//    @PUT("/api/driver/transactions/{type}")
//    Call<Void> getTransactions(@Header("X-access-token") String token, @Path(value = "type", encoded = true) String type, @Query("date") String formattedDate, @Query("page") int page);

}
