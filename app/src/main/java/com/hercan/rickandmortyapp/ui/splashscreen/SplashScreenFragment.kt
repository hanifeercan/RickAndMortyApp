package com.hercan.rickandmortyapp.ui.splashscreen

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.hercan.rickandmortyapp.R
import com.hercan.rickandmortyapp.databinding.FragmentSplashScreenBinding
import com.hercan.rickandmortyapp.presentation.utils.Constants
import com.hercan.rickandmortyapp.presentation.viewbinding.viewBinding

class SplashScreenFragment : Fragment(R.layout.fragment_splash_screen) {

    private val binding by viewBinding(FragmentSplashScreenBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstLoginControl()
    }

    private fun firstLoginControl() {
        val sharedPref: SharedPreferences = requireActivity().getSharedPreferences(
            Constants.LocalStorage.PREFS_FILENAME,
            Context.MODE_PRIVATE
        )
        val hasLaunchedBefore: Boolean =
            sharedPref.getBoolean(Constants.LocalStorage.HAS_LAUNCHED_BEFORE, false)
        if (hasLaunchedBefore) {
            binding.txtWelcome.text = getString(R.string.hello)
        } else {
            binding.txtWelcome.text = getString(R.string.welcome)
            sharedPref.edit().putBoolean(Constants.LocalStorage.HAS_LAUNCHED_BEFORE, true).apply()
        }
    }

}