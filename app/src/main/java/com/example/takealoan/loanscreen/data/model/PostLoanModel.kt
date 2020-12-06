package com.example.takealoan.loanscreen.data.model

import java.time.LocalDateTime

data class PostLoanModel(
    val amount: Int,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val percent: Float,
    val period: Int,
    val date: LocalDateTime,
    val state: String,
    val id: Int
)