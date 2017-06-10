package com.applications.fluffy.piratingupdates.Objects;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

import com.applications.fluffy.piratingupdates.R;

import java.util.List;

public class Preferences extends PreferenceActivity {

    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.prefheaders, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return PrefFragment.class.getName().equals(fragmentName);
    }

    public static class PrefFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }


}
