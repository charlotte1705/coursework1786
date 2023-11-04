package com.example.coursework;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coursework.activities.ContactAdapter;
import com.example.coursework.database.AppDatabase;
import com.example.coursework.models.Hike;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment# } factory method to
 * create an instance of this fragment.
 */


public class HomeFragment extends Fragment {

    private AppDatabase appDatabase;
    private RecyclerView recyclerView;
    private ContactAdapter adapter;
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

        List<Hike> hikes = appDatabase.hikeDao().getAllHikes();

        adapter = new ContactAdapter(hikes);
        recyclerView.setAdapter(adapter);

        return v;
    }
}