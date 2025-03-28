package com.loanmanagementapp.data.strategy

import com.loanmanagementapp.data.model.Loan

/**
 * Gecikmiş ve borcu 5000'den büyük olan krediyi default yapıyor
 */
class DefaultLoanStrategy : LoanUpdateStrategy {
    override fun shouldApply(loan: Loan): Boolean {
        return loan.status == "overdue" && loan.principalAmount > 5000
    }

    override fun apply(loan: Loan) {
        loan.status = "default"
    }
}
