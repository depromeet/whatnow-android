package com.depromeet.whatnow.data.model.response

import com.google.gson.annotations.SerializedName

data class GetPromisesProgress(
    @SerializedName("group") val group: String,
    @SerializedName("progresses") val progresses: List<String>
)