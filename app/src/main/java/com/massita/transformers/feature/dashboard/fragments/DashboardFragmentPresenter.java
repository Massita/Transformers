package com.massita.transformers.feature.dashboard.fragments;

import com.massita.transformers.api.RestClient;
import com.massita.transformers.api.TransformersService;
import com.massita.transformers.api.model.Transformer;
import com.massita.transformers.api.model.Transformers;
import com.massita.transformers.util.SharedPreferencesRepository;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class DashboardFragmentPresenter implements DashboardFragmentContract.Presenter {

    private DashboardFragmentContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private TransformersService mService;
    private SharedPreferencesRepository mSharedPreferencesRepository;
    private String mToken;

    public DashboardFragmentPresenter(DashboardFragmentContract.View view, TransformersService service, SharedPreferencesRepository sharedPreferencesRepository) {
        this.mView = view;
        mCompositeDisposable = new CompositeDisposable();
        mService = service;
        mSharedPreferencesRepository = sharedPreferencesRepository;
    }

    @Override
    public void start() {
        mView.prepareRecyclerView();
    }


    @Override
    public void loadTransformers() {
        Disposable disposable = new RestClient().checkToken(mSharedPreferencesRepository)
                .flatMap((t) -> {
                    mSharedPreferencesRepository.setToken(t);
                    return mService.getTransformers("Bearer " + t);
                } )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(this::onLoadSucceed)
                .doOnError(this::onLoadError)
                .subscribe();

        mCompositeDisposable.add(disposable);
    }

    private void onLoadError(Throwable throwable) {
        //TODO
    }

    private void onLoadSucceed(Response<Transformers> listResponse) {
        if(listResponse.isSuccessful() && listResponse.body() != null) {
            mView.showList(listResponse.body().getTransformers());
        }
    }

    @Override
    public void onDestroy() {
        mView = null;
        mCompositeDisposable.dispose();
    }
}
