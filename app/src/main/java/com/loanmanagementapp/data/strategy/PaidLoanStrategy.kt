package com.loanmanagementapp.data.strategy

import com.loanmanagementapp.data.model.Loan

/**
 * Borcu 1000'den az olan krediyi ödenmiş yapıyor.
 */
class PaidLoanStrategy : LoanUpdateStrategy {
    override fun shouldApply(loan: Loan): Boolean {
        return loan.principalAmount < 1000
    }

    override fun apply(loan: Loan) {
        loan.status = "paid"
    }
}
