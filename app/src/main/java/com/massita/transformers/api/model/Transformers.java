package com.massita.transformers.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Transformers {
    @SerializedName("transformers")
    private ArrayList<Transformer> transformers;

    public void setTransformers(ArrayList<Transformer> transformers) {
        this.transformers = transformers;
    }

    public ArrayList<Transformer> getTransformers() {
        return transformers;
    }
}
