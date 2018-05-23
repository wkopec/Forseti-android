package pl.forseti.android.ui.account_number;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.forseti.android.R;
import pl.forseti.android.models.AccountNumber;
import pl.forseti.android.ui.MainActivity;

public class AccountNumberFragment extends Fragment implements AccountNumberContract.View {

    private AccountNumberContract.Presenter presenter;
    private MainActivity activity;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    @BindView(R.id.mask)
    View mask;
    @BindView(R.id.bankAccount)
    TextView bankAccount;
    @BindView(R.id.thumbsUp)
    TextView thumbsUp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_number, null);
        ButterKnife.bind(this, view);
        activity = (MainActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        presenter = new AccountNumberPresenter(this);
        setHasOptionsMenu(true);
        setSearchView();
        return view;
    }

    @OnClick(R.id.thumbUp)
    public void onThumbUpClick() {
        presenter.sendVote(true);
    }

    @OnClick(R.id.thumbDown)
    public void onThumbDownClick() {
        presenter.sendVote(false);
    }

    @Override
    public void setBankAccountInfo(AccountNumber accountNumber) {
        bankAccount.setText(accountNumber.getAccountNumber());
        thumbsUp.setText(String.valueOf(accountNumber.getThumbsUp() - accountNumber.getThumbsDown()));
        //thumbsDown.setText(String.format("Thumbs down: %s", accountNumber.getThumbsDown()));
        mask.setVisibility(View.GONE);
    }

    @Override
    public void showToast(int message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    private void setSearchView(){
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.getAccountNumberInfo(query);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        activity.getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
    }

}
