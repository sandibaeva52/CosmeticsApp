package com.example.cosmeticsapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

public class DetailFragment extends Fragment {

    public Integer id;
   private ImageView imageLink;
    private TextView name;
    private TextView brand;
    private TextView description;
    private TextView category;
    private TextView createdAt;
    private TextView productType;
    private TextView price;
    private TextView priceSign;
    private LinearLayout thirdCard;
    public static DetailFragment newInstance(Products products){
        DetailFragment fragment=new DetailFragment();
        Bundle bundle=new Bundle();
        bundle.putString("id",products.getId());
        bundle.putString("imageLink",products.getImageLink());
        bundle.putString("brand",products.getBrand());
        bundle.putString("description",products.getDescription());
        bundle.putString("category",products.getCategory());
        bundle.putString("createdAt",products.getCreatedAt());
        bundle.putString("productType",products.getProductType());
        bundle.putString("price",products.getPrice());
        bundle.putString("priceSign",products.getPriceSign());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
final View view =inflater.inflate(R.layout.detailed_fragment,container,false);
return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name=view.findViewById(R.id.name);
        brand=view.findViewById(R.id.brand);
        imageLink=view.findViewById(R.id.image_link);
        description=view.findViewById(R.id.desc);
        category=view.findViewById(R.id.category);
        createdAt=view.findViewById(R.id.createdAt);
        productType=view.findViewById(R.id.product_type);
        price=view.findViewById(R.id.price);
        priceSign=view.findViewById(R.id.price_sign);
        thirdCard = view.findViewById(R.id.third_card);
        Picasso.get().load(getArguments().getString("imageLink")).placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_dashboard_black_24dp).resize(300,150).into(imageLink);
        name.setText(getArguments().getString("name"));
        brand.setText(getArguments().getString("brand"));
        description.setText(getArguments().getString("description"));
        category.setText(getArguments().getString("category"));
        createdAt.setText(getArguments().getString("createdAt"));
        productType.setText(getArguments().getString("productType"));
        price.setText(getArguments().getString("price"));
        priceSign.setText(getArguments().getString("priceSign"));

    }
}
