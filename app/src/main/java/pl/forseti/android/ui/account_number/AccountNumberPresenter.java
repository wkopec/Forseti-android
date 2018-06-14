package pl.forseti.android.ui.account_number;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.ViewOutlineProvider;

import java.util.List;

import pl.forseti.android.FirsetiApp;
import pl.forseti.android.R;
import pl.forseti.android.api.ForsetiApi;
import pl.forseti.android.models.AccountNumber;
import pl.forseti.android.models.Comment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static pl.forseti.android.utils.Constants.AUTHORIZATION;
import static pl.forseti.android.utils.Constants.PREFS;

public class AccountNumberPresenter implements AccountNumberContract.Presenter {

    private AccountNumberContract.View view;

    public AccountNumberPresenter(AccountNumberContract.View view) {
        this.view = view;
    }

    @Override
    public void sendVote(String accountNumber, final String thumb) {
        ForsetiApi.service().sendThumb(accountNumber, thumb).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    view.showToast(R.string.vote_sent);
                    view.setThumb(thumb);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("FAIL", t.getMessage());
            }
        });

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
                Log.d("FAIL", t.getMessage());
            }
        });
    }

    @Override
    public void sendComment(String accountNumber, String comment) {
        SharedPreferences sharedPreferences = FirsetiApp.getContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        ForsetiApi.service().sendComments(accountNumber, comment).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
                    view.refreshView();
                    Log.d("SUCCESS", response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("FAIL", t.getMessage());
            }
        });
    }

}
