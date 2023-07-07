package com.depromeet.whatnow.data.model.response

import com.depromeet.whatnow.domain.model.AbleRegister
import com.google.gson.annotations.SerializedName

data class AbleRegisterResponse(
    @SerializedName("canRegister") val canRegister: Boolean,
)

internal fun AbleRegisterResponse.toDomain() = AbleRegister(
    canRegister = canRegister
)