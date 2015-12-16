package localhost.longbm.gdgexercise.datastore;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import localhost.longbm.gdgexercise.model.Article;

/**
 * Created by longbm on 16/12/2015.
 */
public class FileBasedArticleDataStore implements ArticleDataStore {
    private InputStream fileInputStream;
    private Gson gson;

    public FileBasedArticleDataStore(Gson gson, InputStream jsonInputStream) {
        if (jsonInputStream == null) {
            throw new IllegalArgumentException("Json file should be provided");
        }
        this.gson = gson;
        this.fileInputStream = jsonInputStream;
    }

    @Override
    public void getPostList(OnArticleRetrievedListener onArticleRetrievedListener) {
        if (onArticleRetrievedListener != null) {
            Type type = new TypeToken<List<Article>>(){}.getType();
            List<Article> posts = gson.fromJson(new InputStreamReader(fileInputStream), type);
            onArticleRetrievedListener.onArticleRetrieved(posts, null);
        }
    }
}
