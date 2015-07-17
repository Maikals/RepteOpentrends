package com.example.mcastanys.repte.Activities;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mcastanys.repte.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PreferenceFragment extends Fragment {

    public PreferenceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_preference, container, false);
    }
}
