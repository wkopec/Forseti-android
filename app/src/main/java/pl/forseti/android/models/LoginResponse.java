package pl.forseti.android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Wojtek on 03.05.2018.
 */

public class LoginResponse {

    @SerializedName("Authorization")
    @Expose
    private String authorization;

    @SerializedName("username")
    @Expose
    private String username;

    public String getAuthorization() {
        return authorization;
    }
}
