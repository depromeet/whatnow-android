package com.depromeet.whatnow.ui.promiseAdd

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.depromeet.whatnow.base.BaseActivity
import com.depromeet.whatnow.ui.main.MainActivity
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PromiseAddActivity : BaseActivity() {
    private val viewModel: PromiseAddViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        var selectedTime by mutableStateOf(LocalTime.now())
        setContent {
            WhatNowTheme {
                val uiState by viewModel.uiState.collectAsState()
                uiState.let {
                    if (it == PromiseAddState.MakePromise) {
                        PromiseScreen(
                            viewModel = viewModel,
                            onBack = ::finish,
                        )
                    } else {
                        PromiseDetailScreen(
                            viewModel = viewModel,
                            goHomeClick = { startMainActivity() },
                            inviteClick = { invite() }
                        )
                    }
                }

            }
        }
    }

    fun sendInviteLink(inviteLink: Uri) {

        val inviterName = "현영우"
        val inviteIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            setPackage("com.kakao.talk")
            putExtra(Intent.EXTRA_TEXT, "$inviterName 님이 당신을 약속에 초대하였습니다. \n [초대 링크] : $inviteLink")
        }

        try {
            startActivity(inviteIntent)
        } catch (e: ActivityNotFoundException) {
            Log.d("yw", "카카오톡이 안깔려있어서 못하는임임")
        }
    }

    fun invite() {
        val inviteCode = "test"
        val invitationList = "https://whatnow.page.link/invite?inviteCode=$inviteCode"

        val dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
            .setLink(Uri.parse(invitationList))
            .setDomainUriPrefix("https://whatnow.page.link")
            .setAndroidParameters(DynamicLink.AndroidParameters.Builder().build())
            .buildShortDynamicLink()

        dynamicLink.addOnSuccessListener { task ->
            val inviteLink = task.shortLink!!
            sendInviteLink(inviteLink)
        }
    }

    private fun startMainActivity() {
        MainActivity.startActivity(this)
        finish()
    }

    private fun navigateUp() = finish()

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, PromiseAddActivity::class.java)
            context.startActivity(intent)
        }
    }


}
