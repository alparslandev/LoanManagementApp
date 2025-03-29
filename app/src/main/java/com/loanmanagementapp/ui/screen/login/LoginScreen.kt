package com.loanmanagementapp.ui.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loanmanagementapp.ui.components.common.CustomEditText
import com.loanmanagementapp.ui.components.common.PrimaryButton
import com.loanmanagementapp.ui.screen.UserInfoSharedViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
    sharedViewModel: UserInfoSharedViewModel = hiltViewModel()
) {
    val password by viewModel.password.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val username by sharedViewModel.username.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loginSuccess.collect {
            onLoginSuccess()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.errorMessage.collect { msg ->
            snackbarHostState.showSnackbar(msg)
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(padding),
            verticalArrangement = Arrangement.Center
        ) {
            CustomEditText(
                value = username,
                onValueChange = sharedViewModel::setUsername,
                label = "Username"
            )

            Spacer(modifier = Modifier.height(10.dp))

            CustomEditText(
                value = password,
                onValueChange = viewModel::onPasswordChange,
                label = "Password",
                isPassword = true
            )
            Spacer(modifier = Modifier.height(20.dp))
            PrimaryButton(
                text = "Login",
                onClick = viewModel::onLoginClick
            )
        }
    }
}