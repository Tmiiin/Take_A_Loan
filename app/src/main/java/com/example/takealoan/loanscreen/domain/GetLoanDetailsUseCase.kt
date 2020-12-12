package com.example.takealoan.loanscreen.domain

import com.example.takealoan.loanscreen.data.model.LoanModel
import com.example.takealoan.loanscreen.data.model.PostLoanModel
import com.example.takealoan.loanscreen.domain.repository.LoanRepository
import io.reactivex.Single

class GetLoanDetailsUseCase (private val loanRepository: LoanRepository) {

    operator fun invoke(token: String, loanId: Int): Single<PostLoanModel> {
        return loanRepository.getLoanData(token, loanId)
    }
}