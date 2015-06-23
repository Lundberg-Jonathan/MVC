package com.jdlundberg.pokemoncollection;

import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Architect on 3/18/2015.
 */
public class Controller {

    private HashMap<String, Handler> controlHash = new HashMap<>();

    public Controller() {

        AddPokemonHandler addPokemonHandler = new AddPokemonHandler();
        controlHash.put("Add Pokemon", addPokemonHandler);

        RemovePokemonHandler removePokemonHandler = new RemovePokemonHandler();
        controlHash.put("Remove Pokemon", removePokemonHandler);

    }

    public void handle(String command, Activity activity, Integer position, ArrayList<String>... lists) {

        Handler task = controlHash.get(command);

        task.handleIt(activity, position, lists);

    }

}
