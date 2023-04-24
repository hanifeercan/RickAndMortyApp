package com.hercan.rickandmortyapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.liveData
import com.hercan.rickandmortyapp.domain.repository.RickAndMortyRepository
import com.hercan.rickandmortyapp.domain.utils.Status
import com.hercan.rickandmortyapp.presentation.model.LocationUIModel
import com.hercan.rickandmortyapp.presentation.model.ResidentUIModel
import com.hercan.rickandmortyapp.presentation.model.toResidentUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: RickAndMortyRepository) :
    ViewModel() {

    private val _residentList: MutableLiveData<List<ResidentUIModel?>?> = MutableLiveData()
    val residentList: LiveData<List<ResidentUIModel?>?> = _residentList

    private val _isOnLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isOnLoading: LiveData<Boolean> = _isOnLoading

    private val _isOnError: MutableLiveData<String?> = MutableLiveData(null)
    val isOnError: LiveData<String?> = _isOnError

    var isFirstCall = true

    fun getLocations(): LiveData<PagingData<LocationUIModel>> {
        isFirstCall = false
        return repository.getLocations().liveData
    }

    fun getResidents(ids: ArrayList<Int>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getResidents(ids)
                .onStart {
                    _isOnLoading.postValue(true)
                }
                .onCompletion {
                    _isOnLoading.postValue(false)
                }
                .collect {
                    if (it.status == Status.SUCCESS) {
                        it.data?.toResidentUIModel()?.let {
                            _residentList.postValue(it)
                        }
                    } else {
                        _isOnError.postValue(it.message)
                    }
                }
        }
    }
}