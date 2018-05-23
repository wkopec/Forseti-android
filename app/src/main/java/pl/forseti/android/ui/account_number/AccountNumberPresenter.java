package pl.forseti.android.ui.account_number;

import pl.forseti.android.R;
import pl.forseti.android.api.ForsetiApi;
import pl.forseti.android.models.AccountNumber;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountNumberPresenter implements AccountNumberContract.Presenter {

    private AccountNumberContract.View view;

    public AccountNumberPresenter(AccountNumberContract.View view) {
        this.view = view;
    }

    @Override
    public void sendVote(boolean isPositive) {
        view.showToast(R.string.vote_sent);
    }

    @Override
    public void getAccountNumberInfo(String accountNumber) {
        ForsetiApi.service().getAccountNumberInfo(accountNumber).enqueue(new Callback<AccountNumber>() {
            @Override
            public void onResponse(Call<AccountNumber> call, Response<AccountNumber> response) {
                AccountNumber accountNumber = response.body();
                if(accountNumber != null) {
                    view.setBankAccountInfo(accountNumber);
                }
            }

            @Override
            public void onFailure(Call<AccountNumber> call, Throwable t) {

            }
        });
    }
}
