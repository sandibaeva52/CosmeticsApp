package com.example.cosmeticsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
public List<Products> productsList;
public List<Favourites> favouritesList;
private ClickListener clickListener;
private FavouritesDao favouritesDao;
private FavouriteDB favouriteDB;
public  DataAdapter( List<Products> productsList,  ClickListener clickListener){
    this.productsList=productsList;
    this.clickListener=clickListener;
    favouriteDB=DatabaseBuilder.getInstance().getFavouriteDB();
    favouritesDao=favouriteDB.favouritesDao();
    favouritesList=favouritesDao.getFavouriteProducts();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent,false);
return new ViewHolder(view);
}

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Products products = productsList.get(position);
        holder.id = products.getId();
        holder.name.setText(products.getName());
        holder.price.setText(products.getPrice());
        holder.priceSign.setText(products.getPriceSign());
        holder.productType.setText(products.getProductType());
        Picasso.get().load(products.getImageLink()).placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_spa_black_24dp).resize(100, 100).into(holder.imageLink);
        boolean is_selected = false;
        for (Favourites fav : favouritesList) {
            if (products.getId().equals(fav.id)) {
                is_selected = true;
                holder.save.setImageResource(R.drawable.ic_bookmark_black_24dp);
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.click(position, products);
                }
            }
        });
        boolean is_saved = is_selected;
holder.save.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(is_saved){
            holder.save.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
            favouritesDao.delete(new Favourites(products.getId()));
        }
        else{
            holder.save.setImageResource(R.drawable.ic_bookmark_black_24dp);
            favouritesDao.insert(new Favourites(products.getId()));
        }
    }
});

    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price,priceSign,productType;
        ImageView imageLink,save;
        String id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
            priceSign=itemView.findViewById(R.id.price_sign);
            productType=itemView.findViewById(R.id.product_type);
            imageLink=itemView.findViewById(R.id.image_link);
            save=itemView.findViewById(R.id.save);
        }
    }


    public interface ClickListener {

        void click(int position, Products products);
    }
}
