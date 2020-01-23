package com.example.retrofit_exemple;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Controlador {

    @GET("contacte/{id}")
    Call<Contacte> getContacte(@Path("id") int id);

    @GET("contactes")
    Call<List<Contacte>> getAllContactes();

    @DELETE("contacteTot/{id}")
    Call<Void> deleteContacte(@Path("id") int id);

    @POST("contacte")
    Call<Contacte> afegirContacte(@Body Contacte contacte);


}
