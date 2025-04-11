package com.loanmanagementapp.data.repository

import com.loanmanagementapp.data.model.Loan

interface BaseRepository {
    suspend fun updateLoans(): List<Loan>
}
