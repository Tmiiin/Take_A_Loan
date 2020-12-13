package com.example.takealoan.loanscreen.data.model

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.takealoan.R

enum class LoanStatus{
    APPROVED{
        override fun text() = "Заявка одобрена"
        override fun color(context: Context) = ContextCompat.getColor(context, R.color.teal_700)
    },
    REJECTED{
        override fun text() = "Заявка отклонена"
        override fun color(context: Context) = ContextCompat.getColor(context, R.color.purple_500)
    },
    REGISTERED{
        override fun text() = "Заявка зарегистрирована"
        override fun color(context: Context) = ContextCompat.getColor(context, R.color.teal_200)
    };
    abstract fun text(): String
    abstract fun color(context: Context): Int
}