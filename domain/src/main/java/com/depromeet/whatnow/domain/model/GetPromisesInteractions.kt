package com.depromeet.whatnow.domain.model


data class GetPromisesInteractions(
    val userProgress: UserProgress,
    val interactionDtoList: List<InteractionDtoList>

)