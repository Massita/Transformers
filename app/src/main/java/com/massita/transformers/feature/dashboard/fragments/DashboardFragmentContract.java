package com.massita.transformers.feature.dashboard.fragments;

import com.massita.transformers.api.model.Transformer;

import java.util.List;

public interface DashboardFragmentContract {

    interface View {

        void showLoading();

        void hideLoading();

        void prepareRecyclerView();

        void showList(List<Transformer> transformersList);

    }

    interface Presenter {

        void start();

        void loadTransformers();

        void onDestroy();

    }

}
