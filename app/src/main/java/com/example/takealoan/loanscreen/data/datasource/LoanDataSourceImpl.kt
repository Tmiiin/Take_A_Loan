package com.example.takealoan.loanscreen.data.datasource

import com.example.takealoan.loanscreen.data.model.LoanModel
import com.example.takealoan.loanscreen.data.model.PostLoanModel
import io.reactivex.Single

class LoanDataSourceImpl: LoanDataSource {
    override fun createLoan(token: String, loan: LoanModel): Single<PostLoanModel> {
        TODO("Not yet implemented")
    }

    override fun getLoanData(token: String, loanId: Int): Single<PostLoanModel> {
        TODO("Not yet implemented")
    }
}