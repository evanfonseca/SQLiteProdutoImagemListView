package com.example.enfonseca.sqliteprodutoimagemlistview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by enfonseca on 04/05/16.
 */
public class DAOdb {


    private SQLiteDatabase database;
    private DBhelper dbHelper;

    public DAOdb(Context context) {
        dbHelper = new DBhelper(context);
        database = dbHelper.getWritableDatabase();

        //Ajuda a saber se a base dados foi criada e onde se encontra localizada
        //String sdb=database.toString();
        //Log.d("SBD",sdb);
    }


    /**
     * close any database object
     */
    public void close() {
        dbHelper.close();
    }







}
