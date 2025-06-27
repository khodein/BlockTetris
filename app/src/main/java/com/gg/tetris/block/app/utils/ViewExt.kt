package com.gg.tetris.block.app.utils

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
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

fun View.setOnCustomLongClickListener(
    duration: Long = 300L,
    listener: () -> Unit
) {
    setOnTouchListener(object : View.OnTouchListener {
        private val handler = Handler(Looper.getMainLooper())

        @SuppressLint("ClickableViewAccessibility")
        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            if (event?.action == MotionEvent.ACTION_DOWN) {
                handler.postDelayed({ listener.invoke() }, duration)
            } else if (event?.action == MotionEvent.ACTION_UP) {
                handler.removeCallbacksAndMessages(null)
            }
            return true
        }
    })
}