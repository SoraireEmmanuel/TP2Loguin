package com.example.tp2loguin;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tp2loguin.utilidades.Utilidades;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Registro extends AppCompatActivity {

    ImageView imagen;
    Spinner spiner;
    String path;
    EditText campoDNI;
    EditText campoNombre;
    EditText campoApellido;
    EditText campoContrasenia;
    EditText campoEmail;
    String spinerText;
    Bitmap imgByte;

    private final String CARPETA_RAIZ="misImagenes/";
    private final String RUTA_IMAGEN=CARPETA_RAIZ+"misFotos";

    final int COD_SELECCIONADA=10;
    final int COD_FOTO=20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        //para el boton atras - vuelve a menu de inicio
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //REGISTRO LA IMAGEN DEL FORMULARIO EN LA CONSTANTE imagen
        imagen=(ImageView)findViewById(R.id.imagenId);
    //   imagen.buildDrawingCache();
   //     imgByte = imagen.getDrawingCache();

        //REGISTRO EL SPINNER EN LA VARIABLE spiner PARA MANIPULAR SI VISUALIZACION
        spiner=(Spinner)findViewById(R.id.spinner);
        String [] opciones = {"CEO","Director","Administrador","Jefe o Supervisor", "Empleado"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, opciones);
        spiner.setPrompt("Seleccione su Rol");
        spiner.setAdapter(adapter);


        //REGISTRO EL RESTO DE LOS CAMPOS PARA SU CARGA EN LA BASE DE DATOS
        campoDNI=(EditText)findViewById(R.id.usuarioDni);
        campoNombre = (EditText)findViewById(R.id.nombre);
        campoApellido = (EditText)findViewById(R.id.apellido);
        campoContrasenia =(EditText)findViewById(R.id.contrasenia);
        campoEmail = (EditText)findViewById(R.id.email);

       }

    //metoos para la insercion en base de dato de un nuevo usuario

    public void onClick(View view){
        resgistrarUsuario();
    }

    private void resgistrarUsuario() {
        // transforma el bit map en arreglo de byte
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        imgByte.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] img = bos.toByteArray();

        ConexionSQLiteHelper conex = new ConexionSQLiteHelper(this, "bd_usuario", null, 1);
        SQLiteDatabase db=conex.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_DNI,campoDNI.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_APELLIDO,campoApellido.getText().toString());
        values.put(Utilidades.CAMPO_ROL, (String) spiner.getSelectedItem());
        values.put(Utilidades.CAMPO_CONTRASENIA,campoContrasenia.getText().toString());
        values.put(Utilidades.CAMPO_EMAIL,campoEmail.getText().toString());
        values.put(Utilidades.CAMPO_FOTO, img);
      // values.put(Utilidades.CAMPO_FOTO,imagen. recuperar el uri de la imagen);

        Long idResultante=db.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_DNI,values);

        Toast.makeText(getApplicationContext(),"DNI registro:"+idResultante,Toast.LENGTH_SHORT).show();
    }


    //metodos para la carga de foto para versiones de android 5.1 Lollipop o menores
    public void cargarImagen(View view) {
        cargar();
    }

    private void cargar(){
        final CharSequence[] opciones={"Tomar Foto","Cargar Imagen","Cancelar"};
        final AlertDialog.Builder alertOpcion=new AlertDialog.Builder(Registro.this);
        alertOpcion.setTitle("Seleccione una Opcion");
        alertOpcion.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(opciones[i].equals("Tomar Foto")){
                  tomarFoto();
                }
                else{
                    if(opciones[i].equals("Cargar Imagen")){
                        Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicacion"),COD_SELECCIONADA);
                    }
                    else{
                        dialog.dismiss();
                    }
                }
            }
        });
        alertOpcion.show();
    }

    private void tomarFoto() {
        String nombreImagen="";
        File fileImagen=new File(Environment.getExternalStorageDirectory(),RUTA_IMAGEN);
        boolean isCreada= fileImagen.exists();
        if(isCreada==false){
            isCreada=fileImagen.mkdirs();
        }
        if(isCreada==true) {
            nombreImagen = (System.currentTimeMillis() / 1000) + ".jpg";
        }
        path=Environment.getExternalStorageDirectory()+File.separator+RUTA_IMAGEN+File.separator+nombreImagen;
        File imag=new File(path);
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imag));
        startActivityForResult(intent,COD_FOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case COD_SELECCIONADA:
                    Uri miPath=data.getData();
                    imagen.setImageURI(miPath);
                 //necesito transformar mi foto en un BitMap
                    try {
                        imgByte = MediaStore.Images.Media.getBitmap(this.getContentResolver(), miPath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                case COD_FOTO:
                    MediaScannerConnection.scanFile(this, new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("Ruta de almacenamiento","Path: "+path);
                        }
                    });
                    Bitmap bitmap= BitmapFactory.decodeFile(path);
                    imagen.setImageBitmap(bitmap);
                    imgByte= bitmap;
                    break;
            }
        }
    }

   /* protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case COD_SELECCIONADA:
                    Uri miPath = data.getData();
                    imagen.setImageURI(miPath);
                    try {
                        imgByte = MediaStore.Images.Media.getBitmap(this.getContentResolver(), miPath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                case COD_FOTO:
                    MediaScannerConnection.scanFile(this, new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("Ruta de almacenamiento", "Path: " + path);
                        }
                    });
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    //imgByte = BitmapFactory.decodeFile(path);
                    imagen.setImageBitmap(bitmap);


                    break;
            }
        }
    }*/
}