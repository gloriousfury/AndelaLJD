package com.alc.ljv.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Spinner;

import com.alc.ljv.R;

public class FilterActivity extends AppCompatActivity {


    Spinner citySpinner, langSpinner, sortSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        citySpinner = (Spinner) findViewById(R.id.city_spinner);
        langSpinner = (Spinner) findViewById(R.id.lang_spinner);
        sortSpinner = (Spinner) findViewById(R.id.sort_spinner);


    }



    public void applyFilter(){



    }
}
