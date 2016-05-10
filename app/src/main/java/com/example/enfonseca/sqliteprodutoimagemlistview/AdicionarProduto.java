package com.example.enfonseca.sqliteprodutoimagemlistview;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdicionarProduto extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;
    private Uri mCapturedImageURI;
    private final int THUMBSIZE = 96;
    private DAOdb daOdb;
    private  Uri imageChoosenUri=null;
    Bitmap  mBitmap=null;




    ImageView imageP;
    ImageView my_img_view;
    EditText name,price;
    String nome="";
    Double preco=0.0;

    //Quando tiver adicionar várias imagens para um produto
    ArrayList<Image> myImageList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_produto);

        imageP = (ImageView) findViewById(R.id.item_img_icon);
        name= (EditText) findViewById(R.id.nome);



        price= (EditText) findViewById(R.id.preco);


        myImageList = new ArrayList<Image>() ;

        my_img_view = (ImageView ) findViewById (R.id.item_img_icon);

        if (savedInstanceState!=null){
            imageChoosenUri = Uri.parse(
                    savedInstanceState.getString("imageChoosenUri"));

            mBitmap = savedInstanceState.getParcelable("mBitmap");

            nome=savedInstanceState.getString("nome");
            preco=savedInstanceState.getDouble("preco");

            myImageList = (ArrayList<Image>)savedInstanceState.getSerializable("myImageList");
        }

        name.setText(nome);
        price.setText(preco.toString());

        if(mBitmap!=null){
            my_img_view.setImageBitmap(mBitmap);
        }



    }

    public  void insertProduct(View view){

        daOdb = new DAOdb(this);
        Product product=new Product();

        nome =name.getText().toString();
        product.setName(nome);

        preco= Double.parseDouble(price.getText().toString());
        product.setPrice(preco);

        Log.d("AKI", "AKI");
        daOdb.AddProductWithListImage(product, myImageList);
    }




    /**
     * to gallery
     */
    private void activeGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);




        startActivityForResult(intent, RESULT_LOAD_IMAGE);

    }



    /**
     * take a photo
     */
    private void activeTakePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            String fileName = "temp.jpg";
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, fileName);
            mCapturedImageURI = getContentResolver()
                    .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            values);
            takePictureIntent
                    .putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);


            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);


        }
    }



    public void btnAddOnClick(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_box);
        dialog.setTitle("Alert Dialog View");
        Button btnExit = (Button) dialog.findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                dialog.dismiss();

            }
        });
        dialog.findViewById(R.id.btnChoosePath)
                .setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        activeGallery();
                    }
                });
        dialog.findViewById(R.id.btnTakePhoto)
                .setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        activeTakePhoto();
                    }
                });

        // show dialog on screen
        dialog.show();
    }



    @Override protected void onActivityResult(int requestCode, int resultCode,
                                              Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_LOAD_IMAGE:
                if (requestCode == RESULT_LOAD_IMAGE &&
                        resultCode == RESULT_OK && null != data) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver()
                            .query(selectedImage, filePathColumn, null, null,
                                    null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();

                    Image image = new Image();
                    image.setTitle("Test");
                    image.setDescription(
                            "test choose a photo from gallery and add it to " +
                                    "list view");
                    image.setDatetime(System.currentTimeMillis());
                    image.setPath(picturePath);


                    //Quando tiver que adicionar muitas imagens, utiliza lista neste caso com um "for each"
                    myImageList.add(image);

                    //Toast.makeText(this,""+selectedImage,Toast.LENGTH_LONG).show();

                    imageChoosenUri=selectedImage;

                }
            case REQUEST_IMAGE_CAPTURE:
                if (requestCode == REQUEST_IMAGE_CAPTURE &&
                        resultCode == RESULT_OK) {
                    String[] projection = {MediaStore.Images.Media.DATA};
                    Cursor cursor =
                            managedQuery(mCapturedImageURI, projection, null,
                                    null, null);
                    int column_index_data = cursor.getColumnIndexOrThrow(
                            MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    String picturePath = cursor.getString(column_index_data);

                    Image myImage = new Image();

                    myImage.setTitle("Test");
                    myImage.setDescription(
                            "test take a photo and add it to list view");
                    myImage.setDatetime(System.currentTimeMillis());
                    myImage.setPath(picturePath);

                    //Quando tiver que adicionar muitas imagens, utiliza lista neste caso com um "for each"
                    myImageList.add(myImage);

                    //Toast.makeText(this,""+mCapturedImageURI,Toast.LENGTH_LONG).show();

                    imageChoosenUri=mCapturedImageURI;

                }
        }


        try {
            mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageChoosenUri);
            my_img_view.setImageBitmap(mBitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override protected void onSaveInstanceState(Bundle outState) {
        // Save the user's current game state
        if (mCapturedImageURI != null) {
            outState.putString("mCapturedImageURI",
                    mCapturedImageURI.toString());
        }

        if (imageChoosenUri != null) {
            outState.putString("imageChoosenUri",
                    imageChoosenUri.toString());
        }

        //mBitmap
        if (mBitmap != null) {
            outState.putParcelable("mBitmap", mBitmap);;
        }


        outState.putString("nome", nome);
        outState.putDouble("preco", preco);


        outState.putSerializable("myImageList", myImageList);



            outState.putString("RESULT_LOAD_IMAGE",
                    ""+RESULT_LOAD_IMAGE);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(outState);
    }

    @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        if (savedInstanceState.containsKey("mCapturedImageURI")) {
            mCapturedImageURI = Uri.parse(
                    savedInstanceState.getString("mCapturedImageURI"));
        }

        //imageChoosenUri

        // Restore state members from saved instance
        if (savedInstanceState.containsKey("imageChoosenUri")) {
            imageChoosenUri = Uri.parse(
                    savedInstanceState.getString("imageChoosenUri"));
        }

        myImageList = (ArrayList<Image>)savedInstanceState.getSerializable("myImageList");


    }






}
