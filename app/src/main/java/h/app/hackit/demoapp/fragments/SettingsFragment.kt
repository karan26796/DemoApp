package h.app.hackit.demoapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceFragment
import android.view.View
import h.app.hackit.demoapp.R

/**
 * Created by karan on 11/20/2017.
 */

class SettingsFragment : PreferenceFragment() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.app_preferences)
        sharedPreferences = activity.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {}

    companion object {

        fun newInstance(): SettingsFragment {

            val args = Bundle()
            val fragment = SettingsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
