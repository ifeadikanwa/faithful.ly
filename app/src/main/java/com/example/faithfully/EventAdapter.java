package com.example.faithfully;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;

public class EventAdapter extends FirestoreRecyclerAdapter<Events, EventAdapter.EventHolder> {

        public EventAdapter(@NonNull FirestoreRecyclerOptions<Events> options) {
            super(options);
        }

        class EventHolder extends RecyclerView.ViewHolder {
            TextView religionTags;
            TextView host_name;
            TextView confirmed_guest;
            TextInputEditText title;
            TextInputEditText description;
            TextInputEditText date;
            TextInputEditText max_guest;
            ImageButton bookmark;

            public EventHolder(@NonNull View itemView) {
                super(itemView);
                religionTags = itemView.findViewById(R.id.religionTags);
                host_name = itemView.findViewById(R.id.hostNameTxt);
                confirmed_guest = itemView.findViewById(R.id.guestsTxt);
                title = itemView.findViewById(R.id.titleTxt);
                description = itemView.findViewById(R.id.descriptionTxt);
                date = itemView.findViewById(R.id.dateTxt);
                max_guest = itemView.findViewById(R.id.maxGuestsTxt);
                bookmark = itemView.findViewById(R.id.bookmarkBtn);

            }

        }



        @Override
        protected void onBindViewHolder(@NonNull EventHolder holder, int position, @NonNull Events model) {
           holder.host_name.setText(model.getHost_name());
            holder.confirmed_guest.setText(String.valueOf(model.getConfirmations()));
            holder.title.setText(model.getTitle());
            holder.description.setText(model.getDescription());
            holder.max_guest.setText(String.valueOf(model.getMax_guest_num()));

            StringBuilder religions = new StringBuilder();
            for(String religion: model.getTarget_religions() ){
                religions.append(religion).append(". ");
            }
            holder.religionTags.setText(religions.toString());

            if(model.isBookmarked()){
                holder.bookmark.setImageResource(R.drawable.ic_filled_bookmark);
            }
            else{
                holder.bookmark.setImageResource(R.drawable.ic_bookmark);
            }

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm E, dd MMM yyyy");
            String event_date = simpleDateFormat.format(model.getEvent_time());
            holder.date.setText(event_date);

            holder.title.setKeyListener(null);
            holder.description.setKeyListener(null);
            holder.date.setKeyListener(null);
            holder.max_guest.setKeyListener(null);

        }

        @NonNull
        @Override
        public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item_layout, parent, false);
            return new EventHolder(v);
        }
}
