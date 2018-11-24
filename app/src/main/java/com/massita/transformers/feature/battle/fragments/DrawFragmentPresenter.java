package com.massita.transformers.feature.battle.fragments;

import com.massita.transformers.util.Battle;

public class DrawFragmentPresenter implements DrawFragmentContract.Presenter {

    private DrawFragmentContract.View mView;
    private Battle.Results mResults;

    public DrawFragmentPresenter(DrawFragmentContract.View view, Battle.Results results) {
        this.mView = view;
        this.mResults = results;
    }

    @Override
    public void start() {
        mView.setNumberOfBattles(mResults.getNumberOfFights());
    }

}
