package com.btec.fpt.appdemo_week7.fragments;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.btec.fpt.appdemo_week7.R;
import com.btec.fpt.appdemo_week7.adapters.ExpenseAdapter;
import com.btec.fpt.appdemo_week7.database.DatabaseHelper;
import com.btec.fpt.appdemo_week7.models.Expense;

import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ExpenseAdapter adapter;
    private DatabaseHelper databaseHelper;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        databaseHelper = new DatabaseHelper(getContext());

        // Load expenses from database
        loadExpenses();

        return view;
    }

    private void loadExpenses() {
        List<Expense> expenseList = databaseHelper.getAllExpenses();
        adapter = new ExpenseAdapter(expenseList);
        recyclerView.setAdapter(adapter);
    }
}

