package com.loanmanagementapp.data.repository
import com.loanmanagementapp.data.model.Loan
import com.loanmanagementapp.data.strategy.LoanStrategySelector
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class LoanRepository @Inject constructor(
    private val loanService: LoanService,
    private val strategySelector: LoanStrategySelector
) {
    suspend fun updateLoans(): List<Loan> {
        val loans = loanService.loadLoans()

        for (loan in loans) {
            strategySelector.applyStrategies(loan)
        }

        loanService.saveLoans(loans)
        return loans
    }
}