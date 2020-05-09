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
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_personas,null,false);
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
        TextView dato1, dato2;

       public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dato1=(TextView)itemView.findViewById(R.id.nombre_recicle);
            dato2=(TextView)itemView.findViewById(R.id.apellido_recicle);
        }

        public void asignarDatos(Usuario usuario) {
            dato1.setText(usuario.getNombre());
            dato2.setText(usuario.getApellido());
       }
    }


/*
    LayoutInflater inflater;
    ArrayList<Persona> model;

    //LISTENER
    private View.OnClickListener listener;

    public AdapterPersona(Context context, ArrayList<Persona> model){
        this.inflater=LayoutInflater.from(context);
        this.model=model;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.lista_personas, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nombre=model.get(position).getNombre();
        String fechanacimiento=model.get(position).getFechanacimiento();
        int imagen=model.get(position).getImagenid();
        holder.nombres.setText(nombre);
        holder.fechanacimiento.setText(fechanacimiento);
        holder.imagen.setImageResource(imagen);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombres, fechanacimiento;
        ImageView imagen;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombres=itemView.findViewById(R.id.nombre_persona);
            fechanacimiento=itemView.findViewById(R.id.fecha_nacimiento);
            imagen=itemView.findViewById(R.id.imagen_persona);
        }
    }*/
}
