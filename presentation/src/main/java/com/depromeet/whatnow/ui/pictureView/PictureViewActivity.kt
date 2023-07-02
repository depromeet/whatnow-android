package com.depromeet.whatnow.ui.pictureView

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.depromeet.whatnow.base.BaseActivity
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PictureViewActivity : BaseActivity() {
    private val viewModel: PictureViewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WhatNowTheme {
                PictureViewScreen(
                    viewModel = viewModel,
                    onBack = ::finish,
                    imageUrl = "",
                    iconUrl = ""
                )
            }
        }
    }

    private fun navigateUp() = finish()

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, PictureViewActivity::class.java)
            context.startActivity(intent)
        }
    }
}
