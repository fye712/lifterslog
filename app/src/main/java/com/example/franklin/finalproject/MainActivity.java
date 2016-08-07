//Computer Seminar Final Project Fall 2015
//Franklin Ye
//Sources: Google Android Tutorials, Android Hive
//http://developer.android.com/guide/topics/media/camera.html
//http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
//http://www.androidhive.info/2013/10/android-tab-layout-with-swipeable-views-1/

package com.example.franklin.finalproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    DatabaseHandler db = new DatabaseHandler(this);
    private String liftName;
    private String dateTime;
    private int weightInt;
    private int repsInt;
    private ShowFragment showFragment;
    private ViewPagerAdapter adapter;
    private LiftAdapter liftAdapter;

    /**
     * creates the tab layout
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sets the tool bar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //sets the status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.statusBarColor));
        }
        //sets the title and other attributes of the toolbar
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Lifter's Log");

        //creates the viewpager to create the tablayout
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "VIDEO");
        adapter.addFragment(new ShowFragment(), "LIST");
        adapter.addFragment(new ThreeFragment(), "WEB");
        adapter.addFragment(new FourFragment(), "KCAL");
        viewPager.setAdapter(adapter);
        //gets the fragment for later use
        showFragment = (ShowFragment) adapter.getItem(1);

        //sets the tab layout to the view pager
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    /**
     * View pager adapter class to set up the view pager
     */
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        //constructor
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        /**
         * gets the position of an item in the list
         *
         * @param position
         * @return
         */
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        /**
         * gets the number of tabs
         *
         * @return
         */
        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        /**
         * adds a tab with a title
         *
         * @param fragment
         * @param title
         */
        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        /**
         * gets the title of a tab
         *
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    /**
     * inflates the menu based on the menu_main file
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //switch statement to handle the different items
        switch (item.getItemId()) {
            //case for clicking the add item
            case R.id.action_add:
                //builds a dialog
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Add Exercise");
                builder.setMessage("What exercise?");
                final EditText exercise;
                final EditText reps;
                final EditText weight;
                final EditText date;
                weight = new EditText(this);
                reps = new EditText(this);
                exercise = new EditText(this);
                date = new EditText(this);
                //sets the input types
                reps.setInputType(InputType.TYPE_CLASS_NUMBER);
                weight.setInputType(InputType.TYPE_CLASS_NUMBER);
                date.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
                exercise.setHint(R.string.exercise_hint);
                reps.setHint(R.string.reps_hint);
                weight.setHint(R.string.weight_hint);
                date.setHint(R.string.date_hint);
                builder.setView(exercise);
                //creates dialogs for adding things to the database, gets repeated 4 times
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    /**
                     * on positive click of the dialog, brings up the second dialog
                     * @param dialog
                     * @param which
                     */
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //sets the data from the dialog to a variable
                        liftName = exercise.getText().toString();
                        builder.setTitle("Add Weight");
                        builder.setMessage("How much weight?");
                        builder.setView(weight);
                        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            /**
                             * on positive click of the second dialog, brings up the third dialog
                             * @param dialog
                             * @param which
                             */
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //sets the data from the previous dialog to a variable
                                weightInt = Integer.parseInt(weight.getText().toString());
                                builder.setTitle("Add Reps");
                                builder.setMessage("How many reps?");
                                builder.setView(reps);
                                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                                    /**
                                     * on positive button click of the dialog
                                     * @param dialog
                                     * @param which
                                     */
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //gets the reps from the edittext
                                        repsInt = Integer.parseInt(reps.getText().toString());
                                        builder.setTitle("Add Date");
                                        builder.setMessage("What's the date?");
                                        builder.setView(date);
                                        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                //gets the date from the edit text
                                                dateTime = date.getText().toString();
                                                //adds a new Lift item to the database with the variables from the edit texts
                                                db.addLift(new Lift(liftName, repsInt, weightInt, dateTime));
                                                liftAdapter = (LiftAdapter) showFragment.getListAdapter();
                                                //updates the list fragment
                                                liftAdapter.notifyDataSetChanged();
                                                showFragment.showAll();
                                            }
                                        });
                                        //negative button, exits dialogs
                                        builder.setNegativeButton("Cancel", null);
                                        builder.create().show();
                                    }

                                });
                                //negative button, exits dialogs
                                builder.setNegativeButton("Cancel", null);
                                builder.create().show();
                            }

                        });
                        //negative button, exits dialogs
                        builder.setNegativeButton("Cancel", null);
                        builder.create().show();
                    }
                });
                //negative button, exits dialogs
                builder.setNegativeButton("Cancel", null);
                builder.create().show();
                return true;

            case R.id.action_info:
                //brings up the info dialog
                final AlertDialog.Builder info = new AlertDialog.Builder(this);
                info.setTitle("User's Guide");
                info.setMessage(R.string.info_dialog);
                info.setPositiveButton("Got it!", null);
                info.create().show();

            default:
                return false;
        }
    }
}
