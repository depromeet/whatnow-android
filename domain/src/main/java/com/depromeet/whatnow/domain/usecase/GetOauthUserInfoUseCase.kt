package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.OauthUserInfo
import com.depromeet.whatnow.domain.repo.AuthRepository
import javax.inject.Inject

class GetOauthUserInfoUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(access_token: String): Result<OauthUserInfo> =
        runCatching {
            authRepository.postAuthKakaoInfo(access_token = access_token).getOrThrow()
        }
}