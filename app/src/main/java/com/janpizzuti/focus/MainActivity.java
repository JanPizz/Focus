package com.janpizzuti.focus;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    public static Integer SpinnerState;
    public static Integer[] spinner_minutes = {0, 1, 3, 5, 10};
    public static Integer SwitchState;
    public static final  String TAG = "com.janpizzuti.focus";


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
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        /**
         *  configure spinner from user preferences
         */
        spinner.setSelection(getSpinnerPositionFromMinutes(getSharedPreferences(getResources().getString(R.string.sharedPrefId), Context.MODE_PRIVATE)
                                                            .getInt(getResources().getString(R.string.spinnerKey),0)));

        spinner.setOnItemSelectedListener(this);

        /**
         * configure switch that controls wheter the service will be active or off
         */
        Switch service_switch = findViewById(R.id.service_switch);

        int isSwitchEnabled = getSharedPreferences(getResources().getString(R.string.sharedPrefId), Context.MODE_PRIVATE).getInt(getResources().getString(R.string.switchKey),0);

        Log.d(TAG, String.format("isSwitchEnabled = %s", isSwitchEnabled));

        switch (isSwitchEnabled){
            case 0:
                service_switch.setChecked(false);
                service_switch.setText(R.string.switch_off);
                SwitchState = 0;
                break;

            case 1:
                service_switch.setChecked(true);
                service_switch.setText(R.string.switch_on);
                SwitchState = 1;
                break;
        }

        service_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SwitchState = 1;
                    buttonView.setText(R.string.switch_on);
                } else {
                    SwitchState = 0;
                    buttonView.setText(R.string.switch_off);
                }
            }
        });

        /**
         * Initialize service and receiver
         */


    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "SPINNER SELECTED");
        SpinnerState = spinner_minutes[position]; //gets minutes immediately
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




    @Override
    public void onStop(){
        Log.d(TAG, "onSTOP");
        saveSettings();

        super.onStop();
    }

    public void saveSettings() {
        SharedPreferences.Editor spEditor = getSharedPreferences(getResources().getString(R.string.sharedPrefId), Context.MODE_PRIVATE).edit();
        if (SpinnerState != null) {
            Log.d(TAG, "SPINNER PRESENT");
            spEditor.putInt(getResources().getString(R.string.spinnerKey), SpinnerState);
        }
        if (SwitchState != null) {
            Log.d(TAG, "SWITCH PRESENT");
            spEditor.putInt(getResources().getString(R.string.switchKey), SwitchState);
        }
        spEditor.commit();
    }

    public static Integer getSpinnerPositionFromMinutes(Integer minutes) {
        switch (minutes){
            case 0: return 0;
            case 1: return 1;
            case 3: return 2;
            case 5: return 3;
            case 10: return 4;
        }
        return 0;
    }
}
