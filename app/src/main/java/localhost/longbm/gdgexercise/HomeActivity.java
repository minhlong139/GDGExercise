package localhost.longbm.gdgexercise;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import localhost.longbm.gdgexercise.datastore.ArticleDataStore;
import localhost.longbm.gdgexercise.datastore.FileBasedArticleDataStore;
import localhost.longbm.gdgexercise.model.Article;
import localhost.longbm.gdgexercise.model.ArticleConverter;

public class HomeActivity extends AppCompatActivity {
    public static final String DATA_JSON_FILE_NAME = "data.json";

    private ArticleDataStore dataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Article.class, new ArticleConverter());
        Gson gson = gsonBuilder.create();
        InputStream is = null;
        try {
            is = getAssets().open(DATA_JSON_FILE_NAME);
            dataStore = new FileBasedArticleDataStore(gson, is);
            dataStore.getPostList(new ArticleDataStore.OnArticleRetrievedListener() {
                @Override
                public void onArticleRetrieved(List<Article> articles, Exception ex) {
                    displayArticles(articles);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void displayArticles(List<Article> articles) {
        ListView listView = (ListView) findViewById(R.id.lv_items);
        ArticleAdapter itemAdapter = new ArticleAdapter(this, R.layout.article);
        itemAdapter.addAll(articles);
        listView.setAdapter(itemAdapter);
    }

    private static class ArticleAdapter extends ArrayAdapter<Article> {

        public ArticleAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                convertView = layoutInflater.inflate(R.layout.article, parent, false);
            }
            Article article = getItem(position);

            TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            tvTitle.setText(article.getTitle());

            ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_image);
            Picasso.with(getContext()).load(article.getImage()).error(R.drawable.article).into(imageView);

            return convertView;
        }
    }
}
