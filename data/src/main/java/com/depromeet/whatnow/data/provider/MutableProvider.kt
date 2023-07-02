package com.depromeet.whatnow.data.provider

interface MutableProvider<T> : Provider<T> {
    override var value: T
}
