package com.example.takealoan.loanscreen.di

import android.content.Context
import com.example.takealoan.loanscreen.data.datasource.LoanDataSourceImpl
import com.example.takealoan.loanscreen.data.repository.LoanRepositoryImpl
import com.example.takealoan.loanscreen.domain.CreateLoanUseCase
import com.example.takealoan.loanscreen.domain.GetConditionUseCase
import com.example.takealoan.loanscreen.presentation.interfaces.LoanPresenter
import com.example.takealoan.loanscreen.presentation.LoanPresenterImpl

object LoanPresenterFactory {
    fun create(mContext: Context): LoanPresenter {
        val loanDataSource = LoanDataSourceImpl()
        val loanRepository = LoanRepositoryImpl(loanDataSource)
        val getConditionUseCase = GetConditionUseCase(loanRepository)
        return LoanPresenterImpl(mContext, getConditionUseCase)
    }
}