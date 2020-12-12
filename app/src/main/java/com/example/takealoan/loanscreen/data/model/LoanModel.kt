package com.example.takealoan.loanscreen.data.model

data class LoanModel(
    val amount: Int,
    val firstName: String = "",
    val percent: Float = 2f,
    val period: Int,
    val maxAmount: Int,
    val lastName: String = "",
    val phoneNumber: String = ""
)