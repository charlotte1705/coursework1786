package com.example.coursework.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.example.coursework.R;
import com.example.coursework.UpdateFragment;
import com.example.coursework.models.Hike;

import java.util.List;

// ContactAdapter.java
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
     Context context;
    private List<Hike> hikes;
    private OnClickListener onClickListener;


    public ContactAdapter(Context context, List<Hike> hikes, OnClickListener onClickListener) {
        this.context = context;
        this.hikes = hikes;
        this.onClickListener = onClickListener;
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
        holder.parking.setText(hike.hike_parking);
        holder.length.setText(hike.hike_length);
        holder.level.setText(hike.hike_level);
        holder.description.setText(hike.hike_description);
        holder.delete.setOnClickListener(v -> {
            if (onClickListener != null) {
                onClickListener.onDeleteClick(hikes.get(position));
            }
        });

        holder.update.setOnClickListener(view -> {
            onClickListener.sendData(hike);
            onClickListener.changeFragment(new UpdateFragment());
        });
//

    }
    public interface OnClickListener {
        void onDeleteClick(Hike hike);
        void changeFragment(Fragment fragment);
        void sendData(Hike hike);

    }


    @Override
    public int getItemCount() {
        return hikes.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView name, location, date, length, level, description, parking;
        Button delete;
        Button update;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameCard);
            location = itemView.findViewById(R.id.locationCard);
            date = itemView.findViewById(R.id.dateCard);
            parking = itemView.findViewById(R.id.parkingCard);
            length = itemView.findViewById(R.id.lengthCard);
            level = itemView.findViewById(R.id.levelCard);
            description = itemView.findViewById(R.id.descriptionCard);
            delete = itemView.findViewById(R.id.delete_btn);
            update = itemView.findViewById(R.id.update_btn);
        }
    }
}