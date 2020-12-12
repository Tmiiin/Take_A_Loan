package com.example.takealoan.loanscreen.di

import android.content.Context
import com.example.takealoan.loanscreen.data.datasource.LoanDataSourceImpl
import com.example.takealoan.loanscreen.data.repository.LoanRepositoryImpl
import com.example.takealoan.loanscreen.domain.CreateLoanUseCase
import com.example.takealoan.loanscreen.presentation.GetLoanPresenterImpl
import com.example.takealoan.loanscreen.presentation.interfaces.GetLoanPresenter

object GetLoanPresenterFactory {
    fun create(mContext: Context): GetLoanPresenter {
        val loanDataSource = LoanDataSourceImpl()
        val loanRepository = LoanRepositoryImpl(loanDataSource)
        val createLoanUseCase = CreateLoanUseCase(loanRepository)
        return GetLoanPresenterImpl(mContext, createLoanUseCase)
    }
}