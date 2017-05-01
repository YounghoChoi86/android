package android.hansol.org.postingapp;

import android.hansol.org.postingapp.data.Posting;
import android.hansol.org.postingapp.utils.NetworkUtils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;

/**
 * Created by yhchoi on 2017-05-01.
 */

public class PostActivity extends AppCompatActivity implements View.OnClickListener{
    Button postingButton;
    ProgressBar progressBarForNetwork;
    EditText nameEditText;
    EditText contentsEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_post);

        (postingButton = (Button)findViewById(R.id.button_post_potsing)).setOnClickListener(this);
        progressBarForNetwork = (ProgressBar)findViewById(R.id.pb_for_networking);
        nameEditText = (EditText)findViewById(R.id.et_posting_name);
        contentsEditText = (EditText)findViewById(R.id.et_posting_contents);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_post_potsing:
                postNewPosting();
                break;
        }
    }
    public void makeLongToast(String toastText) {
        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
    }

    public void postNewPosting() {
        String name = nameEditText.getText().toString();
        String contents = contentsEditText.getText().toString();
        if (name.equals("")) {
            nameEditText.requestFocus();
            makeLongToast("이름이 없습니다...");
            return;
        }
        if (contents.equals("")) {
            contentsEditText.requestFocus();
            makeLongToast("내용이 없습니다...");
            return;
        }

        Posting posting = new Posting();
        makeLongToast("게시물을 올립니다..");
        posting.setName(name);
        posting.setContents(contents);

        new postNewPostingTask().execute(posting);
    }

    public class postNewPostingTask extends AsyncTask<Posting, Void, String> {

        // COMPLETED (2) Override the doInBackground method to perform the query. Return the results. (Hint: You've already written the code to perform the query)
        @Override
        protected String doInBackground(Posting... postings) {
            Posting posting = postings[0];
            String postPostingResults = null;
            URL postUrl = NetworkUtils.buildUrl();

            try {
                postPostingResults = NetworkUtils.postResponseFromHttpUrl(postUrl, posting.jsonify());
            } catch (ConnectException e) {
                e.printStackTrace();
                //Toast.makeText(MainActivity.this, "서버 접속 오류입니다.", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                //Toast.makeText(MainActivity.this, "게시물을 가져오는데 실패하였습니다.", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {

            }

            return postPostingResults;
        }

        // COMPLETED (3) Override onPostExecute to display the results in the TextView
        @Override
        protected void onPostExecute(String postPostingResults) {

            Log.d("onPostExecute", postPostingResults.toString());
            progressBarForNetwork.setVisibility(View.INVISIBLE);
        }
    }
}
