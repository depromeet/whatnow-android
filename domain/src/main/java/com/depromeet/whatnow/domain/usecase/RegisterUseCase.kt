package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.Register
import com.depromeet.whatnow.domain.model.TokenAndUser
import com.depromeet.whatnow.domain.repo.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(id_token: String, request: Register): Result<TokenAndUser> =
        runCatching {
            authRepository.postAuthKakaoRegister(id_token = id_token, request = request)
                .getOrThrow()
        }
}