package com.example.finalproject22;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
 @GET("characters")
 Call<List<ImagesResponce>>getAllImages();
}
