package pl.forseti.android.ui.profile;


import pl.forseti.android.models.Profile;

public class ProfileContract {

    public interface Presenter {

        void getProfileInfo();

    }

    public interface View {

        void setUserData(Profile profile);

    }

}
