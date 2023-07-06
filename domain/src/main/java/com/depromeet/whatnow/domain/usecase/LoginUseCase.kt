package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.LoginResult
import com.depromeet.whatnow.domain.repo.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
//    private val usersRepository: UsersRepository,
) {
    suspend operator fun invoke(
        providerToken: String,
    ): Result<LoginResult> = runCatching {
        authRepository.postAuthOauthKakaoLogin(providerToken).getOrThrow()

        LoginResult.Signed
    }
}