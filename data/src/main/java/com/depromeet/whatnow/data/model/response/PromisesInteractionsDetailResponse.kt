package com.depromeet.whatnow.data.model.response

import com.google.gson.annotations.SerializedName

data class PromisesInteractionsDetailResponse(
    @SerializedName("interactions") val interactionsDetail: List<InteractionsDetailResponse>
)
