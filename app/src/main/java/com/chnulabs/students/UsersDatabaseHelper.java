package com.chnulabs.students;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class UsersDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UsersDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "User";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_AGE = "birthday";
    private static final String KEY_IS_USER = "isUser";

    private UsersDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static UsersDatabaseHelper instance;

    public static synchronized UsersDatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new UsersDatabaseHelper(context);
        }
        return instance;
    }

    public String test() {
        return "test";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_AGE + " DATE,"
                + KEY_IS_USER + " INTEGER" + ")";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldV, int newV) {

    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_AGE, user.getBirthday());
        values.put(KEY_IS_USER, user.isStudent() ? 1 : 0);
        db.insert(TABLE_NAME, null, values);
    }

    public void addUser(SQLiteDatabase db, User user) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_AGE, user.getBirthday());
        values.put(KEY_IS_USER, user.isStudent() ? 1 : 0);
        db.insert(TABLE_NAME, null, values);
    }

    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID, KEY_NAME, KEY_AGE, KEY_IS_USER},
                KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        User user = null;

        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int nameIndex = cursor.getColumnIndex(KEY_NAME);
            int birthdayIndex = cursor.getColumnIndex(KEY_AGE);
            int isUserIndex = cursor.getColumnIndex(KEY_IS_USER);

            if (idIndex != -1 && nameIndex != -1 && birthdayIndex != -1 && isUserIndex != -1) {
                user = new User(
                        cursor.getInt(idIndex),
                        cursor.getString(nameIndex),
                        cursor.getString(birthdayIndex),
                        cursor.getInt(isUserIndex) == 1
                );
            }
            cursor.close();
        }
        return user;
    }


    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(KEY_ID);
                int nameIndex = cursor.getColumnIndex(KEY_NAME);
                int birthdayIndex = cursor.getColumnIndex(KEY_AGE);
                int isUserIndex = cursor.getColumnIndex(KEY_IS_USER);

                if (idIndex != -1 && nameIndex != -1 && birthdayIndex != -1 && isUserIndex != -1) {
                    User user = new User();
                    user.setId(cursor.getInt(idIndex));
                    user.setName(cursor.getString(nameIndex));
                    user.setBirthday(cursor.getString(birthdayIndex));
                    user.setIsStudent(cursor.getInt(isUserIndex) == 1);
                    userList.add(user);
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        return userList;
    }



    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_AGE, user.getBirthday());
        values.put(KEY_IS_USER, user.isStudent() ? 1 : 0);
        return db.update(TABLE_NAME, values, KEY_ID + "=?", new String[]{String.valueOf(user.getId())});
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=?", new String[]{String.valueOf(user.getId())});
    }

    public int getUsersCount() {
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

}
