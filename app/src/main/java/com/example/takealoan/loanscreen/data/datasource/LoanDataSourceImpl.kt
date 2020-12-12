package com.example.takealoan.loanscreen.data.datasource

import android.util.Log
import com.example.takealoan.loanscreen.data.model.ConditionsModel
import com.example.takealoan.loanscreen.data.model.LoanModel
import com.example.takealoan.loanscreen.data.model.PostLoanModel
import com.example.takealoan.loanscreen.data.network.IRetrofit
import io.reactivex.Single

class LoanDataSourceImpl : LoanDataSource {
    companion object {
        val service = IRetrofit().getRetrofitService()
    }

    val TAG = "LoanDataSource"

    override fun createLoan(token: String, loan: LoanModel): Single<PostLoanModel> =
        try {
            Log.i(TAG, loan.toString())
            service.createLoan(token, loan)
        } catch (e: Exception) {
            throw Exception(e.message)
        }

    override fun getLoanData(token: String, loanId: Int): Single<PostLoanModel> =
        try {
            Log.i(TAG, "start getting loan data...")
            service.getLoanData(token, loanId)
        } catch (e: Exception) {
            throw Exception(e.message)
        }

    override fun getAllLoans(token: String): Single<List<PostLoanModel>> =
        try {
            Log.i(TAG, "start getting loans conditions...")
            service.getAllLoans(token)
        } catch (e: Exception) {
            throw Exception(e.message)
        }

    override fun getLoansConditions(token: String): Single<ConditionsModel> =
        try {
            Log.i(TAG, "start getting loans conditions...")
            service.getLoansConditions(token)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
}