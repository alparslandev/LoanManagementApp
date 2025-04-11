package com.loanmanagementapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loanmanagementapp.data.model.Loan
import com.loanmanagementapp.data.repository.BaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject
import kotlin.math.pow

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loanRepository: BaseRepository
) : ViewModel() {

    private val _loans = MutableStateFlow<List<Loan>>(emptyList())
    val loans: StateFlow<List<Loan>> = _loans.asStateFlow()

    fun loadLoans() {
        viewModelScope.launch {
            val updatedLoans = loanRepository.updateData()
            _loans.value = updatedLoans
        }
    }

    fun calculateTotalInterest(loan: Loan): String {
        val interest = loan.principalAmount * loan.interestRate / 100
        return String.format(Locale.getDefault(),"%.2f", interest)
    }

    fun calculateMonthlyPayment(loan: Loan): String {
        val monthlyRate = loan.interestRate / 12 / 100
        val result = if (loan.dueIn <= 0) 0.0 else {
            loan.principalAmount * monthlyRate /
                    (1 - (1 + monthlyRate).pow(-loan.dueIn))
        }
        return String.format(Locale.getDefault(), "%.2f", result)
    }
}