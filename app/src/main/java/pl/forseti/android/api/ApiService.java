package pl.forseti.android.api;

import java.util.List;

import pl.forseti.android.models.AccountNumber;
import pl.forseti.android.models.Comment;
import pl.forseti.android.models.LoginRequest;
import pl.forseti.android.models.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @POST("/signup/")
    Call<Void> signup(@Body LoginRequest loginRequest);

    @POST("/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("/api/accountNumber/{number}")
    Call<AccountNumber> getAccountNumberInfo(@Path(value = "number", encoded = true) String number);

    @PUT("/api/accountNumber/comment/{number}")
    Call<Void> sendComments(@Path(value = "number", encoded = true) String number, @Body String comment);

    @PUT("/api/accountNumber/thumb/{number}")
    Call<Void> sendThumb(@Path(value = "number", encoded = true) String number, @Query("thumb") String thumb);

}
