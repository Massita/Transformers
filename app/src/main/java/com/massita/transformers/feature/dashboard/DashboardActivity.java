package com.massita.transformers.feature.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.massita.transformers.R;
import com.massita.transformers.feature.dashboard.fragments.DashboardFragment;
import com.massita.transformers.feature.transformers.TransformersActivity;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_fight:
                // TODO
                Toast.makeText(this, "LUTANDO!", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    @Override
    public void startFightActivity() {

    }

    @OnClick(R.id.fab)
    protected void onAddNewTransformerClick() {
        mPresenter.onAddNewTransformer();
    }

}
