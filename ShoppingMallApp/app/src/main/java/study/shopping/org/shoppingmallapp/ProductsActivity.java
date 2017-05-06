package study.shopping.org.shoppingmallapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

import study.shopping.org.shoppingmallapp.utils.NetworkUtils;

public class ProductsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mJsonDisplyTextView;
    EditText mUrlPathEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        (mJsonDisplyTextView = (TextView)findViewById(R.id.tv_disply_json)).setOnClickListener(this);
        mUrlPathEditText = (EditText)findViewById(R.id.et_url_path);
    }

    @Override
    public void onClick(View view) {
        new restAsyncTask().execute("api", mUrlPathEditText.getText().toString());
    }

    public class restAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String httpResultJson = null;
            URL resultUrl = NetworkUtils.buildUrl(params, null);
            
            try {
                httpResultJson = NetworkUtils.getResponseFromHttpUrl(resultUrl);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return httpResultJson;
        }
        protected void onPostExecute(String httpResultJson) {
            if (mJsonDisplyTextView != null) {
                mJsonDisplyTextView.setText(httpResultJson);
            }
            return ;
        }
    }
}
