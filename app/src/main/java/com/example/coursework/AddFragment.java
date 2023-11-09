package com.example.coursework;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.coursework.activities.ContactAdapter;
import com.example.coursework.database.AppDatabase;
import com.example.coursework.models.Hike;

import java.util.List;


public class AddFragment extends Fragment {

    private AppDatabase appDatabase;
    private ContactAdapter adapter;
    List<Hike> hikes;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        appDatabase = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "sqlite_example_db")
                .allowMainThreadQueries() // For simplicity, don't use this in production
                .build();

        Button addDetailsBtn = view.findViewById(R.id.addDetailsBtn);
        String[] items = getResources().getStringArray(R.array.level_array);
        AutoCompleteTextView levelTxt = view.findViewById(R.id.level_hike);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.droplist, items);
        levelTxt.setAdapter(arrayAdapter);

        addDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDetails();
            }
        });

        return view;
    }


    private void saveDetails(){
        EditText nameTxt = getView().findViewById(R.id.name_hike);
        EditText locationTxt = getView().findViewById(R.id.name_location);
        EditText dateTxt = getView().findViewById(R.id.date_hike);
        EditText lengthTxt = getView().findViewById(R.id.length_hike);
        AutoCompleteTextView levelTxt = getView().findViewById(R.id.level_hike);
        EditText descriptionTxt = getView().findViewById(R.id.description_hike);
        RadioButton yes = getView().findViewById(R.id.radioYes);
        RadioButton no = getView().findViewById(R.id.radioNo);

        String radioSelect = yes.getText().toString();

        if (yes.isChecked()){
            radioSelect = "Yes";
        } else if (no.isChecked()){
            radioSelect = "No";
        }


        String name = nameTxt.getText().toString();
        String location = locationTxt.getText().toString();
        String date = dateTxt.getText().toString();
        String length = lengthTxt.getText().toString();
        String level = levelTxt.getText().toString();
        String description = descriptionTxt.getText().toString();

        // Build the message to display in the AlertDialog
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Name: ").append(name).append("\n");
        messageBuilder.append("Location: ").append(location).append("\n");
        messageBuilder.append("Date: ").append(date).append("\n");
        messageBuilder.append("Length: ").append(length).append("\n");
        messageBuilder.append("Level: ").append(level).append("\n");
        messageBuilder.append("Parking: ").append(radioSelect);
        messageBuilder.append("Description: ").append(description).append("\n");


        Hike hike = new Hike();
        hike.hike_name = name;
        hike.hike_location = location;
        hike.hike_date = date;
        hike.hike_parking = radioSelect;
        hike.hike_length = length;
        hike.hike_level = level;
        hike.hike_description = description;

        // Create and show the AlertDialog
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setTitle("Details to be Added");
        alertDialogBuilder.setMessage(messageBuilder.toString());
        alertDialogBuilder.setPositiveButton("Add", (dialog, which) -> {
        long hike_id = appDatabase.hikeDao().insertHike(hike);
        Toast.makeText(getContext(), "Hike has been created with id: " + hike_id,
                Toast.LENGTH_LONG
        ).show();
    });
       alertDialogBuilder.setNegativeButton("Cancel", (dialog, which) -> {
        // Cancel action
    });

    alertDialogBuilder.create().show();

}}