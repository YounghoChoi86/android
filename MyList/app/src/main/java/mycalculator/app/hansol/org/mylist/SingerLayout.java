package mycalculator.app.hansol.org.mylist;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by yhchoi on 2017-05-01.
 */

public class SingerLayout extends LinearLayout {
    Context mContext;
    TextView singerNameTextView;
    TextView singerAgeTextView;

    public SingerLayout(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public SingerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    private void init() {
        LayoutInflater inflator = (LayoutInflater)mContext.
                getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        inflator.inflate(R.layout.singer_item, this, true);

        this.singerNameTextView = (TextView)findViewById(R.id.tv_singer_name);
        this.singerAgeTextView = (TextView)findViewById(R.id.tv_singer_age);
    }

    public void setNameTextView(String name) {
        this.singerNameTextView.setText(name);
    }
    public void setAgeTextView(String age) {
        this.singerAgeTextView.setText(age);
    }
}
