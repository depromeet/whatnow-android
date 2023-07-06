package com.depromeet.whatnow.data.model.response

import com.google.gson.annotations.SerializedName

data class GetPromisesInteractionsResponse(
    @SerializedName("interactionDtoList") val interactionDtoList: List<InteractionDtoListResponse>

)