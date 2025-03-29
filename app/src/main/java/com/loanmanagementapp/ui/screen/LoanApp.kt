package com.loanmanagementapp.ui.screen

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.loanmanagementapp.ui.screen.home.HomeScreen
import com.loanmanagementapp.ui.screen.login.LoginScreen

@Composable
fun LoanApp() {
    var isLoggedIn by remember { mutableStateOf(false) }
    val sharedViewModel: UserInfoSharedViewModel = hiltViewModel()

    if (isLoggedIn) {
        HomeScreen(sharedViewModel = sharedViewModel)
    } else {
        LoginScreen(onLoginSuccess = {
            isLoggedIn = true
        }, sharedViewModel = sharedViewModel)
    }
}
