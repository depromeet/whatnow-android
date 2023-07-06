package com.depromeet.whatnow.data.model.response

import com.google.gson.annotations.SerializedName

data class PromisesUsersSeparatedListResponse(
    @SerializedName("additionalProp1") val additionalProp1: List<PromisesUsersSeparatedResponse>,
    @SerializedName("additionalProp2") val additionalProp2: List<PromisesUsersSeparatedResponse>,
//    val additionalProp3: PromisesUsersSeparated,
)