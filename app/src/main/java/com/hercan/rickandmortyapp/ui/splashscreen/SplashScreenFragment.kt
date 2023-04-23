package com.hercan.rickandmortyapp.ui.splashscreen

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hercan.rickandmortyapp.R
import com.hercan.rickandmortyapp.databinding.FragmentSplashScreenBinding
import com.hercan.rickandmortyapp.presentation.utils.Constants.LocalStorage.HAS_LAUNCHED_BEFORE
import com.hercan.rickandmortyapp.presentation.utils.Constants.LocalStorage.PREFS_FILENAME
import com.hercan.rickandmortyapp.presentation.utils.showError
import com.hercan.rickandmortyapp.presentation.viewbinding.viewBinding
import kotlin.system.exitProcess

class SplashScreenFragment : Fragment(R.layout.fragment_splash_screen) {

    private val binding by viewBinding(FragmentSplashScreenBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
    }

    private fun bindUI() {
        firstLoginControl()
        if (checkForInternet()) {
            navigateHome()
        } else {
            requireContext().showError(getString(R.string.error_internet_connection))
            quitApp()
        }
    }

    private fun quitApp() {
        Handler(Looper.getMainLooper()).postDelayed({
            requireActivity().finish()
            exitProcess(0)
        }, 3000)
    }

    private fun navigateHome() {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(SplashScreenFragmentDirections.navigateToHomeFragment())
        }, 3000)
    }

    private fun firstLoginControl() {
        val sharedPref: SharedPreferences = requireActivity().getSharedPreferences(
            PREFS_FILENAME,
            Context.MODE_PRIVATE
        )
        val hasLaunchedBefore: Boolean =
            sharedPref.getBoolean(HAS_LAUNCHED_BEFORE, false)
        if (hasLaunchedBefore) {
            binding.tvWelcome.text = getString(R.string.hello)
        } else {
            binding.tvWelcome.text = getString(R.string.welcome)
            sharedPref.edit().putBoolean(HAS_LAUNCHED_BEFORE, true).apply()
        }
    }

    private fun checkForInternet(): Boolean {

        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }

}