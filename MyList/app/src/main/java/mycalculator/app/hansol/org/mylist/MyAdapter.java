package mycalculator.app.hansol.org.mylist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by uaer-01 on 2017-05-01.
 */

public class MyAdapter extends BaseAdapter {
    //private String[] names =  {"소녀시대", "티아라", "걸스데이", "아이유", "애프터스쿨"};
    //private String[] ages =  {"26", "23", "24", "27", "30"};

    ArrayList<SingerItem> items = new ArrayList<SingerItem>();

    private Context mContext;

    public MyAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(SingerItem item) {
        this.items.add(item);
    }
    public void clear() {
        this.items.clear();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SingerLayout linearLayout = new SingerLayout(mContext);

        SingerItem item = this.items.get(position);

        linearLayout.setNameTextView(item.getName());
        linearLayout.setAgeTextView(item.getAge());
        linearLayout.setImageView(item.getRedId());

        return linearLayout;
    }
}
