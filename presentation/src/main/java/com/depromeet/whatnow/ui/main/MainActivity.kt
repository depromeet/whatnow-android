package com.depromeet.whatnow.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.depromeet.whatnow.ui.alarm.AlarmActivity
import com.depromeet.whatnow.ui.archive.ArchiveActivity
import com.depromeet.whatnow.ui.promiseAdd.PromiseAddActivity
import com.depromeet.whatnow.ui.setting.SettingActivity
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatNowTheme {
                MainScreen(
                    viewModel = viewModel,
                    startHistoryActivity = ::startHistoryActivity,
                    startAlarmActivity = ::startAlarmActivity,
                    startSettingActivity = ::startSettingActivity,
                    startPromiseAddActivity = ::startPromiseAddActivity
                )
            }
        }
    }

    private fun startHistoryActivity() {
        val intent = Intent(this, ArchiveActivity::class.java)
        startActivity(intent)
    }

    private fun startAlarmActivity() {
        val intent = Intent(this, AlarmActivity::class.java)
        startActivity(intent)
    }

    private fun startSettingActivity() {
        val intent = Intent(this, SettingActivity::class.java)
        startActivity(intent)
    }

    private fun startPromiseAddActivity() {
        val intent = Intent(this, PromiseAddActivity::class.java)
        startActivity(intent)
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}