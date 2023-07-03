package com.depromeet.whatnow.data.api

class API {
    object AUTH {
        private const val BASE = "/v1/auth/"
    }

    object USER {
        private const val BASE = "/v1/users"
    }

    object PROMISE {
        private const val BASE = "/v1/promises/"
    }

    object LOCATION {
        private const val BASE = "/v1/"
        const val getLocation = BASE + "location"
    }
}