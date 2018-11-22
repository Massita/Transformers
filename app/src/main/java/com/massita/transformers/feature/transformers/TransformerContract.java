package com.massita.transformers.feature.transformers;

public interface TransformerContract {

    interface View {

        void showNewTransformerFragment();

    }

    interface Presenter {

        void start();

    }

}
