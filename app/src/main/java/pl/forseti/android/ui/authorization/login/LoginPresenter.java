package pl.forseti.android.ui.authorization.login;

import pl.forseti.android.api.LoginRequest;

/**
 * Created by Wojtek on 03.05.2018.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void login(LoginRequest account) {

    }

}
