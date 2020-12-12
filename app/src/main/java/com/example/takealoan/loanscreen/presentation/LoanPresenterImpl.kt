package com.example.takealoan.loanscreen.presentation

import android.content.Context
import android.util.Log
import com.example.takealoan.Preferences
import com.example.takealoan.loanscreen.data.model.ConditionsModel
import com.example.takealoan.loanscreen.data.model.LoanModel
import com.example.takealoan.loanscreen.data.model.PostLoanModel
import com.example.takealoan.loanscreen.domain.CreateLoanUseCase
import com.example.takealoan.loanscreen.domain.GetConditionUseCase
import com.example.takealoan.loanscreen.presentation.interfaces.LoanPresenter
import com.example.takealoan.loanscreen.presentation.interfaces.LoanView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.create_loan.*
import java.lang.Exception

class LoanPresenterImpl(val mContext: Context,
                        private val getConditionsUseCase: GetConditionUseCase): LoanPresenter {

    private var view: LoanView? = null
    var myDisposable: Disposable? = null
    val TAG: String = "LoanPresenterImpl"

    override fun attachView(view: LoanView) {
        this.view = view
    }

    private fun doOnError(e: Throwable) {
        Log.e(TAG, e.message.toString())
    }

    override fun getLoanCondition(){
        try {
            Log.i(TAG, "Token is: " + Preferences(mContext).getToken().toString())
            myDisposable = getConditionsUseCase(Preferences(mContext).getToken().toString())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ success -> handleConditionResponse(success) },
                    { error -> doOnError(error) })
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
    }

    private fun handleConditionResponse(conditionsModel: ConditionsModel){
        view?.setMaxAmount(conditionsModel.maxAmount)
        view?.conditionsModel = conditionsModel
        view?.setConditionHint(conditionsModel.percent, conditionsModel.period)
    }

    override fun onDestroy(){
        this.myDisposable?.dispose()
        this.myDisposable = null
        this.view = null
    }
}