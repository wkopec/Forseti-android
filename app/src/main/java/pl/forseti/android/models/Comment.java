package pl.forseti.android.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Comment implements Comparable<Comment> {

    private String username;

    @SerializedName("comment")
    @Expose
    private String comment;

    @SerializedName("is")
    @Expose
    private int id;

    @SerializedName("timeStamp")
    @Expose
    private Date timeStamp;

    public Comment(String username, String comment, int id, Date timeStamp) {
        this.username = username;
        this.comment = comment;
        this.id = id;
        this.timeStamp = timeStamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public int compareTo(@NonNull Comment o) {
        return getTimeStamp().compareTo(o.getTimeStamp());
    }
}
