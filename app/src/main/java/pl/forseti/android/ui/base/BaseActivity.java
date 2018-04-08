package pl.forseti.android.ui.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import pl.forseti.android.R;

public class BaseActivity extends AppCompatActivity {

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        if (addToBackStack)
            transaction.addToBackStack(null);
        transaction.commit();

    }

}
