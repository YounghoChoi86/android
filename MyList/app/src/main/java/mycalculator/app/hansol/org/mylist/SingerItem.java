package mycalculator.app.hansol.org.mylist;

/**
 * Created by yhchoi on 2017-05-01.
 */

public class SingerItem {
    private String name;
    private String age;
    private int redId;

    public SingerItem(String name, String age, int redId) {
        this.name = name;
        this.age = age;
        this.redId = redId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getRedId() {
        return redId;
    }

    public void setRedId(int redId) {
        this.redId = redId;
    }
}
