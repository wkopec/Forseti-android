package pl.forseti.android.ui.authorization.signup;

import pl.forseti.android.api.LoginRequest;


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

    }
}
