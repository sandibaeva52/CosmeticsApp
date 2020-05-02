package com.example.cosmeticsapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @GET("api/v1/products.json?brand=dior&product_type=lipstick")
    Call<List<Products>> getProducts();
    @GET("api/v1/products.json?brand=dior&product_type=lipstick")
    Call<List<Products>> getFilteredProducts(@Query("description") String description, @Query("name") String name);
    @GET("api/v1/products.json?brand=dior&product_type=lipstick")
    Call<List<Products>> getSearchedProducts(@Query("search") String search);
    @GET("api/v1/products/{id}.json?brand=dior&product_type=lipstick")
    Call<Products> getProductById(@Path("id") String id);
}
