package com.depromeet.whatnow.ui.utils

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

object BitmapUtil {

    fun Bitmap.toFile(context: Context, name: String): File {
        val dir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString() + name
        val file = File(dir)
        if (!file.exists()) file.mkdirs()

        val fileName = "$name.jpg"
        val tempFile = File(dir, fileName)

        var out: OutputStream? = null
        try {
            if (tempFile.createNewFile()) {
                out = FileOutputStream(tempFile)
                compress(Bitmap.CompressFormat.PNG, 70, out)
            }
        } finally {
            out?.close()
        }

        return tempFile
    }
}
