package com.loanmanagementapp.ui.screen

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.loanmanagementapp.ui.screen.home.HomeScreen
import com.loanmanagementapp.ui.screen.login.LoginScreen

@Composable
fun LoanApp(sharedViewModel: UserInfoSharedViewModel = hiltViewModel()) {
    val username by sharedViewModel.username.collectAsState()
    var isLoggedIn by remember { mutableStateOf(username.isNotBlank()) }

    if (isLoggedIn) {
        HomeScreen()
    } else {
        LoginScreen(onLoginSuccess = { isLoggedIn = true })
    }
}
