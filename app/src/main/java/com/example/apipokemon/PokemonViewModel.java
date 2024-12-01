package com.example.apipokemon;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import java.util.ArrayList;

public class PokemonViewModel extends ViewModel {
    private final PokemonRepository repository;
    private final MutableLiveData<ArrayList<Pokemon>> pokemonList = new MutableLiveData<>();
    private final MutableLiveData<Pokemon> selectedPokemon = new MutableLiveData<>();

    public PokemonViewModel() {
        repository = new PokemonRepository();
    }

    public MutableLiveData<ArrayList<Pokemon>> getPokemonList() {
        return pokemonList;
    }

    public MutableLiveData<Pokemon> getSelectedPokemon() {
        return selectedPokemon;
    }

    public void loadPokemonList(int offset) {
        repository.fetchPokemonList(pokemonList, offset);
    }

    public void loadPokemonById(String id) {
        repository.fetchPokemonById(selectedPokemon, id);
    }
}
