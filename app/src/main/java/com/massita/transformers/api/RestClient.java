package com.massita.transformers.api;

import retrofit2.Retrofit;

public class RestClient {

    private static final String BASE_URL = "https://transformers-api.firebaseapp.com";

    private Retrofit mRetrofitClient;

    public Retrofit getInstance() {
        if(mRetrofitClient == null) {
            mRetrofitClient = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .build();
        }

        return mRetrofitClient;
    }

}
