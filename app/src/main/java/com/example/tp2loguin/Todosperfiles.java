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

import com.example.tp2loguin.Entidades.Persona;

import java.util.ArrayList;


public class Todosperfiles extends Fragment {

    RecyclerView recyclerViewPersonas;
    ArrayList<Persona> listaPersonas;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.todosperfiles,container,false);
        recyclerViewPersonas= view.findViewById(R.id.recyclerView);
        listaPersonas=new ArrayList<>();

        return view;
    }

}
