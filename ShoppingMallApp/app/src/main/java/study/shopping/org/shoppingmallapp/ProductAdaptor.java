package study.shopping.org.shoppingmallapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

import study.shopping.org.shoppingmallapp.utils.Product;
import study.shopping.org.shoppingmallapp.utils.Variation;

/**
 * Created by uaer-01 on 2017-05-06.
 */

public class ProductAdaptor extends BaseAdapter {

    ArrayList<Product> products = new ArrayList<Product>();

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.product_item, parent, false);
        }


        TextView titleTextView = (TextView) convertView.findViewById(R.id.tv_product_item_title);
        TextView variationsSetTextView = (TextView) convertView.findViewById(R.id.tv_product_item_variation_sets);

        Product product = products.get(position);

        if (product != null) {
            titleTextView.setText(product.getTitle());
            ArrayList<Variation> variations = product.getVariationSets();
            Iterator<Variation> iter = variations.iterator();
            variationsSetTextView.setText("");
            while (iter.hasNext()) {
                Variation variation = iter.next();
                variationsSetTextView.append(variation.getTitle() + " ");
            }
        }

        return convertView;
    }

    public void addItem(Product product) {
        products.add(product);
    }
}
