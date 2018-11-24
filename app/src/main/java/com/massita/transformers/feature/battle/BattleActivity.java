package com.massita.transformers.feature.battle;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.massita.transformers.R;
import com.massita.transformers.api.model.Transformer;
import com.massita.transformers.feature.battle.fragments.AllDestroyedFragment;
import com.massita.transformers.feature.battle.fragments.DrawFragment;
import com.massita.transformers.feature.battle.fragments.WinnerFragment;
import com.massita.transformers.util.Battle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BattleActivity extends AppCompatActivity implements BattleContract.View {
    private static final String TRANSFORMERS_EXTRA = "TRANSFORMERS_EXTRA";

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private BattleContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        ButterKnife.bind(this);

        ArrayList<Transformer> transformers = (ArrayList<Transformer>) getIntent().getSerializableExtra(TRANSFORMERS_EXTRA);
        mPresenter = new BattlePresenter(this, transformers);
        mPresenter.computeBattle();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showBattleDrawFragment(Battle.Results results) {
        Fragment fragment = DrawFragment.newInstance(results);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void showBattleWinnerFragment(Battle.Results results) {
        Fragment fragment = WinnerFragment.newInstance(results);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void showAllDestroyedFragment() {
        Fragment fragment = AllDestroyedFragment.newInstance();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    public static Intent getBattleActivityIntent(Context context, ArrayList<Transformer> list) {
        Intent intent = new Intent(context, BattleActivity.class);
        intent.putExtra(TRANSFORMERS_EXTRA, list);

        return intent;
    }
}
