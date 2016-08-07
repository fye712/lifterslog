package com.example.franklin.finalproject;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Franklin on 12/11/2015.
 */
public class FourFragment extends Fragment {

    View view;
    private boolean male = false;
    private boolean female = false;
    private RadioButton radioMale;
    private RadioButton radioFemale;
    private EditText ageEdit;
    private EditText weightEdit;
    private EditText timeEdit;
    private EditText heartRateEdit;
    private Button calculate;
    private TextView result;

    private double age;
    private double weight;
    private double time;
    private double heartRate;
    private double burned;

    public FourFragment() {
        // Required empty public constructor
    }

    /**
     * Checks which radio button is checked and sets male and female booleans accordingly for calculation
     *
     * @param view
     */
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radio_male:
                if (checked) {
                    male = true;
                    female = false;
                }
                break;

            case R.id.radio_female:
                if (checked) {
                    male = false;
                    female = true;
                }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * gets the view and gets all the views inside of the layout for calculation
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_four, container, false);
        radioMale = (RadioButton) view.findViewById(R.id.radio_male);
        radioFemale = (RadioButton) view.findViewById(R.id.radio_female);
        ageEdit = (EditText) view.findViewById(R.id.age);
        weightEdit = (EditText) view.findViewById(R.id.weight);
        timeEdit = (EditText) view.findViewById(R.id.time);
        heartRateEdit = (EditText) view.findViewById(R.id.heart_rate);
        calculate = (Button) view.findViewById(R.id.calculate);
        result = (TextView) view.findViewById(R.id.result);

        //sets onClickListener for onRadioButtonClicked
        radioMale.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onRadioButtonClicked(v);
            }
        });
        radioFemale.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onRadioButtonClicked(v);
            }
        });
        //sets onClickListener for calculate button
        calculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //checks to see if all fields are full to avoid null pointer exceptions
                if ((male == false && female == false) || ageEdit.getText().toString().matches("") || weightEdit.getText().toString().matches("") || timeEdit.getText().toString().matches("") || heartRateEdit.getText().toString().matches("")) {
                    Toast.makeText(getActivity(), "Please fill out all fields", Toast.LENGTH_SHORT).show();
                } else {
                    //calculation
                    //getting doubles from the input fields
                    age = Double.parseDouble(ageEdit.getText().toString());
                    weight = Double.parseDouble(weightEdit.getText().toString());
                    time = Double.parseDouble(timeEdit.getText().toString());
                    heartRate = Double.parseDouble(heartRateEdit.getText().toString());

                    //formulas from fitnowtraining.com/2012/01/formula-for-calories-burned/
                    if (male == true && female == false) {
                        //formula for male
                        burned = ((age * 0.2017) - (weight * .09036) + (heartRate * .6309) - 55.0969) * time / 4.184;
                        result.setText("You have burned " + String.format("%.2f", burned) + " kCal!");
                    } else if (male == false && female == true) {
                        //formula for female
                        burned = ((age * .074) - (weight * .05741) + (heartRate * .4472) - 20.4022) * time / 4.184;
                        result.setText("You have burned " + String.format("%.2f", burned) + " kCal!");
                    }
                }
            }
        });
        return view;
    }

}
