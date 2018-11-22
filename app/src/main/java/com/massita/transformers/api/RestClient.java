package com.massita.transformers.api;

import com.massita.transformers.util.SharedPreferencesRepository;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RestClient {

    private static final String BASE_URL = "https://transformers-api.firebaseapp.com";

    public static Retrofit newJsonClient() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit newStringClient() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    public static TransformersService getTransformersService() {
        return newJsonClient().create(TransformersService.class);
    }

    public static TokenService getTokenService() {
        return newStringClient().create(TokenService.class);
    }

    public synchronized Single<String> checkToken(SharedPreferencesRepository sharedPreferencesRepository) {
        String token = sharedPreferencesRepository.getToken();

        if(token == null) {
            return getTokenService().getToken();
        }

        return Single.just(token);
    }

}
