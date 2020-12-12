package com.example.takealoan.loanscreen.presentation

import android.content.Context
import android.util.Log
import com.example.takealoan.Preferences
import com.example.takealoan.loanscreen.data.model.PostLoanModel
import com.example.takealoan.loanscreen.domain.GetLoanDetailsUseCase
import com.example.takealoan.loanscreen.presentation.interfaces.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class LoanDetailsPresenterImpl (val mContext: Context, private val detailsUseCase: GetLoanDetailsUseCase
): LoanDetailsPresenter {

    private var view: LoanDetailsView? = null
    var myDisposable: Disposable? = null
    val TAG: String = "LoanListPresenterImpl"

    override fun onDestroy() {
        this.myDisposable?.dispose()
        this.myDisposable = null
        view = null
    }

    override fun attachView(view: LoanDetailsView) {
        this.view = view
    }

    override fun getLoanDetails(id: Int){
        try {
            Log.i(TAG, "Token is: " + Preferences(mContext).getToken().toString())
            myDisposable = detailsUseCase(Preferences(mContext).getToken().toString(), id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ success -> handleCreateResponse(success) },
                    { error -> doOnError(error) })
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
    }

    private fun doOnError(e: Throwable) {
        Log.e(TAG, e.message.toString())
    }

    private fun handleCreateResponse(msg: PostLoanModel) {
        view?.setFieldsOfLoan(msg)
        Log.i(TAG, "response is: $msg")
    }

}