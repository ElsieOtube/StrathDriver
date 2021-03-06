package com.example.strathdriver.ui.history;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.strathdriver.adapter.recyclerviewhistory.HistoryAdapter;
import com.example.strathdriver.model.FirebaseHistoryListener;
import com.example.strathdriver.model.FirebaseHistoryModel;
import com.example.strathdriver.model.firebase.History;

import java.util.ArrayList;

public class HistoryViewModel extends ViewModel {
    private HistoryAdapter adapter;
    private final ArrayList<History> historyList = new ArrayList<>();

    public HistoryViewModel() {

    }

    public void getDriverHistory() {
        final FirebaseHistoryModel firebaseHistoryModel = new FirebaseHistoryModel();
        firebaseHistoryModel.getHistory(new FirebaseHistoryListener.GetFirebaseHistoryListener() {
            @Override
            public void onFirebaseHistoryRetrieved(ArrayList<History> historyArrayList) {
                historyList.clear();
                historyList.addAll(historyArrayList);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public HistoryAdapter getHistoryAdapter(Context context) {
        if (adapter == null) {
            adapter = new HistoryAdapter(context, historyList, (view, index) -> {

            });
        }

        return adapter;
    }

}
