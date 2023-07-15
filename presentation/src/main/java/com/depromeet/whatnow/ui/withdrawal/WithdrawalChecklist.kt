package com.depromeet.whatnow.ui.withdrawal

import androidx.annotation.StringRes
import com.depromeet.whatnow.ui.R

enum class WithdrawalChecklist(
    @StringRes val textRes: Int
) {
    Checklist1(textRes = R.string.withdrawal_checklist_01),
    Checklist2(textRes = R.string.withdrawal_checklist_02),
    Checklist3(textRes = R.string.withdrawal_checklist_03),
    Checklist4(textRes = R.string.withdrawal_checklist_04),
    Checklist5(textRes = R.string.withdrawal_checklist_05),
    Checklist6(textRes = R.string.withdrawal_checklist_06)
}
