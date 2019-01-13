package h.app.hackit.demoapp.activities;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import h.app.hackit.demoapp.R;
import h.app.hackit.demoapp.utils.Constants;

public abstract class BaseActivity extends AppCompatActivity {

    String gridSize, animationDuration, gridSpacing;
    SharedPreferences sharedPreferences;
    SharedPreferences.OnSharedPreferenceChangeListener prefListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadPreferences();
        sharedPreferencesListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(prefListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(prefListener);
    }

    protected void loadPreferences() {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        gridSize = sharedPreferences.getString(Constants.INSTANCE.getGRID_KEY(), getString(R.string.grid_size_default));
        animationDuration = sharedPreferences.getString(Constants.INSTANCE.getANIMATION_KEY(), getString(R.string.slow_animation));
        gridSpacing = sharedPreferences.getString(Constants.INSTANCE.getSPACING_KEY(), getString(R.string.spacing_small));
    }

    protected int getGridSize() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return Integer.valueOf(gridSize);
        else return Integer.valueOf(gridSize + 1);
    }

    protected int getAnimationSpeed() {
        if (animationDuration.equals("Slow"))
            return 300;
        if (animationDuration.equals("Medium"))
            return 100;
        if (animationDuration.equals("Fast"))
            return 50;
        else return 0;
    }

    protected int getGridSpacing() {
        if (gridSpacing.equals("Small"))
            return 10;
        if (gridSpacing.equals("Medium"))
            return 15;
        if (gridSpacing.equals("Large"))
            return 20;
        else return 0;
    }

    protected void sharedPreferencesListener() {

        prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                recreate();
            }
        };
    }
}
