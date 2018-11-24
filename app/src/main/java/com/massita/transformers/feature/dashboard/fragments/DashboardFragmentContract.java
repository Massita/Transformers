package com.massita.transformers.feature.dashboard.fragments;

import com.massita.transformers.api.model.Transformer;

import java.util.ArrayList;
import java.util.List;

public interface DashboardFragmentContract {

    interface View {

        void showLoading();

        void hideLoading();

        void notifyListUpdated();

        void prepareRecyclerView();

        void showList(List<Transformer> transformersList);

        void startFightActivity(ArrayList<Transformer> list);

        void startAddNewTransformerActivity();

        void startEditTransformerActivity(Transformer transformer);

        void showError(String message);

    }

    interface Presenter {

        void start();

        void loadTransformers();

        void onDestroy();

        void onFight();

        void onAddNewTransformer();

        void onTransformerEdit(Transformer transformer, int action);

        void onTransformerSelected(Transformer transformer);

    }

}
