package localhost.longbm.gdgexercise;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

import localhost.longbm.gdgexercise.model.Article;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        createList();
    }

    protected void createList() {

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
            try {
                URL url = new URL(article.getImage());
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                imageView.setImageBitmap(bmp);
            } catch (Exception ex) {
                int imageResource = parent.getResources().getIdentifier("@drawable/artichle", null, null);
                imageView.setImageResource(imageResource);
            }

            return convertView;
        }
    }
}
