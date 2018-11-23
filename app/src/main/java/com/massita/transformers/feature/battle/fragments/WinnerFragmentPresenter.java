package com.massita.transformers.feature.battle.fragments;

import com.massita.transformers.api.model.Transformer;
import com.massita.transformers.util.Battle;

public class WinnerFragmentPresenter implements WinnerFragmentContract.Presenter {

    private WinnerFragmentContract.View mView;
    private Battle.Results mResults;

    WinnerFragmentPresenter(WinnerFragmentContract.View view, Battle.Results results) {
        this.mView = view;
        this.mResults = results;
    }

    @Override
    public void start() {
        mView.setWinner(getWinnerName());
        mView.setNumberOfBattles(mResults.getNumberOfFights());

    }

    private String getWinnerName() {
        if(mResults.getAutobotScore() > mResults.getDecepticonScore()) {
            return Transformer.AUTOBOTS;
        }

        return Transformer.DECEPTICONS;
    }
}
