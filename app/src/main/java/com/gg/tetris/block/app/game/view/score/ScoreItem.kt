package com.gg.tetris.block.app.game.view.score

import android.graphics.drawable.Drawable
import com.gg.tetris.block.app.utils.corner.ViewCorner

class ScoreItem {

    interface View {
        fun bindState(state: State)
    }

    data class State(
        val id: String,
        val width: Int,
        val height: Int,
        val icon: Drawable?,
        val score: String,
        val corner: ViewCorner
    )
}