package com.example.takealoan.loanscreen.presentation.interfaces

import com.example.takealoan.loanscreen.data.model.PostLoanModel

interface LoanListView {

    fun onDestroy()

    fun setListOfDetails(list: List<PostLoanModel>)
}