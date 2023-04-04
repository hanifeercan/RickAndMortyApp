package com.hercan.rickandmortyapp.ui.splashscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.hercan.rickandmortyapp.R
import com.hercan.rickandmortyapp.databinding.FragmentSplashScreenBinding
import com.hercan.rickandmortyapp.presentation.viewbinding.viewBinding

class SplashScreenFragment : Fragment(R.layout.fragment_splash_screen) {

    private val binding by viewBinding(FragmentSplashScreenBinding::bind)

}