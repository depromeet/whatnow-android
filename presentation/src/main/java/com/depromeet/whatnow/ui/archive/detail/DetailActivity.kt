package com.depromeet.whatnow.ui.archive.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.depromeet.whatnow.base.BaseActivity
import com.depromeet.whatnow.ui.archive.highlight.HighlightActivity
import com.depromeet.whatnow.ui.model.Highlight
import com.depromeet.whatnow.ui.model.Promise
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable

@AndroidEntryPoint
class DetailActivity : BaseActivity() {

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WhatNowTheme {
                DetailScreen(
                    viewModel = viewModel,
                    onBack = ::finish,
                    navigateToHighLight = ::startHighlightActivity
                )
            }
        }
    }

    private fun startHighlightActivity(highlights: List<Highlight>) =
        HighlightActivity.startActivity(this, highlights)

    companion object {
        fun startActivity(context: Context, promises: List<Promise>, selectedIndex: Int) {
            val intent = Intent(context, DetailActivity::class.java)
                .putExtra(EXTRA_KEY_PROMISES, promises as Serializable)
                .putExtra(EXTRA_KEY_INDEX, selectedIndex)
            context.startActivity(intent)
        }

        const val EXTRA_KEY_PROMISES = "EXTRA_KEY_PROMISES"
        const val EXTRA_KEY_INDEX = "EXTRA_KEY_INDEX"
    }
}
