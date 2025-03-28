package com.loanmanagementapp.data.repository

import com.loanmanagementapp.data.model.Loan

interface LoanService {
    suspend fun loadLoans(): List<Loan>
    suspend fun saveLoans(loans: List<Loan>)
}