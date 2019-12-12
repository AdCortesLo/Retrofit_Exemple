package com.example.retrofit_exemple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Controlador service;

    Button bt_buscar;
    EditText et_id;
    EditText et_nom;
    EditText et_edat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_buscar = findViewById(R.id.button_buscar);
        et_id  = findViewById(R.id.editText_id);
        et_nom = findViewById(R.id.editText_nom);
        et_edat= findViewById(R.id.editText_edat);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://10.0.2.15:44391/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(Controlador.class);

        bt_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<Alumne> call = service.getAlumne(Integer.parseInt(et_id.getText().toString()));

                call.enqueue(new Callback<Alumne>() {
                    @Override
                    public void onResponse(Call<Alumne> call, Response<Alumne> response) {

                        //recuperem l'objecte (el comic)
                        Alumne comic = response.body();

                        try {

                            if (comic != null) {
                                et_nom.setText(comic.getNom());
                                et_edat.setText(comic.getEdat());
                            }
                        } catch (Exception e) {
                            Log.e("MainActivity", e.toString());
                        }

                    }

                    @Override
                    public void onFailure(Call<Alumne> call, Throwable t) {

                        Toast.makeText(MainActivity.this, "Error amb l'API", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });
    }
}
