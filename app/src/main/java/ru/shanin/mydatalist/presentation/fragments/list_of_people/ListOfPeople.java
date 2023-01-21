package ru.shanin.mydatalist.presentation.fragments.list_of_people;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ru.shanin.mydatalist.R;
import ru.shanin.mydatalist.data.NewData;
import ru.shanin.mydatalist.presentation.fragments.about_people.AboutPeople;

public class ListOfPeople extends Fragment {

    private static final String ARGUMENT_IS_ONE_PANE_STATE = "is one pane";
    private Boolean isOnePane;

    private ListOfPeopleViewModel viewModel;

    private FloatingActionButton fab;
    private RecyclerView recyclerView;

    private Adapter adapter;

    public static ListOfPeople newInstance(Boolean isOnePane) {
        Bundle args = new Bundle();
        args.putBoolean(ARGUMENT_IS_ONE_PANE_STATE, isOnePane);
        ListOfPeople fragment = new ListOfPeople();
        fragment.setArguments(args);
        return fragment;
    }

    private void parseParams() {
        Bundle args = requireArguments();
        if (!args.containsKey(ARGUMENT_IS_ONE_PANE_STATE))
            throw new RuntimeException("Arguments are absent");
        isOnePane = args.getBoolean(ARGUMENT_IS_ONE_PANE_STATE);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseParams();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(
                R.layout.fragment_list_of_people,
                container,
                false
        );
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        initViewModel();
        initView(view);
    }

    private void initAdapter() {
        adapter = new Adapter(new DiffCallback());
        adapter.peopleClickListener = this::startAboutPeople;
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(ListOfPeopleViewModel.class);
        viewModel.getPeopleList().observe(getViewLifecycleOwner(), peoples -> {
            adapter.submitList(peoples);
        });
    }

    private void initView(View view) {
        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            //TODO Make method to action on click on fab
            for (int i = 0; i < 5; i++)
                viewModel.addNewPeople(NewData.newPeople());
        });
        recyclerView = view.findViewById(R.id.rv_people_list);
        recyclerView.setAdapter(adapter);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(
                Adapter.VIEW_TYPE_PEOPLE_AGE_1, Adapter.MAX_POOL_SIZE);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(
                Adapter.VIEW_TYPE_PEOPLE_AGE_2, Adapter.MAX_POOL_SIZE);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(
                Adapter.VIEW_TYPE_PEOPLE_AGE_3, Adapter.MAX_POOL_SIZE);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(
                Adapter.VIEW_TYPE_PEOPLE_AGE_4, Adapter.MAX_POOL_SIZE);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(
                Adapter.VIEW_TYPE_PEOPLE_AGE_DEFAULT, Adapter.MAX_POOL_SIZE);
    }

    private void startAboutPeople(int peopleId) {
        Fragment fragment = AboutPeople.newInstance(peopleId);
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.popBackStack();
        if (isOnePane)
            fragmentManager
                    .beginTransaction()
                    .addToBackStack("null")
                    .replace(R.id.container_list_of_people, fragment, null)
                    .commit();
        else
            fragmentManager
                    .beginTransaction()
                    .addToBackStack("null")
                    .replace(R.id.container_about_people, fragment, null)
                    .commit();
    }
}