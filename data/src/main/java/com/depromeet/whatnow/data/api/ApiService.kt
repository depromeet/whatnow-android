package com.depromeet.whatnow.data.api

import com.depromeet.whatnow.data.model.BaseResponse
import com.depromeet.whatnow.data.model.request.*
import com.depromeet.whatnow.data.model.response.*

import com.depromeet.whatnow.domain.model.CoordinateVo
import retrofit2.http.*

interface ApiService {

    // refreshToken
    @POST(API.AUTH.postAuthTokenRefresh)
    suspend fun postAuthTokenRefresh(
        @Query("token") token: String,
    ): BaseResponse<LocationResponse>

    // 회원가입
    @POST(API.AUTH.postAuthKakaoRegister)
    suspend fun postAuthKakaoRegister(
        @Query("id_token") id_token: String,
        @Body request: RegisterRequest,
    ): BaseResponse<TokenAndUserResponse>

    // oauth user 정보 가져오기
    @POST(API.AUTH.postAuthKakaoInfo)
    suspend fun postAuthKakaoInfo(
        @Query("access_token") access_token: String,
    ): BaseResponse<LocationResponse>

    // 로그아웃
    @POST(API.AUTH.postAuthLogout)
    suspend fun postAuthLogout(): Unit

    // 회원가입 가능 여부 확인
    @GET(API.AUTH.getAuthOauthKakaoRegisterValid)
    suspend fun getAuthOauthKakaoRegisterValid(
        @Query("id_token") id_token: String,
    ): BaseResponse<LocationResponse>

    // 회원 탈퇴
    @DELETE(API.AUTH.deleteAutoMe)
    suspend fun deleteAutoMe(): Unit

    // 로그인
    @POST(API.AUTH.postAuthOauthKakaoLogin)
    suspend fun postAuthOauthKakaoLogin(
        @Query("id_token") id_token: String,
    ): BaseResponse<TokenAndUserResponse>

    // 사용자 프로필 수정
    @PATCH(API.USER.patchUsersProfile)
    suspend fun patchUsersProfile(
        @Body body: UsersProfileRequest,
    ): BaseResponse<UsersProfileResponse>

    // fcm 토큰 수정
    @PATCH(API.USER.patchUsersFcmToken)
    suspend fun patchUsersFcmToken(
        @Body body: UsersFcmTokenRequest,
    ): BaseResponse<UsersProfileResponse>

    // 내 알림 허용 정보 토글링
    @PATCH(API.USER.patchUsersAlarm)
    suspend fun patchUsersAlarm(): BaseResponse<UsersProfileResponse>

    // 다른 사람 유저 정보 가져오기
    @GET(API.USER.getUsers)
    suspend fun getUsers(
        @Path("userId") userId: String,
    ): BaseResponse<UsersResponse>

    // 내 유저 디테일 정보 가져오기
    @GET(API.USER.getUsersMe)
    suspend fun getUsersMe(
    ): BaseResponse<UsersProfileResponse>

    // 약속 장소 수정
    @PUT(API.PROMISE.putPromisesLocation)
    suspend fun putPromisesLocation(
        @Path("promise-id") promise_id: String,
    ): BaseResponse<PromisesLocationResponse>

    // 약속 시간 수정
    @PUT(API.PROMISE.putPromisesEndTimes)
    suspend fun putPromisesEndTimes(
        @Path("promise-id") promise_id: String, @Body body: EndTimeRequest
    ): BaseResponse<PromisesResponse>

    // 약속 생성
    @POST(API.PROMISE.postPromises)
    suspend fun postPromises(
        @Body body: PromiseRequest,
    ): BaseResponse<PromisesResponse>

    // 현재 약속 활성화 여부 조회
    @POST(API.PROMISE.postPromises)
    suspend fun getPromisesActive(
        @Path("promise-id") promise_id: Int
    ): BaseResponse<Boolean>


    // 월 단위 약속 조회
    @GET(API.PROMISE.getPromisesMonthlyUsers)
    suspend fun getPromisesMonthlyUsers(
        @Query("year-month") year_month: String,
    ): BaseResponse<PromisesMonthlyUsersListResponse>

    // 약속 모음집 상세
    @GET(API.PROMISE.getPromisesUsersStatus)
    suspend fun getPromisesUsersStatus(
        @Path("status") status: String,
    ): BaseResponse<GetPromisesUsersStatusListResponse>

