package com.alc.ljv.utility;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.alc.ljv.R;
import com.alc.ljv.service.AppMainServiceEvent;

import org.greenrobot.eventbus.EventBus;

public class ViewDialog {

    Spinner citySpinner, langSpinner, sortSpinner;
    AppMainServiceEvent event;
    Intent responseIntent;

    public void showDialog(final Activity activity, String list_title) {
        final Dialog dialog = new Dialog(activity);
        UtilsClass util = new UtilsClass(activity);
        String stored_location = util.getLocation();
        String stored_language = util.getLanguage();
        String stored_sorting = util.getSort();


        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.activity_filter);

        citySpinner = (Spinner) dialog.findViewById(R.id.city_spinner);
        langSpinner = (Spinner) dialog.findViewById(R.id.lang_spinner);
        sortSpinner = (Spinner) dialog.findViewById(R.id.sort_spinner);


        if(stored_location!= null && stored_language!= null&& stored_sorting !=null){

            int city_spinnerPosition =  ((ArrayAdapter<String>)citySpinner.getAdapter()).getPosition(stored_location);
           citySpinner.setSelection(city_spinnerPosition);

            int lang_spinnerPosition =  ((ArrayAdapter<String>)langSpinner.getAdapter()).getPosition(stored_language);
            langSpinner.setSelection(lang_spinnerPosition);

            int sort_spinnerPosition =  ((ArrayAdapter<String>)sortSpinner.getAdapter()).getPosition(stored_sorting);
            sortSpinner.setSelection(sort_spinnerPosition);
        }

//        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
//        text.setText(msg);

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_applyfilter);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilsClass utils = new UtilsClass(dialog.getContext());

                String city = String.valueOf(citySpinner.getSelectedItem());
                String lang = String.valueOf(langSpinner.getSelectedItem());
                String sort = String.valueOf(sortSpinner.getSelectedItem());

                utils.storeFilterParameters(city, lang, sort);
//TODO Create Event when Dialog is launched
                utils.storeFilterChange(true);

                responseIntent = new Intent();

                event = new AppMainServiceEvent();
                event.setMainIntent(responseIntent);
                event.setEventType(AppMainServiceEvent.FILTER_PARAMETERS_CHANGED);
                EventBus.getDefault().post(event);
                dialog.dismiss();

            }
        });

        dialog.show();

    }

}