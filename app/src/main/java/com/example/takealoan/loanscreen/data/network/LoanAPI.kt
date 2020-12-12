package com.example.takealoan.loanscreen.data.network

import com.example.takealoan.loanscreen.data.model.ConditionsModel
import com.example.takealoan.loanscreen.data.model.LoanModel
import com.example.takealoan.loanscreen.data.model.PostLoanModel
import io.reactivex.Single
import retrofit2.http.*

interface LoanAPI {

    @Headers("Content-Type: application/json")
    @POST("loans")
    fun createLoan(
        @Header("Authorization") token: String,
        @Body loanModel: LoanModel
    ): Single<PostLoanModel>

    @Headers("Content-Type: application/json")
    @GET("loans/{id}")
    fun getLoanData(
        @Header("Authorization") token: String,
        @Path("id") loanId: Int
    ): Single<PostLoanModel>

    @Headers("Content-Type: application/json")
    @GET("loans/all")
    fun getAllLoans(@Header("Authorization") token: String): Single<List<PostLoanModel>>

    @Headers("Content-Type: application/json")
    @GET("loans/conditions")
    fun getLoansConditions(@Header("Authorization") token: String): Single<ConditionsModel>

}