package com.loanmanagementapp.di

import com.loanmanagementapp.data.repository.LoanRepository
import com.loanmanagementapp.data.repository.LoanService
import com.loanmanagementapp.data.repository.MockLoanService
import com.loanmanagementapp.data.strategy.DefaultLoanStrategy
import com.loanmanagementapp.data.strategy.DueDateStrategy
import com.loanmanagementapp.data.strategy.LoanStrategySelector
import com.loanmanagementapp.data.strategy.LoanUpdateStrategy
import com.loanmanagementapp.data.strategy.PaidLoanStrategy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLoanService(): LoanService = MockLoanService()

    @Provides
    @IntoSet
    fun provideDueDateStrategy(): LoanUpdateStrategy = DueDateStrategy()

    @Provides
    @IntoSet
    fun providePaidLoanStrategy(): LoanUpdateStrategy = PaidLoanStrategy()

    @Provides
    @IntoSet
    fun provideDefaultLoanStrategy(): LoanUpdateStrategy = DefaultLoanStrategy()

    @Provides
    @Singleton
    fun provideLoanStrategySelector(
        strategies: Set<@JvmSuppressWildcards LoanUpdateStrategy>
    ): LoanStrategySelector = LoanStrategySelector(strategies.toList())

    @Provides
    @Singleton
    fun provideLoanRepository(
        loanService: LoanService,
        strategySelector: LoanStrategySelector
    ): LoanRepository = LoanRepository(loanService, strategySelector)
}
