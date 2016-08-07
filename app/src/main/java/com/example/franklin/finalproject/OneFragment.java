package com.example.franklin.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Franklin on 12/11/2015.
 */
public class OneFragment extends Fragment implements View.OnClickListener {

    private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
    private Intent intent;
    private Button capture;
    View view;

    /**
     * starts the video recording activity
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
    }

    /**
     * receives the video from the intent and saves it
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //video request code
        if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                // Video captured and saved
                Toast.makeText(getActivity(), "Saved to Camera Roll!"
                        , Toast.LENGTH_LONG).show();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // User cancelled the video capture
            } else {
                // Video capture failed, advise user
            }
        }
    }

    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /// create Intent to capture video
        intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        //makes the video high quality
        intent.putExtra(android.provider.MediaStore.EXTRA_VIDEO_QUALITY, 1);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_one, container, false);
        //sets onclicklistener to the button to launch the video intent
        capture = (Button) view.findViewById(R.id.capture);
        capture.setOnClickListener(this);
        return view;
    }

}
