package com.example.takealoan.loanscreen.domain

import com.example.takealoan.loanscreen.data.model.ConditionsModel
import com.example.takealoan.loanscreen.domain.repository.LoanRepository
import io.reactivex.Single

class GetConditionUseCase (private val loanRepository: LoanRepository) {

    operator fun invoke(token: String): Single<ConditionsModel> {
        return loanRepository.getLoansConditions(token)
    }
}