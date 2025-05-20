package com.gg.tetris.block.app.game.view.block_figure

import android.graphics.Bitmap
import com.gg.tetris.block.app.game.states.BlockState

class GameBlockFigureItem {

    interface View {
        fun bindState(state: State)
    }

    data class State(
        val width: Int,
        val height: Int,
        val blocks: List<FigureBlockState>,
    )

    data class FigureBlockState(
        override val bitmap: Bitmap?,
        override val left: Float,
        override val top: Float
    ) : BlockState
}