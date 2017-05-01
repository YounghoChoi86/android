package mycalculator.app.hansol.org.mylist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class MainListActivity extends AppCompatActivity {
    private String[] names =  {"소녀시대", "티아라", "걸스데이", "아이유", "애프터스쿨"};
    private String[] ages =  {"26", "23", "24", "27", "30"};
    private int[] images = { R.drawable.face01, R.drawable.face02, R.drawable.face03, R.drawable.face04, R.drawable.face05 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        ListView listView = (ListView)findViewById(R.id.lv_basic);

        MyAdapter myAdapter = new MyAdapter(getApplicationContext());
        int length = names.length;
        SingerItem singerItem = null;

        for (int i = 0; i < length; i++) {
            singerItem = new SingerItem(names[i], ages[i], images[i]);
            myAdapter.addItem(singerItem);
        }

        listView.setAdapter(myAdapter);

        //String[] items =  {"소녀시대", "티아라", "걸스데이", "아이유", "애프터스쿨"};

        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
        //        android.R.layout.simple_list_item_1, items);

        //listView.setAdapter(arrayAdapter);
    }
}
