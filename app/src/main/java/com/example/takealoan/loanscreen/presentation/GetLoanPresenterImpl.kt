package com.example.takealoan.loanscreen.presentation

import android.content.Context
import android.util.Log
import com.example.takealoan.Preferences
import com.example.takealoan.loanscreen.data.model.LoanModel
import com.example.takealoan.loanscreen.data.model.PostLoanModel
import com.example.takealoan.loanscreen.domain.CreateLoanUseCase
import com.example.takealoan.loanscreen.presentation.interfaces.BaseView
import com.example.takealoan.loanscreen.presentation.interfaces.GetLoanPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class GetLoanPresenterImpl(val mContext: Context, private val loanUseCase: CreateLoanUseCase): GetLoanPresenter {

    private var view: BaseView? = null
    var myDisposable: Disposable? = null
    val TAG: String = "GetLoanPresenterImpl"

    override fun onDestroy() {
        this.myDisposable?.dispose()
        this.myDisposable = null
        this.view = null
    }

    override fun attachView(view: BaseView) {
        this.view = view
    }

    override fun onCreateButtonClick(loanModel: LoanModel) {
        try {
            Log.i(TAG, "Token is: " + Preferences(mContext).getToken().toString())
            myDisposable = loanUseCase(Preferences(mContext).getToken().toString(), loanModel)
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
        Log.i(TAG, "response is: $msg")
    }
}