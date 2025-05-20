package com.gg.tetris.block.app.utils

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.Px
import androidx.annotation.StringRes
import com.gg.tetris.block.app.utils.corner.RoundMode
import com.gg.tetris.block.app.utils.corner.RoundOutlineProvider

fun View.getColor(@ColorRes id: Int) = context.getColorInt(id)
fun View.getDrawable(@DrawableRes id: Int) = context.getResourceDrawable(id)
fun View.getString(@StringRes id: Int): String = context.getResourceString(id)

fun View.makeRoundCorner(
    @Px radius: Int,
    mode: RoundMode,
) {
    outlineProvider = RoundOutlineProvider(
        mode = mode,
        radius = radius,
    )
    clipToOutline = mode != RoundMode.NONE
}

fun View.applyPadding(
    left: Int = this.paddingLeft,
    top: Int = this.paddingTop,
    right: Int = this.paddingRight,
    bottom: Int = this.paddingBottom
) {
    setPadding(left, top, right, bottom)
}