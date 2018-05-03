package pl.forseti.android.ui.account_number;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.forseti.android.R;
import pl.forseti.android.models.AccountNumber;
import pl.forseti.android.ui.MainActivity;

public class AccountNumberFragment extends Fragment implements AccountNumberContract.View {

    private AccountNumberContract.Presenter presenter;
    private MainActivity activity;

    @BindView(R.id.accountBankNumber)
    EditText accountBankNumber;

    @BindView(R.id.mask)
    View mask;

    @BindView(R.id.bankAccount)
    TextView bankAccount;

    @BindView(R.id.thumbsUp)
    TextView thumbsUp;

    @BindView(R.id.thumbsDown)
    TextView thumbsDown;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_number, null);
        ButterKnife.bind(this, view);
        activity = (MainActivity) getActivity();
        presenter = new AccountNumberPresenter(this);
        setSearchView();
        return view;
    }

    private void setSearchView() {
        accountBankNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    presenter.getAccountNumberInfo(accountBankNumber.getText().toString());
                    return true;
                }
                return false;
            }
        });
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
        thumbsUp.setText(String.format("Thumbs up: %s", accountNumber.getThumbsUp()));
        thumbsDown.setText(String.format("Thumbs down: %s", accountNumber.getThumbsDown()));
        mask.setVisibility(View.GONE);
    }

    @Override
    public void showToast(int message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}
