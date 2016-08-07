package com.example.franklin.finalproject;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

/**
 * listFragment to display the listview of the lifts in the databse
 * Created by Franklin on 12/13/2015.
 */
public class ShowFragment extends ListFragment {

    private SQLiteDatabase database;
    private DatabaseHandler dbHelper;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dbHelper = new DatabaseHandler(getActivity());
        database = dbHelper.getWritableDatabase();

        //longclick to delete list item
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dbHelper.deleteLift((Lift) parent.getItemAtPosition(position));
                showAll();
                return true;
            }
        });
        showAll();
    }

    public void showAll() {
        //sets the adapter to the list using the database values
        ArrayList<Lift> values = dbHelper.getAllLifts();
        LiftAdapter adapter = new LiftAdapter(getActivity(), values);
        setListAdapter(adapter);
    }
}
