package com.example.retrofit_exemple;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Controlador {

    @GET("alumne/{id}/")
    Call<Alumne> getAlumne(@Path("id") int id);

}
