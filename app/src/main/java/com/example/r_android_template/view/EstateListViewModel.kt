package com.example.r_android_template.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.r_android_template.service.Estate

class EstateListViewModel(application: Application) : AndroidViewModel(application) {
    private val _pickedEstate = MutableLiveData<Estate?>(null)
    val pickedEstate: LiveData<Estate?>
        get() = _pickedEstate

    fun changePickedEstate(estate:Estate){
        _pickedEstate.value = estate
    }

}