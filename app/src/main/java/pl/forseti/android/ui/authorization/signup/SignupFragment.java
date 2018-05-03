package pl.forseti.android.ui.authorization.signup;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.forseti.android.R;
import pl.forseti.android.api.LoginRequest;
import pl.forseti.android.ui.MainActivity;
import pl.forseti.android.ui.vote.VoteFragment;

/**
 * Created by Wojtek on 03.05.2018.
 */

public class SignupFragment extends Fragment implements SignupContract.View{

    private SignupContract.Presenter presenter;
    private MainActivity activity;

    @BindView(R.id.login)
    EditText login;

    @BindView(R.id.password)
    EditText password;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, null);
        ButterKnife.bind(this, view);
        activity = (MainActivity) getActivity();
        presenter = new SignupPresenter(this);
        return view;
    }

    @OnClick(R.id.signup)
    public void onSignupClick() {
        presenter.signup(new LoginRequest(login.getText().toString(), password.getText().toString()));
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
    public void proceedToLogin() {
        activity.onBackPressed();
        activity.replaceFragment(new VoteFragment(), true);
    }
}
