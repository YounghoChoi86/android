package android.hansol.org.postingapp.data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by uaer-01 on 2017-04-29.
 */

public class Posting {
    private int    id;
    private String name;
    private String contents;
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CONTENTS = "contents";

    public Posting() {

    }

    public Posting(JSONObject  postingJson) throws JSONException{
        this.id = postingJson.getInt("id");
        this.name = postingJson.getString("name");
        this.contents = postingJson.getString("contents");
    }

    public Posting(int id, String name, String contents) {
        this.id = id;
        this.name = name;
        this.contents = contents;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "작성자:" + this.name + "/ 내용:" + this.contents;
    }

    @Override
    public boolean equals(Object obj) {
        Posting otherPosting = (Posting)obj;
        if (otherPosting.getId() == this.id && otherPosting.getName().equals(this.name) &&otherPosting.getContents().equals(this.contents)) {
            return true;
        }
        else {
            return false;
        }
    }

    public String jsonify() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put(KEY_NAME, this.name);
        obj.put(KEY_CONTENTS, this.contents);

        return obj.toString();
    }
}
