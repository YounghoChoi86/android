package android.hansol.org.postingapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yhchoi on 2017-05-01.
 */

public class DBPostingHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "postingManager";
    private static final String TABLE_POSTINGS = "postings";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CONTENTS = "contents";

    public DBPostingHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_POSTINGS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_CONTENTS + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSTINGS);
        onCreate(db);

    }

    public void addPosting(Posting posting) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ID, posting.getId());
        values.put(KEY_NAME, posting.getName()); // Contact Name
        values.put(KEY_CONTENTS, posting.getContents()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_POSTINGS, null, values);
        db.close(); // Closing database connection
    }

    public Posting getPosting(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Posting posting = null;
        Cursor cursor = db.query(TABLE_POSTINGS, new String[] { KEY_ID,
                        KEY_NAME, KEY_CONTENTS}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
            if (cursor.getCount() == 0) {
                return null;
            }
            posting = new Posting(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2));

        return posting;
    }
    public List<Posting> getAllPostings() {

        List<Posting> postingList = new ArrayList<Posting>();

        // Select All Query

        String selectQuery = "SELECT  * FROM " + TABLE_POSTINGS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {

            do {
                Posting posting = new Posting();

                posting.setId(Integer.parseInt(cursor.getString(0)));
                posting.setName(cursor.getString(1));
                posting.setContents(cursor.getString(2));
                // Adding contact to list
                postingList.add(posting);

            } while (cursor.moveToNext());

        }
        return postingList;
    }
    public int updatePosting(Posting posting) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, posting.getName());
        values.put(KEY_CONTENTS, posting.getContents());

        return db.update(TABLE_POSTINGS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(posting.getId()) });
    }

    public void deletePosting(Posting posting) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_POSTINGS, KEY_ID + " = ?",
                new String[] { String.valueOf(posting.getId()) });

        db.close();
    }
    public int getPostingsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_POSTINGS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
}
