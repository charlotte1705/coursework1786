package com.example.coursework;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.coursework.activities.ContactAdapter;
import com.example.coursework.database.AppDatabase;
import com.example.coursework.models.Hike;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment# } factory method to
 * create an instance of this fragment.
 */


public class HomeFragment extends Fragment implements ContactAdapter.OnClickListener {

    private AppDatabase appDatabase;
    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    private List<Hike> hikes;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        appDatabase = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "sqlite_example_db")
                .allowMainThreadQueries() // For simplicity, don't use this in production
                .build();

        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        hikes = appDatabase.hikeDao().getAllHikes();

        adapter = new ContactAdapter(getContext(),hikes, this);
        recyclerView.setAdapter(adapter);

        // Find the button in the layout
     //   Button updateButton = v.findViewById(R.id.update_btn);

        return v;


    }

    @Override
    public void onDeleteClick(Hike hike) {
        new AlertDialog.Builder(getContext())
                .setTitle("Delete Contact")
                .setMessage("Are you sure you want to delete this contact?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    // Remove from the database
                    appDatabase.hikeDao().deleteHike(hike);
                    // Update the list
                    hikes.remove(hike);
                    adapter.notifyDataSetChanged();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
    public void changeFragment(Fragment fragment){
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.framelayout, fragment);
        ft.commit();
    }
    @Override
    public void sendData(Hike hike){
        Bundle result = new Bundle();
        result.putLong("id",hike.hike_id);
        result.putString("name",hike.hike_name);
        result.putString("location",hike.hike_location);
        result.putString("date",hike.hike_date);
        result.putString("parking",hike.hike_parking);
        result.putString("length", hike.hike_length);
        result.putString("level", hike.hike_level);
        result.putString("description", hike.hike_description);
        getParentFragmentManager().setFragmentResult("hike_data",result);
    }
}