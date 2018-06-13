package pl.forseti.android.models;

import java.util.List;

/**
 * Created by Volfram on 13.06.2018.
 */

public class CommentsResponse {

    private List<Comment> comments;

    public CommentsResponse(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
