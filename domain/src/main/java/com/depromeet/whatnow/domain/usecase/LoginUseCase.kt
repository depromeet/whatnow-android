package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.TokenAndUser
import com.depromeet.whatnow.domain.model.UsersFcmToken
import com.depromeet.whatnow.domain.repo.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
//    private val usersRepository: UsersRepository,
) {
    suspend operator fun invoke(
        providerToken: String,
    ): Result<TokenAndUser> = runCatching {
        val usersFcmToken = UsersFcmToken("")
        authRepository.postAuthOauthKakaoLogin(providerToken, usersFcmToken).getOrThrow()
    }
}