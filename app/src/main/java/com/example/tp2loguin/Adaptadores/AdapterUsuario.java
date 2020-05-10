package com.example.tp2loguin.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp2loguin.R;
import com.example.tp2loguin.entidades.Usuario;

import java.util.ArrayList;

public class AdapterUsuario extends RecyclerView.Adapter<AdapterUsuario.ViewHolder> {
    ArrayList<Usuario> listDatos;
    final private ListClick mOnClickListener;

    public AdapterUsuario(ArrayList<Usuario> listDatos, ListClick listener) {
       this.listDatos = listDatos;
       mOnClickListener=listener;
    }

    public interface ListClick{
        void onListClick(String nombre, String apellido, int dni);
    }



    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_personas, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.asignarDatos(listDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView dato1, dato2, dato3;
        int dni;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dato1 = (TextView) itemView.findViewById(R.id.nombre_recicle);
            dato2 = (TextView) itemView.findViewById(R.id.apellido_recicle);
            dato3 = (TextView) itemView.findViewById(R.id.usuario_recicle);
            //dni=(Number) itemView.findViewById(R.id.usuario_recicle);
            itemView.setOnClickListener(this);
        }

        public void asignarDatos(Usuario usuario) {
            dato1.setText(usuario.getNombre());
            dato2.setText(usuario.getApellido());
            dni = usuario.getDni();
            dato3.setText(String.valueOf(dni));

        }

        @Override
        public void onClick(View v) {
            String nombre = dato1.getText().toString();
            String apellido = dato2.getText().toString();
            mOnClickListener.onListClick(nombre, apellido, dni);
        }
    }
}
