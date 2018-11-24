package com.massita.transformers.feature.transformers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.massita.transformers.R;
import com.massita.transformers.api.model.Transformer;
import com.massita.transformers.feature.transformers.fragments.TransformerFragment;

public class TransformersActivity extends AppCompatActivity implements TransformerContract.View {

    private TransformerContract.Presenter mPresenter;
    public static final String TRANSFORMER_EXTRA = "TRANSFORMER_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transformers);

        mPresenter = new TransformerPresenter(this);

        mPresenter.start((Transformer) getIntent().getSerializableExtra(TRANSFORMER_EXTRA));
    }

    @Override
    public void showNewTransformerFragment() {
        Fragment fragment = TransformerFragment.newInstance();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void showEditTransfomerFragment(Transformer transformer) {
        Fragment fragment = TransformerFragment.newInstance(transformer);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    public static Intent getTransformerActivityIntent(Context context, Transformer transformer) {
        Intent intent = new Intent(context, TransformersActivity.class);
        intent.putExtra(TRANSFORMER_EXTRA, transformer);

        return intent;
    }
}
