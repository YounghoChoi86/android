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

        //String[] items =  {"소녀시대", "티아라", "걸스데이", "아이유", "애프터스쿨"};

        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
        //        android.R.layout.simple_list_item_1, items);

        //listView.setAdapter(arrayAdapter);
    }
}
