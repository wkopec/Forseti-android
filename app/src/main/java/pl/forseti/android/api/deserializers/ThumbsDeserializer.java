package pl.forseti.android.api.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pl.forseti.android.models.ThumbDetail;
import pl.forseti.android.models.ThumbsResponse;

public class ThumbsDeserializer implements JsonDeserializer<ThumbsResponse> {
    @Override
    public ThumbsResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        List<ThumbDetail> thumbDetails = new ArrayList<>();
        Set<Map.Entry<String, JsonElement>> entries = jsonObject.entrySet();
        for (Map.Entry<String, JsonElement> entry : entries) {
            ThumbDetail thumbDetail = context.deserialize(jsonObject.get(entry.getKey()), ThumbDetail.class);
            thumbDetail.setAccountNumber(entry.getKey());
            thumbDetails.add(thumbDetail);
        }

        return new ThumbsResponse(thumbDetails);
    }
}
