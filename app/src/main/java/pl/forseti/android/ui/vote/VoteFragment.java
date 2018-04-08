package pl.forseti.android.ui.vote;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.forseti.android.R;

public class VoteFragment extends Fragment implements VoteContract.View {

    private VoteContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vote, null);
        ButterKnife.bind(this, view);
        presenter = new VotePresenter(this);
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
    public void showToast(int message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}
