package com.example.tp2loguin.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp2loguin.R;
import com.example.tp2loguin.entidades.Usuario;

import java.util.ArrayList;

public class AdapterUsuario extends RecyclerView.Adapter<AdapterUsuario.ViewHolder> {
    ArrayList<Usuario> listDatos;

    public AdapterUsuario(ArrayList<Usuario> listDatos) {
        this.listDatos = listDatos;
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dato1, dato2, dato3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dato1 = (TextView) itemView.findViewById(R.id.nombre_recicle);
            dato2 = (TextView) itemView.findViewById(R.id.apellido_recicle);
            //dato3 = (TextView) itemView.findViewById(R.id.usuario_recicle);
        }

        public void asignarDatos(Usuario usuario) {
           dato1.setText(usuario.getNombre());
            dato2.setText(usuario.getApellido());
           // dato3.setText(usuario.getDni());
        }
    }
}
