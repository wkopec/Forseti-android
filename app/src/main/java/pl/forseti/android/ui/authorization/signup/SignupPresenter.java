package pl.forseti.android.ui.authorization.signup;

import pl.forseti.android.R;
import pl.forseti.android.api.ForsetiApi;
import pl.forseti.android.models.LoginRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Wojtek on 03.05.2018.
 */

public class SignupPresenter implements SignupContract.Presenter{

    private SignupContract.View view;

    public SignupPresenter(SignupContract.View view) {
        this.view = view;
    }

    @Override
    public void signup(LoginRequest account) {
        ForsetiApi.service().signup(account).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case 200:
                        view.showToast(R.string.signed_up);
                        view.proceedToLogin();
                        break;
                    case 409:
                        view.showToast(R.string.user_already_exist);
                        break;
                    default:
                        view.showToast(R.string.something_went_wrong);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                view.showToast(t.getMessage());
            }
        });
    }
}
