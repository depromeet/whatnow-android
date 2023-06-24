package com.depromeet.whatnow.ui.highlight

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.depromeet.whatnow.base.BaseActivity
import com.depromeet.whatnow.ui.model.Highlight
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable

@AndroidEntryPoint
class HighlightActivity : BaseActivity() {

    private val viewModel: HighlightViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WhatNowTheme {
                HighlightScreen(
                    viewModel = viewModel,
                    onBack = ::finish
                )
            }
        }
    }

    companion object {
        fun startActivity(context: Context, highlights: List<Highlight>) {
            val intent = Intent(context, HighlightActivity::class.java)
                .putExtra(EXTRA_KEY_HIGHLIGHTS, highlights as Serializable)
            context.startActivity(intent)
        }

        const val EXTRA_KEY_HIGHLIGHTS = "EXTRA_KEY_HIGHLIGHTS"
    }
}
