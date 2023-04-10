package com.hercan.rickandmortyapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hercan.rickandmortyapp.domain.repository.RickAndMortyRepository
import com.hercan.rickandmortyapp.domain.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: RickAndMortyRepository) :
    ViewModel() {

    fun getLocations(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getLocations(page)
                .onStart { }
                .onCompletion { }
                .collect {
                    if (it.status == Status.SUCCESS) {
                        Log.d("HOME_VIEW_MODEL", it.data?.results.toString())
                    } else {
                        Log.d("HOME_VIEW_MODEL", it.message ?: "")
                    }
                }
        }
    }
}