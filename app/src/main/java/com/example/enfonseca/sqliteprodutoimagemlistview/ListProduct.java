package com.example.enfonseca.sqliteprodutoimagemlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by enfonseca on 06/05/16.
 */
public class ListProduct extends AppCompatActivity {

    private ArrayList<Product> Products;
    private ProdutoAdapter productAdapter;
    private ListView listView;
    private DAOdb daOdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listproduct);


        //Toast.makeText(this,"Aqui",Toast.LENGTH_LONG).show();


        // Construct the data source
        Products = new ArrayList();
        // Create the adapter to convert the array to views

        initDB();

        productAdapter = new ProdutoAdapter(this, Products);
        // Attach the adapter to a ListView

        listView = (ListView) findViewById(R.id.main_list_view);
        listView.setAdapter(productAdapter);

        //addItemClickListener(listView);



    }

    /**
     * initialize database
     */
    private void initDB() {
        daOdb = new DAOdb(this);
        //        add images from database to images ArrayList
        for (Product p : daOdb.getAllProduct()) {
            Products.add(p);
        }
    }



}
