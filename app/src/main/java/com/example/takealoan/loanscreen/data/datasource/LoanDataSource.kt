package com.example.takealoan.loanscreen.data.datasource

import com.example.takealoan.loanscreen.data.model.ConditionsModel
import com.example.takealoan.loanscreen.data.model.LoanModel
import com.example.takealoan.loanscreen.data.model.PostLoanModel
import io.reactivex.Single

interface LoanDataSource {

    fun createLoan(token: String, loan: LoanModel): Single<PostLoanModel>

    fun getLoanData(token: String, loanId: Int): Single<PostLoanModel>

    fun getAllLoans(token: String): Single<List<PostLoanModel>>

    fun getLoansConditions(token: String): Single<ConditionsModel>
}