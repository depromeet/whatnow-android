package com.depromeet.whatnow.data.model.response

import com.google.gson.annotations.SerializedName

data class PromisesImagesResponse(
    @SerializedName("presignedUrl") val presignedUrl: String,
    @SerializedName("key") val key: String
)