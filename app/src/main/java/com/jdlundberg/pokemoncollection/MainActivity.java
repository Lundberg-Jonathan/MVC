package com.jdlundberg.pokemoncollection;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends Activity {

    Controller controller = new Controller();
    ListView pokemonGlobalListView;
    ListView myPokemonListView;
    ArrayAdapter<String> pokemonGlobalListAdapter;
    ArrayAdapter<String> myPokemonListAdapter;
    Context context;
    GetPokemonInfo getInfo = new GetPokemonInfo();
    ArrayList<HashMap> pokemonData = new ArrayList<>();
    ArrayList<String> pokemonGlobalList = new ArrayList<>();
    ArrayList<String> myPokemonList = new ArrayList<>();
    HashMap pokemonHash = new HashMap();
    String command;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        activity = this;

        pokemonGlobalListView = (ListView) findViewById(R.id.pokemonGlobalListView);
        myPokemonListView = (ListView) findViewById(R.id.myPokemonListView);

        pokemonGlobalListAdapter = new ArrayAdapter<>(this, R.layout.pokemon_list_item, pokemonGlobalList);
        pokemonGlobalListView.setAdapter(pokemonGlobalListAdapter);
        myPokemonListAdapter = new ArrayAdapter<>(this, R.layout.small_pokemon_list_item, myPokemonList);
        myPokemonListView.setAdapter(myPokemonListAdapter);

        PokemonListLoader pokemonListLoader = new PokemonListLoader();
        pokemonListLoader.execute();

        pokemonGlobalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final Integer globalListPosition = position;

                final Button addButton = (Button) findViewById(R.id.addButton);

                addButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {

                        command = addButton.getText().toString();
                        controller.handle(command, activity, globalListPosition, pokemonGlobalList, myPokemonList);
                        pokemonGlobalListAdapter.notifyDataSetChanged();

                        Log.w("Add", "Number " + globalListPosition + " was added");
                        addButton.setOnClickListener(null);
                    }

                });



            }
        });

        myPokemonListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final Integer myListPosition = position;

                final Button removeButton = (Button) findViewById(R.id.removeButton);

                removeButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {

                        command = removeButton.getText().toString();
                        controller.handle(command, activity, myListPosition, myPokemonList);

                        //myPokemonList.remove(myPokemonList.get(myListPosition));

                        myPokemonListAdapter.notifyDataSetChanged();
                        Log.w("Remove", "Number " + myListPosition + " was removed");
                        removeButton.setOnClickListener(null);
                    }

                });



            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public class PokemonListLoader extends AsyncTask<Void, Void, Void> {

        ProgressDialog globalListDialog;

        @Override
        protected void onPreExecute() {

            globalListDialog = new ProgressDialog(context);
            globalListDialog.setTitle("Loading Pokemon List");
            globalListDialog.setMessage("Please wait");
            globalListDialog.show();

            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            pokemonData = getInfo.GetPokemonInfo();
            String pokemonNumber;
            String pokemonName;

            for (int i = 0; i < pokemonData.size(); i++) {

                pokemonHash = pokemonData.get(i);
                pokemonNumber = pokemonHash.get("Number").toString();
                pokemonName = pokemonHash.get("Name").toString();

                pokemonGlobalList.add(pokemonNumber + " " + pokemonName);

            }

            return null;

        }

        @Override
        protected void onPostExecute(Void result) {

            globalListDialog.dismiss();

            pokemonGlobalListAdapter.notifyDataSetChanged();

            super.onPostExecute(result);

        }

    }
}
