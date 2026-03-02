package com.lanrhyme.micyou

import androidx.compose.ui.window.WindowScope
import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

actual object BackgroundImagePicker {
    private var windowScope: WindowScope? = null
    
    fun init(scope: WindowScope) {
        windowScope = scope
    }
    
    actual fun pickImage(onResult: (String?) -> Unit) {
        val fileChooser = JFileChooser().apply {
            dialogTitle = "Select Background Image"
            fileFilter = FileNameExtensionFilter(
                "Image Files (*.jpg, *.jpeg, *.png, *.gif, *.bmp, *.webp)",
                "jpg", "jpeg", "png", "gif", "bmp", "webp"
            )
            fileSelectionMode = JFileChooser.FILES_ONLY
            isMultiSelectionEnabled = false
        }
        
        val result = fileChooser.showOpenDialog(null)
        
        if (result == JFileChooser.APPROVE_OPTION) {
            val selectedFile = fileChooser.selectedFile
            onResult(selectedFile.absolutePath)
        } else {
            onResult(null)
        }
    }
}
