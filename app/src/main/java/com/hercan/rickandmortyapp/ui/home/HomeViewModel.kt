package com.hercan.rickandmortyapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.liveData
import com.hercan.rickandmortyapp.domain.repository.RickAndMortyRepository
import com.hercan.rickandmortyapp.domain.utils.Status
import com.hercan.rickandmortyapp.presentation.model.LocationUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: RickAndMortyRepository) :
    ViewModel() {

    fun getLocations(): LiveData<PagingData<LocationUIModel>> {
        return repository.getLocations().liveData
    }

    fun getResidents(ids: ArrayList<Int>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getResidents(ids)
                .onStart { }
                .onCompletion { }
                .collect {
                    if (it.status == Status.SUCCESS) {
                        Log.d("Hanife", it.data.toString())
                    } else {
                        Log.d("Hanife", "Error var")

                    }
                }
        }
    }
}