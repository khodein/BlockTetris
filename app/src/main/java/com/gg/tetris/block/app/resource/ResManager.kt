package com.gg.tetris.block.app.resource

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.createBitmap

class ResManager(
    private val context: Context,
) {
    fun getString(@StringRes stringResId: Int): String = context.getString(stringResId)
    fun getStringArray(@ArrayRes id: Int): Array<String> =
        context.resources.getStringArray(id)

    fun getColor(@ColorRes colorResInt: Int): Int = context.getColor(colorResInt)

    fun getDrawable(@DrawableRes drawableResInt: Int): Drawable? =
        ResourcesCompat.getDrawable(context.resources, drawableResInt, context.theme)

    fun getDrawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }

        val bitmap = createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight)

        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }
}