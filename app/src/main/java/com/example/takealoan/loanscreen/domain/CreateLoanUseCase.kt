package com.example.takealoan.loanscreen.domain

import com.example.takealoan.loanscreen.data.model.LoanModel
import com.example.takealoan.loanscreen.data.model.PostLoanModel
import com.example.takealoan.loanscreen.domain.repository.LoanRepository
import io.reactivex.Single

class CreateLoanUseCase(private val loanRepository: LoanRepository) {

    operator fun invoke(token: String, loanModel: LoanModel): Single<PostLoanModel> {
        return loanRepository.createLoan(token, loanModel)
    }
}