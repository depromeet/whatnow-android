package com.depromeet.whatnow.ui.promiseActivate

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.depromeet.whatnow.base.BaseActivity
import com.depromeet.whatnow.domain.model.CoordinateVo
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PromiseActivateActivity : BaseActivity() {
    private val viewModel: PromiseActivateViewModel by viewModels()
    val permission_request = 99
    var permissions = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )

    //내 위치를 가져오는 코드
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient //자동으로 gps값을 받아온다.
    lateinit var locationCallback: LocationCallback //gps응답 값을 가져온다.
    //lateinit: 나중에 초기화 해주겠다는 의미

    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.promiseId.value = intent.getIntExtra("promiseId", -1)
        viewModel.getPromises()
        viewModel.getPromisesUsers()
        setContent {
            WhatNowTheme {
                val uiState by viewModel.uiState.collectAsState()
                val promise by viewModel.uiState.value.promise.collectAsState()

                if (promise != null) {
                    PromiseActivateScreen(
                        viewModel = viewModel,
                        onBack = ::finish,
                        setUpdateLocationListner = { setUpdateLocationListner() },
                    )
                }

            }
        }
    }

    fun isPermitted(): Boolean {
        for (perm in permissions) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    perm
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }//권한을 허락 받아야함

    @SuppressLint("MissingPermission")
    fun setUpdateLocationListner() {
        if (isPermitted()) {
            fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(this)
            val locationRequest = LocationRequest.create()
            locationRequest.run {
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY //높은 정확도
                interval = 1000 //1초에 한번씩 GPS 요청
            }

            locationCallback = object : LocationCallback() {
                override fun onLocationResult(p0: LocationResult) {
                    for ((i, location) in p0.locations.withIndex()) {
                        Log.d("location: ", "${location.latitude}, ${location.longitude}")
                        setLastLocation(location)
                        viewModel.putPromisesUsersLocation(
                            CoordinateVo(
                                latitude = location.latitude,
                                longitude = location.longitude
                            )
                        )
                        viewModel.getPromises()
                        viewModel.getPromisesUsers()
                    }
                }
            }
            //location 요청 함수 호출 (locationRequest, locationCallback)

            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.myLooper()
            )
        } else {
            ActivityCompat.requestPermissions(this, permissions, permission_request)
        }
    }

    fun setLastLocation(location: Location) {
        val myLocation = LatLng(location.latitude, location.longitude)
    }

    private fun navigateUp() = finish()

    companion object {
        fun startActivity(context: Context, promiseId: Int?) {
            val intent = Intent(context, PromiseActivateActivity::class.java)
            Log.d("ttt a", promiseId.toString())
            intent.putExtra("promiseId", promiseId)
            context.startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPromises()
        viewModel.getPromisesUsers()
    }
}
