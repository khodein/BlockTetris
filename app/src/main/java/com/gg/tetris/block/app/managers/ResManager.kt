package com.gg.tetris.block.app.managers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.gg.tetris.block.app.utils.getColorInt
import com.gg.tetris.block.app.utils.getDrawableToBitmap
import com.gg.tetris.block.app.utils.getResourceDrawable
import com.gg.tetris.block.app.utils.getResourceString
import com.gg.tetris.block.app.utils.getResourceStringArray
import com.gg.tetris.block.app.utils.getScreenHeight
import com.gg.tetris.block.app.utils.getScreenWidth

class ResManager(
    private val context: Context,
) {
    fun getString(@StringRes id: Int): String = context.getResourceString(id)
    fun getStringArray(@ArrayRes id: Int): Array<String> =
        context.getResourceStringArray(id)

    fun getColor(@ColorRes id: Int): Int = context.getColorInt(id)

    fun getDrawable(@DrawableRes id: Int): Drawable? = context.getResourceDrawable(id)

    fun getDrawableToBitmap(
        drawable: Drawable,
        width: Float? = null,
        height: Float? = null
    ): Bitmap {
        return drawable.getDrawableToBitmap(
            width = width,
            height = height,
        )
    }

    fun getScreenHeight(): Int {
        return context.resources.getScreenHeight()
    }

    fun getScreenWidth(): Int {
        return context.resources.getScreenWidth()
    }
}