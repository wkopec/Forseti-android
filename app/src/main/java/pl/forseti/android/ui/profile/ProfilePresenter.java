package pl.forseti.android.ui.profile;

/**
 * Created by Volfram on 14.06.2018.
 */

public class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View view;

    public ProfilePresenter(ProfileContract.View view) {
        this.view = view;
    }
}
