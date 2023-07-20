package com.depromeet.whatnow.ui.splashlogin

import android.util.Log
import com.depromeet.whatnow.base.BaseViewModel
import com.depromeet.whatnow.domain.model.Register
import com.depromeet.whatnow.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
//    private val deleteAutoMeUseCase: DeleteAutoMeUseCase,
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val getOauthUserInfoUseCase: GetOauthUserInfoUseCase,
    private val getAuthOauthKakaoRegisterValidUseCase: GetAuthOauthKakaoRegisterValidUseCase,
    private val getIdTokenUseCase: GetIdTokenUseCase,
) : BaseViewModel() {
    private val _uiState = MutableStateFlow<SplashUiState>(SplashUiState.AppLoading)
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

    private val _registerAgreePopup = MutableStateFlow(false)
    val registerAgreePopup: StateFlow<Boolean> = _registerAgreePopup.asStateFlow()

    private var job: Job? = null
    private var profileImage: String = ""
    private var isDefaultImage: Boolean = false
    private var nickname: String = ""
    private var idToken: String = ""

    init {
        launch {
            val job = launch { delay(MIN_SPLASH_TIME) }
            job.join()
            _uiState.value = SplashUiState.Onboarded

            getIdTokenUseCase().onSuccess {
                Log.d("yw", "Local 에 저장되어있는 아이디 토큰 = $it")
                if (it != null) {
                    loginUseCase(it).onSuccess {
                        _uiState.value = SplashUiState.Signed
                        Log.d("yw", "자동 로그인 성공")
                    }
                        .onFailure { Log.d("yw", "자동 로그인 실패 $it") }
                }
            }.onFailure { Log.d("yw", "id Token 가져오기 실패 $it") }
        }
    }

    fun login(accessToken: String, id_token: String) {
        idToken = id_token

        if (job != null) {
            return
        }
        job = launch {
            getAuthOauthKakaoRegisterValidUseCase(id_token)
                .onSuccess {
                    when {
                        // 회원가입 가능할때 -> 엑세스토큰을 통해 회원정보를 가져와 자동회원가입 후 로그인
                        it.canRegister -> {
                            getOauthUserInfoUseCase(accessToken)
                                .onSuccess { userInfo ->
                                    profileImage = userInfo.profileImage
                                    isDefaultImage = userInfo.isDefaultImage
                                    nickname = userInfo.nickname
                                    _registerAgreePopup.value = true
                                }
                        }
                        // 회원가입 불가능 -> 바로 로그인
                        else -> {
                            loginUseCase(id_token)
                                .onSuccess { _uiState.value = SplashUiState.Signed }
                                .onFailure { throwable -> handleException(throwable) }
                        }
                    }
                }
        }
        job?.invokeOnCompletion { job = null }

    }

    // 회원가입 약관동의 팝업
    fun shownRegisterAgree() {
        if (!registerAgreePopup.value) {
            return
        }
        job = launch {
            registerUseCase(
                idToken, request = Register(
                    profileImage = profileImage,
                    isDefaultImage = isDefaultImage,
                    nickname = nickname,
                    fcmToken = "",
                    appAlarm = false
                )
            ).onSuccess {
                loginUseCase(idToken)
                    .onSuccess {
                        _registerAgreePopup.value = false
                        _uiState.value = SplashUiState.Signed
                    }
                    .onFailure { throwable -> handleException(throwable) }
            }.onFailure { throwable ->
                handleException(throwable)
            }
        }
        job?.invokeOnCompletion { job = null }
    }

    companion object {
        private const val MIN_SPLASH_TIME = 2000L
    }
}