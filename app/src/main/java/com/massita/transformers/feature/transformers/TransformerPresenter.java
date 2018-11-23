package com.massita.transformers.feature.transformers;

import com.massita.transformers.api.model.Transformer;

public class TransformerPresenter implements TransformerContract.Presenter {

    private TransformerContract.View mView;

    public TransformerPresenter(TransformerContract.View view) {
        this.mView = view;
    }

    @Override
    public void start(Transformer transformer) {
        if(transformer == null) {
            mView.showNewTransformerFragment();
        } else {
            mView.showEditTransfomerFragment(transformer);
        }
    }

}
