package com.loanmanagementapp.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.lazy.items
import com.loanmanagementapp.ui.components.common.PrimaryButton
import com.loanmanagementapp.ui.components.loan.LoanCard
import com.loanmanagementapp.ui.screen.UserInfoSharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    sharedViewModel: UserInfoSharedViewModel = hiltViewModel(),
    onLogout: () -> Unit
) {
    val username by sharedViewModel.username.collectAsState()
    val loans by viewModel.loans.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadLoans()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Username: $username") },
                actions = {
                    PrimaryButton(
                        text = "Logout",
                        onClick = {
                            sharedViewModel.clearUser()
                            onLogout()
                        },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(loans) { loan ->
                LoanCard(loan = loan, viewModel = viewModel)
            }
        }
    }
}
