package com.massita.transformers.feature.dashboard;

public class DashboardPresenter implements DashboardContract.Presenter {

    private DashboardContract.View mView;

    public DashboardPresenter(DashboardContract.View view) {
        this.mView = view;
    }

    @Override
    public void start() {
        mView.startDashboardFragment();
    }

    @Override
    public void onFight() {
        mView.startFightActivity();
    }

    @Override
    public void onAddNewTransformer() {
        mView.startAddNewTransformerActivity();
    }
}
