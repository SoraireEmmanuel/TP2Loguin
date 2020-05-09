package com.example.tp2loguin;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.tp2loguin.utilidades.Utilidades;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;
import android.widget.Toast;

import com.example.tp2loguin.ui.main.SectionsPagerAdapter;

public class PerfilActivity extends AppCompatActivity {
    TextView nombre, apellido, usuarioDNI, eMail, rol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_activity);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


        Bundle bundle=getIntent().getExtras();
        String usuario=bundle.getString("usuario");

        nombre = (TextView)findViewById(R.id.nombrePerfil);
        //nombre.setText(usuario);
      //  apellido = (TextView)findViewById(R.id.apellidoMP);
      //  eMail=(TextView)findViewById(R.id.mailMP);
      //  usuarioDNI=(TextView)findViewById(R.id.usuarioMP);
      //  rol =(TextView)findViewById(R.id.rolMP);

        cargarUsuario(usuario);

    }

    private void cargarUsuario(String usuario) {
       final ConexionSQLiteHelper conex=new ConexionSQLiteHelper(getApplicationContext(), "bd_usuario", null,1);
        SQLiteDatabase db = conex.getReadableDatabase();
        String selection = Utilidades.CAMPO_DNI + " = ?";
        String[] selectionArg = {usuario};

        try{
            Cursor c = db.query(Utilidades.TABLA_USUARIO, null,
                    selection , selectionArg, null, null, null);
            c.moveToFirst();
            nombre.setText(c.getString(1));
          //  apellido.setText(c.getString(2));
          //  rol.setText(c.getString(3));
          //  eMail.setText(c.getString(5));
         //   usuarioDNI.setText(c.getString(0));



            Toast.makeText(getApplicationContext(),"El usuario  existe", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El usuario no existe", Toast.LENGTH_SHORT).show();
        }
    }
    }


