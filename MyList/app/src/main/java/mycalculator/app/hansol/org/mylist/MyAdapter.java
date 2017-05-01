package mycalculator.app.hansol.org.mylist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by uaer-01 on 2017-05-01.
 */

public class MyAdapter extends BaseAdapter {
    private String[] names =  {"소녀시대", "티아라", "걸스데이", "아이유", "애프터스쿨"};
    private String[] ages =  {"26", "23", "24", "27", "30"};
    private Context mContext;

    public MyAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return names[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SingerLayout linearLayout = new SingerLayout(mContext);

        linearLayout.setNameTextView(names[position]);
        linearLayout.setAgeTextView(ages[position]);

        return linearLayout;
    }
}
