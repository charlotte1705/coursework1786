package com.example.coursework;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coursework.activities.ContactAdapter;
import com.example.coursework.activities.MainActivity;
import com.example.coursework.dao.HikeDao;
import com.example.coursework.database.AppDatabase;
import com.example.coursework.models.Hike;

import java.util.List;


public class UpdateFragment extends Fragment {
    private AppDatabase appDatabase;
    private String name , location, date, parking, length, level, description;
    private long id;

    HikeDao hikeDao;
    EditText updateLevel;
    RadioButton updateRadioYes,updateRadioNo;
    private View view;
    EditText updateName, updateLocation, updateDate, updateLength, updateDescription;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_update, container, false);


        appDatabase = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "sqlite_example_db")
                .allowMainThreadQueries() // For simplicity, don't use this in production
                .build();
        updateName = view.findViewById(R.id.update_name_hikeCard);
        updateLocation = view.findViewById(R.id.update_name_locationCard);
        updateDate = view.findViewById(R.id.update_date_hikeCard);
        updateLength = view.findViewById(R.id.update_length_hikeCard);
        updateLevel = view.findViewById(R.id.update_level_hike);
        updateDescription = view.findViewById(R.id.update_description_hikeCard);
        updateRadioYes = view.findViewById(R.id.update_radioYes);
        updateRadioNo = view.findViewById(R.id.update_radioNo);

        getData();

        // Build the message to display in the AlertDialog
        StringBuilder messageBuilder = new StringBuilder();

        // Create and show the AlertDialog
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setTitle("Details to be Added");
        alertDialogBuilder.setMessage(messageBuilder.toString());



        // Find the button in the layout and set a click listener
        Button update = view.findViewById(R.id.update_addDetailsBtn);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hike hike = new Hike();
                hike = setData();
                try {
                    appDatabase.hikeDao().updateHike(hike);
                    Toast.makeText(getContext(),"Done", Toast.LENGTH_SHORT ).show();
                }catch (Exception e){
                    Toast.makeText(getContext(),"Not done", Toast.LENGTH_SHORT ).show();
                }

            }
        });

        return view;
    }


    public void getData() {
        getParentFragmentManager().setFragmentResultListener("hike_data", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
               /* updateName = view.findViewById(R.id.update_name_hikeCard);
                updateLocation = view.findViewById(R.id.update_name_locationCard);
                updateDate = view.findViewById(R.id.update_date_hikeCard);
                updateLength = view.findViewById(R.id.update_length_hikeCard);
                updateLevel = view.findViewById(R.id.update_level_hike);
                updateDescription = view.findViewById(R.id.update_description_hikeCard);
                updateRadioYes = view.findViewById(R.id.update_radioYes);
                updateRadioNo = view.findViewById(R.id.update_radioNo);
*/

                id = result.getLong("id");
                // Assuming appDatabase is your Room database
                name = result.getString("name");
                updateName.setText(name);

                location = result.getString("location");
                updateLocation.setText(location);

                date = result.getString("date");
                updateDate.setText(date);

                // Set the parking radio buttons
                parking = result.getString(parking);
                if ("Yes".equals(parking)) {
                    updateRadioYes.setChecked(true);
                } else {
                    updateRadioNo.setChecked(true);
                }

                length = result.getString("length");
                updateLength.setText(length);

                level = result.getString("level");
                updateLevel.setText(level);

                //level

                description = result.getString("description");
                updateDescription.setText(description);

                String[] items = getResources().getStringArray(R.array.level_array);
                AutoCompleteTextView levelTxt = view.findViewById(R.id.update_level_hike);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.droplist, items);
                levelTxt.setAdapter(arrayAdapter);

            }
        });
    }


    public Hike setData(){

        name = updateName.getText().toString();
        location = updateLocation.getText().toString();
        date = updateDate.getText().toString();
        if(updateRadioYes.isChecked() == true){
            parking = "yes";
        }else {
            parking = "no";
        }
        length = updateLength.getText().toString();
        level = updateLevel.getText().toString();
        description = updateDescription.getText().toString();
        Hike hike = new Hike(id,name,location,date,parking,length,level,description);;
        return hike;
    }

    public void changeFragment(Fragment fragment){
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.framelayout, fragment);
        ft.commit();
    }
}
