package com.loanmanagementapp.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loanmanagementapp.data.model.Loan
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val loans by viewModel.loans.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadLoans()
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Loan Management") }) }
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

@Composable
fun LoanCard(loan: Loan, viewModel: HomeViewModel) {
    val backgroundColor = when (loan.status.lowercase()) {
        "overdue" -> Color(0xFFFFCDD2) // Red-ish
        "default" -> Color(0xFFFFF9C4) // Yellow-ish
        "paid" -> Color(0xFFC8E6C9) // Green-ish
        else -> Color(0xFFFFF59D) // Active = light yellow
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Name: ${loan.name}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Status: ${loan.status}")
                    Text("Principal: \$${loan.principalAmount}")
                    Text("Monthly Payment: \$${viewModel.calculateMonthlyPayment(loan)}")
                }
                Column(verticalArrangement = Arrangement.spacedBy(4.dp), horizontalAlignment = Alignment.End) {
                    Text("Due In: ${loan.dueIn} months")
                    Text("Total Interest: \$${viewModel.calculateTotalInterest(loan)}")
                    Text("Interest Rate: ${loan.interestRate}%")
                }
            }
        }
    }
}