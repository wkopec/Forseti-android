package pl.forseti.android.ui.profile;

import android.util.Log;

import pl.forseti.android.R;
import pl.forseti.android.api.ForsetiApi;
import pl.forseti.android.models.Profile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Volfram on 14.06.2018.
 */

public class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View view;

    public ProfilePresenter(ProfileContract.View view) {
        this.view = view;
    }

    @Override
    public void getProfileInfo() {
        ForsetiApi.service().getProfileInfo().enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if(response.isSuccessful()){
                    view.setUserData(response.body());
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Log.d("FAIL", t.getMessage());
            }
        });
    }
}
