package localhost.longbm.gdgexercise.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
/**
 * Created by longbm on 16/12/2015.
 */
public class ArticleConverter extends EasyDeserializer<Article>  {
    @Override
    public Article deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        Article article = null;
        if (json != null && json.isJsonObject()) {
            JsonObject postJsonObject = json.getAsJsonObject();
            article = new Article();
            article.setId(getStringValue(postJsonObject.get("id"), null));
            article.setTitle(getStringValue(postJsonObject.get("title"), null));
            article.setImage(getStringValue(postJsonObject.get("image"), null));
            article.setAuthor(getStringValue(postJsonObject.get("author"), null));
            article.setDescription(getStringValue(postJsonObject.get("description"), null));
            article.setCreated(getStringValue(postJsonObject.get("created"), null));
        }
        return article;
    }
}
