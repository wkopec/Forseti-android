package pl.forseti.android.ui.account_number;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.forseti.android.R;
import pl.forseti.android.models.AccountNumber;
import pl.forseti.android.ui.MainActivity;
import pl.forseti.android.ui.profile.ProfileFragment;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class AccountNumberFragment extends Fragment implements AccountNumberContract.View {

    private AccountNumberContract.Presenter presenter;
    private MainActivity activity;
    private String accountNumber;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    @BindView(R.id.mask)
    TextView mask;
    @BindView(R.id.bankAccount)
    TextView bankAccount;
    @BindView(R.id.comments)
    TextView comments;
    @BindView(R.id.thumbsUp)
    TextView thumbsUp;
    @BindView(R.id.thumbUp)
    ImageView thumbUp;
    @BindView(R.id.thumbDown)
    ImageView thumbDown;
    @BindView(R.id.commentsRecyclerView)
    RecyclerView commentsRecyclerView;
    @BindView(R.id.newComment)
    EditText newComment;

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
        setNewCommentEditText();
        return view;
    }

    @OnClick(R.id.thumbUp)
    public void onThumbUpClick() {
        presenter.sendVote(accountNumber, "UP");
    }

    @OnClick(R.id.thumbDown)
    public void onThumbDownClick() {
        presenter.sendVote(accountNumber, "DOWN");
    }

    @OnClick(R.id.comments)
    public void onCommentsClick() {
        if(newComment.getVisibility() == GONE) {
            newComment.setVisibility(VISIBLE);
        } else {
            newComment.setVisibility(GONE);
        }

    }

    @Override
    public void setBankAccountInfo(AccountNumber accountNumber) {
        this.accountNumber = accountNumber.getAccountNumber();
        bankAccount.setText(accountNumber.getAccountNumber());
        thumbsUp.setText(String.valueOf(accountNumber.getThumbsUp() - accountNumber.getThumbsDown()));
        comments.setText(String.valueOf(accountNumber.getCommentsResponse().getComments().size()));

        commentsRecyclerView.setAdapter(new CommentAdapter(accountNumber.getCommentsResponse().getComments(), activity));
        LinearLayoutManager manager = new LinearLayoutManager(activity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        commentsRecyclerView.setLayoutManager(manager);

        mask.setVisibility(GONE);
    }

    @Override
    public void refreshView() {
        presenter.getAccountNumberInfo(accountNumber);
    }

    @Override
    public void showToast(int message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setThumb(String thumb) {
        switch (thumb) {
            case "UP":
                thumbUp.setColorFilter(Color.argb(255, 0, 240, 0));
                thumbDown.setColorFilter(Color.argb(255, 0, 0, 0));
                break;
            case "DOWN":
                thumbUp.setColorFilter(Color.argb(255, 0, 0, 0));
                thumbDown.setColorFilter(Color.argb(255, 240, 0, 0));
                break;
        }
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

    private void setNewCommentEditText() {
        newComment.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (event != null&& (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                    View view = activity.getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    presenter.sendComment(accountNumber, newComment.getText().toString());
                    newComment.setVisibility(GONE);
                    return true;

                }
                return false;
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        activity.getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem itemSearch = menu.findItem(R.id.action_search);
        searchView.setMenuItem(itemSearch);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                activity.replaceFragment(new ProfileFragment(), true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
