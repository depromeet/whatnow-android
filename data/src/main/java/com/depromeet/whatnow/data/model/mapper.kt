package com.depromeet.whatnow.data.model

import com.depromeet.whatnow.data.model.response.PromisesMonthlyUsers
import com.depromeet.whatnow.data.model.response.UsersResponse
import com.depromeet.whatnow.domain.model.PromisesMonthlyUser
import com.depromeet.whatnow.domain.model.PromisesMonthlyUserList
import com.depromeet.whatnow.domain.model.Users

fun List<PromisesMonthlyUsers>.toDomain(): PromisesMonthlyUserList {
    return PromisesMonthlyUserList(
        map {
            PromisesMonthlyUser(
                title = it.title,
                address = it.address,
                endTime = it.endTime,
                users = it.users.toDomain()
            )
        })
}

fun UsersResponse.toDomain(): Users {
    return Users(
        id = this.id,
        profileImg = this.profileImg,
        nickname = this.nickname,
        isDefaultImg = this.isDefaultImg
    )
}