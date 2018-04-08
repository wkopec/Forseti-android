package pl.forseti.android.ui.vote;

public class VoteContract {

    public interface Presenter {

        void sendVote(boolean isPositive);

    }

    public interface View {

        void showToast(int message);

    }

}
