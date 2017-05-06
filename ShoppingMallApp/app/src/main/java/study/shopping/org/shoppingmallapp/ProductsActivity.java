package study.shopping.org.shoppingmallapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import study.shopping.org.shoppingmallapp.utils.NetworkUtils;
import study.shopping.org.shoppingmallapp.utils.Product;

public class ProductsActivity extends AppCompatActivity {

    private ListView mProductListView;
    private ProductAdaptor mProductAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        mProductListView = (ListView)findViewById(R.id.lv_products);
        mProductAdaptor = new ProductAdaptor(getApplicationContext());

        new restAsyncTask().execute("api", "products");
    }

    public class restAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String httpResultJson = null;
            URL resultUrl = NetworkUtils.buildUrl(params);

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
            if (httpResultJson == null) {
                Toast.makeText(ProductsActivity.this, "결과를 가져오는데 실패하였습니다.", Toast.LENGTH_LONG).show();
                return;
            }
            try {
                JSONObject jsonObject = new JSONObject(httpResultJson);

                JSONArray jsonProductsArray = jsonObject.getJSONArray("results");
                //mJsonDisplyTextView.setText("");
                for (int i = 0; i < jsonProductsArray.length(); i++) {
                    Product product = new Product(jsonProductsArray.getJSONObject(i).toString());
                    Log.d("onPostExecute:", product.toString());
                    //mJsonDisplyTextView.append(product.toString() + "\n\n");
                    mProductAdaptor.addItem(product);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            } catch (Exception e) {
                return;
            }

            mProductListView.setAdapter(mProductAdaptor);

            return;
        }
    }
}
