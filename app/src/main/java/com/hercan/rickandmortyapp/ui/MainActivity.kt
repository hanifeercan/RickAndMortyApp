package com.hercan.rickandmortyapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hercan.rickandmortyapp.databinding.ActivityMainBinding
import com.hercan.rickandmortyapp.presentation.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}