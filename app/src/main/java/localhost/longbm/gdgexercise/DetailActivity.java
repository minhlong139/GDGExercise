package localhost.longbm.gdgexercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import localhost.longbm.gdgexercise.Utils.URLImageParser;
import localhost.longbm.gdgexercise.model.Article;

public class DetailActivity extends AppCompatActivity {
    public static final String HOME_TO_DETAIL_KEY = "home-to-detail";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Article article = (Article) getIntent().getSerializableExtra(HOME_TO_DETAIL_KEY);

        ImageView imageView = (ImageView) findViewById(R.id.iv_thumbnail);
        Picasso.with(this).load(article.getImage()).error(R.drawable.article).into(imageView);

        TextView textView = (TextView) findViewById(R.id.tv_heading);
        textView.setText(article.getTitle());

        TextView description = (TextView) findViewById(R.id.tv_description);
        URLImageParser p = new URLImageParser(description, this);
        Spanned htmlSpan = Html.fromHtml(article.getDescription(), p, null);
        description.setText(htmlSpan);
    }
}
