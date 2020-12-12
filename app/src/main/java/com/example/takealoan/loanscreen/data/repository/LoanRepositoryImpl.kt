package com.example.takealoan.loanscreen.data.repository

import com.example.takealoan.loanscreen.data.datasource.LoanDataSource
import com.example.takealoan.loanscreen.data.model.ConditionsModel
import com.example.takealoan.loanscreen.data.model.LoanModel
import com.example.takealoan.loanscreen.data.model.PostLoanModel
import com.example.takealoan.loanscreen.domain.repository.LoanRepository
import io.reactivex.Single

class LoanRepositoryImpl(private val dataSource: LoanDataSource) : LoanRepository {
    override fun createLoan(token: String, loan: LoanModel): Single<PostLoanModel> =
        dataSource.createLoan(token, loan)


    override fun getLoanData(token: String, loanId: Int): Single<PostLoanModel> =
        dataSource.getLoanData(token, loanId)

    override fun getAllLoans(token: String): Single<List<PostLoanModel>> =
        dataSource.getAllLoans(token)

    override fun getLoansConditions(token: String): Single<ConditionsModel> =
        dataSource.getLoansConditions(token)

}