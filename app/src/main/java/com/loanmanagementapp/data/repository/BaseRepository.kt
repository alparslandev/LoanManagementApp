package com.loanmanagementapp.data.repository

import com.loanmanagementapp.data.model.Loan

interface BaseRepository {
    suspend fun updateData(): List<Loan> // Generic yapılmalıdır.
}
