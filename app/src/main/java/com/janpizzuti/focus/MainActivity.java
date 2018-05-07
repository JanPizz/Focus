package com.janpizzuti.focus;

import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String SPINNER_KEY = "com.janpizzuti.focus.SPINNER_KEY";
    public static final String SWITCH_KEY = "com.janpizzuti.focus.SWITCH_KEY";
    public static Integer SpinnerState;
    public static Integer[] spinner_minutes = {0, 3, 5, 10};
    public static Integer SwitchState;
    private Context mContext;
    private SQLiteDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         *  initialize notification channel
         */

        NotificationHelper mNotificationHelper = new NotificationHelper(this);

        /**
         * configure spinner, spinner adapter and listener
         *
         * this will let the user configure the time that passes before the notification is sent
         */

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        /**
         *  configure spinner from user preferences
         *  TODO: use database instead of shared preferences
         */
        spinner.setSelection(getSpinnerPositionFromMinutes(
                getPreferences(Context.MODE_PRIVATE).getInt(SPINNER_KEY,0)));

        spinner.setOnItemClickListener((AdapterView.OnItemClickListener) this);

        /**
         * configure switch that controls wheter the service will be active or off
         * TODO: use database instead of shared preferences
         */
        Switch service_switch = findViewById(R.id.service_switch);

        switch (getPreferences(Context.MODE_PRIVATE).getInt(SWITCH_KEY,0)){
            case 0: service_switch.setChecked(false);
            case 1: service_switch.setChecked(true);
        }

        service_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SwitchState = 1;
                } else {
                    SwitchState = 0;
                }
            }
        });





    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        SpinnerState = spinner_minutes[position]; //gets minutes immediately
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onStop(){
        saveSettings();

        super.onStop();
    }

    public void saveSettings() {
        SharedPreferences.Editor spEditor = getPreferences(Context.MODE_PRIVATE).edit();
        spEditor.putInt(SPINNER_KEY, SpinnerState);
        spEditor.putInt(SWITCH_KEY, SwitchState);
        spEditor.commit();
    }

    public static Integer getSpinnerPositionFromMinutes(Integer minutes) {
        switch (minutes){
            case 0: return 0;
            case 3: return 1;
            case 5: return 2;
            case 10: return  3;
        }
        return 0;
    }
}
