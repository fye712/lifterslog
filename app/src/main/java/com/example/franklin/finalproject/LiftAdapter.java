package com.example.franklin.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A custom adapter to display the listFragment
 * Created by Franklin on 12/13/2015.
 */
public class LiftAdapter extends ArrayAdapter<Lift> {
    public LiftAdapter(Context context, ArrayList<Lift> lifts) {
        super(context, 0, lifts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Lift lift = getItem(position);

        //gets the custom list item view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lift_view, parent, false);
        }

        //gets the textviews from the custom layout
        TextView exer = (TextView) convertView.findViewById(R.id.exer);
        TextView whey = (TextView) convertView.findViewById(R.id.whey);
        TextView reap = (TextView) convertView.findViewById(R.id.reap);
        TextView theDay = (TextView) convertView.findViewById(R.id.theDay);

        //sets the text based on the database
        exer.setText(lift._name);
        whey.setText(lift._weight + " lbs");
        reap.setText(lift._reps + " reps");
        theDay.setText(lift._date);

        return convertView;
    }
}
