package com.example.takealoan.loanscreen.ui

enum class LoanStatus{
    APPROVED{
        override fun text() = "Заявка одобрена"
    },
    REJECTED{
        override fun text() = "Заявка отклонена"
    },
    REGISTERED{
        override fun text() = "Ваша заявка была зарегистрирована"
    };
    abstract fun text(): String
}