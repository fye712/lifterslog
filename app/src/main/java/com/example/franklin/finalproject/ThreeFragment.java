package com.example.franklin.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Franklin on 12/11/2015.
 */
public class ThreeFragment extends Fragment {

    private Button benchView;
    private Button ohpView;
    private Button squatView;
    private Button dlView;
    View view;

    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * sets onclicklisteners for the buttons to bring up new activities
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
        view = inflater.inflate(R.layout.fragment_three, container, false);
        //gets buttons from view
        benchView = (Button) view.findViewById(R.id.bench);
        ohpView = (Button) view.findViewById(R.id.ohp);
        squatView = (Button) view.findViewById(R.id.squat);
        dlView = (Button) view.findViewById(R.id.dead);

        //onClickListener to start new intent to bring up webview
        benchView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BenchView.class);
                startActivity(intent);
            }
        });

        //onClickListener to start new intent to bring up webview
        ohpView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OHPView.class);
                startActivity(intent);
            }
        });

        //onClickListener to start new intent to bring up webview
        dlView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DLView.class);
                startActivity(intent);
            }
        });

        //onClickListener to start new intent to bring up webview
        squatView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SquatView.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
