package pl.forseti.android.api;

import pl.forseti.android.models.AccountNumber;
import pl.forseti.android.models.LoginRequest;
import pl.forseti.android.models.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("/signup/")
    Call<Void> signup(@Body LoginRequest loginRequest);

    @POST("/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("/api/accountNumber/{number}")
    Call<AccountNumber> getAccountNumberInfo(@Path(value = "number", encoded = true) String type);

}
