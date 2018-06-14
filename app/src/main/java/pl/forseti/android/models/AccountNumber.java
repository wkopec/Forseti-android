package pl.forseti.android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Wojtek on 03.05.2018.
 */

public class AccountNumber {

    @SerializedName("accountNumber")
    @Expose
    private String accountNumber;

    @SerializedName("thumbsDown")
    @Expose
    private int thumbsDown;

    @SerializedName("thumbsUp")
    @Expose
    private int thumbsUp;

    @SerializedName("comments")
    @Expose
    private CommentsResponse comments;

    @SerializedName("thumbsDetails")
    @Expose
    private ThumbsResponse thumbsDetails;

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getThumbsDown() {
        return thumbsDown;
    }

    public int getThumbsUp() {
        return thumbsUp;
    }

    public CommentsResponse getCommentsResponse() {
        return comments;
    }

    public ThumbsResponse getThumbsDetails() {
        return thumbsDetails;
    }
}
