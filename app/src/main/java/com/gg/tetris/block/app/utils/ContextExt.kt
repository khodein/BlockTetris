package com.gg.tetris.block.app.utils

import android.content.Context
import android.view.View
import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

fun Context.getColorInt(@ColorRes id: Int) = ContextCompat.getColor(this, id)
fun Context.getResourceDrawable(@DrawableRes id: Int) =
    ResourcesCompat.getDrawable(this.resources, id, this.theme)
fun Context.getResourceString(@StringRes id: Int): String = this.getString(id)
fun Context.getResourceStringArray(@ArrayRes id: Int): Array<String> = this.resources.getStringArray(id)