package com.example.tp2loguin;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tp2loguin.Adaptadores.AdapterUsuario;
import com.example.tp2loguin.entidades.Usuario;
import com.example.tp2loguin.utilidades.Utilidades;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.ArrayList;

public class Perfil extends AppCompatActivity implements AdapterUsuario.ListClick{
    TextView nombre, apellido, usuarioDNI, eMail, rol;
    ImageView imagen;
    ArrayList<Usuario> listDatos;
    TextView nombreTdoso, apellidoTodos;
    Integer dniTodos;
    RecyclerView recycler;
    byte[] img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);

        //boton atras - vuelve a mainactivity, es decir a inicio
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        /*logica del tab*/
        Resources res = getResources();
        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();
        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Mis Datos",
                res.getDrawable(android.R.drawable.ic_btn_speak_now));
        tabs.addTab(spec);
        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Todos los Usuarios",
                res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);
        tabs.setCurrentTab(0);

        /*logica del perfil del usuario*/
        Bundle bundle=getIntent().getExtras();
        String usuario=bundle.getString("usuario");

        nombre = (TextView)findViewById(R.id.nombrePerfil);
        apellido = (TextView)findViewById(R.id.apellidoPerfil);
        eMail=(TextView)findViewById(R.id.emailPerfil);
        usuarioDNI=(TextView)findViewById(R.id.usuarioPerfil);
        rol =(TextView)findViewById(R.id.rolPerfil);
        imagen=(ImageView)findViewById(R.id.imgPerfil);
       cargarUsuario(usuario);

        /*logica de todos los usuarios*/
      recycler=findViewById(R.id.recyclerView);
      recycler.setLayoutManager(new LinearLayoutManager(this));
      cargarTodos();
      AdapterUsuario adapter=new AdapterUsuario(listDatos, this);
      recycler.setAdapter(adapter);

      /*logica de Imagen del Rol*/

      /*  rolImg = (ImageView)findViewById(R.id.imgRol);
        imgRol();*/
    rol.setOnClickListener(verRol);
    }
private View.OnClickListener verRol = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent web = new Intent(Perfil.this, Internet.class);
        web.putExtra("rol", rol.getText().toString());
        startActivity(web);
    }
};



    /*recupera todos los registros de la tabla usuario*/
    private void cargarTodos(){
        listDatos=new ArrayList<Usuario>();
        final ConexionSQLiteHelper conex=new ConexionSQLiteHelper(getApplicationContext(), "bd_usuario", null,1);
        SQLiteDatabase db = conex.getReadableDatabase();
        String [] proyeccion = {Utilidades.CAMPO_DNI,Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_APELLIDO,Utilidades.CAMPO_FOTO};
        String selection = Utilidades.CAMPO_DNI + " = ?";
        try{
            Cursor c = db.query(Utilidades.TABLA_USUARIO, proyeccion,
                    null , null, null, null, null);
            c.moveToFirst();
            listDatos.add(new Usuario( c.getInt(0),c.getString(1), c.getString(2),c.getBlob(3)) );
          while(c.moveToNext()) {
                listDatos.add(new Usuario(c.getInt(0), c.getString(1), c.getString(2),c.getBlob(3)));
            }
//            Toast.makeText(getApplicationContext(),"El usuario  existe", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            //          Toast.makeText(getApplicationContext(),"El usuario no existe", Toast.LENGTH_SHORT).show();
        }
    }
/*consulta el usuario a la base de datos y lo carga*/
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
            apellido.setText(c.getString(2));
            rol.setText(c.getString(3));
            eMail.setText(c.getString(5));
            usuarioDNI.setText(c.getString(0));
            img = c.getBlob(6);

            ByteArrayInputStream imageStream = new ByteArrayInputStream(img);
            Bitmap image = BitmapFactory.decodeStream(imageStream);
            imagen.setImageBitmap(image);

  //        Toast.makeText(getApplicationContext(),"El usuario  existe", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
     //       Toast.makeText(getApplicationContext(),"El usuario no existe", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onListClick(String nombre, String apellido, int dni) {
        Toast.makeText(getApplicationContext(),"Nombre y Apellido: "+nombre + " " + apellido , Toast.LENGTH_SHORT).show();
        Intent ver = new Intent(this, DetalleTodosLosUsuarios.class);
        ver.putExtra("nombre", nombre);
        ver.putExtra("apellido", apellido);
        ver.putExtra("dni", dni);

        startActivity(ver);
    }
}
