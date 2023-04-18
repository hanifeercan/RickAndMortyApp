package com.hercan.rickandmortyapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.liveData
import com.hercan.rickandmortyapp.domain.repository.RickAndMortyRepository
import com.hercan.rickandmortyapp.presentation.model.LocationUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: RickAndMortyRepository) :
    ViewModel() {

    fun getLocations(): LiveData<PagingData<LocationUIModel>> {
        return repository.getLocations().liveData
    }

}