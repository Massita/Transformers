package com.massita.transformers.feature.dashboard;

public interface DashboardContract {

    interface View {

        void startDashboardFragment();

        void startAddNewTransformerActivity();

        void startFightActivity();

    }

    interface Presenter {

        void start();

        void onFight();

        void onAddNewTransformer();

    }

}
