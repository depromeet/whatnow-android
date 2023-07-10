package com.depromeet.whatnow.data.model.response

import com.google.gson.annotations.SerializedName

data class GetPromisesInteractionsResponse(
    @SerializedName("userProgressResponse") val userProgressResponse: UserProgressResponse,
    @SerializedName("interactionDtoList") val interactionDtoList: List<InteractionDtoListResponse>

)