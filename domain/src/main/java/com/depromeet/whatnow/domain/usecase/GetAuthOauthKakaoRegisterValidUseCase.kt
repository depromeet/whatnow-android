package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.AbleRegister
import com.depromeet.whatnow.domain.repo.AuthRepository
import javax.inject.Inject

class GetAuthOauthKakaoRegisterValidUseCase @Inject constructor(
    val authRepository: AuthRepository,
) {
    suspend operator fun invoke(id_token: String): Result<AbleRegister> =
        runCatching {
            authRepository.getAuthOauthKakaoRegisterValid(id_token = id_token).getOrThrow()
        }
}