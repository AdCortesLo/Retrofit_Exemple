package com.example.retrofit_exemple;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Controlador {

    @GET("contacte/{id}")
    Call<Contacte> getContacte(@Path("id") int id);

    @GET("contactes")
    Call<List<Contacte>> getAllContactes();

    @DELETE("contacte/{id}")
    Call<ResponseBody> deleteContacte(@Path("id") int id);
}
