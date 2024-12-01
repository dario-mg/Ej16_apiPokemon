package com.example.apipokemon;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private PokemonViewModel viewModel;
    private PokemonAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // Dos columnas
        adapter = new PokemonAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // Configurar ViewModel
        viewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

        // Observar la lista de Pokémon
        viewModel.getPokemonList().observe(this, new Observer<ArrayList<Pokemon>>() {
            @Override
            public void onChanged(ArrayList<Pokemon> pokemons) {
                if (pokemons != null) {
                    adapter = new PokemonAdapter(pokemons);
                    recyclerView.setAdapter(adapter);
                }
            }
        });

        // Cargar la lista de Pokémon (offset 0)
        viewModel.loadPokemonList(0);
    }
}
