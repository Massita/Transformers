package com.massita.transformers.feature.dashboard;

public interface DashboardContract {

    interface View {

        void startDashboardFragment();

        void startAddNewTransformerActivity();

    }

    interface Presenter {

        void start();

        void onAddNewTransformer();

    }

}
