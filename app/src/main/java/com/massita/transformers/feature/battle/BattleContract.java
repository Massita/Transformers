package com.massita.transformers.feature.battle;

import com.massita.transformers.util.Battle;

public interface BattleContract {

    interface View {

        void showLoading();

        void hideLoading();

        void showBattleDrawFragment(Battle.Results results);

        void showBattleWinnerFragment(Battle.Results results);

        void showAllDestroyedFragment();

    }

    interface Presenter {

        void computeBattle();

    }

}
