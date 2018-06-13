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

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getThumbsDown() {
        return thumbsDown;
    }

    public int getThumbsUp() {
        return thumbsUp;
    }

    public CommentsResponse getComments() {
        return comments;
    }
}
