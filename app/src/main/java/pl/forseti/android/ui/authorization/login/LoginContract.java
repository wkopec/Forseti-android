package pl.forseti.android.ui.authorization.login;

import pl.forseti.android.models.LoginRequest;

/**
 * Created by Wojtek on 03.05.2018.
 */

public class LoginContract {

    public interface Presenter {

        void login(LoginRequest account);

    }

    public interface View {

        void showToast(int message);

        void showToast(String message);

        void proceedToLoggedIn();

    }

}
