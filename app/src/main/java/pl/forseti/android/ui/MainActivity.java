package pl.forseti.android.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;
import pl.forseti.android.R;
import pl.forseti.android.ui.base.BaseActivity;
import pl.forseti.android.ui.vote.VoteFragment;

public class MainActivity extends BaseActivity {

    @BindView(R.id.search_view)
    MaterialSearchView searchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        setSearchView();
        replaceFragment(new VoteFragment(), true);
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
