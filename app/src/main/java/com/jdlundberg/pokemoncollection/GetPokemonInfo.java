package com.jdlundberg.pokemoncollection;

import android.annotation.TargetApi;
import android.os.Build;

import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONInputStream;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is used to grab all the data essential to our app from the pokeapi.co
 * website.  It will grab a list of pokemon as well as some specific data for each
 * one, which will then be passed to the application to be set in various views.
 * <p/>
 * Created on 3/14/2015 by Jonathan Lundberg
 * <p/>
 * org.quickconnectfamily.json package created by Lee Barney and used with his permission.
 */
public class GetPokemonInfo {

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public ArrayList<HashMap> GetPokemonInfo() {

        String hostName = "52.24.151.204";
        Integer portNumber = 4444;
        ArrayList<HashMap> pokemonData = new ArrayList<>();
        JSONInputStream inFromServer;

        try (Socket toServer = new Socket(hostName, portNumber);) {

            inFromServer = new JSONInputStream(toServer.getInputStream());
            pokemonData = (ArrayList) inFromServer.readObject();

        } catch (IOException | JSONException e) {

            e.printStackTrace();

        }

        return pokemonData;

    }

}
