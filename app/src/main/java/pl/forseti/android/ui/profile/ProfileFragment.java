package pl.forseti.android.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.forseti.android.R;
import pl.forseti.android.models.Profile;
import pl.forseti.android.ui.MainActivity;
import pl.forseti.android.ui.authorization.login.LoginFragment;

import static pl.forseti.android.utils.Constants.AUTHORIZATION;
import static pl.forseti.android.utils.Constants.PREFS;

public class ProfileFragment extends Fragment implements ProfileContract.View {

    private ProfileContract.Presenter presenter;
    private MainActivity activity;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.actionsRecyclerView)
    RecyclerView actionsRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, null);
        ButterKnife.bind(this, view);
        activity = (MainActivity) getActivity();
        setToolbar();
        presenter = new ProfilePresenter(this);
        setHasOptionsMenu(true);
        presenter.getProfileInfo();
        return view;
    }

    @OnClick(R.id.logout)
    public void onLogoutClick() {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AUTHORIZATION, "");
        editor.apply();
        activity.replaceFragment(new LoginFragment(), true);
    }

    private void setToolbar(){
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        final Drawable upArrow =  ContextCompat.getDrawable(getActivity(), R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(ContextCompat.getColor(getActivity(), R.color.white), PorterDuff.Mode.SRC_ATOP);
        activity.getSupportActionBar().setHomeAsUpIndicator(upArrow);
    }

    @Override
    public void setUserData(Profile profile) {
        username.setText(profile.getUsername());
    }
}
