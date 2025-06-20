package com.gg.tetris.block.app.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.core.graphics.createBitmap

fun Drawable.getDrawableToBitmap(
    @ColorInt colorInt: Int? = null,
    width: Float? = null,
    height: Float? = null,
): Bitmap {
    if (this is BitmapDrawable) {
        return this.bitmap
    }

    val bitmap = createBitmap(
        width?.toInt() ?: this.intrinsicWidth,
        height?.toInt() ?: this.intrinsicHeight
    )

    val canvas = Canvas(bitmap)
    this.setBounds(0, 0, canvas.width, canvas.height)

    colorInt?.let {
        this.colorFilter = PorterDuffColorFilter(it, PorterDuff.Mode.SRC_IN)
    }

    this.draw(canvas)

    return bitmap
}