package com.example.tp2loguin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp2loguin.utilidades.Utilidades;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String contrasenia, usuario;
    EditText cContrasenia, cUsuario, bdContrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cContrasenia=(EditText)findViewById(R.id.campoContrasenia);
        cUsuario=(EditText)findViewById(R.id.campoUsuario);
    }

    public void registrar(View v) {
        limpiar();
        Intent registro = new Intent(this, Registro.class);
        startActivity(registro);
    }
    /*intencion hacie el starActivity*/
    private void perfil(){

        Intent iniciar = new Intent(this, Perfil.class);
        iniciar.putExtra("usuario", cUsuario.getText().toString());
        limpiar();
       startActivity(iniciar);
    }
    /*valida formulario*/
    public void iniciar(View v){
        Boolean validador;
        validador = consultarCampos();
        if (validador.booleanValue()){
            //Toast.makeText(getApplicationContext(), "Salio verdadero", Toast.LENGTH_SHORT).show();
            perfil();
        }
        else{
            //Toast.makeText(getApplicationContext(), "Salio falso", Toast.LENGTH_SHORT).show();
        }
    }
    /*trabajar sobre la validacion del formulario*/
    private boolean consultarCampos() {
        usuario = cUsuario.getText().toString();
        contrasenia = cContrasenia.getText().toString();
        if (usuario.equals("")) {
            Toast.makeText(getApplicationContext(), "Tiene que ingresar un Usuario", Toast.LENGTH_SHORT).show();
            cUsuario.requestFocus();
            return false;
        } else {
            if (contrasenia.equals("")) {
                Toast.makeText(getApplicationContext(), "Tiene que ingresar una Contrasenia", Toast.LENGTH_SHORT).show();
                cContrasenia.requestFocus();
                return false;
            } else {
                return consultar();
            }
        }
    }
    private boolean consultar() {
      final ConexionSQLiteHelper conex=new ConexionSQLiteHelper(getApplicationContext(), "bd_usuario", null,1);
      SQLiteDatabase db = conex.getReadableDatabase();

      String[] projection = {Utilidades.CAMPO_CONTRASENIA};
      String selection = Utilidades.CAMPO_DNI + " = ?";
      String[] selectionArg = {cUsuario.getText().toString()};

    try{
    Cursor c = db.query(Utilidades.TABLA_USUARIO, projection,
            selection , selectionArg, null, null, null);
        c.moveToFirst();

            if (contrasenia.equals(c.getString(0))) {
                Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                Toast.makeText(getApplicationContext(), "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
            return false;
            }

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El usuario no existe", Toast.LENGTH_SHORT).show();
            limpiar();
            return false;
        }
    }

    private void limpiar() {
        cUsuario.setText("");
        cContrasenia.setText("");
    }

    //implementacion de salir de la aplicacion
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==event.KEYCODE_BACK){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea salir de RegApp?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.show();
        }
        return super.onKeyDown(keyCode, event);
    }
}
