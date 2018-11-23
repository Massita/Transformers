package com.massita.transformers.feature.transformers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.massita.transformers.R;
import com.massita.transformers.feature.transformers.fragments.TransformerFragment;

public class TransformersActivity extends AppCompatActivity implements TransformerContract.View {

    private TransformerContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transformers);

        mPresenter = new TransformerPresenter(this);

        mPresenter.start();
    }

    @Override
    public void showNewTransformerFragment() {
        Fragment fragment = TransformerFragment.newInstance();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
