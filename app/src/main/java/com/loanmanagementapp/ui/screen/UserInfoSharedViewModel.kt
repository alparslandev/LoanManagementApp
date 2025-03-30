package com.loanmanagementapp.ui.screen

import androidx.lifecycle.ViewModel
import com.loanmanagementapp.data.preferences.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class UserInfoSharedViewModel @Inject constructor(
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _username = MutableStateFlow(userPreferences.getUsername())
    val username: StateFlow<String> = _username

    fun setUsername(name: String) {
        _username.value = name
        userPreferences.saveUsername(name)
    }

    fun clearUser() {
        _username.value = ""
        userPreferences.clear()
    }
}
