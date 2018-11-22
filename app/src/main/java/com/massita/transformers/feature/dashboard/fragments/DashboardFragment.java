package com.massita.transformers.feature.dashboard.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.massita.transformers.R;
import com.massita.transformers.api.RestClient;
import com.massita.transformers.api.model.Transformer;
import com.massita.transformers.util.SharedPreferencesRepository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardFragment extends Fragment implements DashboardFragmentContract.View {

    private DashboardFragmentContract.Presenter mPresenter;

    @BindView(R.id.recycler_view)
    protected RecyclerView mRecyclerView;

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, view);

        return view;
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

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void prepareRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void showList(List<Transformer> transformersList) {
        Toast.makeText(getContext(), "Carregou lista", Toast.LENGTH_LONG).show();
    }

}
