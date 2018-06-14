package pl.forseti.android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Profile {

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("comments")
    @Expose
    private CommentsResponse comments;

    @SerializedName("thumbsDetails")
    @Expose
    private ThumbsResponse thumbsDetails;

    public String getUsername() {
        return username;
    }

    public List<ProfileAction> getProfileActions(){
        List<ProfileAction> profileActions = new ArrayList<>();

        for(Comment comment : comments.getComments()) {
            profileActions.add(new ProfileAction("COMMENT", comment.getUsername(), comment.getTimeStamp()));
        }
        for(ThumbDetail thumbDetail : thumbsDetails.getThumbDetails()) {
            profileActions.add(new ProfileAction(thumbDetail.getThumb(), thumbDetail.getAccountNumber(), thumbDetail.getTimeStamp()));
        }

        Collections.sort(profileActions);
        return profileActions;
    }
}
