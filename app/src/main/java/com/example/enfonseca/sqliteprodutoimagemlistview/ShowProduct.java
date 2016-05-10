package com.example.enfonseca.sqliteprodutoimagemlistview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ShowProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);

        DAOdb daOdb = new DAOdb(this);

        TextView nome= (TextView ) findViewById(R.id.nome);
        TextView  preco= (TextView ) findViewById(R.id.preco);
        TextView  desc= (TextView ) findViewById(R.id.desc);




       int ID=getIntent().getIntExtra("ID",0);

        Product p;
        Image im;
    }

}
