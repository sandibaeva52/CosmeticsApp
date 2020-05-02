package com.example.cosmeticsapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavouritesFragment extends Fragment{
    DataAdapter.ClickListener clickListener;
    DataAdapter dataAdapter;
    FavouriteDB favouriteDB;
    FavouritesDao favouritesDao;
    List<Favourites> favouritesList;
    List<Products> productsList=new ArrayList<>();

    public static FavouritesFragment newInstance(){
        FavouritesFragment fragment=new FavouritesFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favouriteDB=DatabaseBuilder.getInstance().getFavouriteDB();
        favouritesDao=favouriteDB.favouritesDao();
        favouritesList=favouritesDao.getFavouriteProducts();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.saved_fragment, container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Favourites");
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        clickListener=new DataAdapter.ClickListener() {
            @Override
            public void click(int position, Products products) {
                getFragmentManager().beginTransaction().replace(R.id.frame, DetailFragment.newInstance(products)).addToBackStack(null).commitAllowingStateLoss();

            }
        };
        dataAdapter=new DataAdapter(productsList,clickListener);
        recyclerView.setAdapter(dataAdapter);
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://makeup-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build();
        APIService apiService=retrofit.create(APIService.class);
        for(Favourites fav:favouritesList){
apiService.getProductById(fav.id).enqueue(new Callback<Products>() {
    @Override
    public void onResponse(Call<Products> call, Response<Products> response) {
        Products products=response.body();
        productsList.add(products);
        dataAdapter.notifyItemInserted(productsList.size());
    }

    @Override
    public void onFailure(Call<Products> call, Throwable t) {
t.printStackTrace();
    }
});
        }
        return view;
    }




}
