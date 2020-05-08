package com.example.tp2loguin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp2loguin.Adaptadores.AdapterPersona;
import com.example.tp2loguin.Entidades.Persona;

import java.util.ArrayList;


public class Todosperfiles extends Fragment {
    AdapterPersona adapterPersona;
    RecyclerView recyclerViewPersonas;
    ArrayList<Persona> listaPersonas;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.todosperfiles,container,false);
        recyclerViewPersonas= view.findViewById(R.id.recyclerView);
        listaPersonas=new ArrayList<>();
        //cargar la Lista
        cargarLista();
        //mostrar data
        mostrarData();
        return view;
    }
    public void cargarLista(){
        listaPersonas.add(new Persona("Jonathan", "17-06-2020", R.drawable.dos_goku));
        listaPersonas.add(new Persona("Ema", "20-04-2020", R.drawable.uno_gohan));
    }
    public void mostrarData() {
        recyclerViewPersonas.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterPersona=new AdapterPersona(getContext(), listaPersonas);
        recyclerViewPersonas.setAdapter(adapterPersona);
    }
}
