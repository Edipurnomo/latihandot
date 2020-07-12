package com.example.splashlogin.API;

import com.example.splashlogin.model.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface BookApiService {

    @GET("api/buku")
    Call<List<Book>> getAllBuku(@Header("Authorization") String token);
}
