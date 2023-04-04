package com.hercan.rickandmortyapp.ui.splashscreen

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hercan.rickandmortyapp.R
import com.hercan.rickandmortyapp.databinding.FragmentSplashScreenBinding
import com.hercan.rickandmortyapp.presentation.viewbinding.viewBinding

class SplashScreenFragment : Fragment(R.layout.fragment_splash_screen) {

    private val binding by viewBinding(FragmentSplashScreenBinding::bind)
    private val viewModel: SplashScreenViewModel by viewModels()

}