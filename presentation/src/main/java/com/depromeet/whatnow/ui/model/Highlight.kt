package com.depromeet.whatnow.ui.model

import java.io.Serializable

data class Highlight(
    val type: HighlightType,
    val text: String,
    val participants: List<User>,
    val location: String
) : Serializable

fun DUMMY_HIGHLIGHT(
    type: HighlightType = HighlightType.Meet,
    text: String = "나태현, 김동호 붙어라!",
    participants: List<User> = List(2) { DUMMY_USER() },
    location: String = "서울시 종로구"
) = Highlight(type, text, participants, location)
