package pl.forseti.android.ui.authorization.signup;

import pl.forseti.android.api.LoginRequest;

/**
 * Created by Wojtek on 03.05.2018.
 */

public class SignupContract {

    public interface Presenter {

        void signup(LoginRequest account);

    }

    public interface View {

        void showToast(int message);

    }

}
