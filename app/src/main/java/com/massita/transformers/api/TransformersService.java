package com.massita.transformers.api;

import com.massita.transformers.api.model.Transformer;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TransformersService {

    @POST("transformers")
    Single<Response<Transformer>> addTransformer(@Header("Authorization") String authorization, @Body Transformer transformer);

    @GET("transformers")
    Single<Response<List<Transformer>>> getTransformers(@Header("Authorization") String authorization);

    @PUT("transformers")
    Single<Response<Transformer>> updateTransformer(@Header("Authorization") String authorization, @Body Transformer transformer);

    @DELETE("transformers/{id}")
    Single<Response<Void>> deleteTransformer(@Header("Authorization") String authorization, @Path("id") int transformerId);

}
