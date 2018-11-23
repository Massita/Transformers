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

public class WinnerFragment extends Fragment implements WinnerFragmentContract.View {

    private static final String RESULTS_ARGUMENTS = "RESULTS_ARGUMENTS";

    public static WinnerFragment newInstance(Battle.Results results) {
        WinnerFragment winnerFragment = new WinnerFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(RESULTS_ARGUMENTS, results);
        winnerFragment.setArguments(bundle);

        return winnerFragment;
    }

    @BindView(R.id.text_view_winner)
    TextView winnerText;

    @BindView(R.id.text_view_winning_team)
    TextView winningTeamText;

    @BindView(R.id.text_view_number_of_battles)
    TextView numberOfBattlesText;

    @BindView(R.id.text_view_losing_team)
    TextView losingTeamText;

    private WinnerFragmentContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_winner, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        Battle.Results results = args != null ? (Battle.Results) args.getSerializable(RESULTS_ARGUMENTS) : null;
        mPresenter = new WinnerFragmentPresenter(this, results);

        mPresenter.start();
    }

    @Override
    public void setWinner(String winner) {
        winnerText.setText(getString(R.string.winner_text, winner));
    }

    @Override
    public void setNumberOfBattles(int numberOfBattles) {
        numberOfBattlesText.setText(getString(R.string.number_of_battles, numberOfBattles));
    }

    @Override
    public void setWinningTeam(String winningTeam) {
        winningTeamText.setText(getString(R.string.winning_team_text, winningTeam));
    }

    @Override
    public void setLosingTeam(String losingTeam) {
        losingTeamText.setText(getString(R.string.losing_team_text, losingTeam));
    }
}
