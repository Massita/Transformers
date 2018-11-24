package com.massita.transformers.feature.battle.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.massita.transformers.R;
import com.massita.transformers.util.Battle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawFragment extends Fragment implements DrawFragmentContract.View {

    private static final String RESULTS_ARGUMENTS = "RESULTS_ARGUMENTS";

    @BindView(R.id.text_view_number_of_battles)
    TextView numberOfBattlesText;

    private DrawFragmentContract.Presenter mPresenter;

    public static DrawFragment newInstance(Battle.Results results) {
        DrawFragment fragment = new DrawFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(RESULTS_ARGUMENTS, results);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_draw, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        Battle.Results results = args != null ? (Battle.Results) args.getSerializable(RESULTS_ARGUMENTS) : null;
        mPresenter = new DrawFragmentPresenter(this, results);

        mPresenter.start();
    }

    @Override
    public void setNumberOfBattles(int numberOfBattles) {
        numberOfBattlesText.setText(getString(R.string.number_of_battles, numberOfBattles));
    }
}
