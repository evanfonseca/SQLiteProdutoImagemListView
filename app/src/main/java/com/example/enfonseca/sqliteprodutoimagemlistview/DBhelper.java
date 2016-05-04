package com.example.enfonseca.sqliteprodutoimagemlistview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by enfonseca on 5/1/16.
 */
public class DBhelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "sqliteprodutoimage.db";
    public static final int DB_VERSION = 1;

    public static final String COMMA_SEP = ",";
    public static final String TEXT_TYPE = " TEXT";
    public static final String NUMERIC_TYPE = " NUMERIC";
    public static final String INTEGER_TYPE = " INTEGER";

    public static final String TABLE_IMAGE = "image";
    public static final String TABLE_PRODUCT = "product";
    public static final String TABLE_PRODUCTI_IMAGE = "product_imagem";

    public static final String COLUMN_PATH = "path";

    //Colunas da tabela Imagem
    private static final String COLUMN_ID_IMAGE = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DATETIME = "datetime";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String PRIMARY_KEY_IMAGE = "PRIMARY KEY (" + COLUMN_ID_IMAGE + ")";


    //Colunas da tabela Produto
    private static final String COLUMN_ID_PRODUCT = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";
    public static final String PRIMARY_KEY_PRODUCT = "PRIMARY KEY (" + COLUMN_ID_PRODUCT + ")";


    //Colunas da tabela ProdutoImagem
    private static final String COLUMN_PRODUCT_ID = "idP";
    private static final String COLUMN_IMAGE_ID = "idIm";
    public static final String PRIMARY_KEY_PRODUCTIMAGE = "PRIMARY KEY ("  + COLUMN_PRODUCT_ID + "," + COLUMN_IMAGE_ID + ")";


    //TABELA IMAGEM Script para (Criar e Apagar)
    private static final String DELETE_TABLE_IMAGE = "DROP TABLE IF EXISTS " + TABLE_IMAGE;
    private static final String CREATE_TABLE_IMAGE = "CREATE TABLE " + TABLE_IMAGE + " (" +
            COLUMN_ID_IMAGE + INTEGER_TYPE + COMMA_SEP +
            COLUMN_PATH + TEXT_TYPE + COMMA_SEP +
            COLUMN_TITLE + TEXT_TYPE + COMMA_SEP +
            COLUMN_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
            COLUMN_DATETIME + NUMERIC_TYPE + COMMA_SEP +
            PRIMARY_KEY_IMAGE +
            " )";

    //TABELA PRODUTO Script para (Criar e Apagar)
    private static final String DELETE_TABLE_PRODUCT = "DROP TABLE IF EXISTS " + TABLE_PRODUCT;
    private static final String CREATE_TABLE_PRODUCT = "CREATE TABLE " + TABLE_PRODUCT + " (" +
            COLUMN_ID_PRODUCT + INTEGER_TYPE + COMMA_SEP +
            COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
            COLUMN_PRICE + NUMERIC_TYPE + COMMA_SEP +
            PRIMARY_KEY_PRODUCT +
            " )";


    //TABELA PRODUTOIMAGEM Script para (Criar e Apagar)
    private static final String DELETE_TABLE_PRODUCTIMAGE = "DROP TABLE IF EXISTS " + TABLE_PRODUCTI_IMAGE;
    private static final String CREATE_TABLE_PRODUCTIMAGE = "CREATE TABLE " + TABLE_PRODUCTI_IMAGE + " (" +
            COLUMN_PRODUCT_ID + INTEGER_TYPE + COMMA_SEP +
            COLUMN_IMAGE_ID + INTEGER_TYPE + COMMA_SEP +
            PRIMARY_KEY_PRODUCTIMAGE +
            " )";

    public DBhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_IMAGE);
        db.execSQL(CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_PRODUCTIMAGE);

    }


    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE_IMAGE);
        db.execSQL(DELETE_TABLE_PRODUCT);
        db.execSQL(DELETE_TABLE_PRODUCTIMAGE);
        onCreate(db);
    }
}
