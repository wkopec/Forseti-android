package pl.forseti.android.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

import pl.forseti.android.R;
import pl.forseti.android.ui.authorization.login.LoginFragment;
import pl.forseti.android.ui.base.BaseActivity;
import pl.forseti.android.ui.account_number.AccountNumberFragment;

import static pl.forseti.android.utils.Constants.AUTHORIZATION;
import static pl.forseti.android.utils.Constants.PREFS;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        //replaceFragment(new AccountNumberFragment(), true);

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(AUTHORIZATION, "").equals("")) {
            replaceFragment(new LoginFragment(), true);
        } else {
            replaceFragment(new AccountNumberFragment(), true);
        }



    }
}
