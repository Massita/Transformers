package com.massita.transformers.feature.dashboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.massita.transformers.R;

public class DashboardActivity extends AppCompatActivity implements DashboardContract.View {

    private DashboardContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new DashboardPresenter(this);
        mPresenter.start();
    }

    @Override
    public void startDashboardFragment() {
        // TODO
    }
}
