package com.jdlundberg.pokemoncollection;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by Architect on 3/18/2015.
 */
public class AddPokemonHandler implements Handler {

    MainActivity activity;

    @Override
    public void handleIt(Activity activity, Integer position, ArrayList<String>... lists) {

        this.activity = (MainActivity) activity;
        Integer globalListPosition = position;
        ArrayList<String> globalPokemonList = lists[0];
        ArrayList<String> myPokemonList = lists[1];

        myPokemonList.add(globalPokemonList.get(globalListPosition));



    }

}
