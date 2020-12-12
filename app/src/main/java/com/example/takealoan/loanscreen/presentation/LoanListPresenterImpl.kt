package com.example.takealoan.loanscreen.presentation

import android.content.Context
import android.util.Log
import com.example.takealoan.Preferences
import com.example.takealoan.loanscreen.data.model.PostLoanModel
import com.example.takealoan.loanscreen.domain.GetLoanListUseCase
import com.example.takealoan.loanscreen.presentation.interfaces.LoanListPresenter
import com.example.takealoan.loanscreen.presentation.interfaces.LoanListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class LoanListPresenterImpl (val mContext: Context, private val loanListUseCase: GetLoanListUseCase
): LoanListPresenter {

    private var view: LoanListView? = null
    var myDisposable: Disposable? = null
    val TAG: String = "LoanListPresenterImpl"

    override fun onDestroy() {
        this.myDisposable?.dispose()
        this.myDisposable = null
        view = null
    }

    override fun attachView(view: LoanListView) {
        this.view = view
    }

    override fun getLoanList() {
        try {
            Log.i(TAG, "Requested query to get loan list...")
            myDisposable = loanListUseCase(Preferences(mContext).getToken().toString())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ success -> handleLoanListResponse(success) },
                    { error -> doOnError(error) })
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
    }

    private fun doOnError(e: Throwable) {
        Log.e(TAG, e.message.toString())
    }

    private fun handleLoanListResponse(list: List<PostLoanModel>){
        Log.e(TAG, "Get success loan list response")
        view?.setListOfDetails(list)
    }

}