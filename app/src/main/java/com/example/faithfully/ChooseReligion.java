package com.example.faithfully;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import android.os.Bundle;

public class ChooseReligion extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String TAG = ChooseReligion.class.getSimpleName();
    String[] religion = { "Christianity", "Judaism", "Islam", "Religiously Unaffiliated", "Buddhism", "Hinduism","Atheism"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_religion);

        //Getting the instance of Spinner and Applying OnItemSelectedListener on it
        Spinner spin = findViewById(R.id.religionSpn);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, religion);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(spinnerAdapter);


        Button nextButton = findViewById(R.id.religionBtn);
        //Take the user to homepage activity
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseReligion.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(), religion[position] , Toast.LENGTH_LONG).show();

        String selectedReligion = religion[position];
        PreferenceSettings.saveReligionToPref(this, selectedReligion);

        Log.i(TAG, PreferenceSettings.getReligionType(ChooseReligion.this));
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


}