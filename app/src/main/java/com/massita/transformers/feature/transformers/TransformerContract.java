package com.massita.transformers.feature.transformers;

import com.massita.transformers.api.model.Transformer;

public interface TransformerContract {

    interface View {

        void showNewTransformerFragment();

        void showEditTransfomerFragment(Transformer transformer);

    }

    interface Presenter {

        void start(Transformer transformer);

    }

}
