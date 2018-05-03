package pl.forseti.android.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import pl.forseti.android.R;
import pl.forseti.android.ui.authorization.signup.SignupFragment;
import pl.forseti.android.ui.base.BaseActivity;
import pl.forseti.android.ui.vote.VoteFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        //replaceFragment(new VoteFragment(), true);
        replaceFragment(new SignupFragment(), true);


    }
}
