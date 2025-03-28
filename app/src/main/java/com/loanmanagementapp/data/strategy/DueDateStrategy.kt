package com.loanmanagementapp.data.strategy

import com.loanmanagementapp.data.model.Loan

/**
 * Hennüz vadesi dolmamış ve ödenmemiş kredilere faiz bindiriyor.
 */

class DueDateStrategy : LoanUpdateStrategy {
    override fun shouldApply(loan: Loan): Boolean {
        return loan.status != "paid" && loan.status != "default" && loan.dueIn > 0
    }

    override fun apply(loan: Loan) {
        loan.interestRate += 0.5
    }
}
