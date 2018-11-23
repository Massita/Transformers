package com.massita.transformers.feature.battle;

import com.massita.transformers.api.model.Transformer;
import com.massita.transformers.util.Battle;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BattlePresenter implements BattleContract.Presenter {

    private BattleContract.View mView;
    private List<Transformer> mTransformerList;
    private Battle battle;
    private CompositeDisposable mCompositeDisposable;

    public BattlePresenter(BattleContract.View view, List<Transformer> transformers) {
        this.mView = view;
        this.mTransformerList = transformers;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void computeBattle() {
        mView.showLoading();
        battle = new Battle();

        Disposable disposable = battle.prepare(mTransformerList)
                .flatMap(Battle::fight)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(this::onLoadSucceed)
                .doOnError(this::onLoadError)
                .doFinally(this::onFinally)
                .subscribe();

        mCompositeDisposable.add(disposable);
    }

    private void onFinally() {
        mView.hideLoading();
    }

    private void onLoadError(Throwable throwable) {
        // ERROR
    }

    private void onLoadSucceed(Battle.Results results) {
        mView.showBattleWinnerFragment(results);
    }
}
