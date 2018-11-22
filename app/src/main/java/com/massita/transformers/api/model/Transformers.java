package com.massita.transformers.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Transformers {
    @SerializedName("transformers")
    private List<Transformer> transformers;

    public void setTransformers(List<Transformer> transformers) {
        this.transformers = transformers;
    }

    public List<Transformer> getTransformers() {
        return transformers;
    }
}
