package com.depromeet.whatnow.data.model.response

import com.depromeet.whatnow.domain.model.CurrentProgress
import com.google.gson.annotations.SerializedName

data class UserProgressResponse(
    @SerializedName("user") val user: UsersResponse,
    @SerializedName("currentProgress") val currentProgress: CurrentProgressResponse,
    @SerializedName("beforeProgress") val beforeProgress: String?
)

data class CurrentProgressResponse(
    @SerializedName("user") val progressGroup: String,
    @SerializedName("user") val kr: String,
    @SerializedName("user") val code: String,
    @SerializedName("user") val image: String,
)

internal fun CurrentProgressResponse.toDomain() = CurrentProgress(
    progressGroup = progressGroup,
    kr = kr,
    code = code,
    image = image
)