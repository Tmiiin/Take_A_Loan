package com.example.takealoan.loanscreen.di

import android.content.Context
import com.example.takealoan.loanscreen.data.datasource.LoanDataSourceImpl
import com.example.takealoan.loanscreen.data.repository.LoanRepositoryImpl
import com.example.takealoan.loanscreen.domain.GetLoanDetailsUseCase
import com.example.takealoan.loanscreen.presentation.LoanDetailsPresenterImpl
import com.example.takealoan.loanscreen.presentation.interfaces.LoanDetailsPresenter

object LoanDetailsFactory {
    fun create(mContext: Context): LoanDetailsPresenter {
        val loanDataSource = LoanDataSourceImpl()
        val loanRepository = LoanRepositoryImpl(loanDataSource)
        val detailsUseCase = GetLoanDetailsUseCase(loanRepository)
        return LoanDetailsPresenterImpl(mContext, detailsUseCase)
    }
}