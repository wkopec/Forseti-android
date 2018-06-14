package pl.forseti.android.models;

import android.support.annotation.NonNull;

import java.util.Date;

public class ProfileAction implements Comparable<ProfileAction> {

    private String type;

    private String accountNumber;

    private Date date;

    public ProfileAction(String type, String accountNumber, Date date) {
        this.type = type;
        this.accountNumber = accountNumber;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public int compareTo(@NonNull ProfileAction o) {
        return getDate().compareTo(o.getDate());
    }
}
