package pl.forseti.android.ui.authorization.login;

import android.content.Context;
import android.content.SharedPreferences;

import pl.forseti.android.R;
import pl.forseti.android.api.ForsetiApi;
import pl.forseti.android.models.LoginRequest;
import pl.forseti.android.models.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static pl.forseti.android.utils.Constants.AUTHORIZATION;
import static pl.forseti.android.utils.Constants.PREFS;
import static pl.forseti.android.utils.Constants.USERNAME;

/**
 * Created by Wojtek on 03.05.2018.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private Context context;

    public LoginPresenter(LoginContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void login(final LoginRequest account) {
        ForsetiApi.service().login(account).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                switch (response.code()) {
                    case 200:
                        LoginResponse loginResponse = response.body();
                        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(AUTHORIZATION, loginResponse.getAuthorization());
                        editor.putString(USERNAME, account.getUsername());
                        editor.apply();
                        view.showToast(R.string.logged_in_successfully);
                        view.proceedToLoggedIn();
                        break;
                    case 401:
                        view.showToast(R.string.invalid_username_or_password);
                        break;
                    default:
                        view.showToast(R.string.something_went_wrong);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                view.showToast(t.getMessage());
            }
        });
    }

}
