package pl.forseti.android.ui.account_number;

import pl.forseti.android.models.AccountNumber;

public class AccountNumberContract {

    public interface Presenter {

        void sendVote(boolean isPositive);

        void getAccountNumberInfo(String accountNumber);

    }

    public interface View {

        void setBankAccountInfo(AccountNumber accountNumber);

        void showToast(int message);

    }

}
