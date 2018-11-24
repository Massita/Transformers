package com.massita.transformers.feature.battle.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.massita.transformers.R;

public class AllDestroyedFragment extends Fragment {

    public static AllDestroyedFragment newInstance() {
        return new AllDestroyedFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_destroyed, container, false);
    }

}
