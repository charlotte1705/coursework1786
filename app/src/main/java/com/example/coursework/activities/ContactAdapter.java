package com.example.coursework.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.coursework.R;
import com.example.coursework.models.Hike;

import java.util.List;

// ContactAdapter.java
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<Hike> hikes;

    public ContactAdapter(List<Hike> hikes) {
        this.hikes = hikes;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_card, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Hike hike = hikes.get(position);
        holder.name.setText(hike.hike_name);
        holder.location.setText(hike.hike_location);
        holder.date.setText(hike.hike_date);
        holder.length.setText(hike.hike_length);
        holder.level.setText(hike.hike_level);
        holder.description.setText(hike.hike_description);

    }



    @Override
    public int getItemCount() {
        return hikes.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView name, location, date, length, level, description;


        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameCard);
            location = itemView.findViewById(R.id.locationCard);
            date = itemView.findViewById(R.id.dateCard);
            length = itemView.findViewById(R.id.lengthCard);
            level = itemView.findViewById(R.id.levelCard);
            description = itemView.findViewById(R.id.descriptionCard);

        }
    }
}