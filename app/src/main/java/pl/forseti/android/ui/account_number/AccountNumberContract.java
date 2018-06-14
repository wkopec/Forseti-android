package pl.forseti.android.ui.account_number;

import pl.forseti.android.models.AccountNumber;

public class AccountNumberContract {

    public interface Presenter {

        void getAccountNumberInfo(String accountNumber);

        void sendVote(String accountNumber, String thumb);

        void sendComment(String accountNumber, String comment);

    }

    public interface View {

        void setBankAccountInfo(AccountNumber accountNumber);

        void refreshView();

        void showToast(int message);

        void setThumb(String thumb, boolean refreashView);

    }

}
