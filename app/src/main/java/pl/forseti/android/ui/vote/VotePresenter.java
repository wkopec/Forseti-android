package pl.forseti.android.ui.vote;

import pl.forseti.android.R;

public class VotePresenter implements VoteContract.Presenter {

    private VoteContract.View view;

    public VotePresenter(VoteContract.View view) {
        this.view = view;
    }

    @Override
    public void sendVote(boolean isPositive) {
        view.showToast(R.string.vote_sent);
    }
}
