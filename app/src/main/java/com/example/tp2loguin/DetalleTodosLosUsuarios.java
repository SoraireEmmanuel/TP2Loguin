package com.example.tp2loguin;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tp2loguin.utilidades.Utilidades;

public class DetalleTodosLosUsuarios extends AppCompatActivity {
    TextView nombre, apellido, usuarioDNI, eMail, rol;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.detalletodoslosusuarios);

       //para el boton atras - vuelve a perfil
       ActionBar actionBar=getSupportActionBar();
       actionBar.setDisplayShowTitleEnabled(true);

       nombre = (TextView)findViewById(R.id.nombrePerfil);
       apellido = (TextView)findViewById(R.id.apellidoPerfil);
       eMail=(TextView)findViewById(R.id.emailPerfil);
       usuarioDNI=(TextView)findViewById(R.id.usuarioPerfil);
       rol =(TextView)findViewById(R.id.rolPerfil);

       Bundle bundle=getIntent().getExtras();
       String bundleNombre=bundle.getString("nombre");
       String bundlAapellido=bundle.getString("apellido");
       Integer buindlDNI=bundle.getInt("dni");


       final ConexionSQLiteHelper conex=new ConexionSQLiteHelper(getApplicationContext(), "bd_usuario", null,1);
       SQLiteDatabase db = conex.getReadableDatabase();
       String selection = Utilidades.CAMPO_DNI + " = ?";
       String[] selectionArg = {buindlDNI.toString()};
       try{
           Cursor c = db.query(Utilidades.TABLA_USUARIO, null,selection, selectionArg, null, null, null);
           c.moveToFirst();
           nombre.setText(c.getString(1));
           apellido.setText(c.getString(2));
           rol.setText(c.getString(3));
           eMail.setText(c.getString(5));
           usuarioDNI.setText(c.getString(0));
          //        Toast.makeText(getApplicationContext(),"El usuario  existe", Toast.LENGTH_SHORT).show();
       }catch (Exception e){
            //      Toast.makeText(getApplicationContext(),"El usuario no existe", Toast.LENGTH_SHORT).show();
       }
       rol.setOnClickListener(verRol);
    }
    private View.OnClickListener verRol = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent web = new Intent(DetalleTodosLosUsuarios.this, Internet.class);
            web.putExtra("rol", rol.getText().toString());
            startActivity(web);
        }
    };
}
