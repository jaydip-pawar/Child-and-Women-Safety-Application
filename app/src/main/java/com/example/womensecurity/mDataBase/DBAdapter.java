package com.example.womensecurity.mDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static com.example.womensecurity.mDataBase.Constants.NAME;
import static com.example.womensecurity.mDataBase.Constants.NUMBER;

public class DBAdapter {

    Context c;
    SQLiteDatabase db;
    DBHelper helper;

    public DBAdapter(Context c){
        this.c=c;
        helper = new DBHelper(c);
    }

    //OPEN CON
    public void openDB()
    {
        try
        {
            db=helper.getWritableDatabase();
        }catch (SQLException e)
        {

        }
    }

    //CLOSE DB
    public void closeDB()
    {
        try
        {
            helper.close();
        }catch (SQLException e)
        {

        }
    }

    //SAVE
    public boolean add(String name, String number)
    {
        try
        {
            ContentValues cv=new ContentValues();
            cv.put(NAME,name);
            cv.put(Constants.NUMBER,number);

            openDB();
            long result=db.insert(Constants.TB_NAME, Constants.ROW_ID, cv);
            closeDB();
            if(result>0)
            {
                return true;
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    //SELECT
    public Cursor retrieve()
    {
        String[] columns={Constants.ROW_ID, NAME, NUMBER};

        Cursor c=db.query(Constants.TB_NAME, columns, null, null, null, null, null);
        return c;
    }

    //UPDATE
    public boolean update(String newName, int id, String newNumber)
    {
        try
        {
            ContentValues cv=new ContentValues();
            cv.put(NAME,newName);
            cv.put(NUMBER,newNumber);

            openDB();
            int result=db.update(Constants.TB_NAME,cv,Constants.ROW_ID+"=?", new String[]{String.valueOf(id)});
            closeDB();
            if(result>0)
            {
                return true;
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    //DELETE/REMOVE
    public boolean delete(int id)
    {
        try
        {
            int result=db.delete(Constants.TB_NAME,Constants.ROW_ID+"=?",new String[]{String.valueOf(id)});
            if(result>0)
            {
                return true;
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

}
