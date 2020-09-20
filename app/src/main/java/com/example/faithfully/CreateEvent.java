package com.example.faithfully;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CreateEvent extends AppCompatActivity {
    public static final String TAG = CreateEvent.class.getSimpleName();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String username;
    String userID;
    TextInputEditText host;
    TextInputEditText title;
    TextInputEditText description;
    TextInputEditText meeting_link;
    TextInputEditText max_guest_num;
    CheckBox christianity;
    CheckBox judaism;
    CheckBox islam;
    CheckBox buddhism;
    CheckBox hinduism;
    CheckBox atheism;
    CheckBox religiously_Unaffiliated;
    CheckBox all_of_above;
    DatePicker datePicker;
    TimePicker timePicker;
    Button post_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        username = user.getDisplayName();
        userID = user.getUid();

        host = findViewById(R.id.host_edit);
        title = findViewById(R.id.title_edit);
        description = findViewById(R.id.description_edit);
        meeting_link = findViewById(R.id.meeting_link_edit);
        max_guest_num = findViewById(R.id.max_guest_num);
        christianity = findViewById(R.id.christianity);
        judaism = findViewById(R.id.judaism);
        islam = findViewById(R.id.islam);
        buddhism = findViewById(R.id.buddhism);
        hinduism = findViewById(R.id.hinduism);
        atheism = findViewById(R.id.atheism);
        religiously_Unaffiliated = findViewById(R.id.religiously_Unaffiliated);
        all_of_above = findViewById(R.id.all_of_above);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        post_button = findViewById(R.id.post_button);

        //put username in host edit text
        host.setText(username);

        post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postEvent();
            }
        });
    }

    private void postEvent() {
        String title_str;
        String description_str;
        String meeting_link_str;
        int max_guest_num_int;
        Date event_date;
        int check_counter = 0;
        List<String> target_religions_list = new ArrayList<>();

        if (title.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "Title can not be blank", Toast.LENGTH_LONG).show();
            return;
        } else {
            title_str = title.getText().toString();
        }

        if (description.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "description can not be blank", Toast.LENGTH_LONG).show();
            return;
        } else {
            description_str = description.getText().toString();
        }

        if (meeting_link.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "meeting link can not be blank", Toast.LENGTH_LONG).show();
            return;
        } else {
            meeting_link_str = meeting_link.getText().toString();
        }

        if (max_guest_num.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "max. guest number can not be blank", Toast.LENGTH_LONG).show();
            return;
        } else {
            try{
                max_guest_num_int = Integer.parseInt(max_guest_num.getText().toString().trim());
                Log.i(TAG, String.valueOf(Integer.parseInt(max_guest_num.getText().toString().trim())));
            }
            catch (NumberFormatException e){
                Toast.makeText(this, "enter a valid max. guest number", Toast.LENGTH_LONG).show();
                return;
            }

        }

        if (all_of_above.isChecked()){
            target_religions_list.add("Everybody");
            check_counter++;
        }
        else{
            if (christianity.isChecked()){
                target_religions_list.add(christianity.getText().toString());
                check_counter++;
            }
            if (judaism.isChecked()){
                target_religions_list.add(judaism.getText().toString());
                check_counter++;
            }
            if (islam.isChecked()){
                target_religions_list.add(islam.getText().toString());
                check_counter++;
            }
            if (buddhism.isChecked()){
                target_religions_list.add(buddhism.getText().toString());
                check_counter++;
            }
            if (hinduism.isChecked()){
                target_religions_list.add(hinduism.getText().toString());
                check_counter++;
            }
            if (atheism.isChecked()){
                target_religions_list.add(atheism.getText().toString());
                check_counter++;
            }
            if (religiously_Unaffiliated.isChecked()){
                target_religions_list.add(religiously_Unaffiliated.getText().toString());
                check_counter++;
            }

        }

        if(check_counter == 0){
            Toast.makeText(this, "Select at least one target religion", Toast.LENGTH_LONG).show();
            return;
        }
        
        event_date = getDate();

        FirestoreRepository.createEvent(title_str, description_str, meeting_link_str, username, userID, max_guest_num_int, null, event_date, target_religions_list);

        finish();
    }

    private Date getDate() {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        int hour;
        int minute;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            hour = timePicker.getHour();
            minute = timePicker.getMinute();
        }
        else{
            hour = timePicker.getCurrentHour();
            minute = timePicker.getCurrentMinute();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute);

        return calendar.getTime();
    }

}