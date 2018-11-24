package com.massita.transformers.feature.battle.fragments;

public interface DrawFragmentContract {

    interface View {

        void setNumberOfBattles(int numberOfBattles);

    }

    interface Presenter {

        void start();

    }
}
