package com.example.enfonseca.sqliteprodutoimagemlistview;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by enfonseca on 26/04/16.
 */
public class ProdutoAdapter extends ArrayAdapter<Product> {


    private final int THUMBSIZE = 96;
    DAOdb daodb;
    ArrayList<Product> ListProducts;
    Context context;

    private static class ViewHolder {
        ImageView imgIcon;
        TextView name;
        TextView price;

    }


    public ProdutoAdapter(Context context, ArrayList<Product> Products) {
        super(context, 0, Products);
        this.context=context;
        this.ListProducts=Products;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        // view lookup cache stored in tag
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_product_image, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.item_img_infor);
            viewHolder.imgIcon = (ImageView) convertView.findViewById(R.id.item_img_icon);
            viewHolder.price= (TextView) convertView.findViewById(R.id.item_price);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Get the data item for this position
        Product  product = getItem(position);


        // set description text
        viewHolder.name.setText(product.getName());
        viewHolder.price.setText(""+product.getPrice());


        daodb= new DAOdb(getContext());

        Log.d("ProductID",""+product.getId());


        List<Image> ListImages= daodb.getImagesbyIdProduct(product.getId());




        //Por enquanto não utlizar todas as imagens ,   utiliza-se a ultima
        Image im = ListImages.get(0);

        Log.d("im.getPath()",""+im.getPath());
        // set image icon

        viewHolder.imgIcon.setImageBitmap(ThumbnailUtils
                .extractThumbnail(BitmapFactory.decodeFile(im.getPath()),
                        THUMBSIZE, THUMBSIZE));


        return convertView;
    }

}
