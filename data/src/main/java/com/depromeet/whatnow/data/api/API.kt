package com.depromeet.whatnow.data.api

class API {
    object AUTH {
        private const val BASE = "/v1/auth/"
        const val postAuthTokenRefresh = BASE + "token/refresh"
        const val postAuthKakaoRegister = BASE + "oauth/kakao/register"
        const val postAuthKakaoInfo = BASE + "oauth/kakao/info"
        const val postAuthLogout = BASE + "logout"
        const val getAuthOauthKakaoRegisterValid = BASE + "oauth/kakao/register/valid"
        const val deleteAutoMe = BASE + "me"
        const val postAuthOauthKakaoLogin = BASE + "oauth/kakao/login"

    }

    object USER {
        private const val BASE = "/v1/users/"
        const val patchUsersProfile = BASE + "profile"
        const val patchUsersFcmToken = BASE + "fcm-token"
        const val patchUsersAlarm = BASE + "alarm"
        const val getUsers = BASE + "{userId}"
        const val getUsersMe = BASE + "me"
        const val postUsersMeImageSuccess = BASE + "me/images/success/{imageKey}"
        const val getUsersMeImages = BASE + "me/images"

    }

    object PROMISE {
        private const val BASE = "/v1/promises/"
        const val putPromisesLocation = BASE + "{promise-id}/location"
        const val putPromisesEndTimes = BASE + "{promise-id}/end-times"
        const val postPromises = BASE + ""
        const val getPromisesActive = BASE + "{promise-id}/active"
        const val getPromisesMonthlyUsers = BASE + "users"
        const val getPromisesUsersStatus = BASE + "users/status/{status}"
        const val getPromisesUsersSeparated = BASE + "users/separated"
        const val deletePromises = BASE + "{promise-id}"
        const val putPromisesUsersStatus = BASE + "{promise-id}/users/{user-id}/status/{status}"
        const val postPromisesUsers = BASE + "{promise-id}/users"
        const val getPromisesUsers = BASE + "{promiseId}/users"
        const val patchPromisesProgress = BASE + "{promiseId}/progresses/{progressCode}"
        const val getPromisesUsersProgress = BASE + "{promiseId}/users/{userId}"
        const val getPromisesProgress = BASE + "progresses"
        const val postPromisesImagesSuccess = BASE + "{promiseId}/images/success/{imageKey}"
        const val getPromisesImages = BASE + "{promiseId}/images"
        const val postPromisesInteractionsTarget =
            BASE + "{promiseId}/interactions/{interactionType}/target/{targetUserId}"
        const val getPromisesInteractions = BASE + "{promiseId}/interactions"
        const val getPromisesInteractionsDetail =
            BASE + "{promiseId}/interactions/{interactionType}"

    }

    object LOCATION {
        private const val BASE = "/v1/"
        const val getLocation = BASE + "location"
    }
}