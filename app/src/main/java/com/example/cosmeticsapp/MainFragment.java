package com.example.cosmeticsapp;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import androidx.annotation.RequiresApi;
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


public class MainFragment extends Fragment {
    public DataAdapter dataAdapter;
    public RecyclerView recyclerView;
    public List<Products> productsList = new ArrayList<>();
    private Retrofit retrofit;
    private APIService apiService;
    DataAdapter.ClickListener clickListener;

    public MainFragment() {

    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }
    public static MainFragment newInstance(String description, String name){
        MainFragment mainFragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString("description", description);
        args.putString("name", name);
        mainFragment.setArguments(args);
        return mainFragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrofit = new Retrofit.Builder().baseUrl("http://makeup-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build();
        apiService = retrofit.create(APIService.class);
        try {
            String description = getArguments().getString("desc", "");
            String name = getArguments().getString("nameF", "");
            this.getFilteredProducts(description, name);
        } catch (Exception e) {
            this.getProducts();        }
    }

    private void getProducts() {
        apiService.getProducts().enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if (response.body() != null) {
                    productsList.clear();
                    productsList.addAll(response.body());
                    dataAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.main_fragment, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Dior Cosmetics");
        setHasOptionsMenu(true);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        clickListener=new DataAdapter.ClickListener() {
            @Override
            public void click(int position, Products products) {
                getFragmentManager().beginTransaction().replace(R.id.frame,DetailFragment.newInstance(products)).addToBackStack(null).commitAllowingStateLoss();

            }
        };
        dataAdapter = new DataAdapter(productsList, clickListener);
        recyclerView.setAdapter(dataAdapter);
        return view;

    }

    private void getFilteredProducts(String description, String name) {
        apiService.getFilteredProducts(description, name).enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if (response.body() != null) {
                    productsList.clear();
                    productsList.addAll(response.body());
                    dataAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.top_bar, menu);
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                apiService.getSearchedProducts(query).enqueue(new Callback<List<Products>>() {
                    @Override
                    public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                        productsList.clear();
                        productsList.addAll(response.body());
                        dataAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<Products>> call, Throwable t) {
                        Log.e("failed search", t.getMessage());
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });
    }
//    private void getResponse(Response<List<Products>> response) {
////        if (!response.isSuccessful()) {
////            Log.e("get jobs, Code:", ""+response.code());
////            return;
////        }
//
//    }
}