package pl.forseti.android.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ThumbDetail {

    @SerializedName("thumb")
    @Expose
    private String thumb;

    @SerializedName("timeStamp")
    @Expose
    private Date timeStamp;

    private String accountNumber;

    public String getThumb() {
        return thumb;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
