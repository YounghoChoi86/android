package study.shopping.org.shoppingmallapp.utils;

/**
 * Created by uaer-01 on 2017-05-06.
 */

public class Variation {
    private int id;
    private String title;
    private String price;

    public Variation(int id, String title,String price) {
        this.title = title;
        this.id = id;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "variation" + this.id + ":" + this.title + ":" + this.price + "/";
    }
}
