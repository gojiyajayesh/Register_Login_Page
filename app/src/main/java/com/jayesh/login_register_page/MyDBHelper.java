package com.jayesh.login_register_page;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="users";
    private static final int DB_VERSION=1;
    private static final String TABLE_NAME="registers";
    private static final String KEY_ID="id";
    private static final String KEY_NAME="user_name";
    private static final String KEY_EMAIL="email";
    private static final String KEY_PASSWORD="password";

    public MyDBHelper( Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"( "+ KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_NAME+" TEXT,"+KEY_EMAIL+" TEXT,"+KEY_PASSWORD+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
         onCreate(db);
    }
    public boolean register_user(String User_name,String User_email,String User_password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,User_name);
        values.put(KEY_EMAIL,User_email);
        values.put(KEY_PASSWORD,User_password);
        long result= db.insert(TABLE_NAME,null,values);
        return result != -1;
    }
    public boolean isUser(String email)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(" SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_EMAIL+"=?",new String[] {email});
        boolean result= cursor.getCount()>0;
        cursor.close();
        db.close();
        return result;
    }
    public boolean isUserPassword(String email,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(" SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_EMAIL+"= ? AND "+KEY_PASSWORD+" = ? ",new String[] {email,password});
        boolean result= cursor.getCount()>0;
        cursor.close();
        db.close();
        return result;
    }

}
