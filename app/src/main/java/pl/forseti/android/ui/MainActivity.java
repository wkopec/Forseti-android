package pl.forseti.android.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;
import pl.forseti.android.R;
import pl.forseti.android.ui.authorization.login.LoginFragment;
import pl.forseti.android.ui.base.BaseActivity;
import pl.forseti.android.ui.account_number.AccountNumberFragment;
import pl.forseti.android.ui.vote.VoteFragment;

import static pl.forseti.android.utils.Constants.AUTHORIZATION;
import static pl.forseti.android.utils.Constants.PREFS;

public class MainActivity extends BaseActivity {

    @BindView(R.id.search_view)
    MaterialSearchView searchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSearchView();
        replaceFragment(new VoteFragment(), true);

        //replaceFragment(new AccountNumberFragment(), true);

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(AUTHORIZATION, "").equals("")) {
            replaceFragment(new LoginFragment(), true);
        } else {
            replaceFragment(new AccountNumberFragment(), true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    private void setSearchView(){
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                EventBus.getDefault().postSticky(new SearchMapEvent(query));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
            }

            @Override
            public void onSearchViewClosed() {
            }
        });
    }

}
