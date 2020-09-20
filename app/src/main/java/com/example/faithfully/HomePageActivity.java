package com.example.faithfully;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().hide();

        //attach click listener to all the cardviews and set them to open their corresponding activities

        CardView event_feed = findViewById(R.id.eventFeed);
        event_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this, EventFeed.class));
            }
        });

        CardView bookmarks = findViewById(R.id.bookmarks);
        bookmarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this, Bookmarks.class));
            }
        });

        CardView myEvents = findViewById(R.id.myEvents);
        myEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this, MyEvents.class));
            }
        });

        CardView confirmedEvents = findViewById(R.id.confirmedEvents);
        confirmedEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this, ConfirmedEvents.class));
            }
        });

        LinearLayout createEvent = findViewById(R.id.createAnEvent);
        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this, CreateEvent.class));
            }
        });

    }
}