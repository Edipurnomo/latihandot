package com.example.splashlogin.API;

import com.example.splashlogin.model.ApiResponse;
import com.example.splashlogin.model.Book;
import com.example.splashlogin.model.BookResult;
import com.example.splashlogin.model.LoginResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookApiService {
//@Headers({
//        "Accept-Encoding: gzip. deflate",
//        "content-Encoding: gzip",
//})
    @GET("api/buku")
    Call<List<Book>> getAllBuku(@Header("Authorization") String token);

    @POST("api/buku")
    Call<ApiResponse> insertNewBook(@Header("Authorization") String token, @Body Book body);

    @GET("api/buku/byID/{id}")
    Call<BookResult> getBookById(@Header("Authorization") String token, @Path("id") int id);

    @POST("api/buku")
    Call<LoginResult> deleteBook(@Header("Authorization") String token, @Query("id") int id);

}

