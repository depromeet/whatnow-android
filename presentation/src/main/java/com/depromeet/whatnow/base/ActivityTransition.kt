package com.depromeet.whatnow.base

import android.app.Activity

/**
 * ref) https://github.com/HeroTransitions/Hero
 */
enum class ActivityTransition(
    private val enterAnim: Int = 0,
    private val exitAnim: Int = 0,
    private val popEnterAnim: Int = 0,
    private val popExitAnim: Int = 0,
) {
    None,
    Cover(
//        enterAnim = R.anim.cover_enter,
//        exitAnim = R.anim.cover_exit,
//        popEnterAnim = R.anim.cover_pop_enter,
//        popExitAnim = R.anim.cover_pop_exit
    ),
    Push(
//        enterAnim = R.anim.push_enter,
//        exitAnim = R.anim.push_exit,
//        popEnterAnim = R.anim.push_pop_enter,
//        popExitAnim = R.anim.push_pop_exit
    );

    fun overridePendingTransition(activity: Activity) {
        activity.overridePendingTransition(enterAnim, exitAnim)
    }

    fun overridePendingPopTransition(activity: Activity) {
        activity.overridePendingTransition(popEnterAnim, popExitAnim)
    }
}