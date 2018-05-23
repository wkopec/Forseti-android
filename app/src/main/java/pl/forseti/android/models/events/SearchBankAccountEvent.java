package pl.forseti.android.models.events;

/**
 * Created by Volfram on 23.05.2018.
 */

public class SearchBankAccountEvent {

    private String querry;

    public SearchBankAccountEvent(String querry) {
        this.querry = querry;
    }

    public String getQuerry() {
        return querry;
    }

}
