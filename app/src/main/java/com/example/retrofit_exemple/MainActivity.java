package com.example.retrofit_exemple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Controlador service;

    EditText etNouCognom;
    EditText etNouNom;
    Button btAfegir;
    Button bt_buscar;
    EditText et_id;
    EditText et_nom;
    EditText et_edat;
    ListView listView;

    ArrayList<Contacte> listaContactes = new ArrayList<>();
    ArrayAdapter<Contacte> adapterContactes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNouCognom = findViewById(R.id.etNouCognom);
        etNouNom = findViewById(R.id.etNouNom);
        btAfegir = findViewById(R.id.btAfegir);
        bt_buscar = findViewById(R.id.button_buscar);
        et_id  = findViewById(R.id.editText_id);
        listView = findViewById(R.id.listView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:44300/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(Controlador.class);

        adapterContactes = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaContactes);
        selectAllContactes();
        listView.setAdapter(adapterContactes);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Contacte contacte = (Contacte)listView.getItemAtPosition(position);
                Log.e("eeeeeeee",contacte.getContacteId() + "");
                Call<Void> call = service.deleteContacte(contacte.getContacteId());
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(MainActivity.this, "Esborrat satisfactòriament.", Toast.LENGTH_SHORT).show();
                        selectAllContactes();
                        Log.e("eeeeeeee",response.message());
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Problema al esborrar.", Toast.LENGTH_SHORT).show();
                        Log.e("eeeeeeee",t.getMessage());
                    }
                });
            }
        });

        bt_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<Contacte> call = service.getContacte(Integer.parseInt(et_id.getText().toString()));

                call.enqueue(new Callback<Contacte>() {
                    @Override
                    public void onResponse(Call<Contacte> call, Response<Contacte> response) {
                        //recuperem l'objecte (el contacte)
                        Contacte contacte = response.body();

                        try {
                            if (contacte != null) {
                                listaContactes.clear();
                                adapterContactes.clear();
                                listaContactes.add(contacte);
                                adapterContactes.notifyDataSetChanged();
                            }
                        } catch (Exception e) {
                            Log.e("eeeeeeee", e.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<Contacte> call, Throwable t) {

                        Log.i("eeeeeeee", t.getMessage());
                        Toast.makeText(MainActivity.this, "Error amb l'API", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

    public void afegirNouContacte(View view) {

        Contacte contacte = new Contacte();
        contacte.setNom(etNouNom.getText().toString());
        contacte.setCognoms(etNouCognom.getText().toString());
        Log.i("eeeeeeee", contacte.getNom());
        Log.i("eeeeeeee", contacte.getCognoms());
        Call<Contacte> call = service.afegirContacte(contacte);
        call.enqueue(new Callback<Contacte>() {
            @Override
            public void onResponse(Call<Contacte> call, Response<Contacte> response) {
                Toast.makeText(MainActivity.this, "Afegit nou contacte.", Toast.LENGTH_SHORT).show();
                Log.i("eeeeeeee", response.message());
                Log.i("eeeeeeee", "1");
                selectAllContactes();
            }

            @Override
            public void onFailure(Call<Contacte> call, Throwable t) {

            }
        });
    }

    public void selectAllContactes() {

        Call<List<Contacte>> call = service.getAllContactes();
        call.enqueue(new Callback<List<Contacte>>() {
            @Override
            public void onResponse(Call<List<Contacte>> call, Response<List<Contacte>> response) {

                listaContactes.clear();
                adapterContactes.clear();
                listaContactes.addAll(response.body());
                adapterContactes.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Contacte>> call, Throwable t) {

            }
        });
    }
}
