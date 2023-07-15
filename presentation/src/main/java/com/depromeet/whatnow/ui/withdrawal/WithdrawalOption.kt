package com.depromeet.whatnow.ui.withdrawal

import androidx.annotation.StringRes
import com.depromeet.whatnow.ui.R

enum class WithdrawalOption(
    @StringRes val textRes: Int
) {
    Option1(textRes = R.string.withdrawal_answer_01),
    Option2(textRes = R.string.withdrawal_answer_02),
    Option3(textRes = R.string.withdrawal_answer_03),
    Option4(textRes = R.string.withdrawal_answer_04),
    Option5(textRes = R.string.withdrawal_answer_05),
    Option6(textRes = R.string.withdrawal_answer_06)
}
