package mycalculator.app.hansol.org.mylist;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(layoutParams);

        TextView nameView = new TextView(mContext);
        nameView.setText(names[position]);
        nameView.setTextColor(Color.BLUE);
        nameView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);

        linearLayout.addView(nameView);

        TextView ageView = new TextView(mContext);
        ageView.setText(ages[position]);
        ageView.setTextColor(Color.RED);
        ageView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

        linearLayout.addView(ageView);

        return linearLayout;
    }
}
