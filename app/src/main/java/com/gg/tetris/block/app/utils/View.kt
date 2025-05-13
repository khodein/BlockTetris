package com.gg.tetris.block.app.utils

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

fun View.getColor(@ColorRes id: Int) = ContextCompat.getColor(context, id)
fun View.getDrawable(@DrawableRes id: Int) =
    ResourcesCompat.getDrawable(context.resources, id, context.theme)