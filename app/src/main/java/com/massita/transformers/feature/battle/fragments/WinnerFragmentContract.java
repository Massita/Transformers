package com.massita.transformers.feature.battle.fragments;

import com.massita.transformers.api.model.Transformer;

import java.util.List;

public interface WinnerFragmentContract {

    interface View {

        void setWinner(String winner);

        void setNumberOfBattles(int numberOfBattles);

        void setWinningTeam(List<Transformer> winningTeam);

        void setLosingTeam(List<Transformer> losingTeam);

    }

    interface Presenter {

        void start();

    }

}
