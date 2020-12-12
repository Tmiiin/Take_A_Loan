package com.example.takealoan.loanscreen.presentation.interfaces

import com.example.takealoan.loanscreen.data.model.LoanModel

interface LoanPresenter {
    fun onDestroy()

    fun attachView(view: LoanView)

    fun getLoanCondition()
}