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
import com.google.firebase.dynamiclinks.DynamicLink.SocialMetaTagParameters
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

    private fun sendInviteLink(inviteLink: Uri) {

        val inviterName = viewModel.name.value
        val inviteIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            setPackage("com.kakao.talk")
            putExtra(
                Intent.EXTRA_TEXT,
                "$inviterName 님과 약속을 잡았어요!\n\nWhatnow앱에서 수락하고 약속 당일 친구들과 실시간 위치를 공유하세요!\n\n$inviteLink \n\n ---------------------- \nWhatnow와 함께 약속 경험을 더욱 즐겁게!"
            )
        }

        try {
            startActivity(inviteIntent)
        } catch (e: ActivityNotFoundException) {
            Log.d("yw", "카카오톡이 안깔려있어서 못하는임임")
        }
    }

    private fun invite() {
        val inviteCode = viewModel.inviteCode.value
        val invitationList = "https://whatnow.page.link/invite?inviteCode=$inviteCode"

        val dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
            .setLink(Uri.parse(invitationList))
            .setDomainUriPrefix("https://whatnow.page.link")
            .setAndroidParameters(DynamicLink.AndroidParameters.Builder().build())
            .setSocialMetaTagParameters(
                SocialMetaTagParameters.Builder()
                    .setTitle("WHATNOW")
                    .setDescription("약속 장소로 가는 중 모든\n이벤트를 즐기는 share-play")
                    .setImageUrl(
                        Uri.parse(
                            "https://lh3.googleusercontent.com/fife/AKsag4NNVNy0UpPWd6-IRLquj8j3AWnXgZxC1tnFyd_PHbiNzRJizze6_1AWbQpxRahXn-Mo0Hfm6jd3aLm_ojleBPYYCCmKp2bZOlalsLG3AagNgpzfDrWwvPHuNwGrzRuNmDxwYSy-dOx12KqYAUFECreHRaD_xPro5yT_MEn_6vdheGkkwtMw1kUweWF7otQhfnOD3Xc2wAsJuhzmX1CnCUSzkfDVEjt8qfiblfzKr1TPQwts1ODnMHYt6mD9p-NtfbWkEzvoOYykzvXS128lXW8PTXZPGqs9if92E7hB5oLTCqGnzi4mX9RBJMUHqe9YwPfWFQkxv4zEBotx9SMGqmHg2ulAcFz3nrWf4RIYc472sjDz6QGvpMrzBOzo-4suDZSMPjd5Ei58-rGQ1bsHKlhtFG878rJRLjU-7BbraabPDgGoY-0tB_SfctzZZPImztPP6h81etLyrgobkzigbuRDbvNi24UBL8UDGAg5v9mPaBcqS_Tt-WnarShKuG1Vln94YbKuNYOOrdRRBgC5idCbFY-spZafmqFjMwJt2xxe-VMIgiD9SqAH1y0Jy4_cLVHcmPOsfOW46rPjVyvVNH0VzGI6FVmnfthJxG0Fxt8vmDJrrq0rQc0qGrLMJp2TZIrc3QeDGRjFzbkT0YeHDFuGIAmB-8MVFvR7mW9v9s5JiLcZ2oGUEUObfabiL0trDIsoAMJV5SnHxQeOSFD2kP3iHMjT9cVeLqQMg6pYYSzDXNdlwKMWBad47d3Q_nuGKe4ji1L5ogBpQUwS9IIbjgJ1LCecpWnObdnX6WwIwR0-UKmKIptJrK3nRMqcVuDIFmpKCVi5tJgxhyKIJuaNhZ2Gi5HqlevWZEgD8wdYULxTsOfK8A0BX5zraF10TLOJdW8SDeu7oCUxpuANw_ZEUG14wHGldAzmti8FmNZS9u20MFanFE4zyyT1meRougqY3SLbdtwx4dAaBS81ikY3fz7QHepF6rXNiWHy-fdYkyTyH3_1dt19Okh-gRU3xTGr0eY7z5fbksPChpFK7dlhdwVK5Hd_FNMo0hr3O-KhVBLk0CPnSKgFsNE2zYa2IpJwyoCsV000_TVkmMkds0MQLwvBPbSzxLcvH817EXourDeP2FzZY4DImGmo4q9HQzs_NAIZ618W4z9ReHjpy0iugXdHLRY5vHHCRl-EVNiVVWBOVy0UKn7f3PVJgHffmZKQNvCPUVOiiGE_xRdKUkhYORgB9aRq5_COh3C1J2fxSgc856u6hHoLUsVFQqNP_FOOQlfLgEr91w4qL_Xu3RmNFHRuIRnIgKkR-S-IkIhKqqwr4bHfS4ld2WiJwHCuTOAHgWii5z_0KcHTKAG3fR9TO7jMhAXYZGPzLwFelo_5nzGK3Lg1PprqpK4kzjQ9zfi6CFOkssMbxCiUrxsi-i5V6Wyc21vpwlppjRaC7xdChKRwgrCdsgH0K08JLMZi3SU2Wvzf2X3ffKEkNbw6yfGA0yqVGz3vlZAbnQzpuy3G7E1d-veVnCz9CM5xtMZYIw30HEOSiaT9=w2444-h1280"
                        )
                    )
                    .build()
            )
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
