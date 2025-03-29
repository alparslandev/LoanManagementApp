package com.loanmanagementapp.ui.components.loan

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.loanmanagementapp.data.model.Loan
import com.loanmanagementapp.ui.screen.home.HomeViewModel
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment

@Composable
fun LoanDetailsSection(loan: Loan, viewModel: HomeViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text("Status: ${loan.status}")
            Text("Principal: \$${loan.principalAmount}")
            Text("Monthly Payment: \$${viewModel.calculateMonthlyPayment(loan)}")
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text("Due In: ${loan.dueIn} months")
            Text("Total Interest: \$${viewModel.calculateTotalInterest(loan)}")
            Text("Interest Rate: ${loan.interestRate}%")
        }
    }
}