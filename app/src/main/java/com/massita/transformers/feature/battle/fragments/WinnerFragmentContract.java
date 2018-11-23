package com.massita.transformers.feature.battle.fragments;

public interface WinnerFragmentContract {

    interface View {

        void setWinner(String winner);

        void setNumberOfBattles(int numberOfBattles);

        void setWinningTeam(String winningTeam);

        void setLosingTeam(String losingTeam);

    }

    interface Presenter {

        void start();

    }

}
