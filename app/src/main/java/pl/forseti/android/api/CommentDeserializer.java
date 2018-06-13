package pl.forseti.android.api;

import android.support.annotation.Nullable;

import com.google.gson.JsonArray;
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

import pl.forseti.android.models.Comment;
import pl.forseti.android.models.CommentsResponse;

public class CommentDeserializer implements JsonDeserializer<CommentsResponse> {

    @Override
    public CommentsResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        List<Comment> comments = new ArrayList<>();
        Set<Map.Entry<String, JsonElement>> entries = jsonObject.entrySet();
        for (Map.Entry<String, JsonElement> entry: entries) {
            String username = entry.getKey();
            JsonArray userComments = jsonObject.get(username).getAsJsonArray();
            for (JsonElement userComment : userComments) {
                Comment comment = context.deserialize(userComment, Comment.class);
                comment.setUsername(username);
                comments.add(comment);
            }
        }

        return new CommentsResponse(comments);

    }

}