    // 나의 약속 전부 조회
    @GET(API.PROMISE.getPromisesUsersSeparated)
    suspend fun getPromisesUsersSeparated(
    ): BaseResponse<PromisesUsersSeparatedListResponse>

    // 약속 취소
    @DELETE(API.PROMISE.deletePromises)
    suspend fun deletePromises(
        @Path("promise-id") promise_id: String,
    ): Unit

    // 약소 장소 검색
    @GET(API.LOCATION.getLocation)
    suspend fun getLocation(
        @Query("location") locationRequest: LocationRequest,
    ): BaseResponse<LocationResponse>

    // 유저가 약속을 취소
    @PUT(API.PROMISE.putPromisesUsersStatus)
    suspend fun putPromisesUsersStatus(
        @Path("promise-id") promise_id: String,
        @Path("user-id") user_id: Int,
        @Path("status") status: String,
    ): BaseResponse<PromisesUsersStatusListResponse>

    // 약속 유저 생성
    @POST(API.PROMISE.postPromisesUsers)
    suspend fun postPromisesUsers(
        @Path("promise-id") promise_id: String, @Path("user-id") user_id: Int, body: CoordinateVo
    ): BaseResponse<PromisesUsersStatusResponse>


    // 약속 유저 조회
    @GET(API.PROMISE.getPromisesUsers)
    suspend fun getPromisesUsers(
        @Path("promise-id") promise_id: String
    ): BaseResponse<PromisesUsersStatusListResponse>

    // 해당 약속 유저 진행 상태 변경
    @PATCH(API.PROMISE.patchPromisesProgress)
    suspend fun patchPromisesProgress(
        @Path("progressCode") progressCode: String, @Path("promiseId") promiseId: Int,

        ): BaseResponse<PromisesProgressResponse>

    // 해당 약속 유저 진행 상태 확인
    @GET(API.PROMISE.getPromisesUsersProgress)
    suspend fun getPromisesUsersProgress(
        @Path("promiseId") promiseId: Int, @Path("userId") userId: Int,
    ): BaseResponse<PromisesProgressResponse>

    // 약속 진행 단계
    @GET(API.PROMISE.getPromisesProgress)
    suspend fun getPromisesProgress(): BaseResponse<GetPromisesProgressListResponse>

    // 유저 프로필 이미지 업로드
    @POST(API.USER.postUsersMeImageSuccess)
    suspend fun postUsersMeImageSuccess(
        @Path("imageKey") imageKey: String,
    ): Unit

    // 약속 관련 이미지 업로드
    @POST(API.PROMISE.postPromisesImagesSuccess)
    suspend fun postPromisesImagesSuccess(
        @Path("promiseId") promiseId: Int,
        @Path("imageKey") imageKey: String,
        @Query("imageCommentType") imageCommentType: String,

        ): Unit

    // 유저 프로필 이미지 가져오기
    @GET(API.USER.getUsersMeImages)
    suspend fun getUsersMeImages(
        @Query("fileExtension") fileExtension: String,
    ): BaseResponse<PromisesImagesResponse>

    // 약속 관련 이미지 가져오기
    @GET(API.PROMISE.getPromisesImages)
    suspend fun getPromisesImages(
        @Path("promiseId") promiseId: Int,
        @Query("fileExtension") fileExtension: String,
    ): BaseResponse<PromisesImagesResponse>

    // 인터렉션 발송
    @POST(API.PROMISE.postPromisesInteractionsTarget)
    suspend fun postPromisesInteractionsTarget(
        @Path("promiseId") promiseId: Int,
        @Path("interactionType") interactionType: String,
        @Path("targetUserId") targetUserId: Int,
    ): Unit

    // 자신의 인터렉션 가져오기
    @GET(API.PROMISE.getPromisesInteractions)
    suspend fun getPromisesInteractions(
        @Path("promiseId") promiseId: Int,
    ): BaseResponse<GetPromisesInteractionsResponse>

    // 자신의 인터렉션 상세정보 가져오기
    @GET(API.PROMISE.getPromisesInteractionsDetail)
    suspend fun getPromisesInteractionsDetail(
        @Path("promiseId") promiseId: Int,
        @Path("interactionType") interactionType: String,
    ): BaseResponse<PromisesInteractionsDetailResponse>

}