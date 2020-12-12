package com.example.takealoan.loanscreen.domain

import com.example.takealoan.loanscreen.data.model.PostLoanModel
import com.example.takealoan.loanscreen.domain.repository.LoanRepository
import io.reactivex.Single

class GetLoanListUseCase(private val loanRepository: LoanRepository) {

    operator fun invoke(token: String): Single<List<PostLoanModel>> {
        return loanRepository.getAllLoans(token)
    }
}