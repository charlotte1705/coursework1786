package com.example.coursework;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.coursework.database.AppDatabase;
import com.example.coursework.models.Hike;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment# newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
    private AppDatabase appDatabase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        appDatabase = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "sqlite_example_db")
                .allowMainThreadQueries() // For simplicity, don't use this in production
                .build();

        Button addDetailsBtn = view.findViewById(R.id.addDetailsBtn);
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
        EditText levelTxt = getView().findViewById(R.id.level_hike);
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

        Hike hike = new Hike();
        hike.hike_name = name;
        hike.hike_location = location;
        hike.hike_date = date;
        hike.hike_parking = radioSelect;
        hike.hike_length = length;
        hike.hike_level = level;
        hike.hike_description = description;

        long hike_id = appDatabase.hikeDao().insertHike(hike);
        Toast.makeText(getContext(), "Hike has been created with id: " + hike_id,
                Toast.LENGTH_LONG
        ).show();
    }


}