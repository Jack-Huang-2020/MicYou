package com.lanrhyme.micyou

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

actual object BackgroundImagePicker {
    private var launcher: ActivityResultLauncher<Intent>? = null
    private var callback: ((String?) -> Unit)? = null
    
    fun registerLauncher(activity: MainActivity): ActivityResultLauncher<Intent> {
        val l = activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri = result.data?.data
                if (uri != null) {
                    callback?.invoke(uri.toString())
                } else {
                    callback?.invoke(null)
                }
            } else {
                callback?.invoke(null)
            }
        }
        launcher = l
        return l
    }
    
    actual fun pickImage(onResult: (String?) -> Unit) {
        callback = onResult
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
        }
        launcher?.launch(intent) ?: run {
            onResult(null)
        }
    }
}
