package com.depromeet.whatnow.data.model.response

import com.google.gson.annotations.SerializedName

data class PromisesUsersSeparatedResponse(
    @SerializedName("additionalProp1") val additionalProp1: PromisesUsersSeparated,
    @SerializedName("additionalProp2") val additionalProp2: PromisesUsersSeparated,
//    val additionalProp3: PromisesUsersSeparated,
)