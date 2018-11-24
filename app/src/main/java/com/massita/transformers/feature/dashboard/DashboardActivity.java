package com.massita.transformers.feature.dashboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.massita.transformers.R;
import com.massita.transformers.feature.dashboard.fragments.DashboardFragment;

import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity implements DashboardContract.View {

    private DashboardContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mPresenter = new DashboardPresenter(this);
        mPresenter.start();
    }

    @Override
    public void startDashboardFragment() {
        Fragment fragment = DashboardFragment.newInstance();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

    }

}
