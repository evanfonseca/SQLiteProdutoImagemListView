package com.example.enfonseca.sqliteprodutoimagemlistview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

public class ShowProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);

        DAOdb daOdb = new DAOdb(this);

        TextView nome= (TextView ) findViewById(R.id.nome);
        TextView  preco= (TextView ) findViewById(R.id.preco);
        TextView  desc= (TextView ) findViewById(R.id.desc);
        ImageView mImageView= (ImageView) findViewById(R.id.imgProduto);




       int ID=getIntent().getIntExtra("ID",0);

        Product p=daOdb.getProductById(ID);
        List<Image> listImages=daOdb.getImagesbyIdProduct(ID);

        Image imP = listImages.get(0);

        Bitmap bitmap = BitmapFactory.decodeFile(imP.getPath());
        mImageView.setImageBitmap(bitmap);

        nome.setText(p.getName());
        preco.setText("" + p.getPrice());
        desc.setText("Produto adicionado no dia "
                        +imP.getDatetime().get(Calendar.DAY_OF_MONTH)
                        +"/"+imP.getDatetime().get(Calendar.MONTH)
                        +"/"+imP.getDatetime().get(Calendar.YEAR));



    }

}
