package com.loanmanagementapp.ui.components.loan

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.loanmanagementapp.data.model.Loan
import com.loanmanagementapp.ui.screen.home.HomeViewModel
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.CardDefaults

@Composable
fun LoanCard(loan: Loan, viewModel: HomeViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = loanStatusColor(loan.status)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            LoanHeader(loan.name)
            Spacer(modifier = Modifier.height(8.dp))
            LoanDetailsSection(loan, viewModel)
        }
    }
}

fun loanStatusColor(status: String): Color {
    return when (status.lowercase()) {
        "overdue" -> Color(0xFFFFCDD2)
        "default" -> Color(0xFFFFF9C4)
        "paid" -> Color(0xFFC8E6C9)
        else -> Color(0xFFFFF59D)
    }
}