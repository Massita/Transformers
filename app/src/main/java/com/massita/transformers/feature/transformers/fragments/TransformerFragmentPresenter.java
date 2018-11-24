package com.massita.transformers.feature.transformers.fragments;

import com.massita.transformers.api.RestClient;
import com.massita.transformers.api.TransformersService;
import com.massita.transformers.api.model.Transformer;
import com.massita.transformers.util.SharedPreferencesRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class TransformerFragmentPresenter implements TransformerFragmentContract.Presenter {

    private TransformerFragmentContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private TransformersService mService;
    private SharedPreferencesRepository mSharedPreferencesRepository;
    private String mToken;
    private Transformer mTransformer;

    public TransformerFragmentPresenter(TransformerFragmentContract.View view, TransformersService service, SharedPreferencesRepository sharedPreferencesRepository, Transformer transformer) {
        this.mView = view;
        mCompositeDisposable = new CompositeDisposable();
        mService = service;
        mSharedPreferencesRepository = sharedPreferencesRepository;
        mTransformer = transformer;
    }
    
    @Override
    public void start() {
        hideLoading();
        setupValues();
        setupListeners();
    }
    
    private void setupListeners() {
        mView.setupTeamListener();
        mView.setupCourageListener();
        mView.setupEnduranceListener();
        mView.setupFirepowerListener();
        mView.setupIntelligenceListener();
        mView.setupNameListener();
        mView.setupRankListener();
        mView.setupSkillListener();
        mView.setupSpeedListener();
        mView.setupStrengthListener();
    }
    
    private void setupValues() {
        mView.setName(mTransformer.getName());
        // Team
        mView.setTeam(mTransformer.isAutobots());
        mView.setTeamText(mTransformer.isAutobots());
        // Strength
        mView.setStrengthText(mTransformer.getStrength());
        mView.setStrengthValue(mTransformer.getStrength()-1);
        // Intelligence
        mView.setIntelligenceText(mTransformer.getIntelligence());
        mView.setIntelligenceValue(mTransformer.getIntelligence()-1);
        // Courage
        mView.setCourageText(mTransformer.getCourage());
        mView.setCourageValue(mTransformer.getCourage()-1);
        // Endurance
        mView.setEnduranceText(mTransformer.getEndurance());
        mView.setEnduranceValue(mTransformer.getEndurance()-1);
        // Firepower
        mView.setFirepowerText(mTransformer.getFirepower());
        mView.setFirepowerValue(mTransformer.getFirepower()-1);
        // Rank
        mView.setRankText(mTransformer.getRank());
        mView.setRankValue(mTransformer.getRank()-1);
        // Skill
        mView.setSkillText(mTransformer.getSkill());
        mView.setSkillValue(mTransformer.getSkill()-1);
        // Speed
        mView.setSpeedText(mTransformer.getSpeed());
        mView.setSpeedValue(mTransformer.getSpeed()-1);
    }

    @Override
    public void saveTransformer() {
        if(mTransformer.getId() == null) {
            saveNewTransformer();
        } else {
            saveEditedTransformer();
        }
    }

    private void saveNewTransformer() {
        mView.showLoading();
        Disposable disposable = new RestClient().checkToken(mSharedPreferencesRepository)
                .flatMap((t) -> {
                    mSharedPreferencesRepository.setToken(t);
                    return mService.addTransformer("Bearer " + t, mTransformer);
                } )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(this::onNewSucceed)
                .doOnError(this::onNewError)
                .doFinally(this::hideLoading)
                .subscribe();

        mCompositeDisposable.add(disposable);
    }

    private void saveEditedTransformer() {
        mView.showLoading();
        Disposable disposable = new RestClient().checkToken(mSharedPreferencesRepository)
                .flatMap((t) -> {
                    mSharedPreferencesRepository.setToken(t);
                    return mService.updateTransformer("Bearer " + t, mTransformer);
                } )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(this::onEditSucceed)
                .doOnError(this::onEditError)
                .doFinally(this::hideLoading)
                .subscribe();

        mCompositeDisposable.add(disposable);
    }

    private void onEditError(Throwable throwable) {

    }

    private void onEditSucceed(Response<Transformer> transformerResponse) {
        mView.finishWithSuccess(transformerResponse.body(), TransformerFragment.ACTION_EDIT);
    }

    @Override
    public void deleteTransformer() {
        mView.showLoading();
        if(mTransformer.getId() != null) {
            Disposable disposable = new RestClient().checkToken(mSharedPreferencesRepository)
                    .flatMap((t) -> {
                        mSharedPreferencesRepository.setToken(t);
                        return mService.deleteTransformer("Bearer " + t, mTransformer.getId());
                    } )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSuccess(this::onDeleteSucceed)
                    .doOnError(this::onDeleteError)
                    .doFinally(this::hideLoading)
                    .subscribe();

            mCompositeDisposable.add(disposable);
        } else {
            mView.hideLoading();
            mView.finishCanceled();
        }
    }

    private void hideLoading() {
        mView.hideLoading();
    }

    private void onDeleteError(Throwable throwable) {
        // TODO
    }

    private void onDeleteSucceed(Response<Void> voidResponse) {
        mView.finishWithSuccess(mTransformer, TransformerFragment.ACTION_DELETE);
    }

    private void onNewError(Throwable throwable) {
        // TODO
    }

    private void onNewSucceed(Response<Transformer> transformerResponse) {
        mView.finishWithSuccess(transformerResponse.body(), TransformerFragment.ACTION_NEW);
    }

    @Override
    public void updateName(String name) {
        mTransformer.setName(name);
    }

    @Override
    public void updateTeam(boolean isAutobots) {
        mTransformer.setIsAutobots(isAutobots);
        mView.setTeamText(isAutobots);
    }

    @Override
    public void updateStrength(int value) {
        mTransformer.setStrength(value+1);
        mView.setStrengthText(mTransformer.getStrength());
    }

    @Override
    public void updateIntelligence(int value) {
        mTransformer.setIntelligence(value+1);
        mView.setIntelligenceText(mTransformer.getIntelligence());
    }

    @Override
    public void updateSpeed(int value) {
        mTransformer.setSpeed(value+1);
        mView.setSpeedText(mTransformer.getSpeed());
    }

    @Override
    public void updateEndurance(int value) {
        mTransformer.setEndurance(value+1);
        mView.setEnduranceText(mTransformer.getEndurance());
    }

    @Override
    public void updateRank(int value) {
        mTransformer.setRank(value+1);
        mView.setRankText(mTransformer.getRank());
    }

    @Override
    public void updateCourage(int value) {
        mTransformer.setCourage(value+1);
        mView.setCourageText(mTransformer.getCourage());
    }

    @Override
    public void updateFirepower(int value) {
        mTransformer.setFirepower(value+1);
        mView.setFirepowerText(mTransformer.getFirepower());
    }

    @Override
    public void updateSkill(int value) {
        mTransformer.setSpeed(value+1);
        mView.setSkillText(mTransformer.getSkill());
    }
}
