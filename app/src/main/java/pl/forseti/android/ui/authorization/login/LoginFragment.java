package pl.forseti.android.ui.authorization.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.forseti.android.R;
import pl.forseti.android.models.LoginRequest;
import pl.forseti.android.ui.MainActivity;
import pl.forseti.android.ui.account_number.AccountNumberFragment;
import pl.forseti.android.ui.authorization.signup.SignupFragment;

public class LoginFragment extends Fragment implements LoginContract.View{

    private LoginContract.Presenter presenter;
    private MainActivity activity;

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.password)
    EditText password;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, null);
        ButterKnife.bind(this, view);
        activity = (MainActivity) getActivity();
        presenter = new LoginPresenter(this, activity);
        return view;
    }

    @OnClick(R.id.login)
    public void onLoginClick() {
        presenter.login(new LoginRequest(username.getText().toString(), password.getText().toString()));
    }

    @OnClick(R.id.signup)
    public void onSignupClick() {
        activity.replaceFragment(new SignupFragment(), true);
    }

    @Override
    public void showToast(int message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void proceedToLoggedIn() {
        activity.onBackPressed();
        activity.replaceFragment(new AccountNumberFragment(), true);
    }
}
