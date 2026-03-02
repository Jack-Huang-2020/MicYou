package com.lanrhyme.micyou

data class BackgroundSettings(
    val imagePath: String = "",
    val brightness: Float = 0.5f,
    val blurRadius: Float = 0f,
    val cardOpacity: Float = 1f
) {
    val hasCustomBackground: Boolean
        get() = imagePath.isNotEmpty()
}

expect object BackgroundImagePicker {
    fun pickImage(onResult: (String?) -> Unit)
}
