package com.loanmanagementapp.ui.screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class UserInfoSharedViewModel @Inject constructor() : ViewModel() {
    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    fun setUsername(name: String) {
        _username.value = name
    }
}
