package com.example.enfonseca.sqliteprodutoimagemlistview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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




    //DAO OF IMAGE

    /**
     * insert a text report item to the location database table
     *
     * @param image
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    public long addImage(Image image) {
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.COLUMN_PATH, image.getPath());
        cv.put(DBhelper.COLUMN_TITLE, image.getTitle());
        cv.put(DBhelper.COLUMN_DESCRIPTION, image.getDescription());
        cv.put(DBhelper.COLUMN_DATETIME, System.currentTimeMillis());
        return database.insert(DBhelper.TABLE_IMAGE, null, cv);
    }


    /**
     * delete the given image from database
     *
     * @param image
     */
    public void deleteImage(Image image) {
        String whereClause =
                DBhelper.COLUMN_TITLE + "=? AND " + DBhelper.COLUMN_DATETIME +
                        "=?";
        String[] whereArgs = new String[]{image.getTitle(),
                String.valueOf(image.getDatetimeLong())};
        database.delete(DBhelper.TABLE_IMAGE, whereClause, whereArgs);
    }

    /**
     * @return all image as a List
     */
    public List<Image> getImages() {
        List<Image> MyImages = new ArrayList<>();
        Cursor cursor =
                database.query(DBhelper.TABLE_IMAGE, null, null, null, null,
                        null, DBhelper.COLUMN_DATETIME + " DESC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Image MyImage = cursorToImage(cursor);
            MyImages.add(MyImage);
            cursor.moveToNext();
        }
        cursor.close();
        return MyImages;
    }


    /**
     * read the cursor row and convert the row to a MyImage object
     *
     * @param cursor
     * @return MyImage object
     */
    private Image cursorToImage(Cursor cursor) {
        Image image = new Image();
        image.setPath(
                cursor.getString(cursor.getColumnIndex(DBhelper.COLUMN_PATH)));
        image.setTitle(
                cursor.getString(cursor.getColumnIndex(DBhelper.COLUMN_TITLE)));
        image.setDatetime(cursor.getLong(
                cursor.getColumnIndex(DBhelper.COLUMN_DATETIME)));
        image.setDescription(cursor.getString(
                cursor.getColumnIndex(DBhelper.COLUMN_DESCRIPTION)));
        return image;
    }


    /**
     * @return all image by ID Product as a List
     */
    public List<Image> getImagesbyIdProduct(int idProduct) {
        List<Image> MyImages = new ArrayList<>();

        String getImages = "Select  * from " + DBhelper.TABLE_IMAGE +" ," + DBhelper.TABLE_PRODUCT_IMAGE +
                              " Where "+DBhelper.COLUMN_IMAGE_ID+" ="+DBhelper.COLUMN_ID_IMAGE +" AND"
                                +DBhelper.COLUMN_PRODUCT_ID +" ="+idProduct;

        Cursor mCursor = database.rawQuery(getImages,null);
        mCursor.moveToFirst();
        while (!mCursor.isAfterLast()) {
            Image MyImage = cursorToImage(mCursor);
            MyImages.add(MyImage);
            mCursor.moveToNext();
        }
        mCursor.close();
        return MyImages;
    }






    //DAO OF PRODUCT
    // Adicionar um produto
    /**
     * insert a text report item to the location database table
     *
     * @param
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    public long addProduct(Product p) {
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.COLUMN_NAME, p.getName());
        cv.put(DBhelper.COLUMN_PRICE, p.getPrice());
        return database.insert(DBhelper.TABLE_PRODUCT, null, cv);
    }


    /**
     * delete the given image from database
     *
     * @param
     */
    public void deleteProduct(Product p) {
        String whereClause =
                DBhelper.COLUMN_ID_PRODUCT + "=? ";
        String[] whereArgs = new String[]{String.valueOf(p.getId())};
        database.delete(DBhelper.TABLE_PRODUCT, whereClause, whereArgs);
    }



    /**
     * @return all Product as a List
     */
    /*
    public List<Image> getAllProduct() {
        List<Image> MyProducts = new ArrayList<>();
        Cursor cursor =
                database.query(DBhelper.TABLE_PRODUCT, null, null, null, null,
                        null, DBhelper.COLUMN_NAME + " DESC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Product MyProduct = cursorToImage(cursor);
            MyImages.add(MyImage);
            cursor.moveToNext();
        }
        cursor.close();
        return MyImages;
    }
    */

}
