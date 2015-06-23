package com.jdlundberg.pokemoncollection;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by Architect on 3/18/2015.
 */
public interface Handler {

    public void handleIt(Activity activity, Integer position, ArrayList<String>... lists);

}
