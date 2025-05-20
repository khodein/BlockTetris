package com.gg.tetris.block.app.game.view.refresh

import android.graphics.Bitmap
import androidx.annotation.ColorInt

class GameRefreshItem {

    interface View {
        fun bindState(state: State)
    }

    data class State(
        val width: Int,
        val height: Int,
        val count: Int,
        @ColorInt val backgroundColor: Int,
        val refreshBitmap: Bitmap?,
        val onClickRefresh: (() -> Unit),
    )
}