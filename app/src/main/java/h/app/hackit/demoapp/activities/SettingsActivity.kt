package h.app.hackit.demoapp.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import h.app.hackit.demoapp.R
import h.app.hackit.demoapp.fragments.SettingsFragment

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        inflateSettingsFragment()
    }

    private fun inflateSettingsFragment() {
        val mFragmentManager = fragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mPrefsFragment = SettingsFragment.newInstance()
        mFragmentTransaction.replace(R.id.settings_activity_frame_layout, mPrefsFragment)
        mFragmentTransaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }
}
