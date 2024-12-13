package com.example.roomcronoapp.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomcronoapp.model.Cronos
import com.example.roomcronoapp.repository.CronosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CronosViewModel @Inject constructor(private val repository: CronosRepository) : ViewModel() {

    private val _cronosList = MutableStateFlow<List<Cronos>>(emptyList())
    val cronosList = _cronosList.asStateFlow()

    init {
        getAllCronos()
    }

    fun getAllCronos(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllCronos().collect{item ->
                if(item.isEmpty()){
                    _cronosList.value = emptyList()
                }else{
                    _cronosList.value = item
                }

            }
        }
    }

    fun addCrono(crono : Cronos)  = viewModelScope.launch { repository.addCronos(crono) }
    fun updateCrono(crono : Cronos)  = viewModelScope.launch { repository.updateCronos(crono) }
    fun deleteCrono(crono : Cronos)  = viewModelScope.launch { repository.deleteCronos(crono) }

}