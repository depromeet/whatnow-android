package com.depromeet.whatnow.ui.splashlogin

import android.util.Log
import com.depromeet.whatnow.base.BaseViewModel
import com.depromeet.whatnow.domain.model.Register
import com.depromeet.whatnow.domain.usecase.GetAuthOauthKakaoRegisterValidUseCase
import com.depromeet.whatnow.domain.usecase.GetOauthUserInfoUseCase
import com.depromeet.whatnow.domain.usecase.LoginUseCase
import com.depromeet.whatnow.domain.usecase.RegisterUseCase
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
) : BaseViewModel() {
    private val _uiState = MutableStateFlow<SplashUiState>(SplashUiState.AppLoading)
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

    private val _registerAgreePopup = MutableStateFlow(false)
    val registerAgreePopup: StateFlow<Boolean> = _registerAgreePopup.asStateFlow()

    private var job: Job? = null
    private var profileImage: String = ""
    private var isDefaultImage: Boolean = false
    private var nickName: String = ""
    private var id_token: String = ""

    init {
        launch {
            val job = launch { delay(MIN_SPLASH_TIME) }
            job.join()
            _uiState.value = SplashUiState.Onboarded
        }
    }

    fun login(accessToken: String, id_token: String) {
        this.id_token = id_token
        Log.d("yw","accessToken = $accessToken")
        Log.d("yw","id_token = $id_token")

        if (job != null) {
            return
        }
        job = launch {
            getAuthOauthKakaoRegisterValidUseCase(id_token)
                .onSuccess {
                    Log.d("yw","회원가입이 가능하니? ${it.canRegister}")
                    when {
                        // 회원가입 가능할때 -> 엑세스토큰을 통해 회원정보를 가져와 자동회원가입 후 로그인
                        it.canRegister -> {
                            getOauthUserInfoUseCase(accessToken)
                                .onSuccess { userInfo ->
                                    profileImage = userInfo.profileImage
                                    isDefaultImage = userInfo.isDefaultImage
                                    nickName = userInfo.nickname
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


    fun shownRegisterAgree() {
        if (!registerAgreePopup.value) {
            return
        }
        Log.d("yw", "id_token = $id_token")
        Log.d("yw", "profileImage = $profileImage")
        Log.d("yw", "isDefaultImage = $isDefaultImage")
        Log.d("yw", "nickName = $nickName")

        job = launch {
            registerUseCase(id_token, request = Register(
                profileImage = profileImage,
                isDefaultImage = isDefaultImage,
                nickName = nickName,
                fcmToken = "",
                appAlarm = false
            )).onSuccess {
                Log.d("yw", "성공")
                loginUseCase(id_token)
                    .onSuccess {
                        _registerAgreePopup.value = false
                        _uiState.value = SplashUiState.Signed
                    }
                    .onFailure { throwable -> handleException(throwable) }
            }.onFailure {
                Log.d("yw", "실패 $it")
            }
        }

        job?.invokeOnCompletion { job = null }
    }

    companion object {
        private const val MIN_SPLASH_TIME = 2000L
    }
}