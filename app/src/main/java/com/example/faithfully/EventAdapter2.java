package com.example.faithfully;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.List;

public class EventAdapter2 extends RecyclerView.Adapter<EventAdapter2.ViewHolder> {

    private List<Events> events;

    EventAdapter2(List<Events> events) {
        this.events
                = events;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        EditText etTitle;
        EditText etDesc;
        EditText etDate;
        EditText etMaxGuests;
        TextView tvHostName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            etTitle = itemView.findViewById(R.id.titleTxt);
            etDesc = itemView.findViewById(R.id.descriptionTxt);
            etDate = itemView.findViewById(R.id.dateTxt);
            etMaxGuests = itemView.findViewById(R.id.maxGuestsTxt);
            tvHostName = itemView.findViewById(R.id.hostNameTxt);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Events event = events.get(position);
        viewHolder.etTitle.setText(event.getTitle());
        viewHolder.etDesc.setText(event.getDescription());
        viewHolder.etDate.setText(event.getEvent_time().toString());
        viewHolder.etMaxGuests.setText(event.getMax_guest_num() + "");
        viewHolder.tvHostName.setText(event.getHost_name());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

}
