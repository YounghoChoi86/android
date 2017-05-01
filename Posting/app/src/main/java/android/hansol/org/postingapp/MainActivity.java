package android.hansol.org.postingapp;

import android.content.Intent;
import android.hansol.org.postingapp.data.DBPostingHelper;
import android.hansol.org.postingapp.data.Posting;
import android.hansol.org.postingapp.data.PostingAdapter;
import android.hansol.org.postingapp.utils.NetworkUtils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewURL;
    private TextView textViewJSON;
    private ProgressBar progressBarForNetwork;
    private ListView postingsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ((Button) findViewById(R.id.button_get_potsing)).setOnClickListener(this);
        ((Button) findViewById(R.id.button_post_potsing)).setOnClickListener(this);
        //textViewURL = (TextView) findViewById(R.id.tv_url);
        progressBarForNetwork = (ProgressBar) findViewById(R.id.pb_for_networking);

        postingsListView = (ListView) findViewById(R.id.lv_postings) ;

        postingsListViewUpdateByDatabase();
    }
    private void postingsListViewUpdateByDatabase() {
        DBPostingHelper dbPostingHelper = new DBPostingHelper(this);
        List<Posting> postingList = dbPostingHelper.getAllPostings();
        Collections.reverse(postingList);

        postingsUpdateByList(postingList);
    }

    private void postingsUpdateByList(List<Posting> postingList) {
        PostingAdapter adapter = new PostingAdapter(postingList);

        if (postingList.size() > 0) {
            if (postingsListView != null) {
                postingsListView.setAdapter(adapter);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_get_potsing:
                Toast.makeText(MainActivity.this, "게시물 가져오기 버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
                getAllPosting();
                break;
            case R.id.button_post_potsing:
                Toast.makeText(MainActivity.this, "게시물 올리기 버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, PostActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void getAllPosting() {
        URL getUrl = NetworkUtils.buildUrl();
        progressBarForNetwork.setVisibility(View.VISIBLE);
        new getAllpostingTask().execute(getUrl);
    }

    public class getAllpostingTask extends AsyncTask<URL, Void, String> {

        // COMPLETED (2) Override the doInBackground method to perform the query. Return the results. (Hint: You've already written the code to perform the query)
        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];

            String getPostingResults = null;
            try {
                getPostingResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (ConnectException e) {
                e.printStackTrace();
                //Toast.makeText(MainActivity.this, "서버 접속 오류입니다.", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                //Toast.makeText(MainActivity.this, "게시물을 가져오는데 실패하였습니다.", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {

            }

            return getPostingResults;
        }

        // COMPLETED (3) Override onPostExecute to display the results in the TextView
        @Override
        protected void onPostExecute(String getPostingResults) {
            JSONObject jsonobject = null;
            Posting posting = null;
            JSONArray jsonArray = null;
            JSONObject elemJsonObject = null;
            List<Posting> postingArrays = new ArrayList<Posting>();
            Posting dbPosting = null;

            if (getPostingResults != null) {
                DBPostingHelper dbPostingHelper = new DBPostingHelper(MainActivity.this);
                try {
                    jsonobject = new JSONObject(getPostingResults);
                    jsonArray = jsonobject.getJSONArray("postings");
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, "게시물을 가져오는데 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                int jsonObjectLength = jsonArray.length();

                try {
                    for (int i = 0; i < jsonObjectLength; i++) {
                        elemJsonObject = jsonArray.getJSONObject(i);

                        posting = new Posting(elemJsonObject);
                        postingArrays.add(posting);
                        dbPosting = dbPostingHelper.getPosting(posting.getId());

                        if (dbPosting == null) {
                            dbPostingHelper.addPosting(posting);
                            Log.d("dbPostingHelper", "add:" + posting.toString());
                        }
                        else {
                            //Log.d("dbPostingHelper", "equal" + posting.getId() + ":" + posting.toString());
                            //Log.d("dbPostingHelper", "equal" + dbPosting.getId() + " :" + dbPosting.toString());
                            if (!posting.equals(dbPosting)) {
                                dbPostingHelper.updatePosting(posting);
                                Log.d("dbPostingHelper", "update:" + posting.toString());
                            }
                            else {
                                Log.d("dbPostingHelper", "not update:" + posting.toString());
                            }
                        }
                        Collections.reverse(postingArrays);
                        postingsUpdateByList(postingArrays);
                    }
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, "알 수 없는 형식의 데이터입니다.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    return;
                }

            } else {
                Toast.makeText(MainActivity.this, "게시물을 가져오는데 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }
            progressBarForNetwork.setVisibility(View.INVISIBLE);
        }
    }
}
