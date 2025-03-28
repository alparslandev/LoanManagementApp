package com.loanmanagementapp.data.strategy

import com.loanmanagementapp.data.model.Loan

interface LoanUpdateStrategy {
    fun shouldApply(loan: Loan): Boolean
    fun apply(loan: Loan)
}
