package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.repo.AuthRepository
import javax.inject.Inject

class LocalDataResetUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(): Result<Unit> = authRepository.dataReset()
}