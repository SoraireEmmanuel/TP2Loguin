package com.example.tp2loguin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void registrar(View v) {
        Intent registro = new Intent(this, Registro.class);
        startActivity(registro);
    }
    public void iniciar(View v){
        Intent iniciar = new Intent(this, PerfilActivity.class);
        startActivity(iniciar);
    }

}
