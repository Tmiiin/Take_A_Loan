package com.example.takealoan.loanscreen.presentation.interfaces

import com.example.takealoan.loanscreen.data.model.ConditionsModel

interface LoanView {

    var conditionsModel: ConditionsModel

    fun setMaxAmount(maxAmount: Int)
    fun setConditionHint(percent: Float, period: Int)
    fun onDestroy()
}