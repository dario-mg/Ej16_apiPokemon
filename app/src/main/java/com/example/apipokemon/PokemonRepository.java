package com.example.apipokemon;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonRepository {
    private final PokeApiService pokeApiService;

    public PokemonRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PokeApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pokeApiService = retrofit.create(PokeApiService.class);
    }

    public void fetchPokemonList(MutableLiveData<ArrayList<Pokemon>> liveData, int offset) {
        Call<PokemonList> call = pokeApiService.getPokemonList(20, offset);
        call.enqueue(new Callback<PokemonList>() {
            @Override
            public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {
                if (response.body() != null) {
                    liveData.postValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<PokemonList> call, Throwable t) {
                liveData.postValue(null);
            }
        });
    }

    public void fetchPokemonById(MutableLiveData<Pokemon> liveData, String id) {
        Call<Pokemon> call = pokeApiService.getPokemonById(id);
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.body() != null) {
                    liveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                liveData.postValue(null);
            }
        });
    }
}
