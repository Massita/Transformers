package com.massita.transformers.feature.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.massita.transformers.R;
import com.massita.transformers.feature.dashboard.fragments.DashboardFragment;
import com.massita.transformers.feature.transformers.TransformersActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    public void startAddNewTransformerActivity() {
        startActivity(new Intent(this, TransformersActivity.class));
    }

    @OnClick(R.id.fab)
    protected void onAddNewTransformerClick() {
        mPresenter.onAddNewTransformer();
    }

}
