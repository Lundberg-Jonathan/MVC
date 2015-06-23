package com.jdlundberg.pokemoncollection;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by Architect on 3/18/2015.
 */
public class RemovePokemonHandler implements Handler {

    MainActivity activity;

    @Override
    public void handleIt(Activity activity, Integer position, ArrayList<String>... lists) {

        this.activity = (MainActivity) activity;
        Integer myListPosition = position;
        ArrayList<String> myPokemonList = lists[0];

        myPokemonList.remove(myPokemonList.get(myListPosition));

    }

}
