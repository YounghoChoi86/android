package mycalculator.app.hansol.org.mylist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MainListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        ListView listView = (ListView)findViewById(R.id.lv_basic);

        BaseAdapter myAdapter = new MyAdapter(getApplicationContext());

        listView.setAdapter(myAdapter);
    }
}
