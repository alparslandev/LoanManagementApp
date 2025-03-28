package com.loanmanagementapp.data.repository

import android.content.Context
import com.loanmanagementapp.data.model.Loan

interface LoanService {
    suspend fun loadLoans(context: Context): List<Loan>
    suspend fun saveLoans(loans: List<Loan>)
}