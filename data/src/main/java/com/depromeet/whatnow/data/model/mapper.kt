package com.depromeet.whatnow.data.model

import com.depromeet.whatnow.data.model.response.GetPromisesProgressResponse
import com.depromeet.whatnow.data.model.response.GetPromisesUsersStatusResponse
import com.depromeet.whatnow.data.model.response.InteractionDtoListResponse
import com.depromeet.whatnow.data.model.response.InteractionsResponse
import com.depromeet.whatnow.data.model.response.PromiseUsersResponse
import com.depromeet.whatnow.data.model.response.PromisesImagesResponse
import com.depromeet.whatnow.data.model.response.PromisesMonthlyUsersResponse
import com.depromeet.whatnow.data.model.response.PromisesProgressResponse
import com.depromeet.whatnow.data.model.response.PromisesUsersSeparatedListResponse
import com.depromeet.whatnow.data.model.response.PromisesUsersSeparatedResponse
import com.depromeet.whatnow.data.model.response.PromisesUsersStatusResponse
import com.depromeet.whatnow.data.model.response.TimeOverLocationsResponse
import com.depromeet.whatnow.data.model.response.UsersResponse
import com.depromeet.whatnow.domain.model.GetPromisesInteractions
import com.depromeet.whatnow.domain.model.GetPromisesProgress
import com.depromeet.whatnow.domain.model.GetPromisesProgressList
import com.depromeet.whatnow.domain.model.GetPromisesUsersStatus
import com.depromeet.whatnow.domain.model.GetPromisesUsersStatusList
import com.depromeet.whatnow.domain.model.InteractionDtoList
import com.depromeet.whatnow.domain.model.Interactions
import com.depromeet.whatnow.domain.model.PromiseUsers
import com.depromeet.whatnow.domain.model.PromisesImages
import com.depromeet.whatnow.domain.model.PromisesInteractionsDetail
import com.depromeet.whatnow.domain.model.PromisesMonthlyUser
import com.depromeet.whatnow.domain.model.PromisesMonthlyUserList
import com.depromeet.whatnow.domain.model.PromisesProgress
import com.depromeet.whatnow.domain.model.PromisesUsersSeparated
import com.depromeet.whatnow.domain.model.PromisesUsersSeparatedList
import com.depromeet.whatnow.domain.model.PromisesUsersStatus
import com.depromeet.whatnow.domain.model.PromisesUsersStatusList
import com.depromeet.whatnow.domain.model.TimeOverLocations
import com.depromeet.whatnow.domain.model.Users

@JvmName("PromisesMonthlyUsersResponse")
fun List<PromisesMonthlyUsersResponse>.toDomain(): PromisesMonthlyUserList {
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

@JvmName("UsersResponse")
fun UsersResponse.toDomain(): Users {
    return Users(
        id = this.id,
        profileImg = this.profileImg,
        nickname = this.nickname,
        isDefaultImg = this.isDefaultImg
    )
}

@JvmName("GetPromisesUsersStatusResponse")
fun List<GetPromisesUsersStatusResponse>.toDomain(): GetPromisesUsersStatusList {
    return GetPromisesUsersStatusList(
        map {
            GetPromisesUsersStatus(
                title = it.title,
                date = it.date,
                promiseUsers = it.promiseUsers.toDomain(),
                promiseImageUrls = it.promiseImageUrls,
                timeOverLocations = it.timeOverLocations.toDomain()
            )
        })
}

@JvmName("PromiseUsersResponse")
fun List<PromiseUsersResponse>.toDomain(): List<PromiseUsers> {
    return map {
        PromiseUsers(
            profileImg = it.profileImg,
            nickname = it.nickname,
            isDefaultImg = it.isDefaultImg,
            promiseUserType = it.promiseUserType
        )
    }
}

@JvmName("TimeOverLocationsResponse")
fun List<TimeOverLocationsResponse>.toDomain(): List<TimeOverLocations> {
    return map {
        TimeOverLocations(
            userId = it.userId,
            coordinateVo = it.coordinateVo
        )
    }
}

@JvmName("PromisesProgressResponse")
fun PromisesProgressResponse.toDomain(): PromisesProgress {
    return PromisesProgress(
        User = this.User.toDomain(),
        currentProgress = this.currentProgress,
        beforeProgress = this.beforeProgress
    )
}

@JvmName("GetPromisesProgressListResponse")
fun List<GetPromisesProgressResponse>.toDomain(): GetPromisesProgressList {
    return GetPromisesProgressList(
        map {
            GetPromisesProgress(
                group = it.group,
                progresses = it.progresses

            )
        }
    )
}

@JvmName("PromisesImagesResponse")
fun PromisesImagesResponse.toDomain(): PromisesImages {
    return PromisesImages(
        presignedUrl = this.presignedUrl,
        key = this.key
    )
}

@JvmName("InteractionDtoListResponse")
fun List<InteractionDtoListResponse>.toDomain(): GetPromisesInteractions {
    return GetPromisesInteractions(
        map {
            InteractionDtoList(
                promiseId = it.promiseId,
                userId = it.userId,
                interactionType = it.interactionType,
                count = it.count
            )
        }
    )
}

@JvmName("InteractionsResponse")
fun List<InteractionsResponse>.toDomain(): PromisesInteractionsDetail {
    return PromisesInteractionsDetail(
        map {
            Interactions(
                senderUser = it.senderUser.toDomain(),
                count = it.count,
                interactionType = it.interactionType
            )
        }
    )
}

@JvmName("PromisesUsersStatusResponse")
fun List<PromisesUsersStatusResponse>.toDomain(): PromisesUsersStatusList {
    return PromisesUsersStatusList(
        map {
            PromisesUsersStatus(
                promiseId = it.promiseId,
                mainUserId = it.mainUserId,
                userLocation = it.userLocation,
                promiseUserType = it.promiseUserType
            )
        }
    )
}

@JvmName("PromisesUsersSeparatedListResponse")
fun PromisesUsersSeparatedListResponse.toDomain(): PromisesUsersSeparatedList {
    return PromisesUsersSeparatedList(
        additionalProp1 = this.additionalProp1.toDomain(),
        additionalProp2 = this.additionalProp2.toDomain()
    )
}

@JvmName("PromisesUsersSeparatedResponse")
fun List<PromisesUsersSeparatedResponse>?.toDomain(): List<PromisesUsersSeparated>? {
    return this?.map {
        PromisesUsersSeparated(
            title = it.title,
            address = it.address,
            endTime = it.endTime,
            users = it.users.toDomain()
        )
    }
}