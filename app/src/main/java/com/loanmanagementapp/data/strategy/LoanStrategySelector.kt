package com.loanmanagementapp.data.strategy

import com.loanmanagementapp.data.model.Loan

class LoanStrategySelector(
    private val strategies: List<LoanUpdateStrategy>
) {
    fun applyStrategies(loan: Loan) {
        for (strategy in strategies) {
            if (strategy.shouldApply(loan)) {
                strategy.apply(loan)
            }
        }

        loan.dueIn -= 1
        loan.status = determineStatusAfterDue(loan)
    }

    private fun determineStatusAfterDue(loan: Loan): String {
        if (loan.dueIn >= 0) return loan.status
        return when {
            loan.status == "paid" -> "paid"
            loan.principalAmount > 0 -> "overdue"
            else -> "default"
        }
    }
}
