package com.massita.transformers.feature.dashboard.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.massita.transformers.R;
import com.massita.transformers.api.RestClient;
import com.massita.transformers.api.model.Transformer;
import com.massita.transformers.feature.battle.BattleActivity;
import com.massita.transformers.feature.dashboard.adapters.TransformersAdapter;
import com.massita.transformers.feature.transformers.TransformersActivity;
import com.massita.transformers.feature.transformers.fragments.TransformerFragment;
import com.massita.transformers.util.SharedPreferencesRepository;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class DashboardFragment extends Fragment implements DashboardFragmentContract.View, TransformersAdapter.OnItemClickListener {

    private DashboardFragmentContract.Presenter mPresenter;

    public static final int TRANFORMER_REQUEST_CODE = 1;

    @BindView(R.id.recycler_view)
    protected RecyclerView mRecyclerView;

    @BindView(R.id.progress_group)
    Group progressGroup;

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        setHasOptionsMenu(true);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_dashboard, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_fight:
                mPresenter.onFight();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter.start();
        mPresenter.loadTransformers();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferencesRepository.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferencesRepository sharedPreferencesRepository = new SharedPreferencesRepository(sharedPreferences);
        mPresenter = new DashboardFragmentPresenter(this, RestClient.getTransformersService(), sharedPreferencesRepository);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        mPresenter = null;
    }

    @Override
    public void showLoading() {
        progressGroup.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressGroup.setVisibility(View.GONE);
    }

    @Override
    public void prepareRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void showList(List<Transformer> transformersList) {
        mRecyclerView.setAdapter(new TransformersAdapter(transformersList, this));
    }

    @Override
    public void startFightActivity(ArrayList<Transformer> list) {
        startActivity(BattleActivity.getBattleActivityIntent(getContext(), list));
    }

    @Override
    public void startAddNewTransformerActivity() {
        startActivityForResult(new Intent(getContext(), TransformersActivity.class), TRANFORMER_REQUEST_CODE);
    }

    @Override
    public void startEditTransformerActivity(Transformer transformer) {
        startActivityForResult(TransformersActivity.getTransformerActivityIntent(getContext(), transformer), TRANFORMER_REQUEST_CODE);
    }

    @Override
    public void notifyListUpdated() {
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @OnClick(R.id.fab)
    protected void onAddNewTransformerClick() {
        mPresenter.onAddNewTransformer();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == TRANFORMER_REQUEST_CODE && resultCode == RESULT_OK) {
            Transformer transformer = (Transformer) data.getSerializableExtra(TransformerFragment.TRANSFORMER);
            mPresenter.onTransformerEdit(transformer, data.getIntExtra(TransformerFragment.TRANSFORMER_ACTION, -1));
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onItemClick(Transformer transformer) {
        mPresenter.onTransformerSelected(transformer);
    }
}
