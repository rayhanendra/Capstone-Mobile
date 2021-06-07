package com.example.capstonemobile.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstonemobile.data.source.Repository
import okhttp3.RequestBody

class LoginViewModel @ViewModelInject constructor(private val repository: Repository): ViewModel() {

    fun login(body: RequestBody) = repository.login(body).asLiveData()

}