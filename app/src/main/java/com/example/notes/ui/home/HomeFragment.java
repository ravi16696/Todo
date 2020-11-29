package com.example.notes.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.notes.Controller.NotesList;
import com.example.notes.R;
import com.example.notes.RecyclerViewAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public static RecyclerView mrecyclerView;
    public static RecyclerViewAdapter mrecyclerViewAdapter;

    private enum LayoutManger {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    private LayoutManger currentLayoutManager;

    private RecyclerView.LayoutManager mlayoutManger;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notes, container, false);

        mrecyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        mlayoutManger = new LinearLayoutManager(getActivity());
//        mlayoutManger = new GridLayoutManager(getActivity(), 2);

        currentLayoutManager = LayoutManger.LINEAR_LAYOUT_MANAGER;
        mrecyclerViewAdapter = new RecyclerViewAdapter(getContext(), getNotesList());

        mrecyclerView.setAdapter(mrecyclerViewAdapter);
        mrecyclerView.setLayoutManager(mlayoutManger);
        mrecyclerView.scrollToPosition(0);
        return root;
    }

    private ArrayList<NotesList> getNotesList() {
        return NotesList.getNotes();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}