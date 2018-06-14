package pl.forseti.android.models;

import java.util.List;

public class ThumbsResponse {

    private List<ThumbDetail> thumbDetails;

    public ThumbsResponse(List<ThumbDetail> thumbDetails) {
        this.thumbDetails = thumbDetails;
    }

    public List<ThumbDetail> getThumbDetails() {
        return thumbDetails;
    }
}
