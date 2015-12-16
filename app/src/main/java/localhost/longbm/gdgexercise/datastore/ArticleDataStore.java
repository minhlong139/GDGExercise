package localhost.longbm.gdgexercise.datastore;

import java.util.List;

import localhost.longbm.gdgexercise.model.Article;

public interface ArticleDataStore {
    interface OnArticleRetrievedListener {
        void onArticleRetrieved(List<Article> postList, Exception ex);
    }

    void getPostList(OnArticleRetrievedListener onArticleRetrievedListener);

}
