package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.repo.UsersRepository
import javax.inject.Inject

class WithdrawUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {

    suspend operator fun invoke(): Result<Unit> = usersRepository.withdraw()
}
