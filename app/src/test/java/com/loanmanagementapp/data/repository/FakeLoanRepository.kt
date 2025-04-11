package com.loanmanagementapp.data.repository

import com.loanmanagementapp.data.model.Loan

class FakeLoanRepository : BaseRepository {
    override suspend fun updateLoans(): List<Loan> {
        return listOf(
            Loan("Fake Loan", 5000.0, 5.0, "active", 12),
            Loan("Overdue Loan", 10000.0, 7.5, "overdue", -5)
        )
    }
}
