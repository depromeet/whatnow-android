package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.JwtToken
import com.depromeet.whatnow.domain.repo.AuthRepository
import javax.inject.Inject

class GetJwtTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Result<JwtToken> = authRepository.getJwtToken()
}