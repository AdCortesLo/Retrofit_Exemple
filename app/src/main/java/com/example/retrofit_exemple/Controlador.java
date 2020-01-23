package com.example.retrofit_exemple;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Controlador {

    @GET("api/contacteTot/{id}")
    Call<Contacte> getContacte(@Path("id") int id);


}
