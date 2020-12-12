package com.example.takealoan.loanscreen.presentation.interfaces

interface LoanListPresenter {
    fun onDestroy()

    fun attachView(view: LoanListView)

    fun getLoanList()
}