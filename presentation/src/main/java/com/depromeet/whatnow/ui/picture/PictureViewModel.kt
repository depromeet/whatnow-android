package com.depromeet.whatnow.ui.picture

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.camera.core.AspectRatio
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.FLASH_MODE_OFF
import androidx.camera.core.ImageCapture.FLASH_MODE_ON
import androidx.camera.core.ImageCaptureException
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import com.depromeet.whatnow.base.BaseViewModel
import com.depromeet.whatnow.ui.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PictureViewModel @Inject constructor() : BaseViewModel() {


    private val _isRefresh = MutableStateFlow(false)
    val isRefresh = _isRefresh.asStateFlow()

    private val _uiState = MutableStateFlow(PictureState())
    val uiState: StateFlow<PictureState> = _uiState.asStateFlow()

    private val _pictureUploadText = MutableStateFlow(listOf(PictureUploadText("", 0, false)))
    val pictureUploadText: StateFlow<List<PictureUploadText>> = _pictureUploadText.asStateFlow()

    private val _pictureUploadImg = MutableStateFlow(R.drawable.on_the_way_icon)
    val pictureUploadImg: StateFlow<Int> = _pictureUploadImg.asStateFlow()

    val imageAnalysis = ImageAnalysis.Builder()
        .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
        .build()

    val imageCapture = ImageCapture.Builder()
        .setTargetAspectRatio(AspectRatio.RATIO_16_9)
        .build()

    init {
        _pictureUploadText.update {
            listOf(
                PictureUploadText("가는중", R.drawable.on_the_way_icon, true),
                PictureUploadText("헐레벌떡", R.drawable.panting_icon, false),
                PictureUploadText("남겨놔", R.drawable.leave_icon, false),
                PictureUploadText("조그만 기다려", R.drawable.wait_icon, false)
            )
        }
    }

    fun onClickedPictureUploadText(index: Int) {
        /**
         * 왜 update를 시켜야만 하는가ㅏ?
         * 전체 값을 옵저버하고 있어서? 전체 값이 변화해야한다??? 근데 전체 값이 변화고 나서 하나의 값이 변화는데,,
         * _pictureUploadImg의 경우는???
         *
         * **/
        _pictureUploadText.update {
            listOf(
                PictureUploadText("가는중", R.drawable.on_the_way_icon, false),
                PictureUploadText("헐레벌떡", R.drawable.panting_icon, false),
                PictureUploadText("남겨놔", R.drawable.leave_icon, false),
                PictureUploadText("조그만 기다려", R.drawable.wait_icon, false)
            )
        }
        viewModelScope.launch {
//                _pictureUploadText.value.forEach { it.enabled = false }
            _pictureUploadText.value[index].enabled = true
            _pictureUploadImg.value = _pictureUploadText.value[index].img
        }
    }

    fun refresh() {
        _isRefresh.value = true
    }

    fun onRefresh() {
        _isRefresh.value = false
    }

    private val _bitmap: MutableStateFlow<Bitmap?> = MutableStateFlow(null)
    val bitmap: StateFlow<Bitmap?> = _bitmap.asStateFlow()


    fun uriToBitmap(imageUri: Uri?, context: Context) {
        _bitmap.value = imageUri!!.parseBitmap(context)
    }

    fun bitmapInit() {
        _bitmap.value = null
    }

    fun captureAndSaveImage(
        context: Context
    ) {

        //for file name
        val name = SimpleDateFormat(
            "yyyy-MM-dd-HH-mm-ss-SSS",
            Locale.ENGLISH
        ).format(System.currentTimeMillis())


        // for storing
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > 28) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/My-Camera-App-Images")
            }
        }

        // for capture output
        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                context.contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            .build()


        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    Toast.makeText(
                        context,
                        "저장중",
                        Toast.LENGTH_LONG
                    ).show()

                    _bitmap.value = outputFileResults.savedUri!!.parseBitmap(context = context)
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.d("ttt", exception.message.toString())
                    Toast.makeText(
                        context,
                        "some error occurred ${exception.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        )

    }

    fun onClickedFlash() {
        when (imageCapture.flashMode) {
            FLASH_MODE_ON -> {
                imageCapture.flashMode = FLASH_MODE_OFF

            }

            FLASH_MODE_OFF -> {
                imageCapture.flashMode = FLASH_MODE_ON

            }

            else -> {}
        }
    }
}