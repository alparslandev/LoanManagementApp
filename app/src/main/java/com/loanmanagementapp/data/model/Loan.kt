package com.loanmanagementapp.data.model

data class Loan(
    val name: String,
    var principalAmount: Double,
    var interestRate: Double,
    var status: String,
    var dueIn: Int
)