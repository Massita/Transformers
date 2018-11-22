package com.massita.transformers.api;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface TokenService {

    @GET("allspark")
    Single<String> getToken();

}
