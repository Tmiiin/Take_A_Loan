package com.example.takealoan.loanscreen.di

import android.content.Context
import com.example.takealoan.loanscreen.data.datasource.LoanDataSourceImpl
import com.example.takealoan.loanscreen.data.repository.LoanRepositoryImpl
import com.example.takealoan.loanscreen.domain.GetLoanListUseCase
import com.example.takealoan.loanscreen.presentation.LoanListPresenterImpl
import com.example.takealoan.loanscreen.presentation.interfaces.LoanListPresenter

object LoanListPresenterFactory {
    fun create(mContext: Context): LoanListPresenter {
        val loanDataSource = LoanDataSourceImpl()
        val loanRepository = LoanRepositoryImpl(loanDataSource)
        val getLoanListUseCase = GetLoanListUseCase(loanRepository)
        return LoanListPresenterImpl(mContext, getLoanListUseCase)
    }
}