package com.massita.transformers.feature.transformers;

public class TransformerPresenter implements TransformerContract.Presenter {

    private TransformerContract.View mView;

    public TransformerPresenter(TransformerContract.View view) {
        this.mView = view;
    }

    @Override
    public void start() {
        mView.showNewTransformerFragment();
    }

}
