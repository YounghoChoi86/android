package study.shopping.org.shoppingmallapp.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by uaer-01 on 2017-05-06.
 */

public class Product {
    private int id;
    private String title;
    private String imagePath;
    private ArrayList<Variation> variationSets;

    public Product() {
        this.variationSets = new ArrayList<Variation>();
    }

    //throws Json Exception.... must catch
    public Product(String productJson) throws JSONException {
        JSONObject jsonObject = new JSONObject(productJson);

        this.id = jsonObject.getInt("id");
        this.title = jsonObject.getString("title");
        this.imagePath = jsonObject.getString("image");
        JSONArray jsonArray = jsonObject.getJSONArray("variation_set");

        variationSets = new ArrayList<Variation>();
        JSONObject variationJsonObject = null;
        Variation variation = null;

        for (int i = 0; i < jsonArray.length(); i++) {
            variationJsonObject =  jsonArray.getJSONObject(i);
            variation = new Variation(variationJsonObject.getInt("id"),
                    variationJsonObject.getString("title"), variationJsonObject.getString("price"));
            variationSets.add(variation);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public ArrayList<Variation> getVariationSets() {
        return variationSets;
    }

    public void setVariationSets(ArrayList<Variation> variationSets) {
        this.variationSets = variationSets;
    }

    @Override
    public String toString() {
        return this.getId() + ":" + this.getTitle() + ":" + this.getImagePath();
    }
}
