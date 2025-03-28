package com.loanmanagementapp.ui.screen

import androidx.compose.runtime.*
import com.loanmanagementapp.ui.screen.home.HomeScreen
import com.loanmanagementapp.ui.screen.login.LoginScreen

@Composable
fun LoanApp() {
    var isLoggedIn by remember { mutableStateOf(false) }

    if (isLoggedIn) {
        HomeScreen()
    } else {
        LoginScreen(onLoginSuccess = { isLoggedIn = true })
    }
}
