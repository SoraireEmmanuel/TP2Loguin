package com.example.tp2loguin;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.Spinner;


import java.io.File;
public class Registro extends Activity {

    ImageView imagen;
    Spinner spiner;
    String path;

    private final String CARPETA_RAIZ="misImagenes/";
    private final String RUTA_IMAGEN=CARPETA_RAIZ+"misFotos";

    final int COD_SELECCIONADA=10;
    final int COD_FOTO=20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        imagen=(ImageView)findViewById(R.id.imagenId);
        spiner=(Spinner)findViewById(R.id.spinner);
        String [] opciones = {"CEO","Director","Administrador","Jefe o Supervisor", "Empleado"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, opciones);
        spiner.setPrompt("Seleccione su Rol");
        spiner.setAdapter(adapter);
       }


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
                    break;
            }
        }
    }
}